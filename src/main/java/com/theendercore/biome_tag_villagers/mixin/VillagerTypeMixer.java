package com.theendercore.biome_tag_villagers.mixin;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


import java.util.concurrent.atomic.AtomicBoolean;

import static com.theendercore.biome_tag_villagers.BiomeTagVillagers.getBiomeTagToType;
import static net.minecraft.village.VillagerType.PLAINS;

@Mixin(VillagerType.class)
public class VillagerTypeMixer {


    @Inject(at = @At("HEAD"), method = "forBiome", cancellable = true)
    private static void forBiome(RegistryEntry<Biome> biomeEntry, CallbackInfoReturnable<VillagerType> cir) {
        AtomicBoolean seen = new AtomicBoolean(false);
        getBiomeTagToType().forEach((biomeTag, villagerType) -> {
            if (biomeEntry.isIn(biomeTag)) {
                cir.setReturnValue(villagerType);
                seen.set(true);
            }
        });
        if (!seen.get()) cir.setReturnValue(PLAINS);
    }


}