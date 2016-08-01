package fr.scarex.sclib.item;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author SCAREX
 *
 */
public abstract class SCItemList
{
    public HashMap<Class, AbstractSCItem> itemMap = new HashMap<Class, AbstractSCItem>();

    public void preInit() {
        this.addItems();

        for (Entry<Class, AbstractSCItem> e : itemMap.entrySet()) {
            e.getValue().init();
        }
    }

    protected abstract void addItems();

    public void init() {
        for (Entry<Class, AbstractSCItem> e : itemMap.entrySet()) {
            e.getValue().register();
        }
    }

    public void postInit() {
        for (Entry<Class, AbstractSCItem> e : itemMap.entrySet()) {
            e.getValue().registerCrafts();
        }
    }

    protected <E extends AbstractSCItem> E addItem(E item) {
        itemMap.put(item.getClass(), item);
        return item;
    }

    public <E extends AbstractSCItem> E getItem(Class<? extends AbstractSCItem> c) {
        return (E) itemMap.get(c);
    }
}
