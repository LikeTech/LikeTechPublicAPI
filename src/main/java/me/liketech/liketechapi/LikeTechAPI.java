package me.liketech.liketechapi;

import me.liketech.liketechapi.API.ChatColor.ChatUtilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class LikeTechAPI extends JavaPlugin {

    public static final String VERSION_NUMBER = "1.0";

    private static LikeTechAPI plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getLogger().info("Using LikeTechAPI v" + VERSION_NUMBER + "!");

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
        Objects.requireNonNull(getServer().getPluginCommand("liketechapi")).setExecutor((sender, cmd, s, args) -> {
            sender.sendMessage(ChatUtilities.translateColor("&aThis server is running LikeTechAPI version &e" + VERSION_NUMBER + "&a!"));
            return false;
        });
    }
}
