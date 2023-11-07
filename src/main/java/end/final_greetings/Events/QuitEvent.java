package end.final_greetings.Events;

import end.final_greetings.CustomClasses.*;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.awt.*;

public class QuitEvent implements CommandExecutor, Listener {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("Bye")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("Bye").size());
                player.chat(ChatColor.AQUA+ plugin.getConfig().getStringList("Bye").get(num));
            }else{
                sender.sendMessage(ChatColor.RED+"You Can't use this command from the console");
            }
        }
        return true;
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent){
        String color = plugin.getConfig().getString("Leave_Color");
        int[] rgb = HexToInt.turnintohex(color);
        Player player = playerQuitEvent.getPlayer();
        String name = player.getName();
        int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("Quittings").size());
        String  msg = plugin.getConfig().getStringList("Quittings").get(num);
        msg = Replace.replace(msg, "%player%", name);
        playerQuitEvent.setQuitMessage(ChatColor.of("#"+color)+msg);
    }
}
