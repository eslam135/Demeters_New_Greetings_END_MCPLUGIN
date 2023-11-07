package end.final_greetings.Events;
import end.final_greetings.CustomClasses.*;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.awt.*;

public class WelcomeEvent implements CommandExecutor, Listener {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("WcNew")){
            String color = plugin.getConfig().getString("Join_Color");
            int[] rgb = HexToInt.turnintohex(color);
            if(sender instanceof Player) {
                Player player = (Player) sender;
                int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("WcNew").size());
                player.chat(ChatColor.AQUA+plugin.getConfig().getStringList("WcNew").get(num));
            }else{
                sender.sendMessage(ChatColor.RED+"You Can't use this command from the console");
            }
        }
        if(command.getName().equals("Wc")) {
            String color = plugin.getConfig().getString("Join_Color");
            int[] rgb = HexToInt.turnintohex(color);
            if (sender instanceof Player) {
                Player player = (Player) sender;
                int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("Wc").size());
                player.chat(ChatColor.AQUA+ plugin.getConfig().getStringList("Wc").get(num));
            }else{
                sender.sendMessage(ChatColor.RED+"You Can't use this command from the console");
            }
        }
        return true;
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinForFirstTime(PlayerJoinEvent playerJoinEvent) {
        String color = plugin.getConfig().getString("Join_Color");
        int[] rgb = HexToInt.turnintohex(color);
        Player player = playerJoinEvent.getPlayer();
        String name = player.getName();
        boolean found = false;
        if (!(playerJoinEvent.getPlayer().hasPlayedBefore())) {
            int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("Greetings").size());
            String msg = plugin.getConfig().getStringList("Greetings").get(num);
            msg = Replace.replace(msg, "%player%", name);
            playerJoinEvent.setJoinMessage(ChatColor.of("#"+color)+msg);
        } else {
            int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("Greetings_Old_Player").size());
            String msg = plugin.getConfig().getStringList("Greetings_Old_Player").get(num);
            msg = Replace.replace(msg, "%player%", name);
            playerJoinEvent.setJoinMessage(ChatColor.of("#"+color)+msg);
        }

    }
}
