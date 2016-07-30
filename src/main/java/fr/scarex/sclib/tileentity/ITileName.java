package fr.scarex.sclib.tileentity;

/**
 * @author SCAREX
 *
 */
public interface ITileName
{
    public default boolean hasCustomInventoryName() {
        return this.getInventoryName() != null && !this.getInventoryName().isEmpty();
    }

    public String getInventoryName();
}
