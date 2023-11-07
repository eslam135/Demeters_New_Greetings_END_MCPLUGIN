package end.final_greetings.Commands.SubCommands.Greetings;

import end.final_greetings.Commands.SubCMDS;
import end.final_greetings.Commands.SubCommands.Colors.Color;
import end.final_greetings.Commands.SubCommands.Edits.*;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class OldPlayers extends SubCMDS {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    private ArrayList<SubCMDS> subCMDS = new ArrayList<>();
    public OldPlayers(){
        subCMDS.add(new Add("Greetings_Old_Player",2));
        subCMDS.add(new Drop("Greetings_Old_Player",2));
        subCMDS.add(new Show("Greetings_Old_Player"));
        subCMDS.add(new Set("Greetings_Old_Player",2));
        subCMDS.add(new Default("Greetings_Old_Player"));
        subCMDS.add(new Color("Join_Color"));
    }
    @Override
    public String getName() {
        return "OldPlayers";
    }

    @Override
    public String getDescription() {
        return "Applying Changes To the old players greeting messages";
    }

    @Override
    public String getSyntax() {
        return "/Demeter OldPlayers";
    }

    @Override
    public List<String> getPerms() {
        return plugin.getConfig().getStringList("Roles");
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

