package me.liketech.liketechapi.API.Menus.Shop;

import me.liketech.liketechapi.API.Menus.Items.GUIItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class GUIShopItem extends GUIItem {

    @Override
    public ItemStack createItem(){
        ItemStack item = getItem();
        ItemMeta meta = item.getItemMeta();
        meta.setLore(getLore());
        item.setItemMeta(meta);

        return item;
    }
}
