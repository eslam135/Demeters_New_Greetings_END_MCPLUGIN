package end.final_greetings.Commands.SubCommands.Edits;

import end.final_greetings.Commands.SubCMDS;
import end.final_greetings.CustomClasses.StringBuilding;
import end.final_greetings.Events.DeathEvent;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Set extends SubCMDS {
    String field;
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    int fieldIndex;
    public Set(String field, int fieldIndex) {
        this.field = field;
        this.fieldIndex = fieldIndex;
    }

    @Override
    public String getName() {
        return "Set";
    }

    @Override
    public String getDescription() {
        return "Sets all the messages of a certain field to the given messages separated by ' , ' " ;
    }

    @Override
    public String getSyntax() {
        return "/Demeter <Field> Set";
    }

    @Override
    public List<String> getPerms() {
        return plugin.getConfig().getStringList("Roles");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        String msg;
        if(!field.equalsIgnoreCase("Death")) {
            if (args.length > fieldIndex) {
                msg = StringBuilding.Build(fieldIndex, args, " ");
                String[] newMSG = msg.split(" , ");
                List<String> list = new ArrayList<>(Arrays.asList(newMSG));
                plugin.getConfig().set(field, list);
                plugin.saveConfig();
                sender.sendMessage(ChatColor.GREEN + field + " has been updated");
            } else {
                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NoMsg"));
            }
        }else{
            boolean here= false;
            if (args.length > fieldIndex) {
            for(String s: DeathEvent.AllCausesOfDeath) {
             if(s.equalsIgnoreCase(args[0])){
                    here = true;
                    msg = StringBuilding.Build(fieldIndex, args, " ");
                    String[] newMSG = msg.split(" , ");
                    List<String> list = new ArrayList<>(Arrays.asList(newMSG));
                    plugin.getConfig().set(s, list);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.GREEN + args[1] + " has been updated");
                 break;
                }

            }
            }else {
                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NoMsg"));
            }
            if(!here){
                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NoMsg"));
            }
        }
    }
}
