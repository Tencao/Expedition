package wtf.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import wtf.Core;
import wtf.config.GameplayConfig;
import wtf.config.MasterConfig;
import wtf.items.HomeScroll;
import wtf.items.SimpleItem;

public class WTFItems {

	public static Item sulfur;
	public static Item nitre;
	public static Item homescroll;
	
	public static void initItems(){
		sulfur = registerItem(new SimpleItem(), "itemSulfur");
		nitre = registerItem(new SimpleItem(), "itemNitre");
		if (MasterConfig.gameplaytweaks && GameplayConfig.homescroll){
			homescroll = registerItem(new HomeScroll(), "home_scroll");
		}
	}
	
	private static Item registerItem(Item item, String name){
		item.setUnlocalizedName(name);
		ForgeRegistries.ITEMS.register(item.setRegistryName(name));
		Core.proxy.registerItemRenderer(item);
		return item;
	}
	
}
