package fr.scarex.sclib.item;

import fr.scarex.sclib.IRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author SCAREX
 *
 */
public abstract class AbstractSCItem extends Item implements IRegister
{
    protected String modid;
    
    @Override
    public void register() {
        GameRegistry.register(this.setRegistryName(modid, this.getName()));
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
    public void registerCrafts() {}
}
