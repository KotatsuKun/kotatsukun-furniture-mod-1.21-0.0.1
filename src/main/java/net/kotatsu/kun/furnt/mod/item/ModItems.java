package net.kotatsu.kun.furnt.mod.item;


import net.kotatsu.kun.furnt.mod.KotatsuKunFurnitureMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item icon = registerItem("icon", new Item(new Item.Settings()));

    public static final Item tab = registerItem("tab", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(KotatsuKunFurnitureMod.MOD_ID, name), item);
    }

    public static void registerModItems(){

        KotatsuKunFurnitureMod.LOGGER.info("Registering Mod items for " + KotatsuKunFurnitureMod.MOD_ID);
    }
}