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

- [ ] **T8 — Merge `master` into `zzt_develop`.** *(merge/push only on the user's request)*
  `git switch zzt_develop`, `git merge origin/master`.
  **Verification:** merge completes without conflicts; `./gradlew tests:test` green; `./gradlew desktop:dist` builds; `git diff origin/master -- core/src/com/unciv/ui/screens/worldscreen/NotificationsScroll.kt` still shows exactly the 2 hook lines; quick T4 re-check in the game; GitHub shows `zzt_develop` 0 commits behind upstream.
