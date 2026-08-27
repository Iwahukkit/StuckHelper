package me.iwahu.stuckhelper;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public final class StuckHelper extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aStuckHelper&8]&a has been started!"));

        getCommand("stuck").setExecutor(new StuckCommand());
        getCommand("author").setExecutor(new AuthorCommand());
        getCommand("stuckreload").setExecutor(new ReloadCommand()); // Ayrı reload komutumuz
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aStuckHelper&8]&a Stopped!"));
    }
}