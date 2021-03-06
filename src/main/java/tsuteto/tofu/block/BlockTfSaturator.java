package tsuteto.tofu.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidContainerItem;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.gui.TcGuiHandler;
import tsuteto.tofu.tileentity.TileEntityTfSaturator;
import tsuteto.tofu.util.Utils;

public class BlockTfSaturator extends BlockTfMachineBase
{
    private static boolean keepMachineInventory = false;

    protected BlockTfSaturator(boolean isActive)
    {
        super(isActive);
    }

    @Override
    protected String getFrontIconActive()
    {
        return "tofucraft:tfSaturator_front_lit";
    }

    @Override
    protected String getFrontIconIdle()
    {
        return "tofucraft:tfSaturator_front";
    }

    @Override
    protected Block getBlockActive()
    {
        return TcBlocks.tfSaturatorActive;
    }

    @Override
    protected Block getBlockIdle()
    {
        return TcBlocks.tfSaturatorIdle;
    }

    /**
     * Update which block ID the furnace is using depending on whether or not it is burning
     */
    public static void updateMachineState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity var6 = par1World.getTileEntity(par2, par3, par4);
        keepMachineInventory = true;

        if (par0)
        {
            par1World.setBlock(par2, par3, par4, TcBlocks.tfSaturatorActive);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, TcBlocks.tfSaturatorIdle);
        }

        keepMachineInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var5, 2);

        if (var6 != null)
        {
            var6.validate();
            par1World.setTileEntity(par2, par3, par4, var6);
        }
    }

    @Override
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        Utils.onNeighborBlockChange_RedstoneSwitch(this, p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6, keepMachineInventory);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);

        if (tile != null)
        {
            par5EntityPlayer.openGui(TofuCraftCore.instance, TcGuiHandler.GUIID_TF_SATURATOR, par1World, par2, par3, par4);
        }

        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World, int i)
    {
        return new TileEntityTfSaturator();
    }
}
