package me.dirolgaming.shub;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnQuitListener implements Listener {
    private final main plugin;

    public OnQuitListener(main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getWorld().equals(plugin.getConfig().getString("world"))) {
            if (plugin.getConfig().getString("clear-on-quit").equals("true")) {
                event.getPlayer().getInventory().clear();
                plugin.getLogger().info("Quit - Inventory cleared");
            }
        }
        if (plugin.getConfig().getBoolean("enable-join-quit-messages")) {
            event.setQuitMessage(plugin.getConfig().getString("Quit-Message")
                    .replaceAll("&", "§").replace("%player%", player.getName()));
        }
    }
}