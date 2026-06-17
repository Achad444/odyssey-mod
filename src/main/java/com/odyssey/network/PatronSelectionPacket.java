package com.odyssey.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PatronSelectionPacket {
    private final String godName;

    public PatronSelectionPacket(String godName) {
        this.godName = godName;
    }

    public static void encode(PatronSelectionPacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.godName);
    }

    public static PatronSelectionPacket decode(FriendlyByteBuf buf) {
        return new PatronSelectionPacket(buf.readUtf());
    }

    public static void handle(PatronSelectionPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            CompoundTag data = player.getPersistentData();
            CompoundTag odysseyData = data.contains("odyssey")
                ? data.getCompound("odyssey")
                : new CompoundTag();

            odysseyData.putString("patron_god", packet.godName);
            data.put("odyssey", odysseyData);
        });
        ctx.get().setPacketHandled(true);
    }
}
