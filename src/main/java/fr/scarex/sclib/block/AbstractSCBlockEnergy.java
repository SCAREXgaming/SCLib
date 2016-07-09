package fr.scarex.sclib.block;

import net.minecraft.block.material.Material;

/**
 * @author SCAREX
 *
 */
public abstract class AbstractSCBlockEnergy extends AbstractSCBlockDismantleable
{
    protected AbstractSCBlockEnergy(Material m) {
        super(m);
        this.setHardness(15F);
        this.setResistance(25F);
    }
}
