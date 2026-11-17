# CLAUDE.md — SYNTROPY

Claude Code adaptation of the workflow in `.clinerules` (which stays as-is for Cline).
Memory lives in Markdown files under `.doc/`, never in chat history: always re-read the files at the start of a phase.

## Project

- Tech stack: **Kotlin + libGDX**, Gradle (Kotlin DSL), JDK 21. The game is a fork of **Unciv**: `zzt108/Unciv`, local checkout `C:\Git\Unciv` (D-41 in `.doc/26-11-17 Game design brainstorming/brainstorming-base.md`). Root build: `C:\Git\Unciv\settings.gradle.kts`.
- Repos: **Flux** (this repo) = design docs (`.doc/`) only; **Unciv fork** = all source code. Unciv's upstream docs: `C:\Git\Unciv\docs\` (modding: `docs/Modders/`, building: `docs/Developers/Building-Locally.md`).
- Gradle modules: `core` (game logic + UI; own SYNTROPY code goes in its own packages), `tests` (JUnit 4), `desktop` (Windows launcher + packaging, `desktop:dist` / `packr*`), `android` (only included when an Android SDK is found), `server` (multiplayer server, unused for now).
- Content: SYNTROPY ruleset/mod as JSON (Unciv mod format); Civ V content is trimmed by ruleset only during development (D-42).
- Fork rule (D-43): never upstreamed; upstream files get only small, clearly marked hook points; the Fate engine core stays Gdx-independent and unit-testable.
- Build: `./gradlew desktop:dist` (jar in `desktop/build/libs/`) · Run: `./gradlew desktop:run` · Test: `./gradlew tests:test` — all from `C:\Git\Unciv`.
- `.doc/context_state.md` — architecture/status diagrams (update when architecture changes).
- `.doc/plans-issues.md` — backlog of ideas and issues.

## Feature folder layout

Each feature/bug gets its own folder: `.doc/YY-MM-DD <short name>/` (e.g. `.doc/26-10-21 YAML Escaping bug/`).

| File | Phase | Content |
|---|---|---|
| `001 <issue>.md` (or similar) | input | The user's original issue/request |
| `spec.md` | 1 | Agreed specification |
| `todo.md` | 2 | Atomic, sequential, testable tasks as `- [ ]` checkboxes |
| `review.md` | 4 | QA findings |

If it's unclear which feature folder is active, ask. Never overwrite another feature's files.

## Diagrams

- Use PlantUML in fenced ` ```plantuml ` blocks (same as `.doc/context_state.md`).
- Keep them small: 1–3 per doc (class / sequence / activity), only when they clarify something prose doesn't.
- Diagrams are exempt from the "no code blocks" rule in Phases 1, 2 and 4.

## The 4-phase workflow

The user names the phase (or it's obvious from the request). Stop at the end of every phase and wait.

### Phase 1 — Brainstorming (no implementation code)
- Analyze existing code + requirements. Focus on architecture, SOLID, edge cases, system design. No full code blocks.
- Discuss with the user until agreed; ask when in doubt. Use PlantUML diagrams to illustrate design options.
- Then write `spec.md` with exactly these sections:
  1. **Context & Goal** — brief technical summary.
  2. **Affected Files** — exact paths to create/modify.
  3. **Architecture & Data Flow** — classes, interfaces, signatures, SOLID considerations.
  4. **Edge Cases & Risks** — null safety, concurrency, error handling, memory leaks.
  5. **Verification Strategy** — exact unit/integration tests required.
  6. **Diagrams** — PlantUML of the agreed design (classes and/or main flow).
- Stop.

### Phase 2 — Planning (no source code)
- Read `spec.md` (and `review.md` if it exists — its open findings become tasks).
- Write `todo.md`: atomic, sequential, testable tasks with `- [ ]`, each with a **Verification** line.
- Create a branch for the feature if not already on one (`feature/<name>` or `bug/<name>`).
- Stop.

### Phase 3 — Implementation (strict, one task at a time)
- Read `spec.md` and `todo.md`.
- Execute ONLY the first unchecked `- [ ]` task: write code → build → run tests → fix errors → mark `- [x]` in `todo.md`.
- Stop after that single task and wait for the user to say continue. Never batch tasks.
- If a task turns out wrong or blocked, stop and report instead of improvising beyond the spec.

### Phase 4 — QA Review / Feedback (no implementation code)
- Compare `git diff` (against the base branch) with `spec.md`.
- Look for logic errors, memory leaks, missing tests, spec deviations; run build + tests.
- Write `review.md`: summary & verdict, spec compliance checklist, findings (with severity), recommended follow-ups, and PlantUML diagrams of the as-built design (call out deviations from the spec diagrams).
- Check whether `.doc/context_state.md` diagrams need updating.
- Stop. If follow-ups are accepted, loop back to Phase 2 (append tasks to `todo.md`) → Phase 3.

## Terminal commands

Before running any command, state briefly:
1. **Purpose / Reason** — what and why.
2. **Command** — exact command, with important flags/operators explained.
3. **Impact & Risk** — files/processes touched, destructive or network/install side effects (propose safer alternatives where relevant).
4. **Expected outcome.**

After running: briefly report the actual result and anything unexpected.
Never hide destructive operations inside compound commands. Don't run commands just because they look like the obvious next step.

## Git

- One branch per feature/bug, created at Phase 2/3 start.
- Commit or push only when the user asks.
