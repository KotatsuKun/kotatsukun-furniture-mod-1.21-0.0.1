package net.kotatsu.kun.furnt.mod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.kotatsu.kun.furnt.mod.KotatsuKunFurnitureMod;
import net.kotatsu.kun.furnt.mod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemsGroup {
    public static final ItemGroup MOD_GROUP1 = Registry.register(Registries.ITEM_GROUP, Identifier.of(KotatsuKunFurnitureMod.MOD_ID, "kotatsu_blocks" ),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.modBlocks"))
                    .icon(() -> new ItemStack(ModBlocks.WALLPAPER_1)).entries((displayContext, entries) -> {

                        entries.add(ModBlocks.WALLPAPER_1);
                        entries.add(ModBlocks.WALL_1);
                        entries.add(ModBlocks.WALL_2);
                        entries.add(ModBlocks.MARBLE_1);
                        entries.add(ModBlocks.PAVING_1);
                        entries.add(ModBlocks.ROOF_1);

                    }).build());

    public static final ItemGroup MOD_GROUP2 = Registry.register(Registries.ITEM_GROUP, Identifier.of(KotatsuKunFurnitureMod.MOD_ID, "kotatsu_furniture"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.modFurniture"))
                    .icon(() -> new ItemStack(ModBlocks.CHAIR_1)).entries((displayContext, entries) -> {

                        entries.add(ModBlocks.CHAIR_1);
                        entries.add(ModBlocks.TABLE_1);
                        entries.add(ModBlocks.SOFA_1);
                        entries.add(ModBlocks.SOFA_2);
                        entries.add(ModBlocks.SOFA_3);
                        entries.add(ModBlocks.BATHTUB);
                        entries.add(ModBlocks.MIRROR);
                        entries.add(ModBlocks.SINK);

                    }).build());



    public static void registerItemGroups(){
        KotatsuKunFurnitureMod.LOGGER.info("Registering Item Group for " +KotatsuKunFurnitureMod.MOD_ID);
    }
}
