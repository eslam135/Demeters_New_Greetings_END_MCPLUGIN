package end.final_greetings.Commands.SubCommands.Greetings;

import end.final_greetings.Commands.SubCMDS;
import end.final_greetings.Commands.SubCommands.Colors.Color;
import end.final_greetings.Commands.SubCommands.Edits.Add;
import end.final_greetings.Commands.SubCommands.Edits.Drop;
import end.final_greetings.Commands.SubCommands.Edits.Set;
import end.final_greetings.Commands.SubCommands.Edits.Show;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Death extends SubCMDS {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    private  String DeathReason = " ";
    private ArrayList<SubCMDS> subCMDS = new ArrayList<>();
    public Death(){
        subCMDS.add(new Drop("Death", 3));
        subCMDS.add(new Add("Death",3));
        subCMDS.add(new Show("Death"));
        subCMDS.add(new Set("Death", 3));
        subCMDS.add(new Color("Death_Color"));
    }
    @Override
    public String getName() {
        return "Death";
    }

    @Override
    public String getDescription() {
        return "Applying Changes To the Death messages";
    }

    @Override
    public String getSyntax() {
        return "/Demeter Death <DeathReason>";
    }

    @Override
    public List<String> getPerms() {
        return plugin.getConfig().getStringList("Roles");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        boolean found = false;
        if(args.length > 2){
            setDeathReason(args[1]);
            for (int i = 0; i < getSubCMDS().size(); i++) {
                if(args[1].equalsIgnoreCase(getSubCMDS().get(i).getName())&&getSubCMDS().get(i).getName().equalsIgnoreCase("Color")){
                    found = true;
                    getSubCMDS().get(i).perform(sender,args);
                }
                if(args[2].equalsIgnoreCase(getSubCMDS().get(i).getName())){
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

    public String getDeathReason() {
        return DeathReason;
    }

    public void setDeathReason(String deathReason) {
        DeathReason = deathReason;
    }

    public ArrayList<SubCMDS> getSubCMDS() {
        return subCMDS;
    }
}
