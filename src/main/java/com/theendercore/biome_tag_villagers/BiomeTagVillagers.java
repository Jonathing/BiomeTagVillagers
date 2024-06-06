package com.theendercore.biome_tag_villagers;

import com.google.common.collect.Maps;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

import static net.minecraft.village.VillagerType.*;

public class BiomeTagVillagers implements ModInitializer {
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

    @Override
    public void onInitialize() {
        log.info("Tag time :gun:!");
    }
	@SuppressWarnings("unused")
    static void addType(TagKey<Biome> tag, VillagerType type) {
        BIOME_TAG_TO_TYPE.put(tag, type);
    }
    public static Map<TagKey<Biome>, VillagerType> getBiomeTagToType() {
        return BIOME_TAG_TO_TYPE;
    }
    private static TagKey<Biome> of(String id) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(MODID, id));
    }
}