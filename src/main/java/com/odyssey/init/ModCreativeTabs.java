package com.odyssey.init;

import com.odyssey.OdysseyMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
        DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, OdysseyMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ODYSSEY_TAB = TABS.register("odyssey_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.odyssey"))
            .icon(() -> new ItemStack(ModItems.LAUREL_WREATH.get()))
            .displayItems((params, output) -> {
                output.accept(ModItems.LAUREL_WREATH.get());
                output.accept(ModItems.SCROLL_OF_MESSINA.get());
                output.accept(ModItems.TRIDENT_OF_THE_DEEP.get());
            })
            .build());
}
