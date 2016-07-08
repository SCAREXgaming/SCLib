package fr.scarex.sclib.tileentity;

import java.util.List;
import java.util.UUID;

/**
 * @author SCAREX
 *
 */
public interface IWhitelist
{
    public boolean setWhitelist(boolean whitelist);

    public boolean isWhitelist();

    public List<UUID> getPlayerList();

    public boolean addPlayerToList(UUID uuid);

    public boolean removePlayer(int index);

    public boolean removePlayer(UUID uuid);
}
