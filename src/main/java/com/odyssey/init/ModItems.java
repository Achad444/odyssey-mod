package com.odyssey.init;

import com.odyssey.OdysseyMod;
import com.odyssey.item.LaurelWreathItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, OdysseyMod.MOD_ID);

    public static final RegistryObject<Item> LAUREL_WREATH =
        ITEMS.register("laurel_wreath", LaurelWreathItem::new);
}
