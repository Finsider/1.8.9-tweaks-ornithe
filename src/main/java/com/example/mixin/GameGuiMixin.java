package com.example.mixin;

import net.minecraft.client.gui.GameGui;
import net.minecraft.client.render.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameGui.class)
public class GameGuiMixin {

    @ModifyVariable(
            method = "renderScoreboardObjective",
            at = @At("STORE"),
            ordinal = 1
    )
    private String removeRedScore(String k) {
        return null;
    }

    @Redirect(
            method = "renderScoreboardObjective",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TextRenderer;getWidth(Ljava/lang/String;)I", ordinal = 1)
    )
    private int modifyScoreboardWidth(TextRenderer instance, String text) {
        int idx = text.lastIndexOf(':');
        if (idx >= 0) text = text.substring(0, idx);

        return Math.max(instance.getWidth(text) - 1, 0);
    }
}
