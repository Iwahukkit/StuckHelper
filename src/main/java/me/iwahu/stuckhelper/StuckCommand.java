package me.iwahu.stuckhelper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public final class StuckCommand implements CommandExecutor {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // stuck reload
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("stuckhelper.reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cBu komutu kullanmak için yetkin yok!"));
                return true;
            }

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aStuckHelper&8]&a Eklenti başarıyla yeniden yüklendi!"));
            return true;
        }

        // stuck command
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        int cooldownTime = 30;
        int delayTime = 3;

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&e!&8]&e Unstuck started. Don't move for " + delayTime + "s!"));
        Location startLoc = player.getLocation().clone();

        StuckHelper plugin = (StuckHelper) Bukkit.getPluginManager().getPlugin("StuckHelper");

        new BukkitRunnable() {
            int countdown = delayTime;

            @Override
            public void run() {
                if (!player.isOnline()) {
                    this.cancel();
                    return;
                }

                if (player.getLocation().getWorld() != startLoc.getWorld() ||
                        player.getLocation().distanceSquared(startLoc) > 0.25) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[!] Canceled because you moved!"));
                    this.cancel();
                    return;
                }

                countdown--;
                if (countdown <= 0) {
                    cooldowns.put(uuid, System.currentTimeMillis());

                    Location highestLoc = player.getWorld().getHighestBlockAt(player.getLocation()).getLocation();
                    highestLoc.add(0.5, 1.0, 0.5);
                    highestLoc.setPitch(player.getLocation().getPitch());
                    highestLoc.setYaw(player.getLocation().getYaw());

                    player.teleport(highestLoc);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a!&8]&a You are free!"));
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 20L, 20L);

        return true;
    }
}