package me.xemor.superheroesworldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.xemor.skillslibrary2.conditions.Condition;
import me.xemor.skillslibrary2.conditions.EntityCondition;
import me.xemor.skillslibrary2.conditions.LocationCondition;
import me.xemor.skillslibrary2.conditions.TargetCondition;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WorldGuardCondition extends Condition implements EntityCondition, TargetCondition, LocationCondition {

    private StateFlag flag;

    public WorldGuardCondition(int condition, ConfigurationSection configurationSection) {
        super(condition, configurationSection);
        Flag flag = WorldGuard.getInstance().getFlagRegistry().get(configurationSection.getString("flag", "BUILD"));
        if (flag instanceof StateFlag) {
            this.flag = (StateFlag) flag;
        }
        else {
            SuperheroesWorldGuard.getInstance().getLogger().severe("This flag either does not exist, or is not a state flag.");
        }
    }

    @Override
    public boolean isTrue(Entity entity, Location location) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        LocalPlayer localPlayer = null;
        if (entity instanceof Player) {
            localPlayer = WorldGuardPlugin.inst().wrapPlayer((Player) entity, true);
        }
        return query.queryValue(BukkitAdapter.adapt(location), localPlayer, flag) == StateFlag.State.ALLOW;
    }

    @Override
    public boolean isTrue(Entity entity, Entity target) {
        Location location = entity.getLocation();
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        LocalPlayer localPlayer = null;
        if (target instanceof Player) {
            localPlayer = WorldGuardPlugin.inst().wrapPlayer((Player) target, true);
        }
        return query.queryValue(BukkitAdapter.adapt(location), localPlayer, flag) == StateFlag.State.ALLOW;
    }

    @Override
    public boolean isTrue(Entity entity) {
        Location location = entity.getLocation();
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        LocalPlayer localPlayer = null;
        if (entity instanceof Player) {
            localPlayer = WorldGuardPlugin.inst().wrapPlayer((Player) entity, true);
        }
        return query.queryValue(BukkitAdapter.adapt(location), localPlayer, flag) == StateFlag.State.ALLOW;
    }
}
