# Odyssey Mod — Design Spec
**Date:** 2026-06-17
**Author:** Aidan (with Claude)
**Status:** Approved
**Supersedes:** 2026-06-16-odyssey-mod-design.md

---

## What We're Building

A Minecraft Java Edition mod called `odyssey`, built on Forge 1.20.1. The mod is a large-scale, story-driven experience rooted in ancient Greek mythology — inspired by Assassin's Creed Odyssey in tone: epic, grounded, and serious. The player is a mortal who survives something they shouldn't have, earns the attention of the gods, and over the course of three Acts becomes the greatest hero the world has ever seen — not through divine blood, but through trials, choices, and refusal to stop.

This document covers the full vision across all Acts. Each Act will have its own implementation plan. We build in order.

---

## Tone & Philosophy

- **AC Odyssey, not Percy Jackson.** Epic and mythologically grounded. The world feels ancient and real.
- **Story-driven, not grindy.** Big fights are events, not routines. The player earns power through narrative progress.
- **Accessible and fun first.** Difficulty is present but never punishing for its own sake.
- **Solo experience.** No multiplayer support planned.
- **Explorer first.** The world rewards curiosity. Nothing is handed to you.

---

## The World

The Overworld is transformed into a Mediterranean landscape:

- Coastlines, cliffs, and harbors
- Olive groves, cypress trees, Mediterranean flora
- Small fishing towns at the ends of harbors
- Scattered islands navigable by boat
- Ancient temples, ruins, and shrines — some intact, some collapsed, some buried
- The world feels like it was once great and is quietly deteriorating

The world is explored, not explained. Lore is found through:
- **Scrolls and books** discovered in ruins, shipwrecks, temples, and caves
- **Environmental storytelling** — crumbled statues, abandoned offerings, dried bloodstains, broken weapons
- **Storytellers** — named, deliberately placed NPCs who deliver critical story beats and quests
- **Gods** — encountered only at shrines or within their domains; high-stakes appearances only

There are no waypoints. No quest tracker popups. You read, explore, and find things.

---

## NPC Types

| Type | Role |
|---|---|
| Satyrs | Friendly wandering mobs — atmospheric, interactive, may trade or hint at locations |
| Storytellers | Named, placed NPCs — deliver critical lore, quests, and turning points |
| Gods | Appear at shrines or within dimensions — rare, significant encounters only |

Satyrs exist freely in the world near forests and ruins. Storytellers are placed with purpose. Gods are never casual.

---

## How It Starts — The Strait of Messina

There is no tutorial. There is no journal. There is no portal.

When the player finds the Strait of Messina — a generated geographic feature, a narrow rocky passage between two landmasses — Scylla attacks from the cliff walls and Charybdis churns the water beneath into a whirlpool. It is an environmental gauntlet. Ships have wrecked here. Sailors have died here.

If the player survives, a scroll washes ashore in the wreckage. It is the last words of a dead sailor — a warning addressed to no one, written by someone who knew they weren't going to make it.

Reading the scroll triggers the first quest. Somewhere, a god notices that a mortal survived something mortals don't survive.

The player then chooses their **Patron God** — the deity who reaches out first.

---

## The Story — Three Acts

### Act 1 — The Awakening

**Atmosphere:** Ancient mystery and exploration. The player is nobody. A mortal who survived something mortals don't survive.

The Patron God gives the player their first quests. They begin earning divine favor, exploring the Mediterranean world, and fighting their first monsters. They walk paths that Hercules, Odysseus, and Perseus once walked — visiting the places those heroes shaped, encountering monsters they left unfinished.

The player defeats their first boss. They earn their first divine gift.

Something feels quietly wrong. Gods contradict each other. Quests lead to strange places. The player doesn't know why yet.

**Act 1 ends** when the player crosses into something deeper — a place they were never meant to find.

### Act 2 — The Shadow Behind Olympus

**Atmosphere:** Rising stakes, growing power, the creeping sense that the world is being steered.

The trials deepen. God questlines expand. The player's power grows. More of the Mediterranean opens up. The Underworld becomes accessible.

The gods' behavior becomes undeniable. Something is influencing them. Their gifts come with stranger conditions. Their quests seem to serve purposes beyond what they say.

**Late Act 2:** The villain steps out of the shadow. The player realizes there has been a force quietly destabilizing Olympus from the very beginning — a primordial entity, older than the gods, without a true name until this moment. Everything the player did in Act 1 was visible to it. Some of it was useful to it.

**Act 2 ends** with the player knowing what they are truly up against and choosing to face it anyway.

### Act 3 — The Final Trial

**Atmosphere:** Legendary. The world knows the player's name.

The player has their artifacts. Their skill tree is full. Their trophy room is built. Now they face something the gods themselves fear.

The final trial is not a standard boss fight. It is the ultimate test of what a mortal can become when they simply refuse to stop. The player may fight a god. They may descend deeper than the Underworld. The ending reflects the choices they made — which gods they honored, which artifacts they carry, what they left behind.

**The villain:** A primordial force, older than Olympus, that has been pulling strings since before the story began. Not a Titan — something earlier. Something the gods don't have a name for. It has been corrupting Olympus slowly, exploiting divine pride and divine conflict. It does not want to destroy the world. It wants to unmake the order that the gods built — to return to the chaos before Olympus.

---

## The Gods — Affinity System

