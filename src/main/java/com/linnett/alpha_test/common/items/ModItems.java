package com.linnett.alpha_test.common.items;

import com.linnett.alpha_test.Alpha_test;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEM =
            DeferredRegister.create(ForgeRegistries.ITEMS, Alpha_test.MODID);

    public static final Rarity RARITY_RAINBOW = Rarity.create("alpha_test:rainbow",
            style -> style.withColor(Color.HSBtoRGB((System.currentTimeMillis() % 10000) / 10000F, 0.8f, 1f)));
    public static final Rarity RARITY_BUG = Rarity.create("alpha_test:bug",
            style -> style.withColor(0xE64DFF));
    public static final Rarity RARITY_CLEAN = Rarity.create("alpha_test:clean",
            style -> style.withColor(0x8EB6FF));



    public static final RegistryObject<Item> BUG = ITEM.register("bug",
            () -> new Item(new Item.Properties().rarity(RARITY_BUG)));

    public static final RegistryObject<Item> CLEAN_APPLE = ITEM.register("clean_apple",
            () -> new Item(new Item.Properties().rarity(RARITY_CLEAN)));

    public static final RegistryObject<Item> CLEAN_CORE = ITEM.register("clean_core",
            () -> new Item(new Item.Properties().rarity(RARITY_CLEAN)));

    public static final RegistryObject<Item> CLEAN_INGOT = ITEM.register("clean_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THREE_D_PICKAXE = ITEM.register("3d_pickaxe",
            () -> new Item(new Item.Properties().rarity(RARITY_RAINBOW)));

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }
}
