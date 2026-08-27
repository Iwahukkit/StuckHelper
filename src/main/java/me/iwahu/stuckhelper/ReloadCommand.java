package me.iwahu.stuckhelper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("stuckhelper.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"));
            return true;
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aStuckHelper&8]&a Plugin reloaded successfully!"));
        return true;
    }
}