package com.example.mixin;


import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldChunk.class)
public class WorldChunkMixin {

    @Inject(method = "lightGaps", at = @At("HEAD"), cancellable = true)
    private void deleteLightGaps(boolean checkOne, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "populateLight", at = @At("HEAD"), cancellable = true)
    private void deletePopulateLight(CallbackInfo ci) {
        ci.cancel();
    }
}
