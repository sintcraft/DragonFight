package xyz.sintcraft.DragonFight;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class UtilFunctions {
    private DragonFight dragonFight;

    public UtilFunctions(DragonFight plugin) {
        this.dragonFight = plugin;
    }

    public void reloadConfig() {
        File file = new File(dragonFight.getDataFolder(), "config.yml");
        if(!file.exists()) {
            dragonFight.getDataFolder().mkdirs();
            dragonFight.saveResource("config.yml", false);
        }
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
            dragonFight.setConfig(config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void reloadLenguage() {
        File file = new File(dragonFight.getDataFolder(), "lenguage.yml");
        if(!file.exists()) {
            dragonFight.getDataFolder().mkdirs();
            dragonFight.saveResource("lenguage.yml", false);
        }
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
            dragonFight.setLenguage(config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
