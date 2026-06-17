package com.odyssey.network;

import com.odyssey.OdysseyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class OdysseyNetwork {
    private static final String PROTOCOL = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(OdysseyMod.MOD_ID, "main"),
        () -> PROTOCOL,
        PROTOCOL::equals,
        PROTOCOL::equals
    );

    private static int id = 0;

    public static void register() {
        CHANNEL.registerMessage(id++,
            PatronSelectionPacket.class,
            PatronSelectionPacket::encode,
            PatronSelectionPacket::decode,
            PatronSelectionPacket::handle
        );
    }
}
