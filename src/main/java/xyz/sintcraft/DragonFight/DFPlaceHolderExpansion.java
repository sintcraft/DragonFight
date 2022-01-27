package xyz.sintcraft.DragonFight;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class DFPlaceHolderExpansion extends PlaceholderExpansion {
    private DragonFight plugin;

    public DFPlaceHolderExpansion(DragonFight p) {
        plugin = p;
    }

    @Override
    public String getIdentifier() {
        return "DragonFight";
    }

    @Override
    public String getAuthor() {
        return "sintcraft";
    }

    @Override
    public String getVersion() {
        return "0.1.0";
    }

    @Override
    public String onRequest(OfflinePlayer p, String params) {
        if(!plugin.isEnablePlaceHolders()) return "";
        return "";
    }
}
