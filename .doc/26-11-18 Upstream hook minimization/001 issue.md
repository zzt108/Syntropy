# 001 — Upstream hook minimization

## Original request (2026-11-18, translated from Hungarian)

> I try to sync the fork. Now `zzt_develop` is the default branch. We made only doc changes. Why is there a merge conflict with the upstream?

Finding: `zzt_develop` is 49 commits ahead and 1236 behind `yairm210/Unciv:master` (merge-base `18eaa8995`, 2026-03-02). A test merge (`git merge-tree`) shows exactly one conflict: `core/src/com/unciv/ui/screens/worldscreen/NotificationsScroll.kt`. It is caused by the March "hide on-screen notifications with right click" change (`71061dce2`, `e3700ddf5`), which edits five places in that upstream file, two of them lines upstream later refactored (`a4323a68c` hash helper, `6cfa47096` notification order).

Follow-up request:

> Could these changes be moved to one (or a minimal number of) place/hook? The goal: the smallest possible surface for future merge conflicts.

Decision: write the spec, then do the refactor together with the upstream merge.
