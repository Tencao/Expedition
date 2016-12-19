
package wtf.init;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wtf.Core;
import wtf.blocks.BlockDecoAnim;
import wtf.blocks.BlockCrackedStone;
import wtf.blocks.BlockFoxfire;
import wtf.blocks.BlockIcicle;
import wtf.blocks.BlockDecoStatic;
import wtf.blocks.BlockFireLeaves;
import wtf.blocks.BlockPatchFluid;
import wtf.blocks.BlockPatchIce;
import wtf.blocks.BlockRedCactus;
import wtf.blocks.BlockRoots;
import wtf.blocks.BlockSpeleothem;
import wtf.blocks.BlockWTFTorch;
import wtf.blocks.CustomOldLeaves;
import wtf.blocks.BlockMycorrack;
import wtf.blocks.OreNitre;
import wtf.blocks.redstone.RedstoneStalactite;
import wtf.config.CoreConfig;
import wtf.config.GameplayConfig;
import wtf.crafting.WCICTable;
import wtf.crafting.render.WCICTileEntity;
import wtf.gameplay.OreSandGoldNugget;
import wtf.items.ItemBlockState;
import wtf.worldgen.replacers.NetherrackReplacer;

public class WTFBlocks{


	public static HashMap<IBlockState, BlockSpeleothem> speleothemMap = new HashMap<IBlockState, BlockSpeleothem>();
	
	public static Block oreSandGold;
	public static Block oreNitre;
	
	public static Block icePatch;
	public static BlockIcicle icicle;
	public static Block foxfire;
	public static Block mossyDirt;
	public static BlockRoots roots;
	public static Block crackedStone;
	public static Block red_cactus;
	
	public static BlockPatchFluid waterPatch;
	public static BlockPatchFluid lavaPatch;
	public static BlockPatchFluid waterPatchStatic;
	public static BlockPatchFluid lavaPatchStatic;
	public static Block wcicTable;
	public static Block mycorrack;
	public static Block fireLeaves;
	public static Block ubcSand;
	
