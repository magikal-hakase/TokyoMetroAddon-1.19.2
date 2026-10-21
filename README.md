# TokyoMetroAddon — Forge 1.20.1 / MTR 3 Port

**1.20.1 Ported Version by Luminiflux**

Original project (Fabric 1.19.2): [https://github.com/magikal-hakase/TokyoMetroAddon-1.19.2](https://github.com/magikal-hakase/TokyoMetroAddon-1.19.2)

This repository contains a **full port** of the original mod (based on the original CurseForge release `tokyometroaddon-1.19.2-0.0.1hotfix-beta`, including all 49 blocks + the seat entity system) to **Minecraft 1.20.1 + Forge + MTR 3.x**.

## Version info

| Item | Version |
| --- | --- |
| Minecraft | 1.20.1 |
| Forge | 47.4.26 |
| ForgeGradle | 6.0.54 |
| Gradle | 8.1.1 |
| JDK | 17 |
| MTR (Minecraft Transit Railway) | 1.20.1-3.2.2-hotfix-2 (Forge) |
| Mappings | Mojang official 1.20.1 |
| Base | tokyometroaddon-1.19.2-0.0.1hotfix-beta (original release) |

## Building

```
set JAVA_HOME=<JDK17 path>
gradlew build
```

Output: `build/libs/tokyometroaddon-1.0.1.jar` (1.20.1 Ported Version by Luminiflux)

## Installation

1. Install Forge 1.20.1 (47.x).

2. Put `tokyometroaddon-1.0.1.jar` into `mods/`.

3. **MTR 3.2.2-hotfix-2 (Forge)** and **Architectury 9.1.12+forge (Forge 1.20.1)** are both required — MTR 3.2.2 depends on Architectury `[1.26.37,)` at runtime.

* MTR download: [https://modrinth.com/mod/minecraft-transit-railway](https://modrinth.com/mod/minecraft-transit-railway)

* Architectury download: [https://modrinth.com/mod/architectury-api](https://modrinth.com/mod/architectury-api) (use **9.1.12+forge**; **do not use 9.2.14**, its mixins crash on 1.20.1)

## Differences from the original release (Fabric 1.19.2 0.0.1hotfix-beta)

* **Build system**: fabric-loom → ForgeGradle; Yarn mappings → Mojang official mappings.
* **Entry point**: `fabric.mod.json` + `ModInitializer` → `mods.toml` + `@Mod`.
* **Registration**: `Registry.register` / `FabricBlockSettings` → `DeferredRegister` + `BlockBehaviour.Properties`.
* **Creative tab**: `FabricItemGroupBuilder` → `CreativeModeTab` DeferredRegister + `BuildCreativeModeTabContentsEvent` (50 entries: toolbox + 49 blocks, same order as the original).
* **Client render layers**: `BlockRenderLayerMap` → `ItemBlockRenderTypes` (set in `FMLClientSetupEvent`; 13 cutout blocks + `NoopRenderer` for the entity renderer — `EmptyEntityRenderer` no longer exists in 1.20.1).
* **Two placeholder mixins** (`ExampleMixin` / `ExampleClientMixin`, leftover template) removed.
* **`data/.../tags/painting_variant/placeable.json`** (dead file left in the original release; the painting system was removed in 1.19.3, and its referenced `notice/request/keep_left_a/keep_left_b` never existed) deleted to avoid a tag loading error at startup.
* **MTR dependency**: local jar under `libs/` (official Modrinth build, `MTR-forge-1.20.1-3.2.2-hotfix-2.jar`), referenced directly in `build.gradle` via `implementation files('libs/...')`. The jar is mojmap-obfuscated (method names are SRG numbers `m_XXXX_`; class names and parameter types use official names), and Forge's modlauncher remaps everything to official names at runtime.
* **Ticket barrier / vending machine overrides (porting key 1)**: `TokyoTicketBarrierBlock` / `TokyoTicketMachineBlock` extend MTR's `BlockTicketBarrier` / `BlockTicketMachine`. They override `getShape`/`getCollisionShape` **without `@Override` and without calling `super` methods of the same name** (at compile time the parent method names are SRG numbers; at runtime both sides are remapped to official names and the overrides take effect). Verified with `javap`: after reobf the mod's method names `m_5939_`/`m_5940_` exactly match the MTR jar.
* **Registration timing (porting key 2)**: Forge's `DeferredRegister` writes to the registry only at the registration event. All registered fields keep the `RegistryObject<T>` type, and `.get()` is only called at event callbacks / runtime use points (calling `.get()` during static initialization throws `NullPointerException: Registry Object not present`).
* **Entity system**: `SeatEntity` (no physics, size 0×0, `getPassengersRidingOffset() = -0.1`) + `TokyoMetroAddonEntities` (registration id `seat`, `MobCategory.MISC`). Right-clicking a metro bench spawns a seat entity and makes the player ride it.
* **BE registration id**: `guide_bell_device` (matches the release; GitHub master uses `guide_bell_device_sine`).
* **API name differences (1.20.1 vs 1.19.2 Yarn)**: `StairsBlock`→`StairBlock`; `MaterialColor`→`MapColor`; `VoxelShapes`→`Shapes`; `onStateReplaced`→`onRemove`; `getStateForNeighborUpdate`→`updateShape`; `DoorBlock(Properties)`→`DoorBlock(Properties, BlockSetType)`; `SoundType` moved to the `net.minecraft.world.level.block` package; `EmptyEntityRenderer`→`NoopRenderer`; `ResourceLocation.fromNamespaceAndPath`; `SoundEvents.UI_BUTTON_CLICK` returns `Holder.Reference<SoundEvent>` and requires `.value()`.

## Block list (49 blocks, same as the original release)

| Category | Blocks |
| --- | --- |
| Mini tiles | white / red / yellow / blue / cyan / green / brown / black `_mini_tile_block` |
| Tiles | white\_tile\_block, white / light\_gray / black `_small_tile_block` |
| Platform plates | white\_platform\_plate, light\_gray\_platform\_plate, white\_slatted\_wall\_panel_block |
| Stairs | metro\_stairs (with end states), light\_gray\_metro\_stairs |
| Aluminum spindles | aluminum\_spindle, aluminum\_spindle\_block, aluminum\_spindle\_slope |
| Lighting | fluorescent\_light (single/start/end splicing) |
| Guidance / caution | guidance\_block, caution\_block, caution\_block\_with\_guidance\_block, caution\_block\_row, caution\_block\_enclosedline, route\_marker\_blue, route\_marker\_white |
| Facilities | guide\_bell\_device (toolbox cycles sound), emergency\_exit\_sign, aisle\_guide\_sign, aisle\_guide\_sign\_both, indoor\_fire\_hydrant, stainless\_steel\_fence, metro\_bench (rideable), metal\_door, metal\_door\_locking |
| Posters | poster\_notice, poster\_request, poster\_keep\_left\_white, poster\_keep\_left\_blue, poster\_no\_smoking, framed\_poster (6 random variants) |
| Barrier / ticketing (MTR integration) | tokyo\_ticket\_barrier\_entrance, tokyo\_ticket\_barrier\_exit, tokyo\_ticket\_machine, tokyo\_fare\_adjustment\_machine, tokyo\_platform (reuses MTR `BlockPlatform`) |

Items: toolbox. Entities: seat.

> Note: `tokyo_apg_door` has blockstate/model resources in the original release but is **not registered by the release itself** (no registration entry in the code) — this port matches the original behavior and also does not register it.

## Verification (completed)

* `gradlew build` compiles cleanly (only `[removal]` deprecation warnings remain: `FMLJavaModLoadingContext.get()` and `ItemBlockRenderTypes.setRenderLayer`, both standard 1.20.1 Forge usage, no functional impact).
* Production Forge dedicated server smoke tests passed (two rounds): Forge 47.4.26 + MTR 3.2.2-hotfix-2 + Architectury 9.1.12+forge + this mod started together; mod construction succeeded (`Hello Forge world!`), sound registration succeeded, all 49 blocks + seat entity registered without errors, no mod-related ERROR/FATAL, server `Done (6.3s)`.
* Barrier/vending machine override mechanism verified at byte level (`javap` on the reobf jar: `m_5939_`/`m_5940_` match the MTR jar).
* Client (in-game rendering / creative tab) was not visually verified — this machine has no display environment; logically all blocks / items / BEs / entities / sounds / creative tab entries are registered and passed server-side verification.

The smoke-test server lives in `../.smoketest/` (production Forge server installed via the Forge installer + the three mods). Reproduce anytime with `java @user_jvm_args.txt @libraries/net/minecraftforge/forge/1.20.1-47.4.26/win_args.txt --nogui` (eula accepted).

## Toolchain (used for local builds)

Portable JDK 17 and Gradle 8.1.1 live in `.toolchain/` outside this project directory (same chats folder), so they do not pollute the system.
