package com.linnett.alpha_test.common.tab;

import com.linnett.alpha_test.Alpha_test;
import com.linnett.alpha_test.common.blocks.ModBlocks;
import com.linnett.alpha_test.common.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;


import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Alpha_test.MODID);

    public static final Supplier<CreativeModeTab> BUG = CREATIVE_MODE_TAB.register("alphatest",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.NULL.get()))
                    .title(Component.translatable("creativetab.alpha_test.alphatest"))
                    .displayItems((itemDisplayParameters, output) -> {


                        output.accept(ModItems.BUG.get());
                        output.accept(ModBlocks.NO_TEXTURE_BLOCK.get());
                        output.accept(ModBlocks.CRACKED_NO_TEXTURE_BLOCK.get());
                        output.accept(ModBlocks.FAKE_NO_TEXTURE_BLOCK.get());
                        output.accept(ModBlocks.NO_TEXTURE_LAMP.get());
                        output.accept(ModBlocks.NULL.get());

                        output.accept(ModItems.CLEAN_CORE.get());
                        output.accept(ModItems.CLEAN_INGOT.get());
                        output.accept(ModItems.CLEAN_APPLE.get());

                        output.accept(ModItems.THREE_D_PICKAXE.get());
                        output.accept(ModBlocks.RAINBOW_BLOCK.get());

                        output.accept(ModBlocks.ALPHA_GRASS.get());
                        output.accept(ModBlocks.ALPHA_DIRT.get());
                        output.accept(ModBlocks.ALPHA_COBBLESTONE.get());
                        output.accept(ModBlocks.BLUE_ROSE.get());
                        output.accept(ModBlocks.GRASS.get());

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
