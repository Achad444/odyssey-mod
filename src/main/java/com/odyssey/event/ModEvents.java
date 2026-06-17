package com.odyssey.event;

import com.odyssey.OdysseyMod;
import com.odyssey.item.SoulBound;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = OdysseyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    private static final Map<UUID, List<ItemStack>> soulBoundStash = new HashMap<>();

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        List<ItemStack> toSave = new ArrayList<>();
        event.getDrops().removeIf(drop -> {
            if (drop.getItem().getItem() instanceof SoulBound) {
                toSave.add(drop.getItem().copy());
                return true;
            }
            return false;
        });

        if (!toSave.isEmpty()) {
            soulBoundStash.put(player.getUUID(), toSave);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        UUID id = event.getEntity().getUUID();
        List<ItemStack> saved = soulBoundStash.remove(id);
        if (saved == null) return;

        for (ItemStack stack : saved) {
            event.getEntity().getInventory().add(stack);
        }
    }
}
