# Upstream hooks & merge checklist

How SYNTROPY code touches upstream Unciv files, and how to pull upstream `master` into `zzt_develop`.
Background: D-43 in `.doc/26-11-17 Game design brainstorming/brainstorming-base.md`. Origin of the rules: `.doc/26-11-18 Upstream hook minimization/` (spec, review).

## Hook conventions

Our code lives in our own packages (`com.unciv.syntropy.*`; older code in `…/aiexport/`). An upstream file gets a **hook point** only where there is no other way in.

1. **Add lines, never rewrite upstream lines.** If an expression must change, wrap it in a single call (e.g. `update(NotificationHider.visible(list), …)`) instead of editing its internals.
2. **One line per hook point**, calling into our own package. All state and logic live there.
3. **Fully qualified names, no new imports.** Upstream import blocks change often and conflict easily. Kotlin extension functions cannot be called by FQN; call a small object of ours that applies them (e.g. `NotificationHooks`).
4. **Mark every hook** with a trailing `// SYNTROPY-HOOK` comment. Only a line that *ends* with the marker counts; mentions in KDoc or prose do not.
5. Prefer hooking at **call sites** (data passed in) over the inside of upstream classes.
6. **Register every hook** in `tests/src/com/unciv/testing/syntropy/SyntropyHookInventoryTest.kt` (`hooks` list: id, file relative to `core/src`, distinctive part of the call). The test fails if a registered hook is missing or a marked line is not registered.

Current hooks (see the test for the authoritative list):

| Id | File | Purpose |
|---|---|---|
| H0 | `ui/screens/overviewscreen/EmpireOverviewScreen.kt` | AI status export buttons in the overview header |
| H1 | `ui/screens/worldscreen/WorldScreen.kt` | Filter hidden notifications before they reach the scroll |
| H2 | `ui/screens/worldscreen/NotificationsScroll.kt` | Right click on a notification hides it |
| H3 | `ui/screens/worldscreen/NotificationsScroll.kt` | Right click on a category header restores it |

Expect upstream API changes to break **our code behind a hook**, not the hook line itself (e.g. 2026-11-18: `Civilization` → `CivView`). Keep our code depending on as few upstream types as possible.

## Upstream merge checklist

Remotes: `origin` = `zzt108/Syntropy` (our fork); upstream = `https://github.com/yairm210/Unciv.git`. `origin/master` is a pure mirror of upstream; never commit to it.

1. **Sync the mirror.** On GitHub, switch the branch selector to **`master`** and use "Sync fork" (a fast-forward). **Never** use "Sync fork" → "Discard commits" while `zzt_develop` is selected: it deletes our commits from `zzt_develop`.
   Then locally: `git fetch origin`.
   Check: `git rev-list --left-right --count zzt_develop...origin/master` (ahead / behind).
2. **Textual dry run** (no files or branches touched):
   `git merge-tree --write-tree --name-only zzt_develop origin/master`
   Exit 0 and no file list = no conflicts. Conflicts: resolve by keeping upstream's version and re-applying our one hook line.
3. **Compile dry run.** `merge-tree` does not compile; a clean textual merge can still break the build. Use a throw-away worktree:
   - `git worktree add --detach ../Unciv-merge-dryrun zzt_develop`
   - `git -C ../Unciv-merge-dryrun merge --no-edit origin/master`
   - in `../Unciv-merge-dryrun`: `./gradlew tests:test --rerun desktop:dist`
   - fix any compile errors on a feature branch first, then repeat;
   - clean up: `git worktree remove --force ../Unciv-merge-dryrun`
4. **Merge.** `git switch zzt_develop`, `git merge origin/master`.
5. **Verify.** `./gradlew tests:test --rerun desktop:dist`.
   `--rerun` matters: if a merge removed only a hook marker, the compiled classes are unchanged and Gradle would skip `SyntropyHookInventoryTest`.
6. **Manual check** of the hooked features in `./gradlew desktop:run`:
   - right click hides a notification;
   - right click on its header restores it;
   - "Copy Status" in the Empire Overview copies the own civ's report.
7. **Push.** `git push origin zzt_develop`. GitHub should then show `zzt_develop` *ahead of* and **0 behind** upstream.
