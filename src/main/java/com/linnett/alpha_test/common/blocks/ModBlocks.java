package com.linnett.alpha_test.common.blocks;


import com.linnett.alpha_test.Alpha_test;
import com.linnett.alpha_test.common.blocks.custom.CrackedNotextureBlock;
import com.linnett.alpha_test.common.blocks.custom.FakeNotextureBlock;
import com.linnett.alpha_test.common.blocks.custom.NotextureLampBlock;
import com.linnett.alpha_test.common.items.ModItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Alpha_test.MODID);




    public static final RegistryObject<Block> NO_TEXTURE_BLOCK = registerBlock("notexture_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CRACKED_NO_TEXTURE_BLOCK = registerBlock("cracked_notexture_block",
            () -> new CrackedNotextureBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> FAKE_NO_TEXTURE_BLOCK = registerBlock("fake_notexture_block",
            () -> new FakeNotextureBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> NO_TEXTURE_LAMP = registerBlock("notexture_lamp",
            () -> new NotextureLampBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> RAINBOW_BLOCK = registerBlock("rainbow_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> NULL = registerBlock("null",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));



    public static final RegistryObject<Block> ALPHA_GRASS = registerBlock("alpha_grass",
            () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> ALPHA_DIRT = registerBlock("alpha_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    public static final RegistryObject<Block> ALPHA_COBBLESTONE = registerBlock("alpha_cobblestone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> BLUE_ROSE = registerBlock("blue_rose",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 5,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));

    public static final RegistryObject<Block> POTTED_BLUE_ROSE = BLOCKS.register("potted_catmint",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.BLUE_ROSE,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> GRASS = registerBlock("grass",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS).noOcclusion().noCollission()));




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}