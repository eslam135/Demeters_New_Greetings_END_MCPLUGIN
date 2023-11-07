package end.final_greetings.Events;

import end.final_greetings.CustomClasses.*;
import end.final_greetings.Final_Greetings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;


public class DeathEvent implements CommandExecutor, Listener {
    Plugin plugin = Final_Greetings.getPlugin(Final_Greetings.class);
    public static String[] CausesOfDeath = new String[]{
            "BLOCK_EXPLOSION","CONTACT","SUFFOCATION",
            "FALL","FIRE","FIRE_TICK","MELTING","LAVA","DROWNING","BLOCK_EXPLOSION",
            "ENTITY_EXPLOSION","VOID","LIGHTNING","SUICIDE","STARVATION","POISON","MAGIC",
            "WITHER","FALLING_BLOCK","THORNS","DRAGON_BREATH","CUSTOM","FLY_INTO_WALL","HOT_FLOOR",
            "CRAMMING","FREEZE","SONIC_BOOM", "ENTITY_SWEEP_ATTACK"
    };
    public static String[] AllCausesOfDeath = new String[]{
            "BLOCK_EXPLOSION","CONTACT","SUFFOCATION",
            "FALL","FIRE","FIRE_TICK","MELTING","LAVA","DROWNING","BLOCK_EXPLOSION",
            "ENTITY_EXPLOSION","VOID","LIGHTNING","SUICIDE","STARVATION","POISON","MAGIC",
            "WITHER","FALLING_BLOCK","THORNS","DRAGON_BREATH","CUSTOM","FLY_INTO_WALL","HOT_FLOOR",
            "CRAMMING","FREEZE","SONIC_BOOM", "ENTITY_SWEEP_ATTACK", "KILLED", "PROJECTILE"
    };

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("RIP")) {
            String color = plugin.getConfig().getString("Death_Color");
            int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("RIP").size());
            if (sender instanceof Player) {
                Player player = (Player) sender;
            player.chat(ChatColor.RED+plugin.getConfig().getStringList("RIP").get(num));
            } else {
                sender.sendMessage(ChatColor.RED + "You Can't use this command from the console");
            }
        }
        return true;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
        String color = plugin.getConfig().getString("Death_Color");
        Player player = playerDeathEvent.getEntity();
        String playerName = player.getName();
        EntityDamageEvent ede = playerDeathEvent.getEntity().getLastDamageCause();
        assert ede != null;
        for(String s : CausesOfDeath){
            if (ede.getCause() == EntityDamageEvent.DamageCause.valueOf(s)) {
                int num = RandomNoGen.generateNum(plugin.getConfig().getStringList(s).size());
                String def = plugin.getConfig().getStringList(s).get(num);
                String msg = Replace.replace(def, "%player%", playerName);
                playerDeathEvent.setDeathMessage(ChatColor.of("#"+color)+msg);
            }
        }
        if (ede.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            EntityDamageByEntityEvent edd = (EntityDamageByEntityEvent) playerDeathEvent.getEntity().getLastDamageCause();
            Projectile arrow = (Projectile) edd.getDamager();
            Entity shooter = (Entity) arrow.getShooter();
            int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("PROJECTILE").size());
            String def = plugin.getConfig().getStringList("PROJECTILE").get(num);
            assert shooter != null;
            String killername = shooter.getName();
            String msg = Replace.replace(def, "%player%", playerName);
            msg = Replace.replace(msg, "%killer%", killername);
           playerDeathEvent.setDeathMessage(ChatColor.of("#"+color)+msg);
        }
        if(ede.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
            int num = RandomNoGen.generateNum(plugin.getConfig().getStringList("KILLED").size());
            EntityDamageByEntityEvent edd = (EntityDamageByEntityEvent) playerDeathEvent.getEntity().getLastDamageCause();
            String killername = edd.getDamager().getName();
            String def = plugin.getConfig().getStringList("KILLED").get(num);
            String msg = Replace.replace(def, "%player%", playerName);
            msg = Replace.replace(msg, "%killer%", killername);
            playerDeathEvent.setDeathMessage(ChatColor.of("#"+color)+msg);
        }
    }
}
