package me.liketech.liketechapi;

import org.bukkit.plugin.java.JavaPlugin;

public final class LikeTechAPI extends JavaPlugin {

    public static final String VERSION_NUMBER = "1.0";

    private static LikeTechAPI plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getLogger().info("Using LikeTechAPI v" + VERSION_NUMBER + "!");
        getLogger().severe("You are using an experimental version of the LikeTechAPI, which is not supported.");
    }

    @Override
    public void onDisable() {
        getLogger().info("LikeTechAPI shut down successfully!");
    }


    public static LikeTechAPI getPlugin(){
        return plugin;
    }
}
