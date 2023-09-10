# Biome Tag Villagers

A mod that gives you control over how a villager will look in a biome via a set of tag lists!
---
A small server side mod that changes the current hard coded villager biomes to instead have the game read a set of tags
to determine what variant a villager will be in what biome.
If a biome is in multiple tag lists, it will randomize between the two.

### Dependencies
- Fabric api (Turns out you need this to run the mod initializer)

### Configuration
The mod has only server side config in the format of tags. It adds 6 new tags for each villager type (eg. snowy, taiga,
savanna). This is just the standard minecraft biome tag.

The Example datapack can be found [here](https://github.com/theendercore/BiomeTagVillagers/tree/master/example).

### For Mod Developers
If you are a mod developer adding new villager types. You will just want to add the villager types to the map
with `addType`.

Maven repository:

```gradle
repositories {
    maven {
        name = "Modrinth"
        url = "https://api.modrinth.com/maven"
    }
}
```

Dependency:

```gradle
    modImplementation "maven.modrinth:biome-tag-villagers:1.0.0"
```



[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/M4M7DWJCH)