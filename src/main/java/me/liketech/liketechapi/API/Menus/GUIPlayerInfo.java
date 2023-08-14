package me.liketech.liketechapi.API.Menus;

import org.bukkit.entity.Player;

public class GUIPlayerInfo {

    private final Player player;


    public GUIPlayerInfo(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
