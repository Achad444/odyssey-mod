package com.odyssey.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.odyssey.client.screen.PatronSelectionScreen;

@OnlyIn(Dist.CLIENT)
public class ScrollScreen extends Screen {

    private static final int PAGE_1 = 0;
    private static final int PAGE_2 = 1;
    private int currentPage = PAGE_1;

    private static final String[] TEXT_PAGE_1 = {
        "To whoever finds this — turn back.",
        "",
        "The Strait takes everything.",
        "The rocks have eyes.",
        "The water has teeth.",
        "",
        "I am the seventh to try this crossing.",
        "I have not seen the others come through.",
        "",
        "If you are reading this, you have",
        "already made it further than I did.",
        "",
        "Gods help you.",
        "They have not helped me.",
        "",
        "— Nikodemos of Corinth"
    };

    private static final String[] TEXT_PAGE_2 = {
        "You survived the Strait of Messina.",
        "",
        "Something on Olympus has noticed."
    };

    public ScrollScreen() {
        super(Component.literal("Scroll of Messina"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int bottomY = this.height - 50;

        if (currentPage == PAGE_1) {
            this.addRenderableWidget(Button.builder(
                Component.literal("Continue →"),
                btn -> {
                    currentPage = PAGE_2;
                    this.clearWidgets();
                    this.init();
                })
                .pos(centerX - 50, bottomY)
                .size(100, 20)
                .build());
        } else {
            this.addRenderableWidget(Button.builder(
                Component.literal("Choose Your Patron"),
                btn -> this.minecraft.setScreen(new PatronSelectionScreen()))
                .pos(centerX - 70, bottomY)
                .size(140, 20)
                .build());
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        int centerX = this.width / 2;
        int startY = 30;

        graphics.drawCenteredString(this.font,
            Component.literal("Scroll of Messina").withStyle(s -> s.withBold(true)),
            centerX, startY, 0xFFD700);

        String[] lines = currentPage == PAGE_1 ? TEXT_PAGE_1 : TEXT_PAGE_2;
        int color = currentPage == PAGE_1 ? 0xE8D5A3 : 0xFFFFFF;
        int y = startY + 20;

        for (String line : lines) {
            graphics.drawCenteredString(this.font, Component.literal(line), centerX, y, color);
            y += 11;
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
