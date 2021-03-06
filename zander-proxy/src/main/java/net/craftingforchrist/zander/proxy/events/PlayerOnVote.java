package net.craftingforchrist.zander.proxy.events;

import com.vexsoftware.votifier.bungee.events.VotifierEvent;
import net.craftingforchrist.zander.proxy.Variables;
import net.craftingforchrist.zander.proxy.ZanderProxyMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerOnVote implements Listener {
    private ZanderProxyMain plugin = ZanderProxyMain.getInstance();

    @EventHandler
    public void PlayerOnVote(VotifierEvent event) {
        String player = event.getVote().getUsername();
        String service = event.getVote().getServiceName();

        TextComponent message = new TextComponent(ChatColor.translateAlternateColorCodes('&', Variables.voteprefix + " " + player + " voted from " + ChatColor.AQUA + service + ChatColor.RESET + ". You can too using " + ChatColor.GOLD + "/vote"));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Variables.siteaddress + "vote"));
        ProxyServer.getInstance().broadcast(message); // Broadcast to all Servers

        try {
            PreparedStatement insertstatement = plugin.getConnection().prepareStatement("INSERT INTO votes (username, service) VALUES (?, ?)");

            insertstatement.setString(1, player);
            insertstatement.setString(2, service);

            insertstatement.executeUpdate();
            plugin.getLogger().info(Variables.developmentprefix + " " + player + " has voted on " + service + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}