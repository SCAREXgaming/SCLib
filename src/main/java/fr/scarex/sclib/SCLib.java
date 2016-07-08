package fr.scarex.sclib;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author SCAREX
 *
 */
@Mod(modid = SCLib.MODID, name = SCLib.NAME, version = SCLib.VERSION)
public class SCLib
{
    public static final String MODID = "sclib";
    public static final String NAME = "SCAREX Library";
    public static final String VERSION = "@VERSION@";
    public static final boolean DEBUG = "@DEBUG@" == "@" + "DEBUG@";
    
    @Mod.Instance(SCLib.MODID)
    public static SCLib INSTANCE;
    
    public static Logger LOG;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOG = event.getModLog();
        
        FMLInterModComms.sendMessage("Waila", "register", "fr.scarex.sclib.waila.SCLibWailaCompat.load");
    }
}
