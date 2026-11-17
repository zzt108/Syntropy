# Civilization VI AI Status Exporter - Project Plan & Data Summary
*Saved: 2026-03-08*

| Plan Version | Date |
| :--- | :--- |
| CIV6-1-0 | 2026-03-08 |

## Overview
This document outlines the architecture and data requirements for a Civilization VI AI Advisor plugin. It translates the data structure currently used in the UnCiv `AiStatusExporter` into a viable implementation for Civ 6 using a Lua + C# bypass architecture.

## Architecture: Sandbox Bypass
The Civ 6 implementation uses a two-part architecture to bypass the game's Lua sandbox and export data to the OS:

1. **Lua Mod (In-Game Data Collection):** 
   - **Location:** `Documents\My Games\Sid Meier's Civilization VI\Mods\AiExporter`
   - **Files:** Typically requires `.modinfo`, an XML to load the context, and a `.lua` script.
   - **Function:** Reads the game state using Civ 6 Lua APIs (e.g., `Players[localPlayerID]`, `Map`, `Cities`, `Units`).
   - **Serialization:** Formats the collected data into a JSON string.
   - **Output:** Outputs the JSON to `Lua.log` using the `print()` function, prefixed with a unique identifier (e.g., `[AI_EXPORT_DATA] {"turn": ...}`).

2. **C# Application (Log Tailing & Forwarding):**
   - **Function:** Continuously tails the Civ 6 `Lua.log` file (using `FileShare.ReadWrite` to avoid locking issues).
   - **Parsing:** Filters lines containing the unique prefix (`[AI_EXPORT_DATA]`) and parses the JSON payload.
   - **Integration:** Converts the structured JSON into an XML/Markdown prompt format (like UnCiv) and forwards it to the LLM (e.g., via local API, prompt injection, or clipboard).

---

## Exported Data Structure (The "Payload")
To match the intelligence level of the UnCiv advisor, the Lua mod must extract and format the following categories of information into its JSON payload.

### 1. System Context & Instructions
*(Static or periodically sent context to orient the AI.)*
- **Persona:** AI Advisor for Civilization VI.
- **Rules Explanation:** Grid system explanation, purpose of the tactical radar, and overarching goals (tactical, economic, diplomatic advice).

### 2. Global Empire Status
*(Top-level metrics of the player's civilization.)*
- **Game Info:** Current Turn, Current Era, Active Expansions/Ruleset (e.g., Gathering Storm).
- **Treasuries & Yields:** Total Gold and GPT (Gold Per Turn), Science, Culture, Faith per turn.
- **Happiness/Amenities:** Global amenity status or aggregate metric.
- **Era Score/Golden Age:** Current Age (Normal, Golden, Dark) and Era Score progress.
- **Research Status (Science & Civics):**
  - Current Technology being researched and remaining turns.
  - Current Civic being researched and remaining turns.
  - Lists of available and fully researched Techs/Civics.
- **Government & Policies:** Current Government type and active slotted policy cards.

### 3. Religion & Beliefs
*(Spiritual footprint of the empire.)*
- **Pantheon:** Founded pantheon belief.
- **Religion:** Founded Religion name and all adopted beliefs (Follower, Founder, Enhancer, etc.).
- **Majority Religion:** The dominant religion across the empire.

### 4. Map Data & Points of Interest
*(Spatial awareness of the world.)*
- **Visibility:** Sweeps viewable tiles and extracts top prioritized POIs (coordinates `[x,y]`).
- **City Centers:** Player and known foreign cities, including owner and population.
- **Units:** Visible military and civilian units (Player, Enemy, Neutral, Barbarian).
- **Natural Wonders & Resources:** Important geographical features for settling or exploiting.
- *(Note: UnCiv caps this at 50 items to prevent token bloat; the Civ 6 Lua script should prioritize and cap exports similarly).*

### 5. Tactical Radar
*(Immediate threats and opportunities localized around player cities.)*
- **Radius:** Scans 3 tiles outward from each player city center.
- **Data Points:** Identifies Enemy Units, Impassable Terrain (Mountains/Ice), and visible Resources.
- **Vectoring:** Calculates relative direction (N, NE, SE, S, SW, NW) and distance from the city center. This gives the AI spatial context without needing to process a raw visual map.

### 6. City Reports
*(Internal economic breakdown per city.)*
- **Basics:** City Name, Population.
- **Production:** Current queue/construction item.
- **Yields:** Current output of Food, Production, Science, Culture, Gold, and Faith per city.

### 7. Military & Civilians (Unit Roster)
*(Aggregated lists to understand army composition.)*
- **Grouping:** Accumulates identical unit types with the exact same valid promotions.
- **Promotions:** Lists effective promotions (ignoring hidden prerequisites or skill trees) to give the AI context on veterancy.
- **Output:** Counts (e.g., "3x Archer (Volley)", "2x Builder").

### 8. Diplomatic Relations
*(The geopolitical landscape.)*
- **Known Entities:** Lists all met civilizations and City-States.
- **Status:** Current diplomatic state (At War, Peace, Declared Friend, Allied, Suzerain). 

---

### Implementation Next Steps
1. **Civ 6 Lua Environment Setup:** Create the folder structure `AiExporter` with an `AiExporter.modinfo` and an XML UI inclusion file. Civ 6 gameplay scripts have restricted contexts, so UI scripts are usually required to read all player data.
2. **JSON Serializer:** Include a lightweight Lua JSON library compatible with Civ 6's older Lua 5.2/5.3 implementation.
3. **Event Hooks:** Bind the Lua data collection to a specific event (e.g., `Events.PlayerTurnActivated` or a UI button click) to print the payload.
4. **C# App:** Develop the log-tailing executable.