All 12 Olympians are present and active in the world.

Each god has:
- **Major questline** — a full story arc tied to their domain and mythology
- **Minor quests** — smaller tasks the player can encounter in the world
- **Affinity meter** — rises through quests, offerings, and honoring them
- **Patron Gift** — a unique item, weapon, armor piece, or tool unlocked at high affinity
- **Turning points** — moments where the god's questline intersects with the main story

The player's choices between gods determine what gifts they receive and when. No path is locked out — only the gifts differ.

Each god will have their own detailed design document covering their questline, behavior, and gift item.

### Patron Gifts (to be expanded per-god)

All Patron Gifts share these properties:
- **Unbreakable** — divine items do not lose durability
- **Soul-bound** — the item stays with the player on death; a god's gift does not abandon its bearer

| God | Gift | Primary Effect |
|---|---|---|
| Zeus | Thunderbolt Spear | Stuns enemies on hit; lightning strikes on charged throw |
| Poseidon | Trident of the Deep | Water breathing; excels underwater; controls water currents |
| Athena | Aegis Shield | Blocks and reflects projectiles; brief invincibility on block |
| Hermes | Winged Sandals | Increased speed; double jump |
| Hephaestus | Forge Gauntlet | Unlocks unique crafting recipes; deals fire damage |
| Hades | Helm of Darkness | Brief invisibility on activation |
| Apollo | Sun Bow | Long range; arrows set targets on fire |
| Artemis | Hunter's Quiver | Marks and tracks enemies through walls |
| Ares | War Blade | Damage scales with consecutive kills in a single fight |
| Aphrodite | Charm Bracelet | Some enemies become briefly passive |
| Dionysus | TBD — chaotic and fun | TBD |
| Demeter | TBD — nature-based | TBD |

---

## Monsters

### Bosses — Fought Once, Epic Events

These are rare encounters. Each is tied to a specific location or questline. They are not respawnable. Defeating them drops unique materials or gear.

| Boss | Location/Context |
|---|---|
| Minotaur | The Labyrinth (future Act) |
| Medusa | A cursed island — Perseus questline |
| Cyclops | Island of the Cyclopes — Odysseus questline |
| Cerberus | The Underworld — corrupted by the villain; Hades asks the player to free him, not kill him |
| Hydra | A river swamp — Hercules questline |
| Chimera | A volcanic region — Bellerophon questline |
| Sphinx | A desert ruin — guards passage to a deeper area |
| Scylla | The Strait of Messina — environmental, not a traditional fight |

### World Enemies — Patrol and Roam

These appear throughout the world and respawn. They are dangerous but not overwhelming to a prepared player.

- Harpies
- Corrupted soldiers (former heroes, now lost)
- Feral satyrs (distinct from friendly satyrs — these have been corrupted)
- Shades (in and near the Underworld)
- Lesser sea creatures

### Environmental Threats

- **Scylla and Charybdis** — survived, not fought. The Strait of Messina is an environmental gauntlet. They may appear again in late-game areas.

### Titans

Reserved for future storyline content. They exist in Tartarus. Not part of the initial three-Act arc.

---

## Progression

### Level Caps Per Act (BG3 Style)

| Act | Cap | Feel |
|---|---|---|
| Act 1 | Low | Fragile, learning, small powers |
| Act 2 | Mid | Capable, beginning to feel heroic |
| Act 3 | Full | Legendary — the world knows your name |

The player cannot grind past their Act's cap. Story progress raises the cap, not time.

### How the Player Gets Stronger

- **Boss drops** — best gear comes from defeating major enemies, not from mining
- **Quest rewards** — rare materials used to forge specific legendary items
- **Skill tree** — mortal skills only: combat techniques, exploration abilities, crafting unlocks. No magic. Power comes from mastery, not divine blood.
- **Divine gifts** — earned through god affinity; unbreakable and soul-bound

### Trophy Room

A physical space in the player's base that fills over time with relics, monster heads, and artifacts collected throughout the journey. A visual record of everything earned. By Act 3, it tells the story of the player's entire playthrough.

---

## Dimensions

| Realm | Accessible | Notes |
|---|---|---|
| Mediterranean Overworld | From the start | Transformed — harbors, islands, ruins |
| The Underworld | Act 2+ | Dark, layered, haunting |
| The Labyrinth | Future content | Minotaur boss location |
| Atlantis | Possible — much later | Not planned for initial arc |
| Mount Olympus | Possible Act 3 finale | To be determined |

---

## Implementation Structure

The mod is built in Acts. Each Act is a separate implementation plan. We do not start Act 2 until Act 1 is complete and playable.

**Act 1 scope will be defined in its own implementation plan.**

Within Act 1, the priority order is:
1. World transformation (Mediterranean biome/structures)
2. The Strait of Messina structure and opening event
3. Patron God selection
4. First questline and first boss
5. First divine gift item

Everything else follows from there.

---

## What We Are NOT Building Yet

- Multiplayer support
- The Labyrinth
- Atlantis
- Titans / Tartarus
- Act 2 and Act 3 content
- All 12 god questlines (we build one at a time)

---

## Learning Philosophy

- Claude writes the code; Aidan reads and asks questions until she understands it
- Every new concept gets explained before it's used
- Comprehension is confirmed with questions, not "does that make sense?"
- No two major new concepts introduced in the same session
- Each session ends with something visible in-game
