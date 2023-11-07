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

public class Drop extends SubCMDS {
    String field;
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    int fieldindex;
    public Drop(String field, int fieldindex){
        this.field = field;
        this.fieldindex = fieldindex;
    }
    @Override
    public String getName() {
        return "Drop";
    }

    @Override
    public String getDescription() {
        return "Drops a message from a certain field";
    }

    @Override
    public String getSyntax() {
        return "/Demeter <Field> Drop";
    }

    @Override
    public List<String> getPerms() {
        return plugin.getConfig().getStringList("Roles");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(!field.equalsIgnoreCase("Death")) {
            String msg;
            List<String> list = plugin.getConfig().getStringList(field);
            if (args.length > fieldindex) {
                boolean found = false;
                msg = StringBuilding.Build(fieldindex, args, " ");
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).compareToIgnoreCase(msg) == 0) {
                        list.remove(i);
                        found = true;
                    }
                }
                plugin.getConfig().set(field, list);
                plugin.saveConfig();
                if (found) {
                    sender.sendMessage(ChatColor.GREEN + "Msg Removed");
                    found = false;
                } else {
                    sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.ExistsNot"));
                }
            } else {
                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NoMsg"));
            }
        }
        else {
            boolean here = false;
            if (args.length > fieldindex) {
                for (String s : DeathEvent.AllCausesOfDeath) {
                if (s.equalsIgnoreCase(args[1])) {
                    here = true;
                    List<String> list = plugin.getConfig().getStringList(s);
                    String msg;
                        boolean found = false;
                        msg = StringBuilding.Build(fieldindex, args, " ");
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).compareToIgnoreCase(msg) == 0) {
                                list.remove(i);
                                found = true;
                            }
                        }
                        plugin.getConfig().set(s, list);
                        plugin.saveConfig();
                        if (found) {
                            sender.sendMessage(ChatColor.GREEN + "Msg Removed");
                            found = false;
                        } else {
                            sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.ExistsNot"));
                        }
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
