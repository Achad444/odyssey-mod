package com.odyssey.init;

import com.odyssey.OdysseyMod;
import com.odyssey.item.LaurelWreathItem;
import com.odyssey.item.ScrollItem;
import com.odyssey.item.TridentOfTheDeepItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, OdysseyMod.MOD_ID);

    public static final RegistryObject<Item> LAUREL_WREATH =
        ITEMS.register("laurel_wreath", LaurelWreathItem::new);

    public static final RegistryObject<Item> SCROLL_OF_MESSINA =
        ITEMS.register("scroll_of_messina", ScrollItem::new);

    public static final RegistryObject<Item> TRIDENT_OF_THE_DEEP =
        ITEMS.register("trident_of_the_deep", TridentOfTheDeepItem::new);
}
