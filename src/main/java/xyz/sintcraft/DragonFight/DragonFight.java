package xyz.sintcraft.DragonFight;

import com.sun.security.auth.login.ConfigFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class DragonFight extends JavaPlugin {
    private boolean enablePlaceHolders = false;
    private DFPlaceHolderExpansion placeholderexpansion;
    private EventsManager eventsManager;
    private CommandsManager commandsManager;
    private FileConfiguration config;
    private FileConfiguration lenguage;
    private UtilFunctions utilFunctions;

    @Override
    public void onEnable() {
        setupVariables();
    }

    @Override
    public void onDisable() {
        getLogger().info("Bye!");
    }

    private void setupVariables() {

        utilFunctions = new UtilFunctions(this);
        utilFunctions.reloadConfig();
        getLogger().info("Register util fuctions success.");

        placeholderexpansion = new DFPlaceHolderExpansion(this);
        setEnablePlaceHolders();

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

    @Override
    public FileConfiguration getConfig() {
        return this.config;
    }

    public FileConfiguration getLenguage() {
        return this.lenguage;
    }

    public boolean isEnablePlaceHolders() {
        return enablePlaceHolders;
    }

    public void setConfig(FileConfiguration config) {
        this.config = config;
    }

    public void setLenguage(FileConfiguration lenguage) {
        this.lenguage = lenguage;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

    public DFPlaceHolderExpansion getPlaceholderexpansion() {
        return placeholderexpansion;
    }

    public EventsManager getEventsManager() {
        return eventsManager;
    }

    public UtilFunctions getUtilFunctions() {
        return utilFunctions;
    }
}
