package end.final_greetings;

import end.final_greetings.Commands.DemeterGreetings;
import end.final_greetings.Events.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Final_Greetings extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Demeter Greetings is on!");
        saveDefaultConfig();
        getCommand("Demeter").setExecutor(new DemeterGreetings());
        getCommand("Rip").setExecutor(new DeathEvent());
        getCommand("Wc").setExecutor(new WelcomeEvent());
        getCommand("WcNew").setExecutor(new WelcomeEvent());
        getCommand("Bye").setExecutor(new QuitEvent());
        getServer().getPluginManager().registerEvents(new WelcomeEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Demeter Greetings is off!");
    }
}
