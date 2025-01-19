package org.easycobweb.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.easycobweb.EasyCobweb;
import org.easycobweb.utils.WorldGuardHelper;

public class WebPlaceListener implements Listener {

    private final EasyCobweb plugin;

    public WebPlaceListener(EasyCobweb plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWebPlace(BlockPlaceEvent event) {
        if (!plugin.getMainConfigManager().isEnable() ||
                event.getBlock().getType() != Material.COBWEB) {
            return;
        }

        Block block = event.getBlock();

        // Verificar si se puede colocar la telaraña en esta región
        if (!WorldGuardHelper.canPlaceCobweb(block.getLocation(), plugin)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(plugin.getMainConfigManager().getRegionMessage());
            return;
        }

        long disappearTimeInSeconds = plugin.getMainConfigManager().getDelay();
        long disappearTimeInTicks = disappearTimeInSeconds * 20;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (block.getType() == Material.COBWEB) {
                    block.setType(Material.AIR);
                }
            }
        }.runTaskLater(plugin, disappearTimeInTicks);
    }
}