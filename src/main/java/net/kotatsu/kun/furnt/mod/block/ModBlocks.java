package net.kotatsu.kun.furnt.mod.block;

import net.kotatsu.kun.furnt.mod.KotatsuKunFurnitureMod;
import net.kotatsu.kun.furnt.mod.block.custom.*;
import net.kotatsu.kun.furnt.mod.block.custom.FlowerPotBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block WALLPAPER_1 = registerBlock("wallpaper_1_block",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));

    public static final Block MARBLE_1 = registerBlock("marble_1_block",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_GLAZED_TERRACOTTA)));

    public static final Block PAVING_1 = registerBlock("paving_1_block",
            new Block(AbstractBlock.Settings.copy(Blocks.BRICK_WALL)));

    public static final Block WALL_1 = registerBlock("wall_1_block",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_CONCRETE)));

    public static final Block WALL_2 = registerBlock("wall_2_block",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_CONCRETE)));

    public static final Block ROOF_1 = registerBlock("roof_1_block",
            new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));


    //custom blocks
    public static final Block CHAIR_1 = registerBlock("chair_1_wood",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block TABLE_1 = registerBlock("table_1_wood",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block SOFA_1 = registerBlock("sofa",
            new LeftSofaBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block SOFA_2 = registerBlock("sofa2",
            new MiddleSofaBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block SOFA_3 = registerBlock("sofa3",
            new RightSofaBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block SINK = registerBlock("sink",
            new SinkBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).nonOpaque()));

    public static final Block BATHTUB = registerBlock("bathtub",
            new BathtubBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).nonOpaque()));

    public static final Block MIRROR = registerBlock("mirror",
            new MirrorBlock(AbstractBlock.Settings.copy(Blocks.GLASS).nonOpaque()));

    public static final Block FLOWERPOT = registerBlock("flower_pot_1",
            new FlowerPotBlock(AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque()));

    public static final Block WINDOW1 = registerBlock("window",
            new WindowBlock(AbstractBlock.Settings.copy(Blocks.GLASS_PANE).nonOpaque()));

    public static final Block KAONASHI = registerBlock("kaonashi",
            new Kaonashi(AbstractBlock.Settings.copy(Blocks.GLASS_PANE).nonOpaque()));

    public static final Block TOTORO = registerBlock("totoro",
            new Totoro(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).nonOpaque()));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(KotatsuKunFurnitureMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(KotatsuKunFurnitureMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks(){
        KotatsuKunFurnitureMod.LOGGER.info("Registering Modblocks for " + KotatsuKunFurnitureMod.MOD_ID);
    }

}
