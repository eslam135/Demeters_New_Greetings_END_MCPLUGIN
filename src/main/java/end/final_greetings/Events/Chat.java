package end.final_greetings.Events;

import end.final_greetings.Final_Greetings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Locale;

public class Chat implements Listener {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent playerChatEvent){
        String msg = playerChatEvent.getMessage().toLowerCase(Locale.ROOT);
        Player sender = playerChatEvent.getPlayer();
        List<String> SensorMsg = plugin.getConfig().getStringList("Sensor_Words");
        String CensoredWord;
        for(String s: SensorMsg){
            if(msg.contains(s.toLowerCase(Locale.ROOT))){

                sender.sendMessage(ChatColor.RED+"You Can't this word \" "+s+" \" this is a family friendly server");
                int len = s.length();
                StringBuilder builder = new StringBuilder();
                CensoredWord = s;

                for(int i =0; i<len;i++){
                    builder.append("*");
                }
                String as = builder.toString();
                String newMsg = playerChatEvent.getMessage().replaceAll("(?i)"+CensoredWord,as);
                System.out.println(newMsg);
                playerChatEvent.setMessage(newMsg);
                boolean ChatBool = plugin.getConfig().getBoolean("ChatBool");
                playerChatEvent.setCancelled(ChatBool);
                sender.sendTitle("Please Don't Swear Again", "This is a family friendly server",10,70,20);
                break;
            }
        }
    }
}
