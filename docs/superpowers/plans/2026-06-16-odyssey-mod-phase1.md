# Odyssey Mod Phase 1 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build a working Forge 1.20.1 Minecraft mod that adds a Laurel Wreath helmet item and an activatable Gates of Eurydice portal structure with a watery surface and gold particle effects.

**Architecture:** A standard Forge mod using deferred registration for items and blocks. The portal uses a multiblock structure detector on player interaction. All assets (textures, models, recipes) live in `src/main/resources`.

**Tech Stack:** Java 17, Forge 1.20.1, Gradle 8, IntelliJ IDEA Community Edition, Git, GitHub

---

## File Map

These are every file we will create:

| File | Purpose |
|---|---|
| `src/main/java/com/odyssey/OdysseyMod.java` | Main mod entry point |
| `src/main/java/com/odyssey/init/ModItems.java` | Registers all custom items |
| `src/main/java/com/odyssey/init/ModBlocks.java` | Registers all custom blocks |
| `src/main/java/com/odyssey/init/ModCreativeTabs.java` | Custom creative tab |
| `src/main/java/com/odyssey/item/LaurelWreathItem.java` | Laurel Wreath item class |
| `src/main/java/com/odyssey/block/PortalFrameBlock.java` | Portal pillar/capstone block |
| `src/main/java/com/odyssey/block/OdysseyPortalBlock.java` | Portal interior block |
| `src/main/java/com/odyssey/block/PortalDetector.java` | Detects completed portal shape |
| `src/main/resources/META-INF/mods.toml` | Mod metadata (name, version, description) |
| `src/main/resources/pack.mcmeta` | Resource pack declaration |
| `src/main/resources/assets/odyssey/lang/en_us.json` | Display names for items/blocks |
| `src/main/resources/assets/odyssey/models/item/laurel_wreath.json` | Laurel Wreath 3D model |
| `src/main/resources/assets/odyssey/textures/item/laurel_wreath.png` | Laurel Wreath texture (16x16) |
| `src/main/resources/assets/odyssey/models/block/portal_frame.json` | Portal frame block model |
| `src/main/resources/assets/odyssey/textures/block/portal_frame.png` | Portal frame texture (16x16) |
| `src/main/resources/assets/odyssey/models/block/odyssey_portal.json` | Portal interior model |
| `src/main/resources/assets/odyssey/textures/block/odyssey_portal.png` | Portal water texture (16x16 animated) |
| `src/main/resources/assets/odyssey/blockstates/portal_frame.json` | Portal frame blockstate |
| `src/main/resources/assets/odyssey/blockstates/odyssey_portal.json` | Portal interior blockstate |
| `src/main/resources/data/odyssey/recipes/laurel_wreath.json` | Crafting recipe |

---

## Task 0: Install Required Tools

> This task has no code. It is purely setup. Complete each step before moving on.

**Goal:** Have Java, IntelliJ, and Git installed and working.

- [ ] **Step 1: Install Java 17 (JDK)**

  Go to https://adoptium.net/ and download **Temurin 17 (LTS)** for Windows x64. Run the installer with all default options. When it finishes, open PowerShell and run:
  ```
  java -version
  ```
  You should see something like: `openjdk version "17.0.x"`. If you do, Java is installed correctly.

- [ ] **Step 2: Install IntelliJ IDEA Community Edition**

  Go to https://www.jetbrains.com/idea/download/ and download the **Community Edition** (free — the one on the right). Run the installer. On the options screen, check **"Add to PATH"** and **"Associate .java files"**. Leave everything else default.

- [ ] **Step 3: Install Git**

  Go to https://git-scm.com/download/win and download the Windows installer. Run it with all default options. When done, open PowerShell and run:
  ```
  git --version
  ```
  You should see: `git version 2.x.x`.

- [ ] **Step 4: Create a GitHub account**

  Go to https://github.com and sign up for a free account. Remember your username — you'll need it soon.

---

## Task 1: Download and Configure the Forge MDK

> MDK = Mod Development Kit. This is the official Forge starter project that has everything pre-wired.

**Goal:** Have a working Forge project open in IntelliJ that can build and launch Minecraft.

