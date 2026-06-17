package com.odyssey;

import com.odyssey.init.ModCreativeTabs;
import com.odyssey.init.ModItems;
import com.odyssey.network.OdysseyNetwork;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OdysseyMod.MOD_ID)
public class OdysseyMod {
    public static final String MOD_ID = "odyssey";

    public OdysseyMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModCreativeTabs.TABS.register(bus);
        OdysseyNetwork.register();
    }
}
