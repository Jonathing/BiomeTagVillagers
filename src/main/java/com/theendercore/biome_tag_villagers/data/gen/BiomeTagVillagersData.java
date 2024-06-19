package com.theendercore.biome_tag_villagers.data.gen;

import com.theendercore.biome_tag_villagers.BiomeTagVillagers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.theendercore.biome_tag_villagers.BiomeTagVillagers.log;

public class BiomeTagVillagersData {
	public static void onInitializeDataGenerator(GatherDataEvent event) {
		log.info("Hello from DataGen");

		event.getGenerator().addProvider(event.includeServer(), new BiomeTagProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
	}

	static class BiomeTagProvider extends BiomeTagsProvider {
		public BiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
			super(output, provider, BiomeTagVillagers.MODID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider wrapperLookup) {
			this.getOrCreateRawBuilder(BiomeTagVillagers.VILLAGER_DESERT)
					.addTag(Tags.Biomes.IS_DESERT.location())
					.addTag(Tags.Biomes.IS_BADLANDS.location());
			getOrCreateRawBuilder(BiomeTagVillagers.VILLAGER_JUNGLE)
					.addTag(Tags.Biomes.IS_JUNGLE.location());
			getOrCreateRawBuilder(BiomeTagVillagers.VILLAGER_SAVANNA)
					.addTag(Tags.Biomes.IS_SAVANNA.location());
			getOrCreateRawBuilder(BiomeTagVillagers.VILLAGER_SNOWY)
					.addTag(Tags.Biomes.IS_SNOWY.location())
					.addTag(Tags.Biomes.IS_AQUATIC_ICY.location());
			getOrCreateRawBuilder(BiomeTagVillagers.VILLAGER_SWAMP)
					.addTag(Tags.Biomes.IS_SWAMP.location());
			getOrCreateRawBuilder(BiomeTagVillagers.VILLAGER_TAIGA)
					.addTag(Tags.Biomes.IS_TAIGA.location());

		}
	}
}
