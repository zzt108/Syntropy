# SYNTROPY — Brainstorming Base

> Living document for the game-design brainstorming. **No implementation code** until the user says so.
> Source of everything marked *canon*: the original Perplexity brainstorming session (undated, pre-2026-11). The raw transcript was deleted on 2026-11-17; **this document is now the canon**.
> Each session: read this file → work one agenda item → update §3 Decisions, §5 Questions, §9 Log.
> Diagrams live in `diagrams/`, one diagram per `.puml` file, each with a comment header saying what it shows; index in §4.

---

## 1. Vision (one breath)

A turn-based, top-down, tribe-to-civilization game where **nobody wins**. The goal is the **flow state** of whoever plays (and of whoever builds it). Evolution is **social and mental, not biological**. The world state is read as an **I Ching hexagram**: the realm's body casts the six lines, and once per generation the player chooses, through plain-language keywords, which of the changing lines turn, and so sets the realm's direction. The other realms are played by code with their own profiles; new concepts come from a pre-generated library, and the game's own deterministic laws put a price on each of them. *Later version:* LLMs play the other realms live and **invent new concepts** (§6).

Philosophy underneath: *everything is one mind's appearance, so everything is good.* No ego-feeding, no monetization pressure. The builder's reward is the flow of building it.

**Ars poetica (D-46).** SYNTROPY is **not a Buddhist or Taoist game**. It borrows from those traditions (the I Ching, the five families, non-duality) because they model how people and societies actually behave **more closely than the principles under Civ** (growth, competition, victory). Civ's principles are not wrong, only narrower: they stay in the game as the part of reality they describe well.

## 2. Pillars

| # | Pillar | One-line meaning |
|---|---|---|
| P1 | **Communication** | Knowledge travels and distorts; it is not broadcast. |
| P2 | **Evolution** | Social/mental/cultural patterns unfold; extremes flip into their opposites. |
| P3 | **Feedback** | Choices echo across generations (trauma, learning, cultural memory). |
| P4 | **Strength in cooperation** | Alone is slow; together is fast but costs coordination. |
| P5 | **Flow** *(meta-pillar)* | Every mechanic is judged by whether it helps or breaks flow. |
| P6 | **Reality** *(meta-pillar)* | When two rules are possible, prefer the one closer to how people actually behave (D-46). |

## 3. Settled decisions (canon)

