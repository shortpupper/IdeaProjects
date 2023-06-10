package woks.woks.matthew.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class questGUIToolClass {
    private Inventory inventory;
    private String title;
    private Integer integerId;


    public questGUIToolClass(Inventory inventory, String title) {
        this.inventory = inventory;
        this.title     = title;
//        this.integerId = integerId;
    }


    public void setInventorySetItem(Integer index, ItemStack itemStack) {
        this.inventory.setItem(index, itemStack);
    }

    public Integer getIntegerId() {
        return integerId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return title;
    }

    public void setIntegerId(Integer integerId) {
        this.integerId = integerId;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
