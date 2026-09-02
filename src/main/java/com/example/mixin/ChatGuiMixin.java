package com.example.mixin;

import net.minecraft.client.gui.chat.ChatGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ChatGui.class, priority = 1001)
public class ChatGuiMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/chat/ChatGui;fill(IIIII)V", ordinal = 0))
    public void deleteChatBackground(int x1, int y1, int x2, int y2, int color) {
        //...
    }
}
