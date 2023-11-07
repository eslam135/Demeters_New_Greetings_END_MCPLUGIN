package end.final_greetings.Commands;
import end.final_greetings.Final_Greetings;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public abstract class SubCMDS {

    public Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract List<String> getPerms();

    public abstract void perform(CommandSender sender, String[] args);

}
