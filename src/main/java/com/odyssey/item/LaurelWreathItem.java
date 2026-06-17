package com.odyssey.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;

public class LaurelWreathItem extends ArmorItem {
    public LaurelWreathItem() {
        super(ArmorMaterials.GOLD, Type.HELMET, new Item.Properties().stacksTo(1));
    }
}
