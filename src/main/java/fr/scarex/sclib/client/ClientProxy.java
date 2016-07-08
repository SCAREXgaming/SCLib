package fr.scarex.sclib.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.scarex.sclib.CommonProxy;
import fr.scarex.sclib.block.AbstractSCBlock;
import fr.scarex.sclib.client.render.block.AbstractTESR;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

/**
 * @author SCAREX
 *
 */
public class ClientProxy extends CommonProxy implements ISimpleBlockRenderingHandler
{
    public static int renderId;

    @Override
    public void registerRender() {
        this.renderId = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(this);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        AbstractTESR tesr = getAbastractTESRForBlock((AbstractSCBlock) block);
        if (tesr != null) tesr.renderInventoryBlock(block, metadata, modelId, renderer);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        AbstractTESR tesr = getAbastractTESRForBlock((AbstractSCBlock) block);
        return tesr != null ? tesr.renderWorldBlock(world, x, y, z, block, modelId, renderer) : false;
    }

    public static AbstractTESR getAbastractTESRForBlock(AbstractSCBlock block) {
        return (AbstractTESR) TileEntityRendererDispatcher.instance.mapSpecialRenderers.get(((AbstractSCBlock) block).getTileEntityClass());
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }
}
