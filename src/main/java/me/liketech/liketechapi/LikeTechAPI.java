package me.liketech.liketechapi;

import me.liketech.liketechapi.API.ChatColor.ChatUtilities;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LikeTechAPI extends JavaPlugin {

    public static final String VERSION_NUMBER = "EXPERIMENTAL";

    private static LikeTechAPI plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getLogger().info("Using LikeTechAPI v" + VERSION_NUMBER + "!");
        getLogger().severe("You are using an experimental version of LikeTechAPI! Support may not be provided!");

        test();
    }

    @Override
    public void onDisable() {
        getLogger().info("LikeTechAPI shut down successfully!");
    }


    public static LikeTechAPI getPlugin(){
        return plugin;
    }

    private void test(){
        Objects.requireNonNull(getCommand("liketechapi")).setExecutor((sender, cmd, s, args) -> {
            sender.sendMessage(ChatUtilities.translateColor("&aThis server is running LikeTechAPI version &c" + VERSION_NUMBER + "&a! Support is not provided for experimental versions!"));
            return false;
        });
    }
}
