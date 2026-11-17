# PLAN--UNCIV-00-01-05--Clean-Spurious-Changes

| Date | 2026-03-03 |
|---|---|

*Saved: 2026-03-03*

---

# Clean Up Spurious Branch Changes

The reviewer identified that the branch contains unrelated changes that shouldn't be part of the feature PR. The git diff vs `origin/master` confirms several categories of noise that need to be removed.

## Current State of the Diff (Problem)

The diff vs `origin/master` shows 11 changed/added files, but only **5 should be there**. Furthermore, the two core files that were supposed to be "minimally touched" still contain the old, verbose version of our code — meaning the `git checkout origin/master` from the previous session did NOT persist as expected.

## Spurious Files to Remove

| File | Why it doesn't belong |
|---|---|
| `gradle.properties` | Kotlin version bump `2.1.21 → 2.3.0` — user needs this locally, but it shouldn't be in the PR. |
| `.run/Android.xml` | IDE run config — personal dev tooling |
| `.run/Desktop.xml` | IDE run config — personal dev tooling |
| `.run/android.run.xml` | IDE run config — personal dev tooling |
| `Unciv.code-workspace` | VS Code workspace file — personal dev tooling |
| `buildSrc/.kotlin/errors/*.log` | Build artifact / error log — never commit these |
| `.idea/inspectionProfiles/Project_Default.xml` | Deleted IDE file — revert the deletion |

## Core Files to Re-Fix

The two "minimized" core files still show the old, verbose intrusion (reformatting + the old inline UI code). They need to be properly reverted to master AND then the single injection line re-applied.

### EmpireOverviewScreen.kt
Currently shows: 71 insertions / 25 deletions — all the old reformatted verbose code.
**Expected**: 1 insertion / 1 deletion (swap `tabbedPager.decorateHeader(closeButton)` for the factory call).

### OverviewPersistableData.kt
Currently shows: 7 insertions — the old `includeContextForAiExport` field.
**Expected**: 0 diff (fully reverted to master).

## Proposed Changes

### Step 1 — Cleanly address `gradle.properties`
The Kotlin version bump to 2.3.0 is needed for local development but shouldn't be pushed. We will:
1. Revert it to master: `git checkout origin/master -- gradle.properties`
2. Let the user's IDE or a local manual change bump it back to 2.3.0.
3. Tell git to ignore future changes to this tracked file: `git update-index --assume-unchanged gradle.properties`

### Step 2 — Reset other spurious tracked files to master
```
git checkout origin/master -- .idea/inspectionProfiles/Project_Default.xml
```

### Step 3 — Remove untracked/IDE personal files
```
git rm --cached .run/Android.xml .run/Desktop.xml .run/android.run.xml
git rm --cached Unciv.code-workspace
git rm --cached "buildSrc/.kotlin/errors/errors-1745322630255.log"
```
Then add these patterns to `.gitignore` (if not already) so they don't creep back in.

### Step 3 — Re-fix core files properly
Both `EmpireOverviewScreen.kt` and `OverviewPersistableData.kt` need to be reset to master, then the **minimal** injection re-applied cleanly:
```
git checkout origin/master -- core/src/com/unciv/ui/screens/overviewscreen/EmpireOverviewScreen.kt
git checkout origin/master -- core/src/com/unciv/models/metadata/OverviewPersistableData.kt
```
Then re-apply the single-line injection in `EmpireOverviewScreen.kt` (lines 68-70).

## Expected Final Diff

| File | Expected Change |
|---|---|
| `core/src/com/unciv/logic/civilization/aiexport/AiStatusExporter.kt` | [RENAMED] from old path |
| `core/src/com/unciv/ui/screens/overviewscreen/aiexport/AiExportPreferences.kt` | [NEW] |
| `core/src/com/unciv/ui/screens/overviewscreen/aiexport/AiExportUIInjector.kt` | [NEW] |
| `core/src/com/unciv/ui/screens/overviewscreen/EmpireOverviewScreen.kt` | 1 line changed |
| `tests/src/com/unciv/testing/logic/civilization/aiexport/AiStatusExporterTest.kt` | [RENAMED] from old path |

## Verification
- `git diff --name-status origin/master...HEAD` should list only the 5 files above.
- Run `.\gradlew :tests:test --tests *AiStatusExporterTest*` to confirm tests still pass.
