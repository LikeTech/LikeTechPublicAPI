package me.liketech.liketechapi.API.Menus.Items;

import net.minecraft.obfuscate.DontObfuscate;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class GUIItem {

    /**
     * ItemStack of the GUI Item
     * @return The ItemStack of the GUI item
     */
    public abstract ItemStack getItem();

    /**
     * Lore of the item
     * @return - List of string: the item lore
     */
    public abstract List<String> getLore();

    /**
     * Converts the ItemStack and List of Lore into an itemstack readable by Bukkit.
     * @return - the ItemStack version of the GUIItem
     */
    public ItemStack createItem(){
        ItemStack item = getItem();
        ItemMeta meta = item.getItemMeta();
        meta.setLore(getLore());
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Action that happens when this item is clicked in an inventory.
     * @param event - passed in by the API itself. It allows you to manage the event within the click action.
     */
    public void onClick(InventoryClickEvent event){

    }
}
