package fr.scarex.sclib.tileentity;

import net.minecraft.item.ItemStack;

/**
 * @author SCAREX
 *
 */
public interface ISlotFilter
{
    public boolean canAcceptItem(int slotId, ItemStack stack);
}
