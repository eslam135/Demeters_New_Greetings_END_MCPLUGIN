package end.final_greetings.Commands;

import end.final_greetings.Commands.SubCommands.Greetings.*;
import end.final_greetings.Commands.SubCommands.Roles;
import end.final_greetings.CustomClasses.RoleCheck;
import end.final_greetings.Events.DeathEvent;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DemeterGreetings implements TabExecutor {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);

    private ArrayList<SubCMDS> subCMDS = new ArrayList<>();
    public DemeterGreetings(){
        subCMDS.add(new Greetings());
        subCMDS.add(new OldPlayers());
        subCMDS.add(new Quittings());
        subCMDS.add(new Death());
        subCMDS.add(new Roles());
        subCMDS.add(new Bye());
        subCMDS.add(new Wc());
        subCMDS.add(new WcNew());
        subCMDS.add(new RIP());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player || sender instanceof ConsoleCommandSender){
            boolean found = false;
            if(args.length >0){
                for (int i = 0; i < getSubCMDS().size(); i++) {
                    if(args[0].equalsIgnoreCase(getSubCMDS().get(i).getName())) {
                        found = true;
                        if (getSubCMDS().get(i).getName().equalsIgnoreCase("Roles")) {
                            if (sender.isOp() || sender instanceof ConsoleCommandSender) {
                                getSubCMDS().get(i).perform(sender, args);
                            }
                        } else {
                            if (RoleCheck.checkRole(getSubCMDS().get(i).getPerms(), sender)) {
                                getSubCMDS().get(i).perform(sender, args);
                            } else {
                                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NotRole"));
                            }
                        }
                    }
                    if(i == getSubCMDS().size()-1 && !found){
                        sender.sendMessage(ChatColor.RED+ plugin.getConfig().getString("Messages.WrongCMD"));
                    }
                }
            }else{
                sender.sendMessage(ChatColor.GOLD+"===========================================");
                for (int i = 0; i < getSubCMDS().size(); i++) {
                    sender.sendMessage(ChatColor.GOLD+getSubCMDS().get(i).getSyntax()+ChatColor.DARK_PURPLE+
                            " Description: "+ChatColor.GOLD+ getSubCMDS().get(i).getDescription());
                }
                sender.sendMessage(ChatColor.GOLD+"===========================================");
            }

        }else{
            sender.sendMessage(ChatColor.RED + "You need to be online to use this command");
        }
        return true;
    }

    public ArrayList<SubCMDS> getSubCMDS() {
        return subCMDS;
    }
    List<String> subsub = plugin.getConfig().getStringList("Subs");
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            for (SubCMDS s : getSubCMDS()) {
                list.add(s.getName());
            }
                return list.stream().filter(a -> a.startsWith(args[0])).collect(Collectors.toList());
        }
        if(args.length == 2 && !(args[0].equalsIgnoreCase("Death"))){
            if(args[0].equalsIgnoreCase("RIP")||args[0].equalsIgnoreCase("Wc")||
            args[0].equalsIgnoreCase("WcNew")||args[0].equalsIgnoreCase("Bye")
            ){

                List<String> blah = new ArrayList<>();
                blah = subsub;
                blah.remove("Color");
                return blah.stream().filter(a -> a.startsWith(args[1])).collect(Collectors.toList());
            }else{
                return subsub.stream().filter(a -> a.startsWith(args[1])).collect(Collectors.toList());
            }
            }

        if(args.length == 3 && (args[0].equalsIgnoreCase("Death"))) {
            if (args[1].equalsIgnoreCase("Color")) {
                return null;
            } else {
                List<String> list = subsub;
                list.remove("Color");
                list.remove("Default");
                return list.stream().filter(a -> a.startsWith(args[2])).collect(Collectors.toList());
            }
        }

        if(args.length == 2 && (args[0].equalsIgnoreCase("Death"))){
            List<String> strings = new ArrayList<>();
            for(String s: DeathEvent.AllCausesOfDeath){
                strings.add(s.toLowerCase(Locale.ROOT));
            }
            strings.add("Color");
                return strings.stream().filter(a -> a.startsWith(args[1])).collect(Collectors.toList());
            }
        return null;
    }

}
