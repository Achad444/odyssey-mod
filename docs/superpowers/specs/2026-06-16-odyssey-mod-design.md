# Odyssey Mod — Design Spec
**Date:** 2026-06-16  
**Author:** Aidan (she/her) (with Claude)  
**Status:** Approved

---

## What We're Building

A Minecraft Java Edition mod called `odyssey`, built on Forge 1.20.1. The mod draws from ancient Greek mythology — inspired by Percy Jackson and Assassin's Creed Odyssey — and will eventually let players take on heroic quests (Hercules' labors, freeing Sisyphus, holding up the sky with Atlas) to earn powerful magic items.

This document covers **Phase 1 only**: the foundation. One custom item. One portal structure. Proof that the whole pipeline works end-to-end.

---

## Goals

- Learn to read and understand Java code (primary)
- Learn Git and version control
- Build something genuinely cool and playable
- Establish a foundation for a much larger mod (and eventually a modpack)

---

## Phase 1 Scope

### The Laurel Wreath
A custom helmet-slot item inspired by the crowns of Greek heroes and gods.

- Wearable in the helmet armor slot
- Has a unique name ("Laurel Wreath") and custom texture
- Craftable via a shaped recipe (to be designed in Phase 1)
- No special effects in Phase 1 — just proving we can add an item that exists, has a name, and can be equipped
- Phase 2+ will add effects (e.g., night vision, speed, divine protection)

### The Gates of Eurydice
A multiblock portal structure — a freestanding stone doorway, like a Greek temple entrance.

**Structure shape:**
- Two pillar blocks on either side (stacked 3 high)
- One capstone block across the top
- Interior fill: 3×2 (width × height) activatable portal blocks

**Activation:**
- Player holds a specific activation item (TBD in Phase 1 — likely a special torch or artifact) and right-clicks the interior
- Portal activates: the interior fills with an animated watery surface (dark blue-green, like the River Styx)
- Gold particle effects spiral and pull inward toward the center

**In Phase 1:** The portal activates visually but does not teleport. It is purely aesthetic — proving we can detect a multiblock structure, trigger an event, and render custom particles.

**In Phase 2+:** The portal will transport the player to a custom dimension (the Underworld).

---

## Tech Stack

| Tool | Purpose |
|---|---|
| Java 17 | The programming language the mod is written in |
| Forge 1.20.1 | The mod loader — lets custom mods run inside Minecraft |
| Gradle | The build tool — compiles Java code into a `.jar` file Minecraft loads |
| IntelliJ IDEA Community Edition | The IDE — where we write and read code |
| Git | Version control — tracks every change we make |
| GitHub | Where our Git history lives online (backup + sharing) |

---

## What We Are NOT Building in Phase 1

- Custom dimensions
- Quest system
- Custom mobs or bosses
- Hercules / Sisyphus / Atlas content
- Multiplayer support (deferred — solo only for now)
- Railway deployment (separate project)

---

## Learning Philosophy

- Claude writes the code; Aidan reads and asks questions until she understands it
- Every new concept gets explained before it's used
- Comprehension is confirmed with questions, not "does that make sense?"
- No two major new concepts introduced in the same session
- Each session ends with something visible in-game

---

## Phase 2 Preview (not in scope yet)

- Portal teleports player to a custom Underworld dimension
- First quest: choose between Hercules' Labors or another Greek myth challenge
- Magic item rewards with actual gameplay effects
- Custom mobs (Shades, Cerberus, etc.)
