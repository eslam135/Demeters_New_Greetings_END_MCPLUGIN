package end.final_greetings.Commands.SubCommands;

import end.final_greetings.Commands.SubCMDS;
import end.final_greetings.Commands.SubCommands.Edits.*;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Roles extends SubCMDS {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    private ArrayList<SubCMDS> subCMDS = new ArrayList<>();
    public Roles(){
        subCMDS.add(new Add("Roles", 2));
        subCMDS.add(new Drop("Roles", 2));
        subCMDS.add(new Default("Roles"));
        subCMDS.add(new Set("Roles", 2));
        subCMDS.add(new Show("Roles"));
    }
    @Override
    public String getName() {
        return "Roles";
    }

    @Override
    public String getDescription() {
        return "Starts Editing Who Can Use The Rest Of The Commands";
    }

    @Override
    public String getSyntax() {
        return "/Demeter Roles";
    }

    @Override
    public List<String> getPerms() {
        return new ArrayList<>();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        boolean found = false;
        if(args.length > 1){
            for (int i = 0; i < getSubCMDS().size(); i++) {
                if(args[1].equalsIgnoreCase(getSubCMDS().get(i).getName())){
                    found = true;
                    getSubCMDS().get(i).perform(sender,args);
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
    }

    public ArrayList<SubCMDS> getSubCMDS() {
        return subCMDS;
    }
}
