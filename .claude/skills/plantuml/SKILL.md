---
name: plantuml
description: Write and validate PlantUML diagrams (class, sequence, activity, component) in ```plantuml fences inside Markdown — spec.md, review.md, context_state.md. Use whenever creating or editing a PlantUML diagram, or when a PlantUML render shows "Syntax Error?".
---

# PlantUML — write it so it renders the first time

This project keeps diagrams as PlantUML in fenced ` ```plantuml ` blocks in `.doc/**.md`. CLAUDE.md caps them at 1–3 small diagrams per doc. This skill covers the syntax traps that have broken renders here, plus how to check the syntax before the user sees an error.

## Workflow

1. Pick the smallest diagram that adds something prose can't: a class diagram for structure, an activity diagram for a decision flow, a sequence diagram for call order.
2. Write it following the rules below.
3. **Validate** (see the last section) and fix every error before handing over. A diagram you haven't validated is a diagram you haven't finished.

## Known pitfalls (each one has broken a render in this repo)

### 1. Single-line enum or class bodies with values on new lines → `Syntax Error?`

The opening `{` has to end its line, and each member goes on its own line.

Broken (PlantUML reports `Syntax Error? (Assumed diagram type: class)` on the second value):
```
enum CoverLetterAction { OpenOwn
CreateDefault
Collision }
```
Fixed:
```
enum CoverLetterAction {
  OpenOwn
  CreateDefault
  Collision
}
```
A whole body on one line (`enum E { A }` or `class C {}`) is risky too. Use the multi-line form every time.

### 2. A `'` comment after a member is not a comment

`'` starts a comment only at the **start of a line**. Anywhere else it's literal text, so it ends up in the rendered member as `Load(csv) : WorkflowConfig ' migrates…`. The validator won't catch this.

Broken:
```
class WorkflowConfigService {
  +Load(csv) : WorkflowConfig  ' migrates legacy format
}
```
Fixed: move the remark into a note.
```
class WorkflowConfigService {
  +Load(csv) : WorkflowConfig
}
note right of WorkflowConfigService : Load migrates the exact legacy CL format
```

### 3. One activity action per line

An action runs from `:` to the closing `;`. Squeezing two actions into one line (`:Create directory; WriteAllText(target);`) passes the syntax check, but it renders oddly and doesn't mean what it looks like. Write one action per line:
```
:Create directory if missing;
:WriteAllText(target);
```
For a `;` inside an action's text, end the action with `;` on its own line, or reword the text.

## Other rules of thumb

- **Wrap every diagram** in `@startuml` … `@enduml`, one pair per fence.
- **Generics and nullables:** `string?` and `List<string>` are fine in members. For `<` and `>` in a class *name*, quote it: `class "Result<T>" as ResultT`.
- **Stereotypes:** `class FileNaming <<static>>`. Keep the space before `<<`.
- **Notes:**
  - Single line: `note right of X : text`.
  - Multi-line: `note right of X` … `end note`.
  - In activity diagrams: `note right: text` placed right after the action.
  - Use `\n` for line breaks inside one-liners.
- **Activity `if`:**
  - `if (cond?) then (yes)` … `elseif (cond?) then (yes)` … `else (no)` … `endif`.
  - Every `if` needs its `endif`.
  - An `else` branch with no actions is allowed.
- **Component diagrams:**
  - Bracket names, `[MainForm] --> [JobAdService] : label`.
  - Group with `package "X" { … }`.
  - Databases: `database "FileSystem" { [file] }`.
  - Folders: `folder "CL"`.
- **Keep it small.** No more than ~15 elements. Split the diagram, or drop detail the prose already covers.
- **Label deviations.** In review diagrams, flag differences from the spec with a `note` (for example `note right: F3 — not restricted to file name`) instead of a separate diagram.

## Validate before handing over

The PlantUML jar from the VS Code extension `jebbs.plantuml` is on this machine, and Java is on `PATH`. `-checkonly` scans **any** text file for `@startuml` blocks, Markdown included.

**Purpose:** catch syntax errors before the user's renderer does.

Find the jar. Its path changes with the extension version:
```bash
J=$(ls ~/.vscode/extensions/jebbs.plantuml-*/plantuml.jar | tail -1)
```

Check whole Markdown files. Exit `0` means OK; `200` means *"Some diagram description contains errors"*:
```bash
java -jar "$J" -checkonly "<path>.md"
```

**Don't add `-failfast2`.** It makes PlantUML render `<name>.png`, `<name>_001.png` and so on next to the file, even with `-checkonly`. `-checkonly` alone writes nothing and still exits `200` on errors.

Find the failing line. Write the bare diagram to a scratch `.puml` file and pipe it in. The output is `ERROR`, then a **0-based** line number within the diagram, then the message:
```bash
java -jar "$J" -syntax < scratch.puml
```

**Caveats:**
- The bundled jar (1.2024.x) can be older than the user's preview renderer. If they report an error the jar doesn't reproduce, trust their screenshot and fix it anyway.
- `-checkonly` checks syntax only. Pitfalls 2 and 3 pass it, so review the rendered meaning by eye.
- Before running these commands, follow the CLAUDE.md "Terminal commands" rules: state the purpose, the command, the impact and the expected result. They only read files and need no network.
