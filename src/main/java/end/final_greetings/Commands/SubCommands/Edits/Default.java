package end.final_greetings.Commands.SubCommands.Edits;

import end.final_greetings.Commands.SubCMDS;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class Default extends SubCMDS {
    String field;
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);

    public Default(String field) {
        this.field = field;
    }

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public String getDescription() {
        return "Sets The Messages of a certain field to the default messages";
    }

    @Override
    public String getSyntax() {
        return "/Demeter <Field> Default";
    }

    @Override
    public List<String> getPerms() {
        return plugin.getConfig().getStringList("Roles");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        List<String> list;
        list = plugin.getConfig().getStringList("Default_"+field);
        plugin.getConfig().set(field, list);
        plugin.saveConfig();
        sender.sendMessage(ChatColor.GREEN + field +" are set to default");
    }
}
