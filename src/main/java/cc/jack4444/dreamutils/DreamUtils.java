package cc.jack4444.dreamutils;

import cc.jack4444.dreamutils.Listener.GMPListener;
import cc.jack4444.dreamutils.config.copyConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DreamUtils extends JavaPlugin {

    public static DreamUtils DreamUtils;


    @Override
    public void onEnable() {
        // Plugin startup logic
        DreamUtils = this;

        this.getLogger().info("DreamUtils Plugin Enabled");

        if (getServer().getPluginManager().getPlugin("DiscordSRV") == null) {
            getLogger().warning("This plugin required DiscordSRV to run");
            //getLogger().error("This plugin required DiscordSRV to run");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getServer().getPluginManager().getPlugin("GlobalMarketPlus") == null) {
            getLogger().warning("This plugin required GlobalMarketPlus to run");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getServer().getPluginManager().getPlugin("InteractiveChat") == null) {
            getLogger().warning("This plugin required InteractiveChat to run");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getServer().getPluginManager().getPlugin("InteractiveChatDiscordSrvAddon") == null) {
            getLogger().warning("This plugin required InteractiveChat to run");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getPluginManager().registerEvents(new GMPListener(), this);


        copyConfig.copy();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
