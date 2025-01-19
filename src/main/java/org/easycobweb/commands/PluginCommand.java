package org.easycobweb.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.easycobweb.EasyCobweb;
import org.easycobweb.utils.MessageUtils;

public class PluginCommand implements CommandExecutor {

    private EasyCobweb plugin;
    public PluginCommand(EasyCobweb plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        if(!(sender instanceof Player)){
            if(args.length == 0){
                sender.sendMessage(MessageUtils.getColoredMessage(EasyCobweb.prefix + "&cUsa /cobweb reload para recargar los archivos!"));
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                subCommandReload(sender);
            }else{
                sender.sendMessage(MessageUtils.getColoredMessage(EasyCobweb.prefix + "&cComando desconocido."));
            }
            return true;

        }

        if(args.length == 0){
            sender.sendMessage(MessageUtils.getColoredMessage(EasyCobweb.prefix + "&cUsa /cobweb reload para recargar los archivos!"));
            return true;
        }

        Player player = (Player) sender;
        if(args[0].equalsIgnoreCase("reload")){
            subCommandReload(sender);
        }
        return true;
    }

    public void subCommandReload(CommandSender sender){
        if(!sender.hasPermission("easycobweb.use.reload")){
            sender.sendMessage(MessageUtils.getColoredMessage("&cNo tienes permisos para ejecutar este comando!"));
            return;
        }
        plugin.getMainConfigManager().reloadConfig();
        sender.sendMessage(MessageUtils.getColoredMessage(EasyCobweb.prefix + "&aPlugin recargado!"));
    }
}
