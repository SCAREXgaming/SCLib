package fr.scarex.sclib.tileentity;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

/**
 * @author SCAREX
 *
 */
public class AbstractSCTileEntity extends TileEntity
{
    public NBTTagCompound getWailaNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
        if (this instanceof ITileName) {
            tag.setBoolean("HasCustomName", ((ITileName) this).hasCustomInventoryName());
            if (((ITileName) this).hasCustomInventoryName()) tag.setString("CustomName", ((ITileName) this).getInventoryName());
        }
        return tag;
    }

    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getNBTData().getBoolean("HasCustomName")) {
            currenttip.remove(0);
            currenttip.add(0, TextFormatting.WHITE + "" + TextFormatting.ITALIC + accessor.getNBTData().getString("CustomName"));
        }
        return currenttip;
    }

    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public void onNeighborBlockChange(BlockPos nieghbor) {}

    public void writeExtraCompound(NBTTagCompound comp) {}

    public void readExtraCompound(NBTTagCompound comp) {}
}
