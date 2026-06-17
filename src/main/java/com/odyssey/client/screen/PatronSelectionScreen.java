package com.odyssey.client.screen;

import com.odyssey.network.OdysseyNetwork;
import com.odyssey.network.PatronSelectionPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PatronSelectionScreen extends Screen {

    private static final String[][] GODS = {
        { "zeus",       "Zeus",       "Lord of Olympus. Master of thunder and sky." },
        { "poseidon",   "Poseidon",   "God of the sea, earthquakes, and storms." },
        { "hades",      "Hades",      "King of the Underworld. Ruler of the dead." },
        { "athena",     "Athena",     "Goddess of wisdom, warfare, and crafts." },
        { "apollo",     "Apollo",     "God of the sun, music, and prophecy." },
        { "artemis",    "Artemis",    "Goddess of the hunt and the moon." },
        { "ares",       "Ares",       "God of war, bloodshed, and violence." },
        { "hermes",     "Hermes",     "Messenger of the gods. Guide of travelers." },
        { "hephaestus", "Hephaestus", "God of the forge, fire, and craftsmanship." },
        { "aphrodite",  "Aphrodite",  "Goddess of love, beauty, and desire." },
        { "dionysus",   "Dionysus",   "God of wine, festivity, and madness." },
        { "demeter",    "Demeter",    "Goddess of the harvest and the seasons." }
    };

    private String hoveredDescription = "";

    public PatronSelectionScreen() {
        super(Component.literal("Choose Your Patron"));
    }

    @Override
    protected void init() {
        int btnW = 110;
        int btnH = 20;
        int cols = 4;
        int padX = 8;
        int padY = 6;
        int totalW = cols * btnW + (cols - 1) * padX;
        int startX = this.width / 2 - totalW / 2;
        int startY = 70;

        for (int i = 0; i < GODS.length; i++) {
            String[] god = GODS[i];
            int col = i % cols;
            int row = i / cols;
            int x = startX + col * (btnW + padX);
            int y = startY + row * (btnH + padY);

            this.addRenderableWidget(Button.builder(
                Component.literal(god[1]),
                btn -> selectGod(god[0]))
                .pos(x, y)
                .size(btnW, btnH)
                .tooltip(net.minecraft.client.gui.components.Tooltip.create(
                    Component.literal(god[2])))
                .build());
        }
    }

    private void selectGod(String godKey) {
        OdysseyNetwork.CHANNEL.sendToServer(new PatronSelectionPacket(godKey));
        this.onClose();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        graphics.drawCenteredString(this.font,
            Component.literal("Choose Your Patron God").withStyle(s -> s.withBold(true)),
            this.width / 2, 20, 0xFFD700);

        graphics.drawCenteredString(this.font,
            Component.literal("Hover over a name to learn more. Your choice shapes the gifts they will offer."),
            this.width / 2, 36, 0xAAAAAA);

        graphics.drawCenteredString(this.font,
            Component.literal("This choice is permanent.").withStyle(s -> s.withItalic(true)),
            this.width / 2, 50, 0xFF6666);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
