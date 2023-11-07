package end.final_greetings.Commands.SubCommands.Edits;

import end.final_greetings.Commands.SubCMDS;
import end.final_greetings.CustomClasses.StringBuilding;
import end.final_greetings.Events.DeathEvent;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Locale;

public class Show extends SubCMDS {
    String field;
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);

    public Show(String field) {
        this.field = field;
    }

    @Override
    public String getName() {
        return "Show";
    }

    @Override
    public String getDescription() {
        return "Shows all the messages of a certain field";
    }

    @Override
    public String getSyntax() {
        return "/Demeter <Field> Show";
    }

    @Override
    public List<String> getPerms() {
        return plugin.getConfig().getStringList("Roles");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!field.equalsIgnoreCase("Death")) {
            String g;
            List<String> list = plugin.getConfig().getStringList(field);
            g = StringBuilding.Build(0, list, " , ");
            sender.sendMessage(ChatColor.GREEN +  "All "+field+" : " + g);
        }else {
            boolean here = false;
            for (String s : DeathEvent.AllCausesOfDeath) {
                if (s.equalsIgnoreCase(args[1])) {
                    here =true;
                    String g;
                    List<String> list = plugin.getConfig().getStringList(s);
                    g = StringBuilding.Build(0, list, " , ");
                    sender.sendMessage(ChatColor.GREEN + "All " + args[1] + " : " + g);
                    break;
                }
            }
            if(!here){
                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NoMsg"));

            }
        }
    }

    public List<SubCMDS> getSubCMDS() {
        return null;
    }
}
