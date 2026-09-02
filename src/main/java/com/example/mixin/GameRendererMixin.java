package com.example.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

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
}
