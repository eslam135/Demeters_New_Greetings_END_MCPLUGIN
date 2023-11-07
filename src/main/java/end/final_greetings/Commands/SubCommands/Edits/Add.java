package end.final_greetings.Commands.SubCommands.Edits;

import end.final_greetings.Commands.SubCMDS;
import end.final_greetings.CustomClasses.Exists;
import end.final_greetings.CustomClasses.StringBuilding;
import end.final_greetings.Events.DeathEvent;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class Add extends SubCMDS {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    String field;
    int fieldIndex;
    public Add(String field, int fieldIndex){
        this.field = field;
        this.fieldIndex = fieldIndex;
    }
    @Override
    public String getName() {
        return "Add";
    }

    @Override
    public String getDescription() {
        return "Adding a new message";
    }

    @Override
    public String getSyntax() {
        return "/Demeter <Field> Add";
    }

    @Override
    public List<String> getPerms() {
        return plugin.getConfig().getStringList("Roles");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(!field.equalsIgnoreCase("Death")) {
            List<String> list = plugin.getConfig().getStringList(field);
            String msg;
            if (args.length > fieldIndex) {
                msg = StringBuilding.Build(fieldIndex, args, " ");
                boolean exists = Exists.CheckExists(list, msg);
                if (!exists) {
                    list.add(msg);
                    plugin.getConfig().set(field, list);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.GREEN + "Message Added");
                } else {
                    sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.Exists"));
                }
            } else {
                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NoMsg"));
            }
        }
        else{
            boolean here = false;
            if (args.length > fieldIndex) {
            for(String s: DeathEvent.AllCausesOfDeath) {
                if(args[1].equalsIgnoreCase(s)) {
                    here = true;
                    List<String> list = plugin.getConfig().getStringList(s);
                    String msg;
                        msg = StringBuilding.Build(fieldIndex, args, " ");
                        boolean exists = Exists.CheckExists(list, msg);
                        if (!exists) {
                            list.add(msg);
                            plugin.getConfig().set(s, list);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.GREEN + "Message Added");
                        } else {
                            sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.Exists"));
                        }
                    break;
                }
                }
            } else {
                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NoMsg"));
            }
            if(!here){
                sender.sendMessage(ChatColor.RED + "Test");
            }
        }
    }
}
