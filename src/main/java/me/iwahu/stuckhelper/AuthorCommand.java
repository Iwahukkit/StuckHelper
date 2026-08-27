package me.iwahu.stuckhelper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class AuthorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&oThank you for using my plugin"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&dStuckHelper&8] &7Plugin Author: &dIwahu"));
        return true;
    }
}