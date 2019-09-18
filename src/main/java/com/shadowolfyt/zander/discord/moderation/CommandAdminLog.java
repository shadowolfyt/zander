package com.shadowolfyt.zander.discord.moderation;

import com.shadowolfyt.zander.ZanderMain;
import com.shadowolfyt.zander.discord.DiscordMain;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CommandAdminLog implements Listener {
    private ZanderMain plugin;
    public CommandAdminLog(ZanderMain plugin) {
        this.plugin = plugin;
        DiscordMain.getInstance();
    }

    @EventHandler
    public void ServerCommand (PlayerCommandPreprocessEvent event) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Admin Event Log");
        embed.setDescription(event.getPlayer().getDisplayName() + " typed this command: " + event.getMessage());
        embed.setFooter("Executed: " + strDate, null);
        embed.setColor(Color.blue);

        TextChannel textChannel = DiscordMain.getInstance().getJda().getTextChannelsByName(plugin.getConfig().getString("discord.adminlogchannel"), true).get(0);
        textChannel.sendMessage(embed.build()).queue();
    }
}
