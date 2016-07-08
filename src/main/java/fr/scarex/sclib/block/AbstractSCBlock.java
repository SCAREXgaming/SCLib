package fr.scarex.sclib.block;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.scarex.sclib.IRegister;
import fr.scarex.sclib.SCLib;
import fr.scarex.sclib.client.ClientProxy;
import fr.scarex.sclib.tileentity.AbstractSCTileEntity;
import fr.scarex.sclib.tileentity.IOwneable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

/**
 * @author SCAREX
 *
 */
public abstract class AbstractSCBlock extends Block implements IRegister
{
    protected AbstractSCBlock(Material m) {
        super(m);
    }
    
    @Override
    public void register() {
        GameRegistry.registerBlock(this, this.getItemBlock(), this.getName());
        if (this.getTileEntityClass() != null) {
            GameRegistry.registerTileEntity(this.getTileEntityClass(), this.getName());
        } else {
            for (ItemStack stack : this.getAllItemStacks()) {
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", stack);
            }
        }
    }

    @Override
    public String getUnlocalizedName() {
        return "tile." + SCLib.MODID + "_" + this.getName();
    }

    public Class getTileEntityClass() {
        return null;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return this.getTileEntityClass() != null;
    }

    @Override
    public void init() {
        this.setBlockName(this.getName());
    }

    public Class<? extends ItemBlock> getItemBlock() {
        return ItemBlock.class;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return this.hasSpecialRender() ? ClientProxy.renderId : super.getRenderType();
    }

    public boolean hasSpecialRender() {
        return false;
    }

    @Override
    protected String getTextureName() {
        return this.hasSpecialRender() ? "" : SCLib.MODID + ":" + this.getName();
    }

    @Override
    public void registerCrafts() {}

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        if (stack.getTagCompound() != null && world.getTileEntity(x, y, z) instanceof AbstractSCTileEntity) ((AbstractSCTileEntity) world.getTileEntity(x, y, z)).readExtraCompound(stack.getTagCompound());
    }

    public List<ItemStack> getAllItemStacks() {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        stacks.add(new ItemStack(this));
        return stacks;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity ent = world.getTileEntity(x, y, z);
        if (ent instanceof AbstractSCTileEntity) {
            AbstractSCTileEntity te = (AbstractSCTileEntity) ent;
            te.onNeighborBlockChange(block);
        }
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
        return world.getTileEntity(x, y, z) instanceof IOwneable ? (((IOwneable) world.getTileEntity(x, y, z)).isOwner(player.getUniqueID()) ? ForgeHooks.blockStrength(this, player, world, x, y, z) : 0F) : super.getPlayerRelativeBlockHardness(player, world, x, y, z);
    }
}