	public static void initBlocks(){	
		/*
		 * Replacers
		 */
		
		new NetherrackReplacer();
		
		oreNitre =  registerBlock(new OreNitre(), "nitre_ore");
		icePatch =  registerBlock(new BlockPatchIce(), "patchIce");
		icicle = (BlockIcicle) registerBlockItemSubblocks(new BlockIcicle(Blocks.ICE.getDefaultState()), 2, "icicle");
		foxfire = registerBlock(new BlockFoxfire(), "foxfire");
		roots = (BlockRoots) registerBlockItemSubblocks(new BlockRoots(), 4, "roots");
		oreSandGold = registerBlock(new OreSandGoldNugget(), "oreSandGold");
		crackedStone = registerBlock(new BlockCrackedStone(Blocks.STONE.getDefaultState()), "cracked_stone");
		mossyDirt = registerBlockItemSubblocks(new BlockDecoStatic(Blocks.DIRT.getDefaultState()), BlockDecoStatic.DecoType.values().length-1, "dirt0DecoStatic");
		
		red_cactus =  registerBlock(new BlockRedCactus(), "red_cactus");
		mycorrack = registerBlock(new BlockMycorrack().setHardness(0.4F), "mycorrack");
		//fireLeaves = registerBlock(new BlockFireLeaves(), "fireLeaves");
		
		
		waterPatch = (BlockPatchFluid) registerBlock(new BlockPatchFluid(Material.WATER), "patchWater");
		lavaPatch = (BlockPatchFluid) registerBlock(new BlockPatchFluid(Material.LAVA), "patchLava");
		waterPatchStatic = (BlockPatchFluid) registerBlock(new BlockPatchFluid(Material.WATER), "patchWaterStatic");
		lavaPatchStatic = (BlockPatchFluid) registerBlock(new BlockPatchFluid(Material.LAVA), "patchLavaStatic");
		
		waterPatch.otherState = waterPatchStatic.getDefaultState();
		lavaPatch.otherState = lavaPatchStatic.getDefaultState();
		waterPatchStatic.otherState = waterPatch.getDefaultState();
		lavaPatchStatic.otherState = lavaPatch.getDefaultState();
		
		
		
		for(Entry<IBlockState, IBlockState> entry : CoreConfig.StoneCobble.entrySet()){
			
			String stoneName = entry.getKey().getBlock().getRegistryName().toString().split(":")[1] + entry.getKey().getBlock().getMetaFromState(entry.getKey());
			String cobbleName = entry.getValue().getBlock().getRegistryName().toString().split(":")[1] + entry.getKey().getBlock().getMetaFromState(entry.getKey());
			
			registerBlockItemSubblocks(new BlockSpeleothem(entry.getKey()).setFrozen(stoneName + "Speleothem"), 6, stoneName + "Speleothem");// .setFrozen("stoneSpeleothem");
			registerBlockItemSubblocks(new BlockDecoAnim(entry.getKey()), BlockDecoAnim.ANIMTYPE.values().length-1, stoneName+"DecoAnim");
			registerBlockItemSubblocks(new BlockDecoStatic(entry.getKey()), BlockDecoStatic.DecoType.values().length-1, stoneName+"DecoStatic");
				
		}
		
		registerBlockItemSubblocks(new RedstoneStalactite(false).setFrozen("redstoneSpeleothem"), 6, "redstoneSpeleothem");// .setFrozen("stoneSpeleothem");
		registerBlockItemSubblocks(new RedstoneStalactite(false).setFrozen("redstoneSpeleothem_on"), 6, "redstoneSpeleothem_on");// .setFrozen("stoneSpeleothem");
		

		//registerBlockItemSubblocks(new BlockSpeleothem(Blocks.STONE.getDefaultState()).setFrozen("stoneSpeleothem"), 6, "stoneSpeleothem");// .setFrozen("stoneSpeleothem");
		//registerBlockItemSubblocks(new BlockSpeleothem(Blocks.SANDSTONE.getDefaultState()).setFrozen("sandstoneSpeleothem"), 6, "sandstoneSpeleothem");//);
		//decoStone = registerBlockItemSubblocks(new AnimatedBlock(Blocks.STONE.getDefaultState()), 2, "animStone");
		//registerBlock(new BlockMossy(Blocks.STONE.getDefaultState()), "overlayStone");
		
		wcicTable = registerBlock(new WCICTable(), "wcic_table");
		GameRegistry.registerTileEntity(WCICTileEntity.class, "WCICTable");

		//BlockWTFTorch.torch_on = registerBlock(new BlockWTFTorch(true), "torch_on");
		/*
		if (CoreConfig.gameplaytweaks && GameplayConfig.torchLifespan > -1){
			
			BlockWTFTorch.torch_off = new BlockWTFTorch(false);
			BlockWTFTorch.torch_off.setRegistryName("torch");
			//BlockWTFTorch.torch_off.setUnlocalizedName("torch");

			System.out.println("Attempting torch replacement");
			try {
				GameRegistry.addSubstitutionAlias("minecraft:torch", GameRegistry.Type.BLOCK, BlockWTFTorch.torch_off);

			} catch (ExistingSubstitutionException e) {
				e.printStackTrace();
			}
			
		
		}
		*/
		
		//Alias for Leaves
		System.out.println("Attempting leaf replacement");
		//two problems- first, it cannot find the model
		
		//Second- RTG tree leaves are being broken by aliasing- Why?  Are they already aliasing it?
		//Look up the RTG thread to see if they've already tried to address this problem
		/*
		try {
			GameRegistry.addSubstitutionAlias("minecraft:leaves", GameRegistry.Type.BLOCK, new CustomOldLeaves());

		} catch (ExistingSubstitutionException e) {
			e.printStackTrace();
		}
*/

	}

	public static Block registerBlock(Block block, String name){
		block.setRegistryName(name);
		block.setUnlocalizedName(name);
		GameRegistry.register(block);
		ItemBlock temp = (ItemBlock) new ItemBlock(block).setUnlocalizedName(name).setRegistryName(name).setHasSubtypes(true);
		GameRegistry.register(temp);
		Core.proxy.registerItemRenderer(block);
		return block;
	}
	
	/**
	 * Called to register blocks with subblocks that should appear in the inventory
	 * Requires a blockstate json, with values for both the block models and inventory models
	 * @param block
	 * @param meta - value of the maximus metadata
	 * @param name
	 * @return
	 */
	public static Block registerBlockItemSubblocks(Block block, int meta, String name){
		block.setRegistryName(name);
		block.setUnlocalizedName(name);
		GameRegistry.register(block);
		ItemBlock temp = (ItemBlock) new ItemBlockState(block).setUnlocalizedName(name).setRegistryName(name).setHasSubtypes(true);
		GameRegistry.register(temp);
		Core.proxy.registerItemSubblocksRenderer(block, meta);
		return block;
	}
}
