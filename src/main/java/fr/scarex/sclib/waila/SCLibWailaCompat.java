package fr.scarex.sclib.waila;

import java.util.List;

import fr.scarex.sclib.SCLib;
import fr.scarex.sclib.block.AbstractSCBlock;
import fr.scarex.sclib.tileentity.AbstractSCTileEntity;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author SCAREX
 *
 */
public class SCLibWailaCompat implements IWailaDataProvider
{
    public static final SCLibWailaCompat INSTANCE = new SCLibWailaCompat();

    public static void load(IWailaRegistrar registrar) {
        registrar.registerStackProvider(INSTANCE, AbstractSCBlock.class);
        registrar.registerHeadProvider(INSTANCE, AbstractSCBlock.class);
        registrar.registerBodyProvider(INSTANCE, AbstractSCBlock.class);
        registrar.registerTailProvider(INSTANCE, AbstractSCBlock.class);
        registrar.registerNBTProvider(INSTANCE, AbstractSCBlock.class);

        SCLib.LOG.info("Waila compatibility loaded");
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof AbstractSCTileEntity) return ((AbstractSCTileEntity) accessor.getTileEntity()).getWailaStack(accessor, config);
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof AbstractSCTileEntity) currenttip = ((AbstractSCTileEntity) accessor.getTileEntity()).getWailaHead(itemStack, currenttip, accessor, config);
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof AbstractSCTileEntity) currenttip = ((AbstractSCTileEntity) accessor.getTileEntity()).getWailaBody(itemStack, currenttip, accessor, config);
        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof AbstractSCTileEntity) currenttip = ((AbstractSCTileEntity) accessor.getTileEntity()).getWailaTail(itemStack, currenttip, accessor, config);
        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
        if (te instanceof AbstractSCTileEntity) tag = ((AbstractSCTileEntity) te).getWailaNBTData(player, te, tag, world, pos);
        return tag;
    }
}