| ID | Decision | Notes |
|---|---|---|
| D-01 | Turn-based, top-down map, human tribe → civilization. | Civ is the reference genre, not a template. |
| D-02 | No winning. Emergence and a **Journal** (auto-chronicle) take its place. | Revised 2026-11-17: there **is** one fail condition, see D-19. How a game ends and what it shows at the end: D-53, Q-49. |
| D-03 | Evolution is social/mental, not biological. **Religion is emergent, not a menu item**: no built-in religion mechanic, but belief systems may emerge from concepts. | Revised 2026-11-17. Rationale: in Civ every religion is the same buff package with a different name. What really moves people is the **ego illusion**; religion is one of its group-scale forms. See D-16, Q-21. |
| D-04 | ~~Engine: **Unity C#**~~, Windows first, Android tablet maybe later. | ⚠ Engine superseded by D-41 (Unciv fork, Kotlin); C# is dropped. Platform order stays. Repo `CLAUDE.md` tech stack filled in on 2026-11-17 (Kotlin, Gradle, Unciv modules). |
| D-05 | **LLM players** via Markdown: game emits `STATE.md`, LLM returns `RESPONSE.md`. | ⚠ Superseded for rivals by D-28 (coded rivals) and for the human side by D-29 (one-way advisor). |
| D-06 | **Manual Bridge** first: the human copy-pastes/drag-drops between game and chat. No API. | Bridge = ritual. Revised by D-29: the bridge is now **one-way** (game → player's own LLM project) and optional. Still no API. |
| D-07 | LLM rules live in a doc set: `SYSTEM_CORE.md`, `PROTOCOL_RESPONSE.md`, `WORLD_LOGIC.md`, `EVOLUTION_LIBRARY.md`. | ⚠ Revised by D-29: the doc set becomes the **advisor kit**. `PROTOCOL_RESPONSE.md` is obsolete: the bridge is one-way, there is no `RESPONSE.md`. |
| D-08 | **I Ching hexagram = realm state.** Every realm has one. Player picks the changing line via keywords ("Choice of Fate"). | Player never needs to know it's I Ching. Refined by D-22 (several most/least picks) and D-48 (the body casts the lines; the player picks on the trembling lines). |
| D-09 | **Trigrams = passive buffs** (upper + lower trigram → two traits). | Refined by D-47: lower trigram = one yield modifier, upper trigram = connection factor. |
| D-10 | Determinism is **fractal**: same rules at every scale; same choice → same hexagram. | Refined by D-20, D-21: the *rules* are deterministic, the *dice* are not (outside tests). |
| D-11 | Four resources: **Material, Wealth, Knowledge, Culture.** | Mapped to Unciv stats by D-41; internal grouping or UI term: Q-41. |
| D-12 | **Valuation Engine** (own Kotlin code per D-41, deterministic) prices LLM-invented concepts from their **tags**; LLM never sets costs. | Balance stays in code. |
| D-13 | **Auto-Governor** executes the micro-management (builds, improvements) based on the chosen keyword/direction. | "Gardener" play style. ⚠ Deferred by D-38 (hands-on play first). Unciv's own automation as a later Governor: Q-43. |
| D-14 | Name: **SYNTROPY: Order from Chaos.** | The runner-up name was *Flux*, also the name of the former design repo (merged into the Unciv fork on 2026-11-17, see §9). |
| D-15 | **RPG elements are back in.** They were in the original request and dropped silently during the I Ching / Auto-Governor sessions. | 2026-11-17. *What* RPG means here is still open: Q-22. |
| D-16 | The **ego illusion** (sense of a separate self) is a core driving force of people in the game, stronger than religion. | 2026-11-17. How it becomes a mechanic: Q-21. |
| D-17 | The ego illusion shows up as **disturbing emotions**, at the individual level *and* at the group level (**group disturbing emotions**, the user's term). They are the **main antagonist force against cooperation (P4)**. | 2026-11-17. **Never named in the game**: the player only sees the emergent phenomena (feuds, hoarding, dogma, refusing a neighbour's know-how). Candidate set and transformation: Q-23. |
| D-18 | **Institutions are wisdom deflected by ego, not institutionalized poison.** Every force (army, trade, law, belief) has a wise core; the individual/group ego gives it a ricochet. Example: an army's core is peace-keeping (deterrence, balance of power, cf. the Cold War); deflected, it becomes conquest. | 2026-11-17. Explicitly **against** David Loy's reading (§8). The poison is a **raw material the player uses**, not an enemy to eliminate. |
| D-19 | **Fail condition: monopolization.** The weak get swept away and the remaining superpower turns dictatorial/monopolistic. The player's job is to decide how to use the poison: let monopolization happen, or keep it in check. | 2026-11-17. Fits Carse: the infinite game only "ends" when someone wins it. **Not a game over** (Q-24 → B, 2026-11-17): the hegemon falls into a heavy **Standstill** state (hexagram 12 as candidate) that can only be left through **collapse**, as empires do. The game continues after the fall. ⚠ Tension with D-48 (the hexagram is a reading of the body): Q-45. Diagram 15. |
| D-20 | **"Same seed + same choices (+ same stored LLM responses) = same run"** holds for **testing/replay only**. The real game may be random (fresh seed). | 2026-11-17. The engine must still *support* a fixed seed so tests and bug replays are exact. Links Q-17. |
| D-21 | **Hexagram changes are weighted, not uniform.** The situation raises the odds of certain hexagrams (e.g. dictatorship/monopoly → Standstill). Changing lines may be random **and/or** chosen by the player. | 2026-11-17. Mechanism still open: Q-26. Refined by D-48: the weighting is the line score (body reading + pressure); the dice act near the threshold. |
| D-22 | **Choice of Fate, v2:** the player receives the hexagram as **line keywords + a situation summary**, and picks several of them in a **"most / least" (DISC-like forced choice)** way. **How many** they may pick depends on **flexibility stats**. | 2026-11-17. Working reading: "most" = that line changes, "least" = that line is held this generation. Supersedes the "pick one keyword" loop in §4. Refined by D-34 (trembling lines, two keywords each) and D-35 (same-line cancel). |
| D-23 | **Hidden profile from choices.** The pattern of the player's picks builds a profile the player never sees as numbers. The profile feeds back: it influences **how many changing lines** can be chosen (and possibly the line odds of D-21). | 2026-11-17. Strong RPG link (Q-22). Dimensions and feedback balance: Q-27. Shown once, at the end of a game, as a portrait: D-53. |
| D-24 | **The core formula.** (1) The **I Ching** is the game's **state engine**: non-negotiable. (2) The **disturbing emotions / wisdoms** form the **player profile**. (3) The game *is* the **interplay of these two + random effects + player interaction**. | 2026-11-17. Answers the axes part of Q-27: profile = 5 disturbing emotions ↔ 5 wisdoms (inner structure: D-31). Everything else (resources, Valuation Engine, Auto-Governor, LLM realms) serves this core. See §4 "Core engine". Refined by D-48: the I Ching is the **reading** of the state plus the rules of change, not a state of its own beside the body. |
| D-25 | **One rule system: world events push lines.** Random effects include world events (drought, plague, a stranger arriving), but every event is expressed as a push on hexagram lines / line odds. No second rulebook beside the I Ching engine. | 2026-11-17. Resolves Q-28. |
| D-26 | **Same engine, same profile, two kinds of minds.** Every realm has a profile. Other players are embodied by **LLMs with different profiles**. The human player(s) may ask an **LLM advisor** for help with their own choices. | 2026-11-17. Resolves Q-29, mostly answers Q-02. "Player(s)" leaves room for more than one human. Follow-ups: Q-30. ⚠ The "LLMs embody the other players" part is superseded by D-28: rivals are code with profiles, not live LLMs. The one-engine, one-profile-per-realm part stands. |
| D-27 | **The advisor has its own profile.** A proud advisor gives proud advice; choosing an advisor = choosing a mirror. | 2026-11-17. Resolves Q-30 (b). Working view on Q-30 (a) kept: following advice is the human's choice, so the human's profile moves. |
| D-28 | **Rivals are code, written by an LLM from the rulebook.** Non-human-serving realms are not played live by a chat LLM; an LLM writes their decision code at development time from the rulebook. A rival mind = a deterministic function of (visible state, profile) → most/least picks. | 2026-11-17. No copy-paste for rivals. Profile drift (Q-30 c) comes for free: the profile is an input. Makes replay exact (D-20, Q-17). Cost: live concept invention by rivals is lost, see Q-31. Who plays the rivals' body (Unciv AI?): Q-44. |
| D-29 | **Human companion = the player's own LLM project.** We ship an **advisor kit**: recommended instructions, the rulebook, optionally skills, and advisor persona files (each with its own profile, D-27). The player creates a project in their LLM of choice and pastes in the **current state as the player sees it** (player view, no hidden info). | 2026-11-17. One-way: the game never parses the advice; the human reads it and chooses. Optional: the game is fully playable without it. Reuses the doc set of D-07 as the kit. Refined by D-53: after the end of a game the kit also receives the profile export (hidden state included). |
| D-30 | **Concepts come from a pre-generated library first.** At development time an LLM generates a tagged concept library; the Valuation Engine prices it (D-12); realms pick from it by tags. Advisor import of new concepts (Q-31 b) is **deferred**, a possible later extension. | 2026-11-17. Resolves Q-31 as (a) now, (c) maybe later. Library size and tag vocabulary: Q-10. Decay/forgetting: Q-11. What a concept is in Unciv terms: Q-42. |
| D-31 | **Profile = temperament + ego.** Five **families**, each one energy with two expressions (poison ↔ wisdom): Confusion ↔ Space, Anger ↔ Mirror, Pride ↔ Equality, Attachment ↔ Discernment, Jealousy ↔ Accomplishing. **Temperament** = a distribution over the five families (which energy colours this mind). **Ego** = one scalar: how strongly that energy is deflected (D-16 made a variable). **Transformation** = ego goes down, temperament stays: a proud mind does not become humble, it becomes equanimous. | 2026-11-17. Resolves Q-23 (set + transformation). Single ego instead of per-family ego: simpler, and the deflection still shows through the dominant family. Family verbs (authoring rule): Appendix A. |
| D-32 | **Ego is measured by situation fit, not by "nice words".** Every line has, per hexagram, a **fitting** keyword (the wisdom of the family the situation calls for) and a **tempting** keyword (a poison, phrased attractively). No keyword sounds good or bad (D-18). Choosing the fitting one → ego ↓. Choosing the tempting one → ego ↑, and if it matches the own temperament, the temperament hardens (ego = habit). | 2026-11-17. **Same-family lines** (tempting and fitting from one family) test ego; **cross-family lines** test temperament flexibility. Content: 384 lines × 2 = 768 keywords, own phrasing (Q-08), generated at development time by an LLM from the family verbs (cf. D-30). Sample: Appendix A. |
| D-33 | **Flexibility is derived, not a stat:** pick count = f(1 − ego). Guard against the rich-get-richer spiral = a **flow channel**: more picks bring more trembling lines and a more complex situation (challenge grows with skill); rigidity builds pressure until a break (many lines change at once), which humbles and drops ego (P2 flip, small-scale D-19). | 2026-11-17. Resolves the flexibility and spiral parts of Q-27. The exact f() and the fractal scale stay open. Whether pressure must carry over between generations for the "builds until a break" part: Q-47. |
| D-34 | **Turn pipeline: the dice offer, the player decides.** (1) The situation weights each line's odds (D-21, D-25). (2) The dice mark the **trembling lines** (candidates; working value 2–3 per generation, minimum 2, tunable). (3) Each trembling line shows its two keywords (D-32), shuffled, without line numbers, beside the situation summary. (4) The player places most/least picks: "most" = that line changes, "least" = that line is held. (5) Lines that actually flip are the **changing lines** (old yang / old yin); the realm moves to the new hexagram. **Trembling lines the player leaves unmarked are decided by the dice.** | 2026-11-17. Refines D-22. Rationale for the candidate stage: traditional coin casting gives on average 1.5 changing lines (~18% none, ~36% one), too few for a real choice. Low flexibility = fewer picks = more left to fate: ego shows as **loss of agency**, not as penalty points. Step 2 refined by D-48: the **old lines** of the body reading are the trembling lines; the dice only decide near the threshold and fill up the minimum. Step 5 refined by D-48: the flipped lines give the **relating hexagram** (the direction); the next primary hexagram is read from the body again. |
| D-35 | **Most + least on the same line cancel on the line, add on the profile.** The line is treated as unmarked (the dice decide); both profile signals count. Most = fitting + least = tempting → ego ↓↓ ("inner turn": the player changes themself, not the world). Most = tempting + least = fitting → ego ↑↑. | 2026-11-17. Gives the "let go of control" choice a real place in the game. Not farmable: a misread costs double and gives no agency over that line. |
| D-36 | **Body and soul.** The **body** is the Unciv (Civ V) economy (hex map, cities, population, production, research, happiness). The **soul** is the I Ching engine + profile (D-24). They couple at four points: (1) **trigrams → climate** (lower = inner yield modifier, upper = connection factor; D-09 made concrete in D-47); (2) **the picked keyword → the era's policy**: the tempting keyword gives better numbers *now* and a ricochet later, the fitting one pays slower and through cooperation (D-18, D-32: the economy is what makes the temptation real); (3) **ego + temperament → friction** (D-40); (4) **the body's state → line odds** (famine, surplus, monopoly, a neighbour's closeness push lines; D-21, D-25, input of Q-26). | 2026-11-17. Land gives Material and Wealth; people and buildings give Knowledge and Culture (D-11 split by source: the land feeds the body, minds feed the mind). Research unlocks concepts from the library by prerequisite tags (D-30). Trigram → yield table: Appendix B (D-47). See §4 "Body and soul". Point (4) refined by D-48: the body's state **gives** the lines (main weight) and also **pushes** them (pressure). |
| D-37 | **Map: a simple hex tile map with cities.** Unciv's hex map, kept small at first; features not needed yet are switched off by ruleset (D-42, D-51). | 2026-11-17. Resolves Q-13: the map survives. Which Unciv features (units etc.) stay on in the first ruleset: Q-33. |
| D-38 | **Hands-on play: the player chooses builds and research.** The Auto-Governor (D-13) is **deferred**. | 2026-11-17. Its other role, besides automation, was the **translation** of the picked keyword into economic action (the removed pre-D-28 loop: it received trigram traits and caps from the hexagram and spent resources by the chosen direction). That role must survive without the Governor: coupling points (1) and (2) of D-36. Mechanical form: Q-34. |
| D-39 | **Two clocks.** A **turn** is an ordinary Civ-like turn: produce, build, research, move, fight. The hexagram stands still meanwhile, and world events pile up as pressure on the lines. A **generation** is the slow beat: the hexagram change through the Choice of Fate (D-34: trembling lines, most/least picks) and the profile update. Working value: every ~5 turns, earlier if the pressure crosses a threshold. **No calendar years are shown, only eras.** | 2026-11-17. Resolves Q-05. Wording fix (same day): trembling lines and picks happen **per generation**, not per turn; the text of D-34, Q-30 and Q-32 was corrected accordingly. Rationale: a hexagram changing every turn would make the "climate" too jumpy to plan a city around. Civ's non-linear time scale: parked (§6). Refined by D-52: the length is chosen at game setup, ~5 turns is the default. |
| D-40 | **Ego symptoms in the economy, by family** (never named in game, D-17). Confusion → **waste** (Material leaks). Anger → **unrest** (more unhappiness). Pride → **research penalty** (foreign know-how is refused). Attachment → **trade income penalty** (hoarding, no investment, no exchange). Jealousy → **penalty on shared work** (joint research, the cooperation bonus of P4), not on ordinary production. | 2026-11-17, rewritten in Unciv terms on 2026-11-18 (D-51). Size of the penalty grows with group ego × city size/power (as Civ V unhappiness grows with population). Jealousy hits shared work because its wisdom is Accomplishing ("act together", Appendix A.1): the poison breaks exactly what the wisdom builds. Culture has no direct drain: Q-35. The Unciv form of each symptom: D-50. |
| D-41 | **Engine: a fork of Unciv** (open-source Civ V clone, **Kotlin + libGDX + Gradle**, MPL-2.0), fork `zzt108/Unciv`, local `C:\Git\Unciv`. Unciv is the **body** (D-36, D-37: hex map, cities, yields, research, happiness/unrest, AI, saves, desktop + Android). The **soul** (Fate engine, most/least screen, weighted dice, Journal, Standstill/collapse) is our own Kotlin code. | 2026-11-17. Supersedes D-04's Unity C#. Resource mapping: Production (+Food) → Material, Gold → Wealth, Science → Knowledge, Culture → Culture (D-11); Faith unused (D-03). The existing `AiStatusExporter` in the fork is the seed of the advisor kit's state export (D-29). MPL-2.0: modified Unciv files stay MPL and open. |
| D-42 | **Civ V content is trimmed by ruleset only during development**: whatever can be removed or disabled without a code change (religion, city-states, great people, spaceship, victory types, Civ V nations/leaders…) goes out through a custom ruleset/mod. Code changes for trimming come later, if at all. | 2026-11-17. Keeps the fork's diff against upstream small (D-43). What can't be removed by ruleset stays visible for now. Refined by D-51: switched off for testing, brought in later through brainstorming. |
| D-43 | **The fork stays unofficial forever; own code hooks in with minimal upstream changes.** Upstream did not accept the earlier `AiStatusExporter`, so no upstream merge is planned. Rule: our code lives in our own packages; upstream files get only small, clearly marked hook points (as with `AiExportUIInjector`). | 2026-11-17. Purpose: pulling upstream changes into the fork stays cheap. The Fate engine core should be Gdx-independent and unit-testable. |
| D-44 | **Era policy = a modifier set per (family, fitting / tempting), lasting one generation.** (1) Mechanical form is Q-34 (a); (b) comes free, because the keyword text itself already suggests a direction, so there is no separate "suggested focus" UI. (2) The policy is keyed by the picked keyword's **family and fit**, not by the keyword: 5 × 2 = 10 policy sets instead of 768; the keyword gives the mood, the family gives the numbers, balance stays in one table. (3) Only a **"most"** pick sets a policy. "Least" (held line) = continuity, no new policy; a dice-flipped line = no policy, only its trigram change acts (coupling point 1); a cancelled line (D-35) = no policy, profile signal only. (4) **No separate ricochet mechanic.** A tempting pick already strikes back through ego ↑ → friction (coupling point 3, D-40) and line pressure next generation (coupling point 4, D-25). (5) **Fitting is smaller and grows with connection** (P4); **tempting is bigger, immediate and works alone.** (6) The policy lives **until the next generation change**, not for N turns (a generation can end early, D-39). (7) Policies of several "most" picks **stack additively**; repeated temptation is punished by ego, not by the numbers. | 2026-11-17. Resolves Q-34. Working values (**to be tuned in playtests, not decided**): tempting = flat +15%; fitting = +5% per connected realm, cap +20%. Draft policy table: Appendix C. The tempting column uses the **tempting keyword's** family, which differs from the fitting one on cross-family lines. Unciv form: §4 "Engine notes". |
| D-45 | **"There are no others"** (Nishargadatta). The game has **no partner category**: no ally list, no friend/foe status the fitting bonus depends on. Reading (confirmed by the user): every realm the player is **in contact with** (has met) counts as a connection, **whatever the diplomatic state, war included**. | 2026-11-17. Answers "who counts as a partner" from D-44 (5). Splitting the world into own and other is itself an ego symptom (Pride's "Keep to our own", Appendix A.4), so the game must not build that split into its rules. Open detail: whether the bonus counts realms only, or also the *intensity* of the contact (trade, borders, diffusion); links Q-14, Q-21. Civ V treaties (friendship, research agreements) vs. this rule: Q-40. |
| D-46 | **Ars poetica: a model of reality, not a religion.** The game is not Buddhist or Taoist. Those traditions are used because they approximate reality better than Civ's principles; Civ's principles are not wrong, only narrower, and they stay. Text: §1. | 2026-11-17. User's words, wording approximate ("kb az ars poetica"). Fits D-03, D-08, D-17: no doctrine, no tradition's terms in the game. **Consequences (agreed 2026-11-17):** (a) Civ's logic is the **tempting column** (D-32, D-44): it really pays, now; the game only shows its ricochet. Civ is not the opponent, it is one half of reality. (b) A second design test beside flow: when two rules are possible, prefer the one closer to how people actually behave. Recorded as meta-pillar **P6** (§2). **Why the I Ching** (user, 2026-11-17): it looks like a horoscope, but its map of possible changes between states is remarkably well-founded (§4 "The I Ching as a state space"). It is used for its **formalism** (the transition map), not for divination; the meaning (keywords, situation summary) is our own (Q-08). Parallel: quantum mechanics has one well-tested formalism and many interpretations that experiments cannot (yet) tell apart. **The game is an experiment:** D-20 gives repeatability, playtests are the experiment, tuning the working values is the evaluation; so P6 is a testable claim inside the model, not a belief. |
| D-47 | **Trigram climate: lower = one yield modifier, upper = connection factor.** (1) The **lower** trigram gives one yield or growth modifier, each an existing Unciv unique. (2) The **upper** trigram gives a **connection factor** that multiplies the D-44 fitting bonus per connection (D-45): the outer side is *how much contact pays*. (3) Neighbour-facing effects of the old draft (friction, envy, monopoly pressure, trade risk) are **not** yields; they move to Q-06 / Q-26 as line pressure (D-25). (4) Visibility: two plain words, inner and outer (e.g. "Inner: creative · Outer: receptive"), plus the effects in the stat breakdown as in D-44; no trigram names or symbols (D-08). The line → trigram link stays hidden, because keywords carry no line numbers (D-34), so picks cannot be optimised for yields (protects D-32). (5) The climate **stacks** with the era policy (D-44); a climate that amplifies a temptation is intended (P6: circumstances tempt; A.6 obs. 3). | 2026-11-17. Resolves Q-36. Table: Appendix B. All magnitudes and factors are **playtest values, not decided**; the factors are uneven on purpose (four ×1.5, three ×1, one ×0.5). |
| D-48 | **The body casts the lines.** No divination happens in the game, so the current hexagram must **correspond to the game state**, line by line. Key mechanism. (1) **Six lines = six axes read from the body**, each between two legitimate poles (D-18); lower lines 1–3 = the inner state, upper lines 4–6 = the relation to others (consistent with D-47). (2) **Line score** per line at the generation change: **s = w · b + (1 − w) · p**, w ≈ 0.7. *b* = body reading ∈ [−1, +1] (−1 full yin pole, +1 full yang pole), the main weight. *p* = pressure ∈ [−1, +1], piled up during the generation's turns (D-39) from world events (D-25), neighbours (Q-06) and the profile (ego pushes a line toward its current pole), reset at the generation change. (3) **Sign of s → yin / yang; \|s\| above a threshold → old line** (the traditional 6 / 9), otherwise young (7 / 8); near the threshold the dice decide, weighted by distance (D-21). (4) **Old lines are the trembling lines.** If there are fewer than the minimum (2, D-34), the dice add the lines closest to the threshold. (5) Pipeline: reading → **primary hexagram** (the present) → picks on the old lines (D-32 … D-35 unchanged) → **relating hexagram** (the direction) → its climate (D-47) and the "most" policies (D-44) push the body until the next reading. | 2026-11-17. The user: both "the body gives the lines" and "the body pushes the line odds" are wanted, with more weight on the first; the blend does both. With w = 0.7 pressure can flip a line's sign only where \|b\| < 0.3 / 0.7 ≈ 0.43: a clear body is read as it is, an ambiguous one is decided by events (P6). Pressure can also push a young line into old, i.e. make it tremble: the old "odds" role survives. Ego rigidity (D-33) needs no own rule: clinging pushes a line to its own extreme until it turns old and flips (P2). The gap between the intended direction (relating) and the next reading (the next primary) goes to the Journal. Refines D-21, D-24, D-34, D-36 (4). Axes: D-49, Appendix D. w and the thresholds are playtest values. Why the reset (2026-11-18): the event's real consequences reach the body and are read through b next generation, so keeping p would count them twice; p would also saturate and outweigh b; and the early trigger (D-39) would fire every turn after its first crossing. Reset vs. carry-over: Q-47. |
| D-49 | **The six axes and how they are read.** (1) **Rates and ratios, not size**, so the six readings stay as independent as possible and all 64 hexagrams stay reachable; size is measured on line 5 only, because monopoly (D-19) *is* size. (2) **Lower lines (the realm itself):** line 1 Material = trend of (food surplus + production) against the realm's own previous generation; line 2 Wealth = spent share of gross income, spent = gross income − Δtreasury (upkeep, purchases, gold given in trades and gifts, all in one term); line 3 Expansion = **wide vs. tall**, the production-weighted share of wide things (new cities, tiles, tile improvements, settlers, workers) against tall things (buildings, wonders). (3) **Upper lines (the outside)** read against a blend: **outside = (1 − v) · world + v · realms, v = n / (n + 1)**, n = number of contacted realms. The world part (barbarians, explored land, discoveries) keeps the upper lines meaningful before any contact and never disappears afterwards (D-45). (4) Normalisation per axis: **b = tanh(k · (x − x₀))**, with its own k and neutral point x₀. (5) **Hysteresis on the sign** (a yang line stays yang until s < −h); the dice act only at the old threshold (D-48). (6) **The profile does not shift the thresholds**: ego already acts through the pressure p; one effect, one mechanism (D-25). (7) All readings come from snapshots at the start and end of a generation, read-only, with no upstream change (D-43). | 2026-11-17. Resolves most of Q-38. The user's refinements: production belongs in Material; upkeep counts as spending; territory alone is too slow and too jumpy for a ~5-turn generation, so built things count (became wide vs. tall); before contact the outside is the world (scouts, settlers, barbarians, city-states). Military units are left out of line 3 because they feed line 4. All k, x₀, h and thresholds are playtest values. Full table, edge cases, feedback check and the coverage test: Appendix D. |
| D-50 | **Ego symptoms on the Unciv body.** The player sees the **phenomenon**, never the emotion (D-17). Confusion → **Waste**: − % Production and Food, stronger in larger cities (population tiers, `in cities with at least [N] [Population]`). Anger → **Unrest**: + % Unhappiness from Population, stronger in larger cities, acting through Civ V's empire happiness and its existing penalties. Pride → **"We have nothing to learn"**: the known-tech discount (`TechManager.getScienceModifier`, techs known by met civs are cheaper) shrinks. Attachment → **Hoarding**: − % Gold from Trade Routes. Jealousy → **Rivalry**: − % Science from research agreements, and the connection bonus of D-44 / D-47 shrinks. **Mechanism:** intensity(f) = ego × temperament(f) (D-31); penalty = c_f × intensity(f). At each generation change the Fate engine computes the concrete percentages and injects them as uniques into `temporaryUniques` (as D-44), with the **phenomenon name as source name**, so the stat breakdown shows e.g. "Waste: −8% Production" and nothing about ego, family or temperament (D-23). No hidden resources needed. **One upstream hook for the symptoms:** a marked factor in `getScienceModifier` for Pride (D-43). | 2026-11-17. Resolves Q-37. Q-37's three options were: (a) own Kotlin code behind a hook in an upstream file; (b) Unciv's existing uniques (rule text the engine already understands, no code change); (c) remap to a Civ V mechanic that already exists. Used: Confusion (b), Anger (c) + (b), Pride (a), Attachment (b), Jealousy (b) + own Fate code. **Pride and Jealousy do nothing to a realm alone**: with no one to learn from there is nothing to refuse, with no one to work with there is nothing to envy (P6: these poisons live in relations). Jealousy hits exactly what Accomplishing builds (research agreements = Civ V's shared research, connection bonus = P4). Anger stays empire-wide, as Civ V happiness is: the group's anger is the group's mood (D-17). Population conditionals are evaluated live, so a city that grows mid-generation moves up a tier at once. The `for every [n] [resource]` path stays a mod-only alternative. c_f are playtest values. Culture drain still open: Q-35. |
| D-51 | **Unciv / Civ V is the only reference for the body; Civ 1 is out.** Every body mechanic is described in Unciv (Civ V) terms. Unciv features are **switched off for testing by JSON ruleset configuration** (extends D-42) and **brought into the game's philosophy later through brainstorming**. | 2026-11-18. Civ 1 was the reference before the engine choice; with Unciv (D-41) every Civ 1 reference lost its meaning. A switched-off Civ V feature is "off until brainstormed in", not rejected. Civ 1 wording removed from D-36, D-37, D-40, D-50 and Q-33; the decision log (§9) keeps the old wording, as it is append-only. "Test-off" vs. "design-out" features: Q-39. |
| D-52 | **Generation length is set at game setup.** The player chooses how many turns a generation lasts when starting a game; D-39's ~5 turns stays the default. The early end on a pressure threshold works at every length. The pressure is **normalised to the generation length**, so a long generation does not end early just because more turns piled up pressure, and a short one still can. The shortest allowed length keeps D-39's rationale: the climate must not change too fast to plan a city around. | 2026-11-18. The user: "generation length should be user configurable at game setup". Refines D-39. The body readings barely depend on the length (rates and ratios, D-49); the pressure does, hence the normalisation. Longer = calmer, more strategic, fewer Choice of Fate rounds per game; shorter = restless, faster ego dynamics. Presets, coupling with Unciv's game speed, the normalisation method and where the setting lives: Q-48. Diagram 04. |
| D-53 | **The Mirror: an end-of-game portrait of the ruler.** When a game ends, the hidden profile (D-23) is shown for the first and only time, as a portrait of **the ruler the player was**, not a personality test of the person. (1) **End triggers:** (a) an Unciv end condition (a victory type or the player's defeat, defined in the ruleset); (b) a **Retire** button; (c) the player asks for the Mirror (whether this ends the game: Q-49). (2) **Six layers, all from data the soul already keeps:** temperament (D-31); the ego path over the generations, with its breaks (D-33); temptation vs. flexibility (same-family vs. cross-family lines, A.6); agency (lines left to fate, inner turns, D-34, D-35); the path through Q6 (time near HG 11 / HG 12, long jumps, §4); and **words vs. deeds**: the families of the picked keywords against what the six axes measured (D-49), e.g. "Lift others with you" picked while line 5 read dominant. (3) **Two outputs:** a short in-game portrait in plain words, and a **profile export** into the advisor kit (D-29), from which the player's own LLM writes the full analysis. (4) **Guards:** patterns only, no numbers and no keyword → family mapping (otherwise the next game gets optimised, D-32); no moralising, every family has its wisdom (D-18); no tradition's terms (D-46); no diagnosis. | 2026-11-18. The user's idea: a psychological analysis of the player at the end, from the decisions and the profile; would DISC fit? **DISC stays the question format (D-22), not the answer vocabulary:** 4 factors vs. 5 families (Accomplishing, the P4 family, has no clean place), no ego axis (DISC measures style, the soul measures situation fit), weak validity (P6). Optional Big Five translation: parked (§6), wanted but not urgent. The export is the only place where hidden state leaves the game, and only after the end (refines D-29, which sends the player view only). "Mirror" is a working name (Mirror wisdom; D-27: choosing an advisor = choosing a mirror). Diagram 23. |
| D-54 | **Audience: the builder first, maybe a public release later.** The primary player is the user. There is no circle of friends or testers to design for. A free public release stays an **open option, not a goal**: nothing may block it, but nothing is built for it (tutorial, installer, Android, store page) before the smallest playable slice (Q-20) gives flow. (1) **English and Hungarian from the first slice.** English is the source text: Unciv's translation files use the English string as the key, and a mod ships its own `jsons/translations/Hungarian.properties` (`docs/Translating/Translation-mods.md`), so Hungarian needs no code change. Unciv's own Hungarian file already exists. The 768 keywords (D-32), the situation summary and the Mirror's in-game portrait (D-53) are **written in both languages, each on its own terms**: the fitting / tempting balance ("no keyword sounds good or bad", D-18) must hold in each language, and a literal translation does not guarantee that. Which language the soul speaks: D-55. (2) **The advisor kit is provider-neutral** (D-29): plain Markdown instructions, the rulebook and persona files that work in any LLM project. One provider's skills (e.g. Claude's) are optional extras only. (3) **Consequences:** the P6 experiment (D-46) has **n = 1** until a release: Q-50. The only player **wrote the keywords**, which threatens the hidden measurement (D-23, D-32): Q-51. The name check (§8) and the MPL-2.0 notices (D-41) become preconditions of a release, not of the slice. | 2026-11-18. Resolves Q-19. The user: "only me, and maybe a public release; I don't know anyone directly who plays this kind of game." The proposed middle option (design for a small circle) was dropped: there is no such circle, so there is no "friend who knows Civ but not the I Ching" to test against. D-08 (the player never needs to know it's the I Ching) still stands for a possible public. Inputs to Q-03, Q-04, Q-08, Q-16, Q-18, Q-20, Q-22, Q-30 are noted at each question. |
| D-55 | **Soul language: the soul speaks its own language, the body speaks the UI language.** A separate **Soul language** setting, independent of Unciv's UI language (`settings.language`), so the Choice of Fate can be in the player's native language (e.g. Hungarian) while the game UI is English. (1) **Rule:** every text the Fate engine produces is shown in the Soul language, **even where it appears on a body screen**: the keywords and the situation summary (D-34), the climate words (D-47), the policy and symptom source names in the stat breakdown (D-44, D-50), the Journal and the in-game Mirror (D-53). Everything else (Unciv's own screens, units, buildings, techs) follows the UI language. (2) **ID-based storage:** the save, the Journal and the Mirror's profile export keep **keyword IDs** (and other content IDs), never display text; the text is rendered when shown. (3) **Switching any time.** The new language shows at once on every screen the soul renders live; source names already injected into `temporaryUniques` keep the old language until the next generation change, when they are re-injected (D-44 (6)). (4) **Lookup:** our code asks Unciv's translation store for an explicit language, `Translations.getText(text, language, activeMods)` (`core/src/com/unciv/models/translations/Translations.kt`), instead of `String.tr()`, which always uses the UI language; the texts live in the SYNTROPY mod's translation files (D-54 (1)). | 2026-11-18. The user: the Choice of Fate should be possible in the player's native language with an English game UI; the setting is named **Soul language** (the user's rename of the proposed "Fate language"). Known cost, accepted (n = 1, D-54): a keyword may tempt more or less in one language than in the other, so profiles measured in different languages are not strictly comparable (D-32). Where the setting is stored (own settings file in our package, or a marked hook in `GameSettings`, D-43): open, to place with the other hooks (§4 Engine notes). Hungarian working name of the Choice of Fate: *Sorsválasztás* (not decided). |
| D-56 | **Discoverability route (later): a `.github/README.md` on our branch + a separate showcase repo.** The fork `zzt108/Unciv` is public, but its default branch is `master` (the upstream mirror, D-41), its GitHub description is still Unciv's, and **GitHub's search leaves forks out by default**, so a README in the fork alone is not found. (1) **`.github/README.md` on `zzt_develop`**: GitHub shows it in place of the root `README.md`, so Unciv's README stays untouched and upstream merges stay conflict-free (D-43). It needs the repo's default branch switched to `zzt_develop` (a GitHub setting, the user's step). (2) **A separate showcase repo** (e.g. `zzt108/syntropy`): README and design overview only, linking to the fork; this is the searchable one. | 2026-11-18. The user: "c + a, but not now". **Not now:** written when a release comes closer (D-54); preconditions: the name check (§8) and an up-to-date description of the game. Rejected options: detaching the fork from the fork network (loses GitHub's upstream comparison), the `.github/README.md` alone (not searchable). Whether the README names the I Ching: Q-52. Motivation behind it: Q-03 ("ego brings, flow keeps"). |

## 4. Core model (as understood so far)

### Diagram index

All diagrams are in `diagrams/`; open a `.puml` file with the PlantUML preview (VS Code, `jebbs.plantuml`). Each file starts with a comment saying what it shows and which decisions it draws on.

| # | Diagram | What it shows | Decisions |
|---|---|---|---|
| 01 | [Core engine](diagrams/01-core-engine.puml) | body, I Ching reading, profile, player, random effects | D-24, D-48 |
| 02 | [Fate pipeline](diagrams/02-fate-pipeline.puml) | one generation from body reading to relating hexagram | D-34, D-35, D-48 |
| 03 | [Body and soul](diagrams/03-body-and-soul.puml) | the four coupling points | D-36, D-47, D-48, D-50 |
| 04 | [Two clocks](diagrams/04-two-clocks.puml) | turns pile up pressure, generations read and choose | D-39, D-52 |
| 05 | [Line score](diagrams/05-line-score.puml) | b, p, s, hysteresis, old threshold for one line | D-48, D-49 |
| 06 | [Six axes](diagrams/06-six-axes.puml) | the six axes and their poles | D-49 |
| 07 | [Outside blend](diagrams/07-outside-blend.puml) | world part + realms part for the upper lines | D-45, D-49 |
| 08 | [Profile](diagrams/08-profile.puml) | temperament + ego over five families | D-31 |
| 09 | [Pick effects](diagrams/09-pick-effects.puml) | what a most / least mark does to line and profile | D-32, D-35 |
| 10 | [Era policy](diagrams/10-era-policy.puml) | from "most" picks to injected policies | D-44 |
| 11 | [Trigram climate](diagrams/11-trigram-climate.puml) | lower = yield modifier, upper = connection factor | D-47 |
| 12 | [Ego symptoms](diagrams/12-ego-symptoms.puml) | five families → five phenomena on Unciv | D-50 |
| 13 | [Unciv integration](diagrams/13-unciv-integration.puml) | reads, the write path, hooks known and to place | D-43, D-52, Q-46 |
| 14 | [Worked example](diagrams/14-worked-example.puml) | HG 12 → HG 33 / HG 56, antipode HG 11 | Appendix A.6 |
| 15 | [Standstill and collapse](diagrams/15-standstill-collapse.puml) | monopolization → Standstill → collapse | D-19, Q-25, Q-45 |
| 16 | [LLM side](diagrams/16-llm-side.puml) | advisor, rival code, concept library, later version | D-26 … D-30 |
| 17 | [Concept pipeline](diagrams/17-concept-pipeline.puml) | library → pricing → unlock by tags | D-12, D-30, Q-42 |
| 18 | [Visibility](diagrams/18-visibility.puml) | what the player sees, what stays hidden | D-17, D-23 |
| 19 | [Question priority, core](diagrams/19-question-priority-core.puml) | dependencies of the P1 and P2 questions | §5 |
| 20 | [Question priority, rest](diagrams/20-question-priority-rest.puml) | dependencies of the P3 questions | §5 |
| 21 | [Decision lineage, engine](diagrams/21-decision-lineage-engine.puml) | superseded engine and LLM decisions | §3 |
| 22 | [Decision lineage, soul](diagrams/22-decision-lineage-soul.puml) | "refined by" chains of the soul mechanics | §3 |
| 23 | [The Mirror](diagrams/23-mirror.puml) | end triggers, the six portrait layers, in-game portrait + profile export | D-53, Q-49 |

### Core engine (D-24, D-48)

Diagram 01. The body casts the lines; the player picks on them; profile and random effects add pressure; the soul acts back on the body through climate and era policy.

### Fate pipeline, once per generation (D-34, D-35, D-39, D-48)

Diagram 02; the line score inside it in detail: diagram 05; what each mark does: diagram 09.

### Body and soul (D-36..D-40)

Two clocks (D-39): the body runs every **turn** (build, research, move, fight), the soul moves every **generation** (hexagram change, most/least picks, profile update). Between two generations the hexagram stands, so its trigrams act as a stable climate the player plans around. Diagrams 03 (coupling points) and 04 (clocks).

### The I Ching as a state space (D-46, 2026-11-17)

The 64 hexagrams are the vertices of a **6-dimensional hypercube (Q6)**: each hexagram is a 6-bit state, each line one bit.

- **One changing line = one edge.** Every hexagram has exactly 6 neighbours; the space has 192 edges.
- **Number of changing lines = distance.** The distance between two hexagrams is their Hamming distance, at most 6. A collapse (many lines changing at once, D-33, Q-32 b) is a long jump in this space.
- **Opposites sit at the maximum distance.** HG 1 ↔ HG 2, and **HG 11 Peace ↔ HG 12 Standstill**: all 6 lines differ. Peace and the hegemon trap of D-19 are antipodes on the map.
- Historical note: Leibniz recognised binary numbers in the hexagram order (1703).

Diagram 14 shows one step through this space (the worked example of Appendix A.6). For the engine this gives a ready-made metric: "how far is this realm from Peace / Standstill" is a Hamming distance, usable for line odds (Q-26) and for showing a Standstill coming (Q-25).

### Engine notes: hidden soul state in Unciv (D-41, 2026-11-17)

Overview of reads, writes and hooks: diagram 13; what stays hidden: diagram 18.

The hexagram lines, ego and temperament can live as **stockpiled resources** that the player never sees (D-23). Whether they do, or live as own state in the save, is open: Q-46. Checked in the code: a resource is kept out of the UI by these uniques/settings:

| Leak point | Hidden by |
|---|---|
| World screen top bar, city screen, Resources overview, City overview | `Not shown on world screen` |
| Civilopedia | `Will not be displayed in Civilopedia` |
| Trade / diplomacy screen | `Cannot be traded` |
| `AiStatusExporter` (advisor paste, D-29) | resource type **Bonus**: the exporter lists Strategic and Luxury only |
| Effect lines under an event choice button (e.g. "Instantly gain [1] [Ego]") | `<hidden from users>` modifier on each effect unique |

Not checked yet: notifications triggered by gains, and the save file (plain JSON, readable by anyone who looks; acceptable).

**Era policy (D-44) in Unciv**, checked in the code on 2026-11-17:

- Unciv has timed uniques (`for [N] turns`, `UniqueType.ConditionalTimedUnique`), stored in `Civilization.temporaryUniques` and read by `Civilization.getMatchingUniques`. A fixed N does not fit, because a generation can end early (D-39).
- `TemporaryUnique.endTurn()` only counts down entries with `turnsLeft >= 0`, so an entry with **`turnsLeft = -1` never expires**. The Fate engine adds its policy uniques that way and replaces them itself at the generation change, recognising its own entries by `uniqueObject.sourceObjectName`. No upstream file needs to change (D-43); the list is already saved with the game.
- Catch: the public constructor expects a `for [N] turns` modifier and the source fields are private. Solve it at implementation time with a small own factory or a minimal marked hook.
- The ego symptoms (D-50) use the same path: one injected unique per symptom, the phenomenon name ("Waste", "Unrest", …) as source name. Upstream hooks known so far: a marked factor in `TechManager.getScienceModifier` (Pride, D-50). Still to be placed: the generation clock (a turn hook), the generation length option at game setup (D-52, Q-48), the Choice of Fate screen (a UI hook, as `AiExportUIInjector`), saving the soul state (Q-46), the Soul language setting (D-55), and possibly a `TemporaryUnique` factory.
- Visibility: no screen lists `temporaryUniques`. The city stat breakdown labels a bonus by `getSourceNameForUser()` + `sourceObjectName`. If the source name is **the keyword itself**, the player sees e.g. "Let it ripen: +10% Food": immediate feedback (P5) without leaking family or fit (D-23).

## 5. Open questions & tensions

Grouped by the session that should resolve them (§7). Each open question carries its priority tag (`P1` … `P4`) from the table below; dependency diagrams: 19 (P1, P2) and 20 (P3).

### Priority by impact (2026-11-18)

**Impact** = how many other open questions and decisions the answer would change, judged by hand from the links in this document (correct a link and the score moves). Tiers: **P1** ≥ 6, **P2** 4–5, **P3** 2–3, **P4** 0–1. Clusters to discuss together: **RPG** (Q-01, Q-07, Q-12, Q-21, Q-22), **Standstill** (Q-25, Q-45, Q-11, Q-32, Q-47), **Ruleset** (Q-33, Q-39, Q-44, Q-15), **Cooperation** (Q-06, Q-14, Q-40).

| Tier | Question | Impact | Changes |
|---|---|---|---|
| ~~P1~~ | ~~Q-19 Audience~~ | — | resolved by D-54 (2026-11-18) |
| P1 | Q-42 Concept in Unciv terms | 7 | Q-10, Q-11, Q-20, Q-33; D-12, D-30, D-36 |
| P1 | Q-01 Who is the human | 6 | Q-03, Q-07, Q-12, Q-20, Q-22; D-15 |
| P1 | Q-33 First ruleset | 6 | Q-15, Q-20, Q-39, Q-44; D-37, D-42 |
| P2 | Q-46 Soul state in the save | 5 | Q-20; D-44, D-48, D-49, D-50 |
| P2 | Q-21 Ego illusion: on what scale | 5 | Q-07, Q-14, Q-22, Q-27; D-31 |
| P2 | Q-25 Standstill and collapse | 5 | Q-11, Q-18, Q-32, Q-35; D-19 |
| P2 | Q-22 What RPG means | 5 | Q-01, Q-07, Q-12, Q-21; D-15 |
| P2 | Q-06 Realm interaction | 5 | Q-14, Q-15, Q-26; D-47, D-48 |
| P2 | Q-49 The Mirror's end triggers | 5 | Q-03, Q-18, Q-39; D-02, D-23 |
| P2 | Q-45 Standstill trap | 4 | Q-11, Q-25, Q-32; D-19 |
| P2 | Q-03 Flow goals | 4 | Q-18, Q-20, Q-25; D-02 |
| P2 | Q-07 Fractal hexagrams | 4 | Q-12, Q-21, Q-22, Q-27 |
| P2 | Q-14 Cooperation | 4 | Q-06, Q-40; D-44, D-45 |
| P2 | Q-27 Profile numbers | 4 | Q-30, Q-32; D-33, D-34 |
| P2 | Q-51 The author as player | 4 | Q-08; D-23, D-32, D-53 |
| P3 | Q-50 Synthetic playtests | 3 | Q-32, Q-38; D-20 |
| P3 | Q-40 No others vs. treaties | 3 | Q-14; D-45, D-50 |
| P3 | Q-39 Test-off vs. design-out | 3 | Q-33; D-42, D-51 |
| P3 | Q-44 Rivals' body | 3 | Q-16, Q-20; D-28 |
| P3 | Q-47 Pressure carry-over | 3 | Q-25, Q-46; D-48 |
| P3 | Q-48 Generation length in numbers | 3 | Q-20, Q-38; D-52 |
| P3 | Q-32 Turn edge cases | 2 | Q-08; D-34 |
| P3 | Q-10 Tag vocabulary | 2 | Q-11; D-30 |
| P3 | Q-11 Concept decay | 2 | Q-25, Q-35 |
| P3 | Q-12 People and generations | 2 | Q-07, Q-22 |
| P3 | Q-15 How war feels | 2 | Q-06, Q-33 |
| P3 | Q-18 Narrator | 2 | Q-20; D-02 |
| P3 | Q-08 Keyword source | 2 | Q-32; D-32 |
| P4 | Q-26 Weighting (remaining) | 1 | Q-38 |
| P4 | Q-35 Culture drain | 1 | D-50 |
| P4 | Q-41 Four resources | 1 | D-11 |
| P4 | Q-43 Unciv automation as Governor | 1 | D-13 |
| P4 | Q-16 Rival count | 1 | Q-20 |
| P4 | Q-30 Advisor profiles | 1 | D-29 |
| P4 | Q-04 Paste: ritual or chore | 1 | D-29 |
| P4 | Q-38 Axis constants | 0 | playtest values only |
| P4 | Q-52 README names the I Ching | 1 | D-56 |
| P4 | Q-20 Smallest playable slice | 0 | the sink: it collects all others |

### Player & loop
- `P1` Q-01 Who is the human? A catalyst/meme inside the society (session 1), the ruler of one realm picking keywords (I Ching sessions), or the "Bridge" serving the LLMs? Possibly all three. If so, which one is the *main* experience?
- ~~Q-02 Does the human play by the same interface as the LLMs?~~ Resolved by D-26 + D-28: one engine, one profile per realm; the human picks in the UI, rivals pick in code.
- `P2` Q-03 Flow needs *clear goals + immediate feedback*. Without winning, what is the per-turn and per-era "goal-shaped thing"? (Journal milestones? Discovering all 64 hexagrams? Self-set intentions?) *Input from D-53: a game now has an end and a closing reflection (the Mirror).* *Input from D-54: the goals must carry the builder first; no onboarding goals are needed before a release.* **Working hypothesis (2026-11-18): "ego brings, flow keeps."** The user: only the ego will play this, only the ego is willing to spend money (and time is money) on it; and the motivating power of flow is a big question. Reading: the game applies its own tempting / fitting structure (D-32, D-46 a) to itself. The **ego brings** the player in: it pays now (curiosity, "what does it say about me", the familiar Civ frame); the Mirror (D-53) is the strongest such hook, and it is fine to use it (D-18: the poison is raw material). **Flow keeps** the player: it is autotelic but weak at recruiting (nobody searches for "a flow game"), and fragile: without clear goals and immediate feedback it breaks, which makes this question more urgent. Where the ego hook is shown to the outside: D-56.
- `P2` Q-49 The Mirror's end triggers (D-53). (a) **Mirror on request:** does asking for the Mirror end the game? If not, the hidden profile leaks mid-game (D-23) and the picks can be optimised (D-32). Options: it always ends the game (= Retire with a portrait); a mid-game Mirror that shows only past eras, at most once per era; a blurred mid-game Mirror. (b) **Unciv end conditions vs. D-02 and Q-39:** victory types are ruleset JSON (`VictoryTypes.json`: milestones, `victoryString`, `defeatString`), so their texts can be rewritten without code. The **Time** type ("Have highest score after max turns", `GameParameters.maxTurns`) comes closest to a set game length, but it still names a winner by score. Defeat = `Civilization.isDefeated()`. Unciv's `oneMoreTurnMode` lets play go on after the end screen (fits D-19: the game continues). Which types stay, and as what: a reframed end rather than a win? (c) Where the Mirror screen hooks in: beside `VictoryScreen`, which `WorldScreen` pushes on victory or defeat; a marked hook (D-43).
- `P4` Q-43 Auto-Governor via Unciv (D-13, D-38, D-51): Unciv already has city and worker automation. Can it serve as the Gardener play style later, steered by the era policy, instead of an own Governor?
- `P4` Q-04 Does the copy-paste **feed** flow (ritual) or **break** it (chore)? At what count does it become a chore? *(Since D-29 this is the optional, one-way advisor paste, at most once per generation.)* *D-54: the kit is provider-neutral, so the paste must work with a plain Markdown block, not a provider-specific feature.*

### I Ching mechanic
- ~~Q-05 Turn granularity.~~ Resolved by D-39: turns (economy) and generations (Choice of Fate); no calendar years, only eras.
- `P4` Q-26 How does the weighting work (D-21)? *Largely resolved by D-48: the line score s = w · b + (1 − w) · p is the situation → per-line function; what remains is the axes and thresholds (Q-38) and the pressure sources' sizes. Earlier: partly resolved by D-34 (pipeline) and D-22/D-32 (DISC-like = forced choice between fitting and tempting keywords).* Original text: Candidate pipeline: *situation* (disturbing emotions, cooperation, monopoly) → per-line odds of becoming a changing line → random draw marks the "trembling" lines → player picks among them through keywords. Note: the traditional casting methods are already non-uniform (yarrow stalks vs. coins give different odds for old yin/old yang), so the odds are a natural tuning knob. Idea: in Standstill lines get "stuck" (low odds) while pressure builds; collapse = many lines changing at once. Open: what does "like the DISC test" mean for the player's choice (forced choice of most/least-fitting words?), and does the choice profile feed back into the weights? *Inputs added 2026-11-17: Water above = risk of the trade flow as line pressure (D-47); Hamming distance to HG 11 / HG 12 as a possible odds term (§4 "The I Ching as a state space").*
- `P2` Q-27 Profile feedback (D-22, D-23): axes settled by D-24, structure by D-31; flexibility = derived (D-33); spiral guard = flow channel (D-33). **Still open:** the exact f(1 − ego) and the ego step sizes per pick; is the profile fractal (character / group / realm, same two numbers at every scale)? Working assumption: yes.
- `P3` Q-32 Turn edge cases and content balance (D-34): (a) Standstill: few lines tremble, how is the pressure shown? (b) Collapse: 5–6 trembling lines would mean 10–12 keywords. Proposal (not decided): fate takes most of them, the player steers only 1–2 lines. (c) Family balance over the 384 lines: no family should be nowhere or everywhere, and the share of same-family lines (7 of 24 in the sample) should be set on purpose. (d) Numbers: trembling count per generation, minimum, cap.
- `P3` Q-47 Pressure carry-over between generations (D-33, D-48): p is reset at every generation change (reasons: D-48 notes). Ego rigidity does **not** need a carry-over: ego persists in the profile, so its push toward the current pole comes back every generation. Open: must some **event** pressure build over several generations, e.g. what builds the pressure in Standstill (Q-25)? Option if yes: **decay instead of reset**, p ← λ · p with λ ∈ [0, 1) (λ = 0 = today's reset). Costs: one more constant; the early trigger (D-39) must then compare only the pressure added this generation, or it fires right after a generation change; the carried p must survive save/load (Q-46). Working assumption: reset, until a playtest shows a slow build-up is missing.
- ~~Q-28 What are the "random effects"?~~ Resolved by D-25.
- ~~Q-29 Do LLM realms have a profile too?~~ Resolved by D-26.
- `P4` Q-30 Advisor & LLM profiles (D-26): (a) If the human follows the advisor's suggestion, whose profile moves? Working view: following advice is itself a choice, so the human's. ~~(b) Does the advisor have a profile of its own?~~ Yes, D-27. Still open: can the player pick/switch advisors, and does the advisor's profile drift too? ~~(c) persona drift~~ and ~~(d) bridge load~~: largely solved by D-28 (rivals are code, profile is an input) and D-29 (one optional, one-way paste per generation). Remaining: since the game never sees the advice (D-29), the advisor's profile cannot drift in-game; acceptable? *D-54: the persona files are provider-neutral Markdown.*
- ~~Q-31 Who invents new concepts now?~~ Resolved by D-30: (a) library now, (c) maybe later. Original text kept for context: D-12's Valuation Engine assumed rivals invent concepts live. With coded rivals (D-28), options: (a) a concept library pre-generated by an LLM at development time, rivals pick from it by tags; (b) the human imports a concept proposed by their advisor (a small, validated `NEW_CONCEPT` paste back into the game); (c) both. Without some channel the "infinite evolution" idea shrinks to a finite library.
- `P4` Q-38 The six axes of D-48. *Mostly resolved by D-49 (axes, readings, world/realm blend, hysteresis, no profile shift of thresholds). Still open: the constants k, x₀, h and the old threshold per axis (playtests), and the result of the coverage test (Appendix D.6).*
- `P2` Q-06 How do realms' hexagrams **interact**? (Trade/war/contact pushing lines in the neighbour's hexagram.) *Candidates moved here by D-47 from the old trigram draft: Heaven above = monopoly pressure on neighbours; Thunder above = sudden contact, border friction; Fire above = neighbours copy and envy.*
- `P2` Q-07 "Fractal": is there also a hexagram per **city / group / person**, not only per realm? *Since D-48 a city hexagram is cheap: read the same six axes for one city.*
- `P3` Q-08 Keyword source: own phrasing derived from public-domain translations (e.g. Legge, 1882)? Some later translations are still under copyright. *D-54: own phrasing in English **and** Hungarian, each language balanced on its own; copyright matters because a public release stays open. Hungarian public-domain sources: still to check. Who reads the mapping: Q-51.*

### Evolution & concepts
- ~~Q-09 "No religion" vs. mythology/ritual as social evolution.~~ Resolved by D-03 (revised): religion may emerge, it is not a mechanic.
- `P2` Q-21 Ego illusion as a mechanic: *partly resolved by D-31 (ego = one scalar per profile) and D-33 (it shows as loss of agency).* Still open: what is it measured on (person / group / realm, fractal?), what does it drive (status, in-group vs. out-group (a symptom only, never a rule: D-45), conflict, belief systems), and how does it dissolve? Does it link to the hexagram (e.g. yang-excess = ego inflation)?
- `P4` Q-41 The four resources (D-11) vs. Unciv's stats (D-41, D-51): the player sees Food, Production, Gold, Science, Culture, Happiness. Working proposal: Material, Wealth, Knowledge, Culture are an **internal grouping** for the readings (D-49) and the design, not UI terms. Confirm?
- `P1` Q-42 What is a concept in Unciv terms (D-12, D-30, D-36)? "Research unlocks concepts from the library": is a concept a tech, a building, a policy, a unit, or any ruleset object? Decides what the library generates (a ruleset?) and what the tag vocabulary (Q-10) must express.
- `P3` Q-10 What is the **tag vocabulary**? It *is* the real rulebook: the LLM can only invent what tags can express.
- `P3` Q-11 Can concepts **die, decay, or be forgotten** (entropy vs. syntropy)? That's the name of the game, after all.
- `P3` Q-12 Biological evolution is out. What remains of the session-1 "generations" idea? Do people age, and do generations exist at all?

### World, communication, cooperation
- ~~Q-23 Which disturbing emotions, and can they transform?~~ Resolved by D-31, D-32: the classic five; transformation is continuous (ego ↓ through fitting picks and breaks), not a one-off event; hidden from the player (D-23) and therefore also absent from the advisor's player view (D-29). Original text: Candidate: the classic five (confusion, attachment/greed, anger, pride, jealousy), each with a group-scale symptom (dogma, hoarding, feud/war, chauvinism, rivalry/sabotage). In the Vajrayana view each one transforms into a wisdom, which fits "everything is good" and P2 (extremes flip). Is transformation a game event? What triggers it? Hidden variables vs. something the LLM sees in `STATE.md`?
- ~~Q-24 What happens at monopolization?~~ Resolved: (b) Standstill + collapse, see D-19.
- `P2` Q-25 Mechanics of Standstill & collapse: how long can a realm stay in Standstill, what builds the pressure (hidden disturbing emotions?), what does the collapse produce (successor realms, a dark age, lost concepts, cf. Q-11), and does the player play *through* it or watch it? Is monopoly measured per realm only, or fractally (a person, a guild, a belief monopolizing a group)? How does the player *see* it coming? *Whether pressure must carry over between generations for this: Q-47.*
- `P2` Q-45 Standstill trap vs. the body reading (D-19, D-48): D-19 says the hegemon can leave Standstill **only through collapse**; D-48 makes the hexagram a reading of the body, so the realm leaves HG 12 as soon as its body changes. Either a body-side mechanism makes the trap real (what holds the hegemon in that state?), or D-19 is rewritten. Links Q-25.
- `P2` Q-22 What does "RPG" mean here? Options: (a) the player *is* one character (hero/sage/catalyst) inside the society; (b) a cast of named characters with social roles, traits and growth (session 1); (c) both. Strongly coupled to Q-01, Q-07 (hexagram per person?) and Q-13. *D-54: named characters mean names and texts in two languages.*
- ~~Q-13 Does the map with units survive the pivot?~~ Resolved by D-37: yes, a simple hex map with cities.
- `P1` Q-33 The body's first ruleset (D-36, D-37, D-42, D-51): which Unciv features stay on in the first SYNTROPY ruleset, and which are switched off until brainstorming brings them in? (a) Food: settled by Unciv, a separate stock that drives growth, counted into Material in the readings (D-41, D-49). (b) Terrain and city radius: Unciv's defaults unless a reason appears. (c) Units: which Unciv units stay (settlers, workers, scouts, military). Links Q-15.
- ~~Q-34 Era policy without the Governor (D-38).~~ Resolved by D-44 (modifier set per family and fit, one generation, ricochet through ego and line pressure only) and D-45 (no partner category).
- `P4` Q-35 Culture has no direct ego drain (D-40). Intended (Culture = memory, hit by collapse, cf. Q-11, Q-25), or should one family drain it?
- ~~Q-36 Trigram → yield table.~~ Resolved by D-47 (lower = yield modifier, upper = connection factor); magnitudes left to playtests.
- ~~Q-37 Ego symptoms on the Unciv body (D-40, D-41).~~ Resolved by D-50: Waste, Unrest (empire happiness), "We have nothing to learn" (known-tech discount, one hook), Hoarding (trade-route gold), Rivalry (research agreements + connection bonus); injected by the Fate engine with the phenomenon as source name.
- `P2` Q-14 Cooperation: is it emergent (session 2) or a mechanic with numbers (session 1: logarithmic group effect, overlapping coalitions)? *Constraint since D-45: no partner category; cooperation is measured on contact (and maybe its intensity), never on an ally list.*
- `P3` Q-40 "No others" vs. Civ V diplomacy (D-45, D-50, D-51): Unciv's diplomacy is a friend/foe system (Declaration of Friendship, denouncing, defensive pacts), and research agreements are treaties with one realm. D-50 and Appendix C build on research agreements. Working reading: a treaty is an **act** the player may take; only **our** bonuses must not depend on a status. Confirm, or keep treaties out of the soul's rules?
- `P3` Q-15 Combat exists in Unciv and already counts in the design (D-45: war included; D-49: line 4). Open: how does war "feel" in a no-lose game, and which parts of Unciv's combat stay on in the first ruleset (Q-33)?

### Unciv integration
- `P3` Q-39 Two kinds of switched-off features (D-42, D-51): some Civ V features are off **for testing** ("off until brainstormed in"), others are out **by design**: religion (D-03), victory types and the spaceship (D-02). D-42's list mixes both. Proposal: mark each feature in the ruleset plan as "test-off" or "design-out". *Since D-53 victory types may come back as end triggers (Q-49 b).*
- `P3` Q-44 Rivals' body (D-28, D-41): the rivals' soul is our code (most/least picks), but who plays their body (builds, war, diplomacy)? Working assumption: Unciv's own AI (`NextTurnAutomation`), steered by the era policy and the symptoms like any realm. Confirm.
- `P2` Q-46 Where the soul state lives in the save (§4 Engine notes, D-49, D-50): ego, temperament, the generation-start snapshot and the piled-up pressure must survive save/load. Options: (a) hidden stockpiled resources (no upstream change, numbers only); (b) own state in the save through a marked hook; (c) both. `temporaryUniques` already persist, but only the injected effects, not the state behind them.
- `P3` Q-48 Generation length in numbers (D-52): (a) the presets (working values 3 / 5 / 8 turns, minimum 3) and whether the default follows Unciv's game speed (`GameParameters.speed`, `Speed.modifier` in `Speeds.json`); (b) how the pressure is normalised: each event's push scaled by 1 / length, or the early-trigger threshold scaled with the length; (c) where the setting lives: per game, a marked hook in `GameParameters` + the new-game screen (D-43), or a ruleset constant (no hook, but the same for every game). The Q-38 constants are tuned at the default length.

### LLM side
- `P4` Q-16 How many rival realms? *(Rewritten 2026-11-17: rivals are code, D-28, so diplomacy between them runs inside the engine, not via MD messages bridged by the human.)* *D-54: no audience pressure on the number; the bot-only test harness (Q-50) may want more rivals than the slice.*
- ~~Q-17 Determinism vs. LLM randomness: does a save store the LLM transcripts so a replay is exact?~~ Resolved by D-28 (rivals are deterministic code, no transcripts) and D-20 (fixed seed for tests/replay).
- `P3` Q-18 Does the LLM also play the **narrator** (Journal prose), or only the rivals? *Partly answered by D-53: the Mirror's full prose is written by the player's own LLM from the profile export.* *D-54: whatever prose the game writes itself (Journal, in-game portrait) is needed in English and Hungarian, so templates are cheaper than free prose.*

### Meta
- ~~Q-19 Who is the audience: you alone (flow while building), a few friends, or the public?~~ Resolved by D-54: the builder first, maybe a public release later; English + Hungarian; provider-neutral advisor kit.
- `P4` Q-52 Does the README (D-56) name the I Ching? D-08 keeps it out of the game itself; in the README it may be the hook ("a 4X whose state machine is the I Ching") or stay a discovery left to the player ("an ancient map of how situations change"). The user: TBD.
- `P3` Q-50 Synthetic playtests (D-54: n = 1 until a release). The coded rivals (D-28) are deterministic functions of (state, profile), so **bot-only games** with fixed seeds (D-20) and spread-out profiles could run many times as a test harness for the engine's dynamics: are all 64 hexagrams reachable (Appendix D.6), how often does Standstill happen, does ego spiral or stay in the flow channel (D-33), how many lines tremble per generation (Q-32 d). They measure the **engine, not the flow** (P5 still needs the human). Open: worth building early (a headless runner beside `tests`), or only after the slice? Which numbers count as a pass?
- `P2` Q-51 The author as player (D-54). The only player authored the family verbs (Appendix A.1) and maybe reads the 768 keywords' family / fit table, so the hidden profile (D-23) could be optimised from knowledge, not choice (D-32), and the Mirror (D-53) would portray the author's knowledge of the rules. Options: (a) **blind content pipeline**: the keywords are generated by an LLM from the family verbs (as D-32 already says), validated by tests, and the user never reads the mapping table; (b) **accept it, P6 argument**: knowing a temptation does not remove it, the tempting column pays now in the economy (D-46 a), and with 768 shuffled keywords without line numbers (D-34) memorising is unrealistic; (c) both. Links Q-08 (who phrases the keywords, in two languages).
- `P4` Q-20 What's the **smallest playable thing** that already produces flow? Candidate (revised 2026-11-17 for D-28, D-37, D-39, D-41): 1 human realm + 1 coded rival, a small hex map in Unciv, a generation every ~5 turns, hidden profile, the body reading (D-48, D-49), coupling points (1) climate and (2) era policy of D-36, Journal; ego symptoms (D-50) optional. *(Old candidate: 1 LLM realm, no map, 20 turns.)* *D-54: the slice is for the builder only, in English and Hungarian; no tutorial, installer or Android build in it.*

## 6. Parked ideas (from earlier sessions, not yet accepted or rejected)

- Telephone-game distortion of information over distance; communication tech ladder (face-to-face → smoke → runners → writing).
- Overlapping coalitions; logarithmic group efficiency; collective-intelligence-only discoveries.
- Trauma & Learning: society reacts to events; political pendulum where extremes flip into their opposites. *(Note: this is literally an I Ching "old yang becomes yin" changing line. Strong link to D-08.)*
- Cultural memory: past eras limit or open present options.
- Meme evolution: ideas mutate as they spread.
- Emergence detector / pattern recognizer that names unique events.
- Simple element layer for the player (Stability / Momentum / Flow) over the 8 trigrams.
- Hexagram as diplomacy mood: AI realm in "Conflict" ignores gold.
- Art: minimalist, earth/pastel palette, "breathing" UI. Audio: generative ambient, silence allowed.
- Logo: a hexagram resolving from chaos to order, left to right.
- **Later version: LLM realms and concept invention** (the user, 2026-11-18: "would be really cool"). LLMs play the other realms live and invent new concepts (technologies, customs, units), priced by the Valuation Engine (D-12). Pattern to study: component-based, moddable unit design, as in *Alpha Centauri*'s unit workshop. Deferred for now by D-28 (rivals are code) and D-30 (pre-generated library).
- Advisor concept import (D-30, deferred): the player pastes a validated `NEW_CONCEPT` block proposed by their advisor back into the game; the Valuation Engine prices it. The only return channel from the LLM side.
- **Big Five translation of the Mirror** (D-53): the portrait also mapped to Big Five terms for the curious, as an option. The user, 2026-11-18: wanted, not urgent.
- Non-linear time scale (as in Civ: early eras span more time per turn). Not needed while only eras are shown (D-39); revisit if eras feel uneven.
- Old phase plans (Gantt) from the source session: Protocol Design → Unity Foundation → Evolution & Emergence → Refinement & Flow. They are implementation plans, so they're out of scope for now.

## 7. Session roadmap (proposal)

| # | Session | Resolves | Output |
|---|---|---|---|
| S1 | **Player role & the core loop** | Q-01, Q-03, Q-04, Q-43, Q-49, Q-51 (Q-02, Q-19 closed) | One-paragraph "what the player does" + loop diagram v2 |
| S2 | **The Hexagram engine** | Q-06..08, Q-47 (Q-05 closed) | Rules for lines, keywords, realm interaction, fractal scale |
| S3 | **Evolution & concept economy** | Q-10..12, Q-41, Q-42 (Q-09 closed) | Tag vocabulary draft, decay rules, 4-resource roles |
| S4 | **World & cooperation** | Q-14, Q-15, Q-40, Q-45 (Q-13 closed) | Communication & cooperation model |
| S5 | **Rivals & narrator** | Q-16, Q-18 (Q-17 closed) | Rival count, persona/profile list, who writes the Journal prose. *(Was "The LLM table"; mostly obsolete since D-28, D-29.)* |
| S6 | **Feel** | — | Art/audio/UI mood board, the "generation ritual" |
| S7 | **First playable slice** | Q-20, Q-50 | Scope of the first Unciv build (was: paper/LLM prototype) |
| S8 | **Unciv integration** *(added 2026-11-17)* | Q-33, Q-39, Q-44, Q-46, Q-48 (Q-34, Q-37 closed) | Where the soul state lives, how each D-36 coupling point maps to uniques or hooks, which Civ V systems stay (D-42, D-51), rivals vs. Unciv AI |

Cheap trick worth trying early (still no code): **play a paper prototype in chat**. Draw a hexagram with coins, offer keywords, let an LLM play the rival. One evening of that answers more of Q-01..05 than a week of debate.

## 8. Inspirations (to study, not to copy)

- **Books/ideas:** Nishargadatta Maharaj, *I Am That* (1973): non-duality; "there are no others" is the root of D-45. David Loy, *The Great Awakening: A Buddhist Social Theory* (2003): the three poisons as *institutionalized* group-level forces. Kept as a **counterpoint**: the user rejects it (D-18: institutions are wisdom deflected by ego); Csikszentmihalyi, *Flow*; James P. Carse, *Finite and Infinite Games* (the source of "the infinite game"); Prigogine & Stengers, *Order Out of Chaos* (heads-up: the subtitle nearly repeats this book title); Fantappiè's syntropy; Eno & Schmidt's *Oblique Strategies* (a card deck of cryptic prompts, the nearest relative of "keywords as fate").
- **Game patterns:** story generators that chronicle emergent history; minimalist choice-card games; non-violent, meditative builders; "social engineering" sliders in 4X games. Discuss which *patterns* we want, not which games.
- **Name check:** "Syntropy" is also a term in agriculture (syntropic farming) and may be used by other products. Search before committing.
- *HallucInc notes* (deleted 2026-11-17): a Reddit post (r/ClaudeAI, "I built a free browser game with Claude Code") on a CLAUDE.md and sub-agent setup for an AI-built game. **Where it overlaps with `CLAUDE.md`, `CLAUDE.md` wins** (start-of-session reading = "re-read `.doc/` at every phase"; gated milestones = the 4-phase workflow with a stop after each phase; commit cadence = "commit only when the user asks", not "after each change"). Only one non-overlapping idea is kept for implementation: "same seed + same choices = same run" as a never-break rule (cf. D-10, Q-17). Its sub-agent workflow is **rejected**: no sub-agents in this project.

## 9. Decision log (append-only, newest on top)

- **2026-11-18**: Follow-ups to D-54 from the user. Soul language (D-55): a separate setting so the Choice of Fate can be in the player's native language while the UI is English; everything the soul produces follows it, the body follows the UI language; ID-based storage; switching any time; lookup through `Translations.getText` with an explicit language. Discoverability route (D-56): `.github/README.md` on `zzt_develop` + a separate showcase repo, because GitHub search leaves forks out; not now. New Q-52 (does the README name the I Ching; TBD). Q-03 gets the working hypothesis "ego brings, flow keeps". D-54 points to D-55.

- **2026-11-18**: Next P1 topic, Q-19 Audience, resolved as D-54: the builder first, maybe a public release later (no circle of testers exists, so the proposed "design for a small circle" middle option was dropped); English + Hungarian from the first slice (English source strings, Hungarian as the mod's translation file, keywords balanced in each language on its own); provider-neutral advisor kit. Consequences recorded as new Q-50 (synthetic bot-only playtests, because n = 1) and Q-51 (the author as player: knowing the keyword mapping threatens the hidden profile). Inputs noted at Q-03, Q-04, Q-08, Q-16, Q-18, Q-20, Q-22, Q-30; priority table, roadmap S1, S7 and diagram 19 updated.

- **2026-11-18**: The user's idea: a psychological analysis of the player at the end of a game, from the decisions and the profile; would DISC fit? Agreed as D-53, **the Mirror**: a portrait of the ruler in six layers (temperament, ego path, temptation vs. flexibility, agency, path in Q6, words vs. deeds); end triggers = an Unciv end condition, a Retire button, the player's request; an in-game portrait plus a profile export for the player's own LLM. DISC kept as the question format only (4 factors vs. 5 families, no ego axis, weak validity). Big Five translation parked (§6). New Q-49 (does the Mirror on request end the game; Unciv victory types vs. D-02 and Q-39; where the screen hooks in). Cross-references from D-02, D-23, D-29, Q-03, Q-18, Q-39 and roadmap S1; diagram 23 new, diagram 19 updated.

- **2026-11-18**: Questions on diagram 04 ("Pressure p is reset"). D-52: generation length is set at game setup, ~5 turns stays the default, the pressure is normalised to the length (refines D-39). D-48 notes now say why p is reset (no double counting, no saturation, no stuck trigger). New Q-47 (does pressure need to carry over between generations? option: decay p ← λ · p; working assumption: reset) and Q-48 (presets, game speed coupling, normalisation method, where the setting lives). Cross-references from D-33, Q-25, the engine notes and roadmap S2, S8; diagrams 04, 13, 20, 22 updated.

- **2026-11-18**: At the user's request: diagrams moved out of this file into `diagrams/` (one per `.puml` file, each with a comment header), and 19 new diagrams drawn (22 in all); §4 now holds the index and references only. Open questions prioritised by impact (how many other questions and decisions each answer changes): table at the top of §5, a `P1` … `P4` tag on every open question (replacing the old bold marks), dependency diagrams 19 and 20.

- **2026-11-18**: The design tensions found in the consistency pass become open questions Q-39 … Q-46: test-off vs. design-out features (Q-39), "no others" vs. Civ V treaties (Q-40), the four resources as internal grouping (Q-41), what a concept is in Unciv terms (Q-42), Unciv automation as a later Governor (Q-43), who plays the rivals' body (Q-44), Standstill trap vs. the body reading (Q-45), where the soul state lives in the save (Q-46). New §5 group "Unciv integration"; cross-references from D-11, D-13, D-19, D-28, D-30, D-45, D-51 and the engine notes; roadmap S1, S3, S4, S8 updated.

- **2026-11-18**: Consistency pass after D-51, no new decisions. §1 Vision rewritten for the current model; LLM realms and concept invention parked for a later version (§6, the user: Alpha Centauri-like moddable units). Refined-by marks added to D-08, D-09, D-34 (step 5), D-42; D-22 "this turn" → "this generation"; D-36 (1) and the body/soul diagram now say climate (yield modifier + connection factor); D-37 notes point to Q-33. Appendix C: "− trade" removed from Attachment-tempting (no built-in ricochet, D-44 (4)). Core engine diagram gets the body (D-48). Engine notes list the hooks still to place. Q-15, Q-20 and roadmap S8 updated.

- **2026-11-18**: Civ 1 dropped as a reference (D-51): the body is described in Unciv / Civ V terms only; features are switched off by JSON ruleset for testing and brought in later through brainstorming. D-36, D-37, D-40, D-50 and Q-33 rewritten accordingly; D-50 notes now spell out Q-37's options (a), (b), (c).

- **2026-11-17**: Ego symptoms mapped to Unciv (D-50, Q-37 closed): Waste, Unrest, "We have nothing to learn", Hoarding, Rivalry; intensity = ego × temperament share; injected by the Fate engine like D-44, phenomenon as source name; one upstream hook (Pride, `getScienceModifier`). D-40 marked as refined; Appendix C's Accomplishing-fitting gap closed; engine note added. Q-35 stays open.

- **2026-11-17**: The six axes agreed (D-49, Q-38 mostly resolved): rates and ratios instead of size; Material = trend of food surplus + production; Wealth = spent share of gross income (upkeep included); Expansion = wide vs. tall; upper lines read against the outside = world + realms, blended by v = n / (n + 1), so they mean something before any contact. Hysteresis on the sign, no profile shift of thresholds. Appendix D rewritten (readings, edge cases, climate feedback check, coverage test).

- **2026-11-17**: Key mechanism agreed: the body casts the lines (D-48). Line score s = w · b + (1 − w) · p (body reading as main weight, pressure from events, neighbours and profile as the second); old lines = trembling lines; primary hexagram = the present, relating hexagram = the direction. D-21, D-24, D-34, D-36 (4) marked as refined; fate pipeline and body/soul diagrams updated; Q-26 largely resolved; new Q-38 (the six axes); six-axis draft in Appendix D.

- **2026-11-17**: Why the I Ching, added to D-46: a well-founded state-transition map, used as formalism, not divination; the game as a repeatable experiment. New §4 note "The I Ching as a state space" (Q6 hypercube, Hamming distance, HG 11 ↔ HG 12 antipodes). Trigram climate agreed (D-47, Q-36 closed): lower = one Unciv yield modifier, upper = connection factor for the D-44 fitting bonus; Appendix B rewritten; neighbour-facing effects moved to Q-06 and Q-26.

- **2026-11-17**: Both D-46 consequences agreed: Civ's logic = the tempting column; the reality test becomes meta-pillar P6 (§2).

- **2026-11-17**: D-45 reading confirmed by the user. Ars poetica added (D-46, §1): a model of reality, not a Buddhist/Taoist game; Civ's principles are narrower, not wrong. Two consequences proposed under D-46, not agreed yet.

- **2026-11-17**: Era policy agreed (D-44, Q-34 closed): a modifier set per (family, fitting / tempting) for one generation, only "most" picks set it, no separate ricochet mechanic, fitting grows with connection, tempting is big and immediate. Working values left to playtests. "There are no others" (D-45): no partner category, every contacted realm counts. Unciv form (timed uniques with `turnsLeft = -1`, keyword as source name) added to §4 "Engine notes"; draft policy table in Appendix C. The brainstorming moves to its own branch `feature/game-design-brainstorming`.

- **2026-11-17**: Consistency pass after D-41, no new decisions. Stale references fixed: D-12 (C# → Kotlin), title and D-14 (Flux), D-07 (`PROTOCOL_RESPONSE.md` obsolete), D-26 (⚠ rivals are code, D-28). Q-02 and Q-17 closed, Q-16 and Q-20 rewritten, Q-04 and Q-05 reworded; "per turn" → "per generation" in D-34, Q-30, Q-32. Appendix A.6 recount: same-family lines are 7 of 24, not 4; Confusion does appear in HG 12 (line 5). D-40 flagged against Unciv, new Q-37. Roadmap: S5 renamed, S8 Unciv integration added.

- **2026-11-17**: The Flux design repo is merged into the Unciv fork (`zzt_develop`) with its history; one repo for docs and code from now on. Flux's Apache-2.0 `LICENSE` and `Flux.code-workspace` were left out. `CLAUDE.md` and `.claude/` are not tracked in this repo either: the user keeps them in the AIPrompts repo. The repo name *Flux* in the title line is historical.

- **2026-11-17**: Unciv assessed as engine. Decided: Unciv fork in Kotlin (D-41, D-04's Unity C# dropped); Civ V content trimmed by ruleset only during development (D-42); fork stays unofficial, own code hooks in with minimal upstream changes (D-43). Hidden soul state can be stored as hidden stockpiled resources, see the note under §4 "Engine notes".

- **2026-11-17**: D-39 wording fixed: "turn" = ordinary turn (build, research, move, fight), "generation" = hexagram change + most/least picks + profile update. Turn pipeline renamed to fate pipeline. The two outdated diagrams (LLM-centric "who decides what" and the old one-turn loop) removed from §4; §4 now holds 3 diagrams.

- **2026-11-17**: The body gets designed: Civ-1-like economy coupled to the soul at four points (D-36); simple hex map with cities (D-37, Q-13 closed); hands-on builds and research, Auto-Governor deferred (D-38, D-13 marked); two clocks, turns and generations, eras only, no calendar years (D-39, Q-05 closed); ego symptoms as economic penalties per family (D-40). New questions Q-33..Q-36, body/soul diagram in §4, trigram → yield draft in Appendix B.

- **2026-11-17**: Profile structure agreed: temperament + single ego scalar (D-31); ego measured by situation fit via fitting/tempting keyword pairs (D-32); flexibility derived, flow channel against the spiral (D-33); turn pipeline with trembling lines and fate for unmarked lines (D-34); most + least on one line cancel on the line, add on the profile (D-35). Q-23 closed; Q-21, Q-26, Q-27 narrowed; new Q-32. Turn pipeline diagram added to §4, sample mapping of HG 1, 2, 11, 12 in Appendix A.

- **2026-11-17**: Concepts from a pre-generated, tagged library; advisor import deferred (D-30, Q-31 closed).
- **2026-11-17**: Rivals become LLM-written code from the rulebook (D-28). Human advisor = the player's own LLM project with a shipped advisor kit, one-way state paste (D-29). D-05/D-06 revised, LLM-centric diagram marked outdated. New question Q-31 (concept invention).
- **2026-11-17**: Advisor has its own profile (D-27).
- **2026-11-17**: World events push lines, one rule system (D-25, Q-28 closed). Every realm has a profile; rivals = LLMs with different profiles, humans may use an LLM advisor (D-26, Q-29 closed). Follow-ups in Q-30.
- **2026-11-17**: Core formula agreed (D-24): I Ching state engine × disturbing-emotions/wisdoms profile + random effects + player interaction. Core engine diagram added to §4. New questions Q-28, Q-29.
- **2026-11-17**: Choice of Fate v2: line keywords + situation summary, DISC-like most/least picks, count by flexibility (D-22). Hidden profile from choices feeds back into the pick count (D-23). Open: Q-27.
- **2026-11-17**: Same-seed rule limited to testing/replay; real game may be random (D-20). Hexagram changes weighted by situation, changing lines random and/or chosen (D-21). Mechanism in Q-26. HallucInc sub-agent workflow rejected.
- **2026-11-17**: Source files `Brain storming.md` and `HallucInc.md` deleted by the user; links removed, this document is the canon. HallucInc ideas that overlap `CLAUDE.md` are superseded by `CLAUDE.md`.
- **2026-11-17**: Q-24 resolved: monopolization leads to Standstill → collapse, not game over (D-19 updated). Follow-up mechanics in Q-25.
- **2026-11-17**: Institutions = wisdom deflected by ego (D-18, against Loy). Fail condition introduced: monopolization (D-19, D-02 revised). New question Q-24.
- **2026-11-17**: Disturbing emotions (individual + group) named as the main antagonist of cooperation, shown only through emergent phenomena (D-17). New question Q-23.
- **2026-11-17**: Big-picture walkthrough, station 1. RPG elements restored (D-15). Religion: emergent, not a mechanic (D-03 revised, Q-09 closed). Ego illusion named as a core driver (D-16). New questions Q-21, Q-22.
- **2026-11-17**: Base document created from the old brainstorming. Embedded PNG Gantt charts in the source replaced by PlantUML.

## Appendix A. Line → family mapping, sample (2026-11-17)

Trial run for D-32 on four hexagrams. The keywords are our own phrasing (Q-08), not quotes from any translation. The mappings are arguable by design; the family verbs below are the authoring rule that keeps them consistent.

### A.1 Family verbs (authoring rule)

| Family | Wisdom as a verb | Poison as a verb |
|---|---|---|
| Space (Confusion) | let be, wait, include, not force | ignore, overlook, postpone |
| Mirror (Anger) | see clearly, face it, set a boundary | attack, make an enemy |
| Equality (Pride) | serve, share the credit, treat as equals | rise above, show off |
| Discernment (Attachment) | value, choose well, bond | keep, hoard, cling |
| Accomplishing (Jealousy) | get it done, act together | outrun, compete, claim the credit |

### A.2 HG 1 The Creative

| Line | Situation | Fitting | Tempting |
|---|---|---|---|
| 1 | hidden strength, the time is not ripe | "Let it ripen" → Space | "Strike before the others do" → Jealousy |
| 2 | emerging; whom to follow | "Seek the one worth following" → Discernment | "Go it alone" → Pride |
| 3 | work all day, watchful at night | "Work, and stay watchful" → Accomplishing | "Rest on the success" → Confusion |
| 4 | a leap over the depths: leap or stay | "Test yourself honestly" → Mirror | "Wait until it's certain" → Confusion |
| 5 | at the peak, greatest influence | "Lift others with you" → Equality | "Make them see who leads" → Pride |
| 6 | overreached, regret comes | "Step down from the peak" → Equality | "Climb higher" → Attachment |

### A.3 HG 2 The Receptive

| Line | Situation | Fitting | Tempting |
|---|---|---|---|
| 1 | frost underfoot, ice is coming | "Read the frost" → Mirror | "It's only frost" → Confusion |
| 2 | straight and natural, no effort | "Be what you are" → Space | "Polish the image" → Pride |
| 3 | hidden worth, serving another's cause | "Finish it, let others shine" → Equality | "Make sure it bears your name" → Jealousy |
| 4 | dangerous times, a tied sack | "Keep your counsel" → Discernment | "Tell them what you think of them" → Anger |
| 5 | modesty in a high place | "Wear plain cloth" → Equality | "Dress the rank" → Attachment |
| 6 | the receptive wants to lead, a fight follows | "Step out of the fight" → Mirror | "Win the field" → Anger |

### A.4 HG 11 Peace

| Line | Situation | Fitting | Tempting |
|---|---|---|---|
| 1 | grass pulled up with its roots: the like-minded move together | "Move together" → Accomplishing | "Get ahead of the others" → Jealousy |
| 2 | bear with the rough ones, no factions | "Make room for the rough ones" → Space | "Keep to our own" → Pride |
| 3 | no plain without a slope | "Remember the slope" → Mirror | "This will last" → Attachment |
| 4 | coming down to the neighbour, no boasting | "Come down to the neighbours" → Equality | "Show them what we have" → Pride |
| 5 | an alliance through a humble gesture | "Bind through a gift" → Discernment | "Keep the best for ourselves" → Attachment |
| 6 | the wall falls back into the moat | "Let the wall fall, tend the town" → Space | "Call out the army" → Anger |

### A.5 HG 12 Standstill

| Line | Situation | Fitting | Tempting |
|---|---|---|---|
| 1 | the same uprooted grass: withdraw with your own kind | "Withdraw with your own kind" → Discernment | "Get a seat at the top table" → Attachment |
| 2 | the small adapt, the great endure | "Don't trade your principles" → Mirror | "Make a deal with them" → Attachment |
| 3 | the shame of the usurpers | "Own the shame" → Equality | "Blame the others" → Anger |
| 4 | acting on a true mandate | "Act on a true mandate" → Accomplishing | "Act on your own authority" → Pride |
| 5 | the stagnation eases, but carefully | "Stay wary in the thaw" → Mirror | "It's over, relax" → Confusion |
| 6 | the standstill breaks | "Push it over" → Accomplishing | "Make sure it's your victory" → Jealousy |

### A.6 Observations

1. **The habit measure works.** 11/1 and 12/1 share one image but call for different families ("forward together" vs. "back together"). A player who always moves together is right in one and wrong in the other.
2. **Two kinds of lines.** Same-family lines (1/5, 2/6, 11/1, 11/4, 11/5, 12/1, 12/6: 7 of 24, by accident) test ego; cross-family lines test temperament flexibility. The ratio can be set on purpose (Q-32 c).
3. **A hexagram's temptations tell its story.** Standstill tempts most often with Attachment (2 of 6; no other family more than once) (positioning, deals, comfort near power): the monopolization trap of D-19, unprompted.
4. **Balance is uneven, and that's fine locally.** Confusion is absent from 11 and appears only once in 12 (line 5); Pride tempts often (6 of 24). Check the full 384 globally (Q-32 c).
5. **Worked example (D-34, diagram 14):** HG 12, lines 3 and 5 tremble, 1 most + 1 least. Most = "Own the shame", least = "It's over, relax" → line 3 changes (Earth → Mountain below), line 5 held → **HG 33 Retreat**; ego ↓ twice. Had line 5 been unmarked and flipped by the dice → **HG 56 The Wanderer**.

## Appendix B. Trigram climate (D-47, 2026-11-17)

Structure agreed in D-47; all numbers are playtest values. Diagram 11. The **lower** trigram gives one inner yield modifier (an existing Unciv unique, checked in `UniqueType.kt`); the **upper** trigram gives the connection factor that multiplies the D-44 fitting bonus per connection. The player sees only the climate word (inner / outer) and the effects in the stat breakdown.

| Trigram | Climate word | Inner (lower) → Unciv unique | Outer (upper): connection factor |
|---|---|---|---|
| ☰ Heaven | creative | + 10% Science: `[+10]% [Science]` | × 1 (assertive: speaks, does not listen) |
| ☷ Earth | receptive | + 10% growth: `[+10]% growth [in all cities]` | × 1.5 (open) |
| ☳ Thunder | arousing | + 25% Production on settlers: `[+25]% Production when constructing [Settler] units [in all cities]` | × 1 |
| ☵ Water | flowing | + 10% Gold: `[+10]% [Gold]` | × 1 |
| ☶ Mountain | still | + 25% city strength, − 10% growth: `[+25]% Strength for cities`, `[-10]% growth [in all cities]` | × 0.5 (closed) |
| ☴ Wind | gentle | − 25% improvement time: `[-25]% tile improvement construction time` | × 1.5 (diffusion, P1) |
| ☲ Fire | clear | + 10% Culture: `[+10]% [Culture]` | × 1 (visible) |
| ☱ Lake | joyous | − 10% unhappiness: `[-10]% Unhappiness from [Population] [in all cities]` | × 1.5 (exchange) |

The climate words are working names. Removed from the old draft and moved as line pressure: Heaven above (monopoly pressure), Thunder above (sudden contact, border friction), Fire above (neighbours copy and envy) → Q-06; Water's trade risk → Q-26.

Reading check: **HG 11 Peace** (Heaven below, Earth above) = thinking at home (+ Science), open outward (× 1.5). **HG 12 Standstill** (Earth below, Heaven above) = only growth at home, outward it speaks but does not listen (× 1): the hegemon of D-19. The two are antipodes in the state space (§4).

## Appendix C. Era policy table, draft (2026-11-17)

Draft for D-44 (flow: diagram 10). The effects are directions, not numbers; magnitudes are playtest values (D-44 notes). "Per connection" = per contacted realm (D-45). The tempting column is looked up by the **tempting keyword's** family.

| Family | Fitting (slow, together) | Tempting (now, alone) |
|---|---|---|
| Space / Confusion | + growth, − unrest | + Production % (cutting corners) |
| Mirror / Anger | + defence, more information about neighbours | + combat strength, + production of military units |
| Equality / Pride | + Knowledge per connection | + Culture, flat (prestige) |
| Discernment / Attachment | + Wealth per connection (trade) | + Gold, flat |
| Accomplishing / Jealousy | + Science from research agreements, + connection bonus (D-50) | + Production on wonders (the race) |

Accomplishing-fitting acts on the same shared work that Rivalry (Jealousy, D-50) damages: research agreements and the connection bonus.

The tempting column has no built-in minus: the ricochet comes only through ego and line pressure (D-44 (4)); hoarding, for example, is D-50's Attachment symptom, not part of the policy.

## Appendix D. The six axes (D-49, 2026-11-17)

Agreed in D-49; all constants are playtest values. Diagrams: 06 (axes), 07 (outside blend), 05 (line score). Line positions keep their traditional meaning (bottom = foundation, 2 and 5 = the inner and outer centre, 3 = the threshold, 6 = beyond). Lower lines read the realm itself; upper lines read it against **the outside** (D-45). Neither pole is good or bad (D-18).

### D.1 Lower lines: the realm itself

| Line | Position | Axis | Yang pole | Yin pole | Reading x (per generation) |
|---|---|---|---|---|---|
| 1 | foundation, the land | Material | surplus (the base grows) | sufficiency (lives within its means) | M = food surplus + production, averaged over the turns, ÷ M of the previous generation |
| 2 | inner centre | Wealth | flows out (spends, invests) | gathers (saves) | (G − Δtreasury) ÷ G, G = gross gold income (positive entries of `getStatMapForNextTurn()`) |
| 3 | threshold (inside → outside) | Expansion | wide | tall | W ÷ (W + T), production-cost weighted: W = new cities, tiles, tile improvements, settlers, workers; T = new buildings, wonders |

### D.2 Upper lines: the outside

outside = (1 − v) · world + v · realms, v = n / (n + 1), n = number of contacted realms.

| Line | Position | Axis | Yang pole | Yin pole | Realms part | World part |
|---|---|---|---|---|---|---|
| 4 | meeting the neighbour | Contact | armed beyond its weight | lighter armed than its weight | Force share ÷ Score share among the contacted | own Force vs. the nearby barbarian force (seen units, encampments) |
| 5 | the ruler | Power | dominant | one among equals | Score share vs. the equal share 1 / (n + 1) | own territory ÷ explored land |
| 6 | beyond, the end | Ideas | radiates (ahead of its weight) | absorbs (behind, learns) | (Technologies + Culture) share ÷ Score share | own Science + Culture ÷ discovery (new tiles, ruins, natural wonders) |

Line 4 is the army example of D-18: the yang pole can be deterrence (Mirror: a boundary) or conquest (Anger); the keywords decide which, not the measurement. Line 5, world part: the more a scout discovers, the smaller the realm reads; settlers push it toward dominant.

### D.3 Reading and normalisation

- b = tanh(k · (x − x₀)), with its own k and neutral point x₀ per axis (e.g. line 2: x₀ ≈ 0.75 of income spent).
- Sources, all read-only: `Civilization.getStatForRanking(RankingType)` (Score, Force, Territory, Technologies, Culture…), `stats.statsForNextTurn`, `CivInfoStatsForNextTurn.getStatMapForNextTurn()`, `Civilization.hasExplored(tile)`, `GameInfo.getBarbarianCivilization()`. The Fate engine stores the generation-start snapshot in its own state.
- Sign: hysteresis band h (≈ 0.1). Old threshold: the dice decide near it, weighted by distance (D-48).

### D.4 Edge cases

- **First generation:** line 1 compares against the start-of-game snapshot.
- **Line 2:** spent above 100% (living on reserves) = strongly yang; large trade income can make spent negative = yin, read correctly as gathering.
- **Line 3, conquest:** a captured city counts in W as one settler. Its buildings and improvements count neither in W nor in T: only cities and tiles owned at both snapshots count. Military units are left out (they feed line 4).
- **Nothing built** (W + T = 0) or **barbarians switched off** (line 4 world part): b = 0; pressure decides and hysteresis keeps the sign.
- **City-states** (if the ruleset keeps them, D-42): no own rule, they count as contacted realms in n.

### D.5 Climate feedback check (D-47 lower climate on the lower lines)

| Lower trigram (lines 1-2-3) | Climate effect | Pushes | Direction |
|---|---|---|---|
| Earth (yin-yin-yin) | + growth | line 1 → yang | corrective |
| Thunder (yang-yin-yin) | + settlers | line 3 → yang | corrective |
| Water (yin-yang-yin) | + Gold | line 2 → yin (gathers) | corrective |
| Wind (yin-yang-yang) | − improvement time | line 1 → yang | corrective |
| Mountain (yin-yin-yang) | − growth | line 1 → yin | **reinforcing** |
| Lake (yang-yang-yin) | − unhappiness | line 1 → yang | weakly reinforcing |
| Heaven, Fire | Science, Culture | line 6 (upper) | none on the lower lines |

The climate mostly pushes the body toward its own opposite, so the hexagram keeps moving (the Book of Changes does exactly this). Mountain ("keeping still") holds itself, which fits its meaning; old → flip breaks it in the end (P2).

### D.6 Coverage test (candidate for a future Verification Strategy)

Over many simulated games with fixed seeds (D-20): every one of the 64 hexagrams appears, none dominates, and the correlation matrix of the six b values stays low. This is the experiment side of P6: the model claims six independent axes, and the claim is measurable.

Reading check: **HG 11 Peace** (1–3 yang, 4–6 yin) = the base grows, wealth flows, the realm goes wide at home; outward lightly armed, one among equals, learning. **HG 12 Standstill** (1–3 yin, 4–6 yang) = sufficiency, gathering, building tall at home; outward armed beyond its weight, dominant, radiating without learning: the hegemon of D-19, and line 5 is the monopoly measure itself. **HG 1** (all yang) = overreach everywhere; line 6 "regret" sits where it should.

### D.7 What D-48 gives for free

- **Collapse** = many axes at an extreme at once → many old lines (Q-32 b, D-33).
- **Standstill** = lines held young and rigid: little to choose (Q-25).
- **Fractal** (Q-07): the same six axes can be read for a city.
