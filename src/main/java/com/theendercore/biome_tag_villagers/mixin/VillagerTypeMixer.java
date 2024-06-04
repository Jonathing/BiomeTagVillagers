package com.theendercore.biome_tag_villagers.mixin;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

import static com.theendercore.biome_tag_villagers.BiomeTagVillagers.getBiomeTagToType;
import static net.minecraft.village.VillagerType.PLAINS;

@Mixin(VillagerType.class)
public class VillagerTypeMixer {

	@Unique
	private final static Random BiomeTag$random = Random.create();

	@Inject(at = @At("RETURN"), method = "forBiome", cancellable = true)
	private static void typeFromBiomeTag(RegistryEntry<Biome> biomeEntry, CallbackInfoReturnable<VillagerType> cir) {
		List<VillagerType> types = getBiomeTagToType().entrySet().stream()
				.filter(pair -> biomeEntry.isIn(pair.getKey()))
				.map(Map.Entry::getValue)
				.toList();

		cir.setReturnValue(types.isEmpty() ? PLAINS : types.get(BiomeTag$random.nextBetween(0, types.size() - 1)));
	}
}