- [ ] **Step 1: Download the Forge MDK**

  Go to https://files.minecraftforge.net/net/minecraftforge/forge/ and find version **1.20.1**. Click it, then download the **MDK** (not the installer). You'll get a `.zip` file.

- [ ] **Step 2: Extract to your Odyssey folder**

  Extract the contents of the `.zip` directly into `C:\Users\aidan\Desktop\Odyssey`. When done, you should have files like `build.gradle`, `gradlew.bat`, and a `src` folder sitting in that directory.

- [ ] **Step 3: Open the project in IntelliJ**

  Open IntelliJ IDEA. Choose **Open**, navigate to `C:\Users\aidan\Desktop\Odyssey`, and select it. IntelliJ will detect it as a Gradle project and ask to import — click **Trust Project** and let Gradle download its dependencies. This will take a few minutes the first time.

- [ ] **Step 4: Generate the IntelliJ run configurations**

  Open the Terminal tab at the bottom of IntelliJ. Run:
  ```
  gradlew genIntellijRuns
  ```
  This creates the "Run Client" and "Run Server" buttons that launch Minecraft from inside IntelliJ. Wait for it to finish (you'll see `BUILD SUCCESSFUL`).

- [ ] **Step 5: Verify the project builds**

  In the Terminal, run:
  ```
  gradlew build
  ```
  Expected: `BUILD SUCCESSFUL`. If you see errors, stop and flag them before continuing.

- [ ] **Step 6: Launch Minecraft once to verify**

  In the top-right of IntelliJ, click the green **Run** button next to "runClient". Minecraft should launch with the Forge logo. You don't need to do anything in-game — just verify it opens and reaches the main menu. Close it.

---

## Task 2: Rename the Example Mod to Odyssey

> The Forge MDK comes with a sample mod called `examplemod`. We rename everything to `odyssey`.

**Goal:** The mod is now called Odyssey with our own package name.

- [ ] **Step 1: Update gradle.properties**

  Open `gradle.properties` in IntelliJ. Replace its entire contents with:
  ```properties
  org.gradle.jvmargs=-Xmx3G
  org.gradle.daemon=false

  minecraft_version=1.20.1
  minecraft_version_range=[1.20.1,1.21)
  forge_version=47.3.0
  forge_version_range=[47,)
  loader_version_range=[47,)
  mod_id=odyssey
  mod_name=Odyssey
  mod_license=MIT
  mod_version=0.1.0
  mod_authors=Aidan
  mod_description=An ancient Greek mythology mod for Minecraft.
  ```

- [ ] **Step 2: Delete the example mod source files**

  In IntelliJ's Project panel, navigate to `src/main/java`. Delete whatever package folder exists there (it will be something like `com/example/examplemod`). Right-click → Delete.

- [ ] **Step 3: Create the Odyssey package structure**

  Right-click on `src/main/java` → New → Package. Name it:
  ```
  com.odyssey
  ```
  Then inside that, create sub-packages (right-click `com.odyssey` → New → Package):
  - `com.odyssey.init`
  - `com.odyssey.item`
  - `com.odyssey.block`

- [ ] **Step 4: Update mods.toml**

  Open `src/main/resources/META-INF/mods.toml`. Replace its entire contents with:
  ```toml
  modLoader="javafml"
  loaderVersion="${loader_version_range}"
  license="${mod_license}"

  [[dependencies.odyssey]]
      modId="forge"
      mandatory=true
      versionRange="${forge_version_range}"
      ordering="NONE"
      side="BOTH"

  [[dependencies.odyssey]]
      modId="minecraft"
      mandatory=true
      versionRange="${minecraft_version_range}"
      ordering="NONE"
      side="BOTH"

  [[mods]]
  modId="${mod_id}"
  version="${mod_version}"
  displayName="${mod_name}"
  description='''${mod_description}'''
  ```

- [ ] **Step 5: Update pack.mcmeta**

  Open `src/main/resources/pack.mcmeta`. Replace its entire contents with:
  ```json
  {
    "pack": {
      "description": "Odyssey Mod Resources",
      "pack_format": 15
    }
  }
  ```

- [ ] **Step 6: Create the main mod class**

  Right-click `com.odyssey` → New → Java Class. Name it `OdysseyMod`. Replace all generated content with:
  ```java
  package com.odyssey;

  import net.minecraftforge.fml.common.Mod;

  @Mod(OdysseyMod.MOD_ID)
  public class OdysseyMod {
      public static final String MOD_ID = "odyssey";

      public OdysseyMod() {
      }
  }
  ```

- [ ] **Step 7: Build to verify**

  In the Terminal:
  ```
  gradlew build
  ```
  Expected: `BUILD SUCCESSFUL`. The mod now exists as an empty shell with our name.

- [ ] **Step 8: Initialize Git and push to GitHub**

  In the Terminal, run these one at a time:
  ```
  git init
  git add .
  git commit -m "feat: initial Odyssey mod setup from Forge MDK"
  ```
  Then on GitHub, create a new repository called `odyssey-mod` (no README, no .gitignore). Copy the URL it gives you. Back in IntelliJ Terminal:
  ```
  git remote add origin <paste your GitHub URL here>
  git branch -M main
  git push -u origin main
  ```

---

## Task 3: Add the Laurel Wreath Item

**Goal:** A craftable Laurel Wreath item appears in a custom creative tab and can be equipped as a helmet.

- [ ] **Step 1: Create ModCreativeTabs.java**

  Right-click `com.odyssey.init` → New → Java Class → `ModCreativeTabs`. Replace with:
  ```java
  package com.odyssey.init;

  import com.odyssey.OdysseyMod;
  import net.minecraft.network.chat.Component;
  import net.minecraft.world.item.CreativeModeTab;
  import net.minecraft.world.item.ItemStack;
  import net.minecraftforge.registries.DeferredRegister;
  import net.minecraftforge.registries.ForgeRegistries;
  import net.minecraftforge.registries.RegistryObject;

  public class ModCreativeTabs {
      public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
          DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, OdysseyMod.MOD_ID);

      public static final RegistryObject<CreativeModeTab> ODYSSEY_TAB = CREATIVE_MODE_TABS.register("odyssey_tab",
          () -> CreativeModeTab.builder()
              .title(Component.translatable("itemGroup.odyssey"))
              .icon(() -> new ItemStack(ModItems.LAUREL_WREATH.get()))
              .displayItems((params, output) -> {
                  output.accept(ModItems.LAUREL_WREATH.get());
              })
              .build());
  }
  ```

- [ ] **Step 2: Create LaurelWreathItem.java**

  Right-click `com.odyssey.item` → New → Java Class → `LaurelWreathItem`. Replace with:
  ```java
  package com.odyssey.item;

  import net.minecraft.world.entity.EquipmentSlot;
  import net.minecraft.world.item.ArmorItem;
  import net.minecraft.world.item.ArmorMaterial;
  import net.minecraft.world.item.ArmorMaterials;
  import net.minecraft.world.item.Item;

  public class LaurelWreathItem extends ArmorItem {
      public LaurelWreathItem() {
          super(ArmorMaterials.GOLD, Type.HELMET, new Item.Properties().stacksTo(1));
      }
  }
  ```

- [ ] **Step 3: Create ModItems.java**

  Right-click `com.odyssey.init` → New → Java Class → `ModItems`. Replace with:
  ```java
  package com.odyssey.init;

  import com.odyssey.OdysseyMod;
  import com.odyssey.item.LaurelWreathItem;
  import net.minecraft.world.item.Item;
  import net.minecraftforge.registries.DeferredRegister;
  import net.minecraftforge.registries.ForgeRegistries;
  import net.minecraftforge.registries.RegistryObject;

  public class ModItems {
      public static final DeferredRegister<Item> ITEMS =
          DeferredRegister.create(ForgeRegistries.ITEMS, OdysseyMod.MOD_ID);

      public static final RegistryObject<Item> LAUREL_WREATH =
          ITEMS.register("laurel_wreath", LaurelWreathItem::new);
  }
  ```

- [ ] **Step 4: Wire registries into OdysseyMod.java**

  Replace the contents of `OdysseyMod.java` with:
  ```java
  package com.odyssey;

  import com.odyssey.init.ModCreativeTabs;
  import com.odyssey.init.ModItems;
  import net.minecraftforge.eventbus.api.IEventBus;
  import net.minecraftforge.fml.common.Mod;
  import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

  @Mod(OdysseyMod.MOD_ID)
  public class OdysseyMod {
      public static final String MOD_ID = "odyssey";

      public OdysseyMod() {
          IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
          ModItems.ITEMS.register(modEventBus);
          ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
      }
  }
  ```

- [ ] **Step 5: Create the item model**

  Create the file at:
  `src/main/resources/assets/odyssey/models/item/laurel_wreath.json`

  Contents:
  ```json
  {
    "parent": "item/generated",
    "textures": {
      "layer0": "odyssey:item/laurel_wreath"
    }
  }
  ```

- [ ] **Step 6: Create the item texture**

  Create `src/main/resources/assets/odyssey/textures/item/laurel_wreath.png`.

  This is a 16x16 PNG file. For now, create a placeholder: a golden-green wreath shape on a transparent background. You can draw this in any image editor (even MS Paint). We will replace it with a proper texture later.

  > Claude will provide a base64-encoded starter texture at this step if needed.

- [ ] **Step 7: Create the language file**

  Create `src/main/resources/assets/odyssey/lang/en_us.json`:
  ```json
  {
    "item.odyssey.laurel_wreath": "Laurel Wreath",
    "itemGroup.odyssey": "Odyssey"
  }
  ```

- [ ] **Step 8: Create the crafting recipe**

  Create `src/main/resources/data/odyssey/recipes/laurel_wreath.json`:
  ```json
  {
    "type": "minecraft:crafting_shaped",
    "pattern": [
      "LGL",
      "G G",
      "   "
    ],
    "key": {
      "L": { "item": "minecraft:oak_leaves" },
      "G": { "item": "minecraft:gold_ingot" }
    },
    "result": {
      "item": "odyssey:laurel_wreath",
      "count": 1
    }
  }
  ```

- [ ] **Step 9: Build and verify**

  ```
  gradlew build
  ```
  Expected: `BUILD SUCCESSFUL`

- [ ] **Step 10: Launch and test in-game**

  Run the client. In Minecraft:
  1. Open creative inventory → find the **Odyssey** tab
  2. Confirm the Laurel Wreath appears there
  3. Place it in your helmet slot — it should equip
  4. Craft it using the recipe: oak leaves in the corners + gold ingots forming a crown shape

  If all three work, the item is complete.

- [ ] **Step 11: Commit**

  ```
  git add .
  git commit -m "feat: add Laurel Wreath helmet item with crafting recipe"
  git push
  ```

---

## Task 4: Add the Portal Frame Block

**Goal:** A decorative stone pillar block (the Gates of Eurydice frame) can be placed in the world.

- [ ] **Step 1: Create ModBlocks.java**

  Right-click `com.odyssey.init` → New → Java Class → `ModBlocks`. Replace with:
  ```java
  package com.odyssey.init;

  import com.odyssey.OdysseyMod;
  import com.odyssey.block.PortalFrameBlock;
  import net.minecraft.world.level.block.Block;
  import net.minecraft.world.level.block.SoundType;
  import net.minecraft.world.level.block.state.BlockBehaviour;
  import net.minecraft.world.level.material.MapColor;
  import net.minecraftforge.registries.DeferredRegister;
  import net.minecraftforge.registries.ForgeRegistries;
  import net.minecraftforge.registries.RegistryObject;

  public class ModBlocks {
      public static final DeferredRegister<Block> BLOCKS =
          DeferredRegister.create(ForgeRegistries.BLOCKS, OdysseyMod.MOD_ID);

      public static final RegistryObject<Block> PORTAL_FRAME =
          BLOCKS.register("portal_frame", () -> new PortalFrameBlock(
              BlockBehaviour.Properties.of()
                  .mapColor(MapColor.STONE)
                  .strength(3.5f, 6.0f)
                  .sound(SoundType.STONE)
                  .requiresCorrectToolForDrops()
          ));
  }
  ```

- [ ] **Step 2: Add block items to ModItems.java**

  Add this import and field to `ModItems.java`:
  ```java
  import net.minecraft.world.item.BlockItem;

  // Add inside the class body, after LAUREL_WREATH:
  public static final RegistryObject<Item> PORTAL_FRAME =
      ITEMS.register("portal_frame", () -> new BlockItem(ModBlocks.PORTAL_FRAME.get(), new Item.Properties()));
  ```

- [ ] **Step 3: Create PortalFrameBlock.java**

  Right-click `com.odyssey.block` → New → Java Class → `PortalFrameBlock`. Replace with:
  ```java
  package com.odyssey.block;

  import net.minecraft.world.level.block.Block;
  import net.minecraft.world.level.block.state.BlockBehaviour;

  public class PortalFrameBlock extends Block {
      public PortalFrameBlock(BlockBehaviour.Properties properties) {
          super(properties);
      }
  }
  ```

- [ ] **Step 4: Wire blocks into OdysseyMod.java**

  Add to the `OdysseyMod` constructor (after the existing register calls):
  ```java
  ModBlocks.BLOCKS.register(modEventBus);
  ```

  Also add this import at the top:
  ```java
  import com.odyssey.init.ModBlocks;
  ```

- [ ] **Step 5: Create the block model**

  Create `src/main/resources/assets/odyssey/models/block/portal_frame.json`:
  ```json
  {
    "parent": "block/cube_all",
    "textures": {
      "all": "odyssey:block/portal_frame"
    }
  }
  ```

- [ ] **Step 6: Create the block item model**

  Create `src/main/resources/assets/odyssey/models/item/portal_frame.json`:
  ```json
  {
    "parent": "odyssey:block/portal_frame"
  }
  ```

- [ ] **Step 7: Create the blockstate file**

  Create `src/main/resources/assets/odyssey/blockstates/portal_frame.json`:
  ```json
  {
    "variants": {
      "": { "model": "odyssey:block/portal_frame" }
    }
  }
  ```

- [ ] **Step 8: Create the block texture**

  Create `src/main/resources/assets/odyssey/textures/block/portal_frame.png` — a 16x16 PNG. Use a grey stone texture with faint Greek key/meander pattern accents. Placeholder is fine for now.

- [ ] **Step 9: Add names to en_us.json**

  Add to `src/main/resources/assets/odyssey/lang/en_us.json`:
  ```json
  {
    "item.odyssey.laurel_wreath": "Laurel Wreath",
    "block.odyssey.portal_frame": "Gate of Eurydice",
    "item.odyssey.portal_frame": "Gate of Eurydice",
    "itemGroup.odyssey": "Odyssey"
  }
  ```

- [ ] **Step 10: Add to creative tab**

  In `ModCreativeTabs.java`, add inside the `displayItems` lambda (after the Laurel Wreath line):
  ```java
  output.accept(ModItems.PORTAL_FRAME.get());
  ```

- [ ] **Step 11: Build and test**

  ```
  gradlew build
  ```
  Launch the client. Find the Portal Frame in the Odyssey creative tab. Place it in the world — it should look like a stone block. You should be able to build the gate shape: two pillars (3 high) with a top block.

- [ ] **Step 12: Commit**

  ```
  git add .
  git commit -m "feat: add portal frame block for Gates of Eurydice"
  git push
  ```

---

## Task 5: Add the Portal Interior and Activation

**Goal:** When the correct frame shape is built and right-clicked with a gold ingot, the interior fills with an animated watery portal block and gold particles spiral inward.

- [ ] **Step 1: Create OdysseyPortalBlock.java**

  Right-click `com.odyssey.block` → New → Java Class → `OdysseyPortalBlock`. Replace with:
  ```java
  package com.odyssey.block;

  import net.minecraft.core.BlockPos;
  import net.minecraft.core.Direction;
  import net.minecraft.core.particles.ParticleTypes;
  import net.minecraft.world.level.Level;
  import net.minecraft.world.level.block.Block;
  import net.minecraft.world.level.block.Blocks;
  import net.minecraft.world.level.block.state.BlockBehaviour;
  import net.minecraft.world.level.block.state.BlockState;
  import net.minecraft.world.level.material.MapColor;

  import java.util.Random;

  public class OdysseyPortalBlock extends Block {
      public OdysseyPortalBlock(BlockBehaviour.Properties properties) {
          super(properties);
      }

      @Override
      public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
          // Spawn gold sparkling particles that drift inward toward the block center
          double centerX = pos.getX() + 0.5;
          double centerY = pos.getY() + 0.5;
          double centerZ = pos.getZ() + 0.5;

          for (int i = 0; i < 3; i++) {
              double offsetX = (random.nextDouble() - 0.5) * 1.5;
              double offsetY = (random.nextDouble() - 0.5) * 1.5;
              double offsetZ = (random.nextDouble() - 0.5) * 0.1;

              // Velocity pulls the particle toward the center
              double velX = (centerX - (centerX + offsetX)) * 0.05;
              double velY = (centerY - (centerY + offsetY)) * 0.05;
              double velZ = 0;

              level.addParticle(
                  ParticleTypes.FLASH,
                  centerX + offsetX,
                  centerY + offsetY,
                  centerZ + offsetZ,
                  velX, velY, velZ
              );
          }
      }
  }
  ```

- [ ] **Step 2: Create PortalDetector.java**

  Right-click `com.odyssey.block` → New → Java Class → `PortalDetector`. Replace with:
  ```java
  package com.odyssey.block;

  import com.odyssey.init.ModBlocks;
  import net.minecraft.core.BlockPos;
  import net.minecraft.world.level.Level;
  import net.minecraft.world.level.block.Block;

  public class PortalDetector {

      /**
       * Checks if a valid Gates of Eurydice frame exists around the given interior position.
       * Frame shape (viewed from front, X axis, 3 wide x 4 tall):
       *
       *   [F][ ][F]   <- row 3 (top, y+3): capstone frame blocks at edges
       *   [F][ ][F]   <- row 2 (y+2): interior
       *   [F][ ][F]   <- row 1 (y+1): interior
       *   [F][F][F]   <- row 0 (base, y+0): bottom row is frame
       *
       * Interior fill positions: (x+1, y+1), (x+1, y+2) — 2 blocks tall, 1 wide
       * This is the minimal 3x4 doorway shape.
       */
      public static boolean isValidFrame(Level level, BlockPos bottomInterior) {
          Block frame = ModBlocks.PORTAL_FRAME.get();
          BlockPos base = bottomInterior.below();

          // Bottom row: three frame blocks
          if (!isFrame(level, base.west(), frame)) return false;
          if (!isFrame(level, base, frame)) return false;
          if (!isFrame(level, base.east(), frame)) return false;

          // Left pillar: three frame blocks going up from base corners
          if (!isFrame(level, base.west().above(), frame)) return false;
          if (!isFrame(level, base.west().above(2), frame)) return false;
          if (!isFrame(level, base.west().above(3), frame)) return false;

          // Right pillar: three frame blocks going up
          if (!isFrame(level, base.east().above(), frame)) return false;
          if (!isFrame(level, base.east().above(2), frame)) return false;
          if (!isFrame(level, base.east().above(3), frame)) return false;

          // Top capstone: three frame blocks
          if (!isFrame(level, base.west().above(3), frame)) return false;
          if (!isFrame(level, base.above(3), frame)) return false;
          if (!isFrame(level, base.east().above(3), frame)) return false;

          return true;
      }

      private static boolean isFrame(Level level, BlockPos pos, Block frame) {
          return level.getBlockState(pos).getBlock() == frame;
      }

      /**
       * Fills the 2-block interior with portal blocks.
       */
      public static void fillPortal(Level level, BlockPos bottomInterior) {
          Block portal = ModBlocks.ODYSSEY_PORTAL.get();
          level.setBlock(bottomInterior, portal.defaultBlockState(), 3);
          level.setBlock(bottomInterior.above(), portal.defaultBlockState(), 3);
      }
  }
  ```

- [ ] **Step 3: Register the portal block in ModBlocks.java**

  Add to `ModBlocks.java`:
  ```java
  import com.odyssey.block.OdysseyPortalBlock;
  import net.minecraft.world.level.block.state.BlockBehaviour;
  import net.minecraft.world.level.material.PushReaction;

  // Add inside the class body:
  public static final RegistryObject<Block> ODYSSEY_PORTAL =
      BLOCKS.register("odyssey_portal", () -> new OdysseyPortalBlock(
          BlockBehaviour.Properties.of()
              .noCollission()
              .strength(-1.0f)
              .lightLevel(state -> 11)
              .noLootTable()
              .pushReaction(PushReaction.BLOCK)
      ));
  ```

- [ ] **Step 4: Add activation logic to PortalFrameBlock.java**

  Replace `PortalFrameBlock.java` contents with:
  ```java
  package com.odyssey.block;

  import net.minecraft.core.BlockPos;
  import net.minecraft.world.InteractionHand;
  import net.minecraft.world.InteractionResult;
  import net.minecraft.world.entity.player.Player;
  import net.minecraft.world.item.Items;
  import net.minecraft.world.level.Level;
  import net.minecraft.world.level.block.Block;
  import net.minecraft.world.level.block.state.BlockBehaviour;
  import net.minecraft.world.level.block.state.BlockState;
  import net.minecraft.world.phys.BlockHitResult;

  public class PortalFrameBlock extends Block {
      public PortalFrameBlock(BlockBehaviour.Properties properties) {
          super(properties);
      }

      @Override
      public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                   Player player, InteractionHand hand, BlockHitResult hit) {
          // Only activate if player is holding a gold ingot
          if (!player.getItemInHand(hand).is(Items.GOLD_INGOT)) {
              return InteractionResult.PASS;
          }

          // Scan nearby positions for a valid interior candidate.
          // The player can click any frame block, so we check a small area
          // around the clicked position — including horizontally — to find
          // the interior bottom regardless of which frame block was clicked.
          for (int dx = -1; dx <= 1; dx++) {
              for (int dy = -2; dy <= 3; dy++) {
                  BlockPos candidate = pos.offset(dx, dy, 0);
                  if (PortalDetector.isValidFrame(level, candidate)) {
                      if (!level.isClientSide) {
                          PortalDetector.fillPortal(level, candidate);
                      }
                      return InteractionResult.SUCCESS;
                  }
              }
          }

          return InteractionResult.PASS;
      }
  }
  ```

- [ ] **Step 5: Create portal block assets**

  Create `src/main/resources/assets/odyssey/models/block/odyssey_portal.json`:
  ```json
  {
    "parent": "block/cube_all",
    "textures": {
      "all": "odyssey:block/odyssey_portal"
    }
  }
  ```

  Create `src/main/resources/assets/odyssey/blockstates/odyssey_portal.json`:
  ```json
  {
    "variants": {
      "": { "model": "odyssey:block/odyssey_portal" }
    }
  }
  ```

  Create `src/main/resources/assets/odyssey/textures/block/odyssey_portal.png` — a 16x16 PNG. Use a dark teal/blue-green color with a wavy ripple pattern to suggest water. We will animate this in a later phase.

- [ ] **Step 6: Add portal names to en_us.json**

  Update `src/main/resources/assets/odyssey/lang/en_us.json`:
  ```json
  {
    "item.odyssey.laurel_wreath": "Laurel Wreath",
    "block.odyssey.portal_frame": "Gate of Eurydice",
    "item.odyssey.portal_frame": "Gate of Eurydice",
    "block.odyssey.odyssey_portal": "Portal to the Underworld",
    "itemGroup.odyssey": "Odyssey"
  }
  ```

- [ ] **Step 7: Build and verify**

  ```
  gradlew build
  ```
  Expected: `BUILD SUCCESSFUL`

- [ ] **Step 8: In-game test**

  Launch the client. In a creative world:
  1. Build the gate frame: two 3-block pillars with a capstone on top and a base row connecting them (5 frame blocks wide at base, 3 tall on each side, 3 wide on top)
  2. Hold a gold ingot and right-click any frame block
  3. The interior should fill with the teal portal blocks
  4. Gold `FLASH` particles should appear on the portal surface

  If all three work, the portal is complete.

- [ ] **Step 9: Commit**

  ```
  git add .
  git commit -m "feat: add Gates of Eurydice portal with gold particle activation"
  git push
  ```

---

## Phase 1 Complete

At this point the mod has:
- A working Laurel Wreath helmet item (craftable, equippable)
- A portal frame block (placeable, solid)
- An activatable portal (detects frame shape, fills with particles on right-click with gold ingot)
- Everything committed to GitHub with a clean history

**Next phase** will add: a custom Underworld dimension the portal teleports you to, the first quest choice screen, and the beginning of the Hercules labor sequence.
