package end.final_greetings.CustomClasses;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RoleCheck {
    public static boolean checkRole(List<String> roles, CommandSender sender){
        boolean hasPerms = false;
        for(String s: roles) {
            if(hasPerms){
                continue;
            }
            if (sender.hasPermission(s)) {
                hasPerms = true;
            } else {
                hasPerms = false;
                sender.sendMessage(ChatColor.RED+"You Don't Have Permessions");
            }
        }
        return hasPerms;
    }
}
