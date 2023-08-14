package me.liketech.liketechapi.API.Menus.Shop;

import me.liketech.liketechapi.LikeTechAPI;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public abstract class ItemRequirement {

    public final ItemRequirementType itemRequirementType;

    /**
     * Instantiates the class of and ItemRequirement
     * @param itemRequirementType - from enum ItemRequirementType. Determines what information the class will use later.
     */
    public ItemRequirement(@NotNull ItemRequirementType itemRequirementType) {
        if(itemRequirementType == null){
            throw new IllegalArgumentException("ItemRequirementType cannot be null");
        }
        this.itemRequirementType = itemRequirementType;
    }

    /**
     * Returns the enum ItemRequirementType. Can be either ITEM or ACHIEVEMENT. Determines whether this instance pays attention to the achievment
     * list or itemstack list.
     * @return itemRequirementType
     */
    public ItemRequirementType getItemRequirementType(){
        return itemRequirementType;
    }

    /**
     * Provide a map of an (Item Required, Amount Required) if itemRequirementType is type ITEM
     * @return map of an (Item Required, Amount Required)
     */
    public abstract Map<ItemStack, Integer> getItemStackRequirements();

    /**
     * Provide a list of advancement required if itemRequirementType is type ADVANCEMENT
     * @return List of advancements required.
     */
    public abstract List<Advancement> getAdvancementRequirements();


    /**
     * Checks whether a player has a certain requirement for shop item.
     * @param player - the player to check
     * @param requirement - the class of item requirements with type item/achievement set.
     * @return true if the player meets all the item/achievement requirements or false if they don't
     */
    public static boolean hasRequirement(Player player, ItemRequirement requirement) {
        switch(requirement.getItemRequirementType()){
            case ITEM:
                for(ItemStack item : requirement.getItemStackRequirements().keySet()){
                    if(!player.getInventory().containsAtLeast(item, requirement.getItemStackRequirements().get(item))){
                        return false;
                    }
                }
                return true;
            case ACHIEVEMENT:

                for(Advancement advancement : requirement.getAdvancementRequirements()){
                    if(!player.getAdvancementProgress(advancement).isDone()){
                        return false;
                    }
                }
                return true;
            default:
                LikeTechAPI.getPlugin().getLogger().severe("Error: ItemRequirementType does not exist for " + requirement);
                return false;
        }
    }


    public enum ItemRequirementType{
        ACHIEVEMENT,
        ITEM
    }

}
