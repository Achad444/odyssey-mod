package com.odyssey.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TieredItem;

public class TridentOfTheDeepItem extends TieredItem implements SoulBound {

    public TridentOfTheDeepItem() {
        super(Tiers.NETHERITE, new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.EPIC)
            .fireResistant()
        );
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
