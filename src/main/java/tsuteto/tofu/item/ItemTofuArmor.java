package tsuteto.tofu.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.util.ItemUtils;

public class ItemTofuArmor extends ItemArmor
{
    private String armorTextureFile;

    public ItemTofuArmor(ArmorMaterial material, int par3, int par4)
    {
        super(material, par3, par4);

    }

    public ItemTofuArmor setArmorTexture(String file)
    {
        armorTextureFile = file;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return armorTextureFile;
    }

	@Override
	public CreativeTabs[] getCreativeTabs()
    {
		return ItemUtils.getCreativeTabs(this);
	}
}
