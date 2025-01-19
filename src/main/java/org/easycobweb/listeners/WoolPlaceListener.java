package org.easycobweb.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.easycobweb.EasyCobweb;
import org.easycobweb.utils.WorldGuardHelper;

public class WoolPlaceListener implements Listener {

    private final EasyCobweb plugin;

    public WoolPlaceListener(EasyCobweb plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWoolPlace(BlockPlaceEvent event) {
        if (!plugin.getMainConfigManager().isEnable2() ||
                event.getBlock().getType() != plugin.getMainConfigManager().getWoolMaterial()) {
            return;
        }

        Block block = event.getBlock();

        // Verificar si se puede colocar la lana en esta región
        if (!WorldGuardHelper.canPlaceCobweb(block.getLocation(), plugin)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(plugin.getMainConfigManager().getRegionMessage());
            return;
        }

        long disappearTimeInSeconds = plugin.getMainConfigManager().getDelay2();
        long disappearTimeInTicks = disappearTimeInSeconds * 20;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (block.getType() == plugin.getMainConfigManager().getWoolMaterial()) {
                    block.setType(Material.AIR);
                }
            }
        }.runTaskLater(plugin, disappearTimeInTicks);
    }
}