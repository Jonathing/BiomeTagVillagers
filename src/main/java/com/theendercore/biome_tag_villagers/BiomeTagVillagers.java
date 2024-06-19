package com.theendercore.biome_tag_villagers;

import com.google.common.collect.Maps;

import com.theendercore.biome_tag_villagers.data.gen.BiomeTagVillagersData;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

import static net.minecraft.world.entity.npc.VillagerType.*;

@Mod(BiomeTagVillagers.MODID)
public class BiomeTagVillagers {
    public static final String MODID = "biome_tag_villagers";
    public static final Logger log = LoggerFactory.getLogger(MODID);
    public static final TagKey<Biome> VILLAGER_DESERT = of("villager_desert");
    public static final TagKey<Biome> VILLAGER_JUNGLE = of("villager_jungle");
    public static final TagKey<Biome> VILLAGER_SAVANNA = of("villager_savanna");
    public static final TagKey<Biome> VILLAGER_SNOWY = of("villager_snowy");
    public static final TagKey<Biome> VILLAGER_SWAMP = of("villager_swamp");
    public static final TagKey<Biome> VILLAGER_TAIGA = of("villager_taiga");
    @Unique
    private static Map<TagKey<Biome>, VillagerType> BIOME_TAG_TO_TYPE =
            Util.make(Maps.newHashMap(), map -> {
                map.put(VILLAGER_DESERT, DESERT);
                map.put(VILLAGER_JUNGLE, JUNGLE);
                map.put(VILLAGER_SAVANNA, SAVANNA);
                map.put(VILLAGER_SNOWY, SNOW);
                map.put(VILLAGER_SWAMP, SWAMP);
                map.put(VILLAGER_TAIGA, TAIGA);
            });

    public BiomeTagVillagers(IEventBus eventBus, ModContainer container) {
        log.info("Tag time :gun:!");

        eventBus.addListener(BiomeTagVillagersData::onInitializeDataGenerator);
    }
	@SuppressWarnings("unused")
    static void addType(TagKey<Biome> tag, VillagerType type) {
        BIOME_TAG_TO_TYPE.put(tag, type);
    }
    public static Map<TagKey<Biome>, VillagerType> getBiomeTagToType() {
        return BIOME_TAG_TO_TYPE;
    }
    private static TagKey<Biome> of(String id) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(MODID, id));
    }
}