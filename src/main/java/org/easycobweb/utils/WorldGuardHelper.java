package org.easycobweb.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.easycobweb.EasyCobweb;

import java.util.List;

public class WorldGuardHelper {

    public static boolean canPlaceCobweb(Location location, EasyCobweb plugin) {
        try {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(location.getWorld()));

            if (regions == null) {
                return true;
            }

            ApplicableRegionSet set = regions.getApplicableRegions(BukkitAdapter.adapt(location).toVector().toBlockPoint());

            // Si no hay regiones, se permite colocar
            if (set.getRegions().isEmpty()) {
                return true;
            }

            List<String> configRegions = plugin.getMainConfigManager().getRegionList();
            String mode = plugin.getMainConfigManager().getRegionMode();

            // Comprobar si alguna región coincide con la lista de configuración
            boolean regionMatches = set.getRegions().stream()
                    .map(ProtectedRegion::getId)
                    .anyMatch(configRegions::contains);

            // En modo whitelist, solo se permite si la región está en la lista
            // En modo blacklist, solo se permite si la región NO está en la lista
            return mode.equalsIgnoreCase("whitelist") ? regionMatches : !regionMatches;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}