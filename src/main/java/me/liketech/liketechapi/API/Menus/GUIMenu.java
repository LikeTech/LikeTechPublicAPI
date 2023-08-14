package me.liketech.liketechapi.API.Menus;

import me.liketech.liketechapi.API.Menus.Items.GUIItem;
import net.minecraft.obfuscate.DontObfuscate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public abstract class GUIMenu implements InventoryHolder {

    GUIPlayerInfo playerInfo;
    Inventory inventory;


    /**
     * Instantiates the custom GUI class.
     * @param playerInfo - a PlayerInfo object generated which provides information about the player viewing the menu
     */
    public GUIMenu(GUIPlayerInfo playerInfo){
        this.playerInfo = playerInfo;
    }


    /**
     * @return - The PlayerInfo object which provides information about the player viewing the menu
     */
    public GUIPlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(GUIPlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    /**
     * @return - The Bukkit inventory object of the GUI
     * createInventory() or openInventory() MUST BE CALLED FIRST ELSE THE INVENTORY WILL BE NULL
     */
    public @NotNull Inventory getBukkitInventory() {
        if(inventory == null){
            throw new NullPointerException("Inventory is null. createInventory() or openInventory() must be called first.");
        }
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    /**
     * The name of the GUI shown at the top of the menu.
     */
    public abstract String getName();

    /**
     * How many slots are in the gui. Max: 54
     */
    public abstract int getSize();

    /**
     * Determines whether black stained-glass panes with no name should be
     * put in slots which has no item.
     */
    public abstract boolean useFillerGlass();

    /**
     * A map of the (Inventory Slot, GUIItem) where the key determines which slot the item is placed in
     */
    public abstract Map<Integer, GUIItem> getItems();

    /**
     * Determines what happens when the inventory is clicked.
     * By default - calls the onClick event of the GUIItem if it is a GUIItem.
     */
    public void onClick(InventoryClickEvent e){
        e.setCancelled(true);
        if(e.getClickedInventory() == this.getInventory()){ //checks if inventory clicked is this inventory, as it could possibly be the player's inventory.
            if(getItems().containsKey(e.getSlot())){ //checks if the slot has a guiItem in it
                getItems().get(e.getSlot()).onClick(e); //calls the onClick method of the GUIItem passing in the inventoryclickevent
            }
        }
    }


    /**
     * Creates the inventory using the information defined in the abstract methods.
     * Should only be called. Not overridden.
     */
    public Inventory createInventory(){
        inventory = Bukkit.createInventory(this, getSize(), getName());

        getItems().keySet().forEach(integer -> {

            inventory.setItem(integer, getItems().get(integer).createItem());

        });

        if(useFillerGlass()){
            ItemStack fillerGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta meta = fillerGlass.getItemMeta();
            meta.setDisplayName(" ");
            fillerGlass.setItemMeta(meta);

            for(int i = 0; i < inventory.getSize(); i++){
                if(inventory.getItem(i) == null){
                    inventory.setItem(i, fillerGlass);
                }
            }
        }

        return getBukkitInventory();
    }

    /**
     * Opens the inventory for the player defined in the playerInfo object
     */
    public void openInventory(){
        playerInfo.getPlayer().openInventory(createInventory());
    }
}
