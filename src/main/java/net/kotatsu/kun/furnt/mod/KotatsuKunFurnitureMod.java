package net.kotatsu.kun.furnt.mod;

import net.fabricmc.api.ModInitializer;

import net.kotatsu.kun.furnt.mod.block.ModBlocks;
import net.kotatsu.kun.furnt.mod.item.ModItems;
import net.kotatsu.kun.furnt.mod.item.ModItemsGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KotatsuKunFurnitureMod implements ModInitializer {
	public static final String MOD_ID="kotatsukun-furniture-mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize(){

		ModItemsGroup.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
	}
}