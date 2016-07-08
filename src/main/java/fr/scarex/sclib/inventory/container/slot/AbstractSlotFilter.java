package fr.scarex.sclib.inventory.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * @author SCAREX
 *
 */
public class AbstractSlotFilter extends Slot
{
    public AbstractSlotFilter(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    public boolean deleteSlotOnClick(int data, int action, EntityPlayer player) {
        return true;
    }
}
