package com.theendercore.biome_tag_villagers.mixin;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

import static com.theendercore.biome_tag_villagers.BiomeTagVillagers.getBiomeTagToType;
import static net.minecraft.world.entity.npc.VillagerType.PLAINS;

@Mixin(VillagerType.class)
public class VillagerTypeMixer {

	@Unique
	private final static RandomSource BiomeTag$random = RandomSource.create();

	@Inject(at = @At("RETURN"), method = "byBiome", cancellable = true)
	private static void typeFromBiomeTag(Holder<Biome> biomeEntry, CallbackInfoReturnable<VillagerType> cir) {
		List<VillagerType> types = getBiomeTagToType().entrySet().stream()
				.filter(pair -> biomeEntry.is(pair.getKey()))
				.map(Map.Entry::getValue)
				.toList();

		cir.setReturnValue(types.isEmpty() ? PLAINS : types.get(BiomeTag$random.nextIntBetweenInclusive(0, types.size() - 1)));
	}
}
