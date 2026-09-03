package com.fin.tweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow
    private boolean lightMapDirty;
    @Shadow
    private Minecraft minecraft;

    @Unique
    private boolean hasCachedLightmap = false;

    @ModifyExpressionValue(
            method = "updateLightMap",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/options/GameOptions;gamma:F",
                    opcode = Opcodes.GETFIELD
            )
    )
    private float forceFullbright(float gamma) {
        return 100;
    }

    // inspired by thecountrox's light map caching
    @Inject(method = "updateLightMap", at = @At("HEAD"), cancellable = true)
    private void cacheLightMap(float tickDelta, CallbackInfo ci) {
        if (!hasCachedLightmap && this.lightMapDirty && this.minecraft.world != null) {
            hasCachedLightmap = true;
            return;
        }

        if (hasCachedLightmap) {
            ci.cancel();
        }
    }
}
