package fr.scarex.sclib.inventory.container.slot;

import fr.scarex.sclib.tileentity.ISlotFilter;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author SCAREX
 *
 */
public class SlotFilter extends AbstractSlotFilter
{
    protected ISlotFilter filter;

    public SlotFilter(IInventory inv, ISlotFilter filter, int index, int x, int y) {
        super(inv, index, x, y);
        this.filter = filter;
    }

    @Override
    public void putStack(ItemStack stack) {
        if (stack != null) stack.stackSize = 1;
        super.putStack(stack);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return this.filter.canAcceptItem(this.slotNumber, stack);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        this.inventory.setInventorySlotContents(this.slotNumber, null);
        return null;
    }

    /**
     * @return the filter
     */
    public ISlotFilter getFilter() {
        return filter;
    }

    /**
     * @param filter
     *            the filter to set
     */
    public void setFilter(ISlotFilter filter) {
        this.filter = filter;
    }
}
