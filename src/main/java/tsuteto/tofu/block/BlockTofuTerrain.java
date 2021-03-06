package tsuteto.tofu.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import tsuteto.tofu.item.TcItem;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import tsuteto.tofu.item.TcItems;

public class BlockTofuTerrain extends BlockTofuBase
{
    public BlockTofuTerrain()
    {
        super(Material.sponge);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
        Block plant = plantable.getPlant(world, x, y + 1, z);
        int var6 = world.getBlockMetadata(x, y + 1, z) & 3;
        if (plant == TcBlocks.tcSapling && var6 == 1)
        {
            return true;
        }
        else
        {
            return super.canSustainPlant(world, x, y, z, direction, plantable);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return TcItems.tofuMomen;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
    	this.blockIcon = par1IconRegister.registerIcon("tofucraft:blockTofuMomen");
    }

    @Override
    public ItemStack createScoopedBlockStack()
    {
        return new ItemStack(TcBlocks.tofuMomen);
    }

    @Override
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return TcItem.getItemFromBlock(TcBlocks.tofuMomen);
    }
}
