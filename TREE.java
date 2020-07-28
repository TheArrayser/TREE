import java.util.logging.Logger;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EntityTracker;
import net.minecraft.server.v1_12_R1.WorldServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TREE extends JavaPlugin {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof CraftPlayer)) {
            sender.sendMessage("You must be a player to tree yourself!");
            return true;
        }
            CraftPlayer player = (CraftPlayer) sender;
            if (cmd.getName().equalsIgnoreCase("tree")) {
                logger.info(player.getName()+" is now a Tree!");
                sender.sendMessage("TREE!");
                PluginManager p = getServer().getPluginManager();
                p.callEvent(new PlayerQuitEvent(player, "changing name"));
                player.setDisplayName("Tree");
                trickortreet(player.getHandle(), "Tree");
                p.callEvent(new PlayerJoinEvent(player, "changing name"));
            } else if(cmd.getName().equalsIgnoreCase("untree")) {
            	logger.info(player.getName()+" is no longer a Tree!");
                sender.sendMessage("Why would you untree yourself :(");
                PluginManager p = getServer().getPluginManager();
                p.callEvent(new PlayerQuitEvent(player, "changing name"));
                player.setDisplayName(player.getName());
                trickortreet(player.getHandle(), player.getName());
                p.callEvent(new PlayerJoinEvent(player, "changing name"));
            }
            return true;
    }
    public void trickortreet(EntityPlayer player, String tree) {
        WorldServer world = (WorldServer) player.world;
        EntityTracker tracker = world.tracker;
        tracker.untrackEntity(player);
        player.displayName = tree;
        tracker.track(player);
    }
    public void onDisable() {
        logger.info(this.getDescription().getName() + "disabled!");
    }
    public void onEnable() {
        logger.info(this.getDescription().getName() + "enabled!");
    }
    Logger logger = Logger.getLogger("Minecraft");
}