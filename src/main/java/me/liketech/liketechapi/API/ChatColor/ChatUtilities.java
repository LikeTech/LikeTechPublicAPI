package me.liketech.liketechapi.API.ChatColor;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtilities {

    public ChatUtilities(){
        throw new UnsupportedOperationException("You cannot instantiate a helper class.");
    }


    public static String translateColor(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private static final Pattern pattern = Pattern.compile("%" + "(?<!\\\\)(#[a-fA-F0-9]{6})" + "%");

    public static String translateColorHex(String message){
        Matcher matcher = pattern.matcher(message);

        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while(matcher.find()){
            matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.of(matcher.group(1)).toString());
        }

        return matcher.appendTail(buffer).toString();
    }

}
