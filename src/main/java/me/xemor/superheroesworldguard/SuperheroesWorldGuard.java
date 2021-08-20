package me.xemor.superheroesworldguard;

import me.xemor.skillslibrary2.conditions.Conditions;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperheroesWorldGuard extends JavaPlugin {

    private static SuperheroesWorldGuard superheroesWorldGuard;

    @Override
    public void onEnable() {
        // Plugin startup logic
        superheroesWorldGuard = this;
        Conditions.register("WORLDGUARD", WorldGuardCondition.class);
    }

    public static SuperheroesWorldGuard getInstance() {
        return superheroesWorldGuard;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
