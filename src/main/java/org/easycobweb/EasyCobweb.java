package org.easycobweb;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.easycobweb.commands.PluginCommand;
import org.easycobweb.config.MainConfigManager;
import org.easycobweb.listeners.WebPlaceListener;
import org.easycobweb.listeners.WoolPlaceListener;

public class EasyCobweb extends JavaPlugin {

    public static String prefix = "&8[&eEasyCobweb&8] ";
    private MainConfigManager mainConfigManager;
    private String version = getDescription().getVersion();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        mainConfigManager = new MainConfigManager(this);
        registerCommand();
        registerEvents();

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&e====&6==================================&e===="));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f- &bName: &eEasyCobweb"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f- &bVersion: &7"+version));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f- &bAuthor: &fretiredbydavid"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f- Enabling &cPlugin"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f- Enabling &aCommands"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f- Enabling &eEvents"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&e"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f- &bDiscord: &fretiredbydavid"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f- &cYouTube: &fzDavidSam"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&f"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&fThanks for using my plugin &c❤"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&5====&d==================================&5===="));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+" &7➤ &fwas deactivated correctly!"));
    }

    public void registerCommand() {
        this.getCommand("easycobweb").setExecutor(new PluginCommand(this));
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new WebPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new WoolPlaceListener(this), this);
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }
}