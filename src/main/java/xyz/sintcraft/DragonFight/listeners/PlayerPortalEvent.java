package xyz.sintcraft.DragonFight.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.sintcraft.DragonFight.DragonFight;

import java.util.Date;

public class PlayerPortalEvent implements Listener {
    private DragonFight plugin;
    private Date cooldown = new Date();
    public PlayerPortalEvent(DragonFight p) {
        plugin = p;
    }

    @EventHandler
    public void onPlayerPortalEvent(org.bukkit.event.player.PlayerPortalEvent e) {
        if(!e.getTo().getWorld().getName().endsWith("the_end"))return;
        if(plugin.getConfig().getBoolean("cancelPortalEvent") && !e.getPlayer().hasPermission("dragonfight.portal.bypass")) {
            if(cooldown.getTime() <= (new Date()).getTime()) {
                cooldown = new Date();
                cooldown.setTime(cooldown.getTime()+5000);
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getLenguage().getString("prefix")+"&cDon't access"));
            }
            e.setCancelled(true);
        }
    }
}
