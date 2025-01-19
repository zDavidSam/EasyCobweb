package org.easycobweb.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.easycobweb.EasyCobweb;

import java.util.List;

public class MainConfigManager {
    private final CustomConfig configFile;
    private final EasyCobweb plugin;

    public int delay;
    public boolean enable;
    public int delay2;
    public boolean enable2;
    public Material woolMaterial;
    private String regionMode;
    private List<String> regionList;
    private String regionMessage;

    public MainConfigManager(EasyCobweb plugin) {
        this.plugin = plugin;
        this.configFile = new CustomConfig("config.yml", null, plugin);
        configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();

        // Cobweb settings
        delay = config.getInt("cobweb.delay", 30);
        enable = config.getBoolean("cobweb.enable", true);

        // Wool settings
        delay2 = config.getInt("wool.delay", 30);
        enable2 = config.getBoolean("wool.enable", true);
        woolMaterial = Material.getMaterial(config.getString("wool.material", "WHITE_WOOL").toUpperCase());

        if (woolMaterial == null) {
            plugin.getLogger().severe("Material de lana no válido en la configuración: " + config.getString("wool.material"));
        }

        // Region settings
        regionMode = config.getString("regions.mode", "blacklist");
        regionList = config.getStringList("regions.list");
        regionMessage = config.getString("regions.message", "§cNo puedes colocar telarañas en esta región.");
    }

    public void reloadConfig() {
        configFile.reloadConfig();
        loadConfig();
    }

    // Existing getters
    public Material getWoolMaterial() {
        return woolMaterial;
    }

    public int getDelay2() {
        return delay2;
    }

    public boolean isEnable2() {
        return enable2;
    }

    public boolean isEnable() {
        return enable;
    }

    public int getDelay() {
        return delay;
    }

    // New region-related getters
    public String getRegionMode() {
        return regionMode;
    }

    public List<String> getRegionList() {
        return regionList;
    }

    public String getRegionMessage() {
        return regionMessage;
    }
}