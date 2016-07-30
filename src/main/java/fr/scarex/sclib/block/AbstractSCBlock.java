package fr.scarex.sclib.block;

import java.util.ArrayList;
import java.util.List;

import fr.scarex.sclib.IRegister;
import fr.scarex.sclib.tileentity.AbstractSCTileEntity;
import fr.scarex.sclib.tileentity.IOwneable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author SCAREX
 *
 */
public abstract class AbstractSCBlock extends Block implements IRegister
{
    protected String modid;

    protected AbstractSCBlock(Material m) {
        super(m);
    }

    @Override
    public void register() {
        GameRegistry.register(this);
        GameRegistry.register(this.getItemBlock().setRegistryName(this.getRegistryName()));
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
        return "tile." + modid + "_" + this.getName();
    }

    public Class getTileEntityClass() {
        return null;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return this.getTileEntityClass() != null;
    }

    @Override
    public void init() {
        this.modid = Loader.instance().activeModContainer().getModId();
        this.setRegistryName(modid, this.getName());
    }

    public ItemBlock getItemBlock() {
        return new ItemBlock(this);
    }

    public boolean hasSpecialRender() {
        return false;
    }

    @Override
    public void registerCrafts() {}

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
        if (stack.getTagCompound() != null && world.getTileEntity(pos) instanceof AbstractSCTileEntity) ((AbstractSCTileEntity) world.getTileEntity(pos)).readExtraCompound(stack.getTagCompound());
    }

    public List<ItemStack> getAllItemStacks() {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        stacks.add(new ItemStack(this));
        return stacks;
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        TileEntity ent = world.getTileEntity(pos);
        if (ent instanceof AbstractSCTileEntity) {
            AbstractSCTileEntity te = (AbstractSCTileEntity) ent;
            te.onNeighborBlockChange(neighbor);
        }
    }

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World world, BlockPos pos) {
        return world.getTileEntity(pos) instanceof IOwneable ? (((IOwneable) world.getTileEntity(pos)).isOwner(player.getUniqueID()) ? ForgeHooks.blockStrength(state, player, world, pos) : 0F) : ForgeHooks.blockStrength(state, player, world, pos);
    }
}
