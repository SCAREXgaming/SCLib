package fr.scarex.sclib.item;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.scarex.sclib.IRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author SCAREX
 *
 */
public abstract class AbstractSCItem extends Item implements IRegister
{
    protected String modid;
    
    @Override
    public void register() {
        GameRegistry.registerItem(this, this.getName());
    }

    @Override
    public void init() {
        this.modid = Loader.instance().activeModContainer().getModId();
    }

    @Override
    public String getUnlocalizedName() {
        return "item." + modid + "_" + this.getName();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName();
    }

    @Override
    protected String getIconString() {
        return modid + ":" + this.getName();
    }

    @Override
    public void registerCrafts() {}
}
