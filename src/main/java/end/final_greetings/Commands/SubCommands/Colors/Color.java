package end.final_greetings.Commands.SubCommands.Colors;

import end.final_greetings.Commands.SubCMDS;
import end.final_greetings.CustomClasses.HexToInt;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class Color extends SubCMDS {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    String field;
    public Color(String field) {
        this.field = field;
    }

    @Override
    public String getName() {
        return "Color";
    }

    @Override
    public String getDescription() {
        return "Edit the chat color of the field ";
    }

    @Override
    public String getSyntax() {
        return "/Demeter <Field> Color";
    }

    @Override
    public List<String> getPerms() {
        return plugin.getConfig().getStringList("Roles");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length == 3 ) {
            plugin.getConfig().set(field, args[2]);
            int[] ints;
            ints = HexToInt.turnintohex(args[2]);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.of(new java.awt.Color(ints[0],ints[1],ints[2]))+ "Color Changed To The Current Color");
        } else {
            sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("Messages.NoColor"));
        }
    }
}
