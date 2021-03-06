package tsuteto.tofu.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import tsuteto.tofu.api.tileentity.ContainerTfMachine;

public class ContainerTfSaturator extends ContainerTfMachine<TileEntityTfSaturator>
{
    private int lastTickCounter;
    private int lastInterval;

    public ContainerTfSaturator(InventoryPlayer invPlayer, TileEntityTfSaturator machine)
    {
        super(machine);
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);

        par1ICrafting.sendProgressBarUpdate(this, 0, this.machine.tickCounter);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.machine.interval);
    }

    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastTickCounter != this.machine.tickCounter)
            {
                var2.sendProgressBarUpdate(this, 0, this.machine.tickCounter);
            }
            if (this.lastInterval != this.machine.interval)
            {
                var2.sendProgressBarUpdate(this, 1, this.machine.interval);
            }
        }

        this.lastTickCounter = this.machine.tickCounter;
        this.lastInterval = this.machine.interval;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.machine.tickCounter = par2;
        }
        if (par1 == 1)
        {
            this.machine.interval = par2;
        }
    }

    @Override
    public void updateTfMachineData(int id, ByteBuf data)
    {
    }
}
