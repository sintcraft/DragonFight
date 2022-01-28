package xyz.sintcraft.DragonFight.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DragonFight implements CommandExecutor, TabExecutor {
    public xyz.sintcraft.DragonFight.DragonFight plugin;

    public DragonFight(xyz.sintcraft.DragonFight.DragonFight p) {
        this.plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.hasPermission("dragonfight.command.dragonfight"))return false;
        if(args.length < 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l---------- &6&lDragon &f&lFight ----------"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fVersion: &e0.1.0"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fAuthor: &asintcraft"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fHelp: &9https://spigotmc.org"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&m----------------------------------"));
            return false;
        }
        if((args.length == 1 || args.length == 2) && args[0].equalsIgnoreCase("statusEnd")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        plugin.getLenguage().getString("prefix")
                                + plugin.getLenguage().getString("commands.dragonfight-endstatus.status-msg")
                                .replace("%now%", plugin.getConfig().getBoolean("cancelPortalEvent")
                                        ? plugin.getLenguage().getString("commands.dragonfight-endstatus.disable")
                                        : plugin.getLenguage().getString("commands.dragonfight-endstatus.enable"))
                        ));
            }
            if(args.length == 2) {
                if(!args[1].equalsIgnoreCase("enable") && !args[1].equalsIgnoreCase("disable")){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getLenguage().getString("prefix")
                            + plugin.getLenguage().getString("commands.dragonfight-endstatus.error-msg")
                    ));
                    return false;
                } else if(args[1].equalsIgnoreCase("enable")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getLenguage().getString("prefix")
                                    + plugin.getLenguage().getString("commands.dragonfight-endstatus.enable-msg")
                                    .replace("%before%", plugin.getConfig().getBoolean("cancelPortalEvent")
                                    ? plugin.getLenguage().getString("commands.dragonfight-endstatus.disable")
                                    : plugin.getLenguage().getString("commands.dragonfight-endstatus.enable"))
                                    .replace("%now%", plugin.getLenguage().getString("commands.dragonfight-endstatus.enable"))
                    ));
                    plugin.getConfig().set("cancelPortalEvent", false);
                    try {
                        plugin.getConfig().save(new File(plugin.getDataFolder(), "config.yml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if(args[1].equalsIgnoreCase("disable")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getLenguage().getString("prefix")
                                    + plugin.getLenguage().getString("commands.dragonfight-endstatus.enable-msg")
                                    .replace("%before%", plugin.getConfig().getBoolean("cancelPortalEvent")
                                            ? plugin.getLenguage().getString("commands.dragonfight-endstatus.disable")
                                            : plugin.getLenguage().getString("commands.dragonfight-endstatus.enable"))
                                    .replace("%now%", plugin.getLenguage().getString("commands.dragonfight-endstatus.disable"))
                    ));
                    plugin.getConfig().set("cancelPortalEvent", true);
                    try {
                        plugin.getConfig().save(new File(plugin.getDataFolder(), "config.yml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if(args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.getUtilFunctions().reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getLenguage().getString("prefix")
                            + plugin.getLenguage().getString("command.dragonfight-reload-success")
            ));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        /*
            /dragonfight statusEnd -> status
            /dragonfight statusEnd enable/disable -> set status
         */
        List<String> result = new ArrayList<>();

        if(!sender.hasPermission("dragonfight.command.dragonfight"))return result;
        if(args.length < 1)return result;

        if(args.length == 1) {
            String serch = args[0];
            if("statusEnd".toLowerCase().startsWith(serch.toLowerCase())) result.add("statusEnd");
            if("reload".toLowerCase().startsWith(serch.toLowerCase())) result.add("reload");
        } else if(args.length == 2) {
            String serch = args[1];
            if(args[0].equalsIgnoreCase("statusEnd")) {
                if("enable".startsWith(serch.toLowerCase())) result.add("enable");
                if("disable".startsWith(serch.toLowerCase())) result.add("disable");
            }
        }
        return result;
    }
}
