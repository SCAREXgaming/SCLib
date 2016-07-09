package fr.scarex.sclib.block;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author SCAREX
 *
 */
public abstract class SCBlockList
{
    public HashMap<Class, AbstractSCBlock> blockMap = new HashMap<Class, AbstractSCBlock>();

    public void preInit() {
        this.addBlocks();

        for (Entry<Class, AbstractSCBlock> e : blockMap.entrySet()) {
            e.getValue().init();
        }
    }

    protected abstract void addBlocks();

    public void init() {
        for (Entry<Class, AbstractSCBlock> e : blockMap.entrySet()) {
            e.getValue().register();
        }
    }

    public void postInit() {
        for (Entry<Class, AbstractSCBlock> e : blockMap.entrySet()) {
            e.getValue().registerCrafts();
        }
    }

    protected <E extends AbstractSCBlock> E addBlock(E b) {
        blockMap.put(b.getClass(), b);
        return b;
    }

    public <E extends AbstractSCBlock> E getBlock(Class<? extends E> c) {
        return (E) blockMap.get(c);
    }
}
