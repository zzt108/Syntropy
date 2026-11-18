# todo — Upstream hook minimization

Branch: `feature/upstream-hook-minimization` (from `zzt_develop` @ `ff66d754d`).
Upstream base of the touched files = merge-base `18eaa8995` (the upstream version `zzt_develop` currently builds on).

- [x] **T1 — `NotificationHider` with unit tests (no upstream file touched).**
  Create `core/src/com/unciv/syntropy/ui/NotificationHider.kt` per spec §3 (identity-based `hidden` set, `onChanged`, `visible`, `hide`, `restore`, `restoreAll`, KDoc incl. "render thread only" and the identity note).
  Create `tests/src/com/unciv/testing/syntropy/NotificationHiderTest.kt` with the six cases of spec §5; reset state in `@Before` (`restoreAll()`, `onChanged` = counter).
  **Verification:** `./gradlew tests:test --tests "com.unciv.testing.syntropy.NotificationHiderTest"` green; `./gradlew tests:test` green.

- [x] **T2 — Switch the feature over to the hooks (H1–H3) and remove the old edits.**
  Restore `NotificationsScroll.kt` to its upstream base (`git show 18eaa8995:<path>`), then add H2 (after `ListItem`'s `onClick { … }`) and H3 (after `header?.onClick { … }`), both FQN, each with `// SYNTROPY-HOOK`.
  In `WorldScreen.kt` wrap the first argument of `notificationsScroll.update(...)` with `com.unciv.syntropy.ui.NotificationHider.visible(...)` + marker (H1).
  Delete `core/src/com/unciv/ui/screens/worldscreen/NotificationsScrollExtensions.kt`.
  **Verification:** `./gradlew tests:test` green; `git diff 18eaa8995 -- core/src/com/unciv/ui/screens/worldscreen/NotificationsScroll.kt` shows exactly 2 added lines and no removed lines; `git diff 18eaa8995 -- core/src/com/unciv/ui/screens/worldscreen/WorldScreen.kt` shows exactly one changed line; `git grep NotificationsScrollExtensions` finds nothing.

- [x] **T3 — Mark the existing AI-export hook (H0) and check the hook inventory.**
  Append `// SYNTROPY-HOOK` to the `createAiExportHeader` line in `EmpireOverviewScreen.kt`.
  Done as: the two-line hook (`val headerTable = …` + `decorateHeader(headerTable)`) folded into one wrapped argument (convention 1); diff vs. upstream base is now 1 changed line instead of +2/−1.
  **Verification:** `git grep -n SYNTROPY-HOOK -- core` lists exactly 4 lines (H0–H3); `./gradlew desktop:dist` builds.

- [x] **T4 — Manual check in the desktop game.**
  `./gradlew desktop:run`, load or start a game with notifications.
  **Verification:** right-click on a notification hides it; right-click on its category header restores it; left-click on a header still collapses/expands; Empire Overview → Notifications still lists the hidden ones; the AI-export header buttons still work.

- [x] **T5 — Prove the merge is conflict-free (dry run).**
  Fetch upstream `master` into `refs/tmp/upstream-master` (refresh), then `git merge-tree --write-tree --name-only HEAD refs/tmp/upstream-master`.
  **Verification:** no conflicted files listed. If any remain, stop and report (do not improvise beyond the spec).
  Result (2026-11-18): feature commit `c5849258b` vs. upstream `f00881660` → `merge-tree` exit 0, no conflicts; all 4 `SYNTROPY-HOOK` lines present in the merged tree; merged `NotificationsScroll.kt` differs from upstream by +2/−0, `WorldScreen.kt` by 1 line.

- [x] **T6 — Land the feature on `zzt_develop`.** *(commit/merge only on the user's request)*
  Commit the work on the feature branch, merge it into `zzt_develop`.
  **Verification:** `zzt_develop` contains the commits; `./gradlew tests:test` green on `zzt_develop`.

- [x] **T7 — Sync `origin/master` with upstream.** *(user action on GitHub)*
  On GitHub, switch the branch selector to `master` and use "Sync fork" there (fast-forward; `origin/master` has no own commits). Do **not** use "Discard commits" on `zzt_develop`. Then `git fetch origin`.
  **Verification:** `git rev-list --left-right --count origin/master...refs/tmp/upstream-master` → `0 0` (or upstream only ahead by commits newer than the fetch).

- [x] **T8 — Merge `master` into `zzt_develop`.** *(merge/push only on the user's request)*
  `git switch zzt_develop`, `git merge origin/master`.
  Result (2026-11-18): merge `80c6008fc` had no textual conflicts, but `core` did not compile: upstream turned `Civilization.isSpectator()` into a property and introduced the view layer (#15280), so `EmpireOverviewScreen.viewingPlayer` is now a `CivView` (its `unwrap()` is protected). Fixed in a follow-up commit without touching the hook line: `createAiExportHeader` takes `CivView` and exports `GUI.getSelectedPlayer()` (the civ the overview is built from); `AiStatusExporter` uses `isSpectator`; `AiExportWiringTest` signature guard updated. After the fix: `tests:test` 1044 tests green, `desktop:dist` builds. Lesson for review: the T5 `merge-tree` dry run only detects textual conflicts; a dry run must also compile the merged tree.
  Manual T4 re-check in the game after the fix: all OK, including "Copy Status" (2026-11-18).
  Open: push `zzt_develop` so GitHub shows 0 behind (on the user's request).
  **Verification:** merge completes without conflicts; `./gradlew tests:test` green; `./gradlew desktop:dist` builds; `git diff origin/master -- core/src/com/unciv/ui/screens/worldscreen/NotificationsScroll.kt` still shows exactly the 2 hook lines; quick T4 re-check in the game; GitHub shows `zzt_develop` 0 commits behind upstream.

## Follow-up: uniform hook guard (spec §7)

Branch: `feature/upstream-hook-minimization`, fast-forwarded to `zzt_develop` @ `e9bbc6175`.

- [x] **T9 — `SyntropyHookInventoryTest` (source-based guard for all hooks).**
  Create `tests/src/com/unciv/testing/syntropy/SyntropyHookInventoryTest.kt` per spec §7: registry with H0–H3, marker rule "trimmed line ends with `// SYNTROPY-HOOK`", source-root resolution by walking up from `user.dir`, Test 1 (every registered hook present exactly once), Test 2 (no unregistered hook line under `core/src`), Test 3 (unique ids, count matches).
  **Verification:** `./gradlew tests:test --tests "com.unciv.testing.syntropy.SyntropyHookInventoryTest"` green (3 tests); negative checks done temporarily and reverted: (a) marker removed from H2 → Test 1 fails naming H2 and its file; (b) a marked line added in an unregistered place → Test 2 fails naming file:line; `git status` clean of those edits afterwards.
  Result (2026-11-18): 3/3 green. (a) H2 marker removed → `everyRegisteredHookIsPresentExactlyOnce` fails naming H2, file and expected call; `registryIsConsistent` 4 vs 3. (b) `// probe  // SYNTROPY-HOOK` appended to `GUI.kt` → `noUnregisteredHookLines` fails naming `com/unciv/GUI.kt:72`; count 4 vs 5. Both edits reverted with `git restore`.
  Finding: the first run of (a) passed because Gradle considered `tests:test` up-to-date (a comment-only change leaves the compiled classes identical, and the source files are not declared task inputs). The negative checks were rerun with `--rerun`. A dropped hook *call* changes bytecode and does rerun the tests; only a marker-only edit slips through incremental builds. For review: declare `core/src` as an input of `tests:test` (an upstream build-file hook) or accept the gap.

- [x] **T10 — Remove the bytecode guard.**
  Delete `tests/src/com/unciv/testing/logic/civilization/aiexport/AiExportWiringTest.kt`.
  **Verification:** `./gradlew tests:test` green (total = previous 1044 − 3 removed + 3 new); `git grep -n AiExportWiringTest -- core tests` finds nothing; `git grep -n -E "SYNTROPY-HOOK\s*$" -- core` lists exactly the 4 registered lines.
