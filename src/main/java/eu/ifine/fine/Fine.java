package eu.ifine.fine;

import eu.ifine.fine.commands.TransferCommand;
import eu.ifine.fine.papi.ServerMotd;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Fine extends JavaPlugin implements Listener {
    public boolean loadSpigot = false;

    public static Fine plugin;
    public Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            Bukkit.getPluginManager().registerEvents(this, this);
            new ServerMotd().register();
        } else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        loadSpigot = true;
        plugin = this;
        logger = getLogger();
        Objects.requireNonNull(this.getCommand("transfer")).setExecutor(new TransferCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
