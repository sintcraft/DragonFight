package xyz.sintcraft.DragonFight;

import com.sun.security.auth.login.ConfigFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DragonFight extends JavaPlugin {
    private boolean enablePlaceHolders = false;
    private boolean cancelPortalEvent;
    private DFPlaceHolderExpansion placeholderexpansion;
    private EventsManager eventsManager;
    private CommandsManager commandsManager;

    @Override
    public void onEnable() {
        placeholderexpansion = new DFPlaceHolderExpansion(this);
        setEnablePlaceHolders();
        saveDefaultConfig();
        setupVariables();
    }

    @Override
    public void onDisable() {
        getLogger().info("Bye!");
    }

    private void setupVariables() {
        cancelPortalEvent = getConfig().getBoolean("cancelPortalEvent");
        getLogger().info("Cancel Event set "+cancelPortalEvent);

        eventsManager = new EventsManager(this);
        getLogger().info("Register events success.");

        commandsManager = new CommandsManager(this);
        getLogger().info("Register commands success.");
    }

    private void setEnablePlaceHolders() {
        // is plugin PlaceholderAPI exits
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            enablePlaceHolders = true;
            getLogger().info("Registering PlaceholderAPI expansion..");
            placeholderexpansion.register();
            getLogger().info("PlaceholderAPI expansion registered!");
        }
    }

    public boolean isEnablePlaceHolders() {
        return enablePlaceHolders;
    }

    public boolean isCancelPortalEvent() {
        return cancelPortalEvent;
    }

    public void setCancelPortalEvent(boolean cancelPortalEvent) {
        this.cancelPortalEvent = cancelPortalEvent;
        getConfig().set("cancelPortalEvent", cancelPortalEvent);
        saveConfig();
    }
}
