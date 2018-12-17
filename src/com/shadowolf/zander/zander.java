package com.shadowolf.zander;

import com.shadowolf.zander.Events.playeronjoin;
import com.shadowolf.zander.Events.playeronquit;
import com.shadowolf.zander.commands.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class zander extends JavaPlugin implements Listener {
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nZander has been enabled.\n");
        getServer().getPluginManager().registerEvents(new playeronjoin(), this);
        getServer().getPluginManager().registerEvents(new playeronquit(), this);
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Command Registry
        this.getCommand("heal").setExecutor((CommandExecutor)new heal());
        this.getCommand("feed").setExecutor((CommandExecutor)new feed());
        this.getCommand("starve").setExecutor((CommandExecutor)new starve());
        this.getCommand("adventure").setExecutor((CommandExecutor)new adventure());
        this.getCommand("creative").setExecutor((CommandExecutor)new creative());
        this.getCommand("survival").setExecutor((CommandExecutor)new survival());
        this.getCommand("spectator").setExecutor((CommandExecutor)new spectator());
        this.getCommand("fly").setExecutor((CommandExecutor)new fly());
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\nZander has been disabled.\n");
    }
}
