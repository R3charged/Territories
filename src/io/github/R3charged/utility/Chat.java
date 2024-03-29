package io.github.R3charged.utility;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Chat {
    final private static ChatColor error=ChatColor.DARK_RED;
    final private static ChatColor SUCCESS = ChatColor.GREEN;
    final private static boolean debugMessages=true;

    public static void success(CommandSender sender, String msg) {
        sender.sendMessage(SUCCESS + msg);
    }
    public static void greatSuccess(CommandSender sender, String msg) {
        sender.sendMessage(SUCCESS + msg);
    }

    public static void warning(CommandSender sender, String msg) {
        sender.sendMessage(ChatColor.GRAY + msg);
    }
    public static void error(CommandSender sender, String msg) {
        sender.sendMessage(error + msg);
    }
    public static void errorOffline(CommandSender sender,String offlinePlayer) {
        sender.sendMessage(error+offlinePlayer+" is not online or does not exist.");
    }
    public static void debug(String line) {
        if(debugMessages)
            Bukkit.getServer().broadcastMessage("DEBUG: "+line);
    }
}
