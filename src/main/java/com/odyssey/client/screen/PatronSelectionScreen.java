package com.odyssey.client.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PatronSelectionScreen extends Screen {

    public PatronSelectionScreen() {
        super(Component.literal("Choose Your Patron"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
