package me.M0dii.CraftBlocker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main extends JavaPlugin
{
    private static Main plugin;
    
    private PluginManager manager;
    
    private boolean loaded;
    
    public Main()
    {
        this.manager = getServer().getPluginManager();
    }
    
    FileConfiguration config = null;
    File configFile = null;
    
    public void onEnable()
    {
        this.configFile = new File(getDataFolder(), "config.yml");
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    
        if(!this.configFile.exists())
        {
            this.configFile.getParentFile().mkdirs();
        
            copy(getResource("config.yml"), configFile);
        }
        
        Config.load(this);
        
        this.getCommand("craftblocker").setExecutor(new CommandHandler(this));
        
        this.manager.registerEvents(new CraftListener(this), this);
    }
    
    private void copy(InputStream in, File file)
    {
        if(in == null)
        {
            this.getLogger().warning("Cannot copy, resource null");
            
            return;
        }
        
        try
        {
            OutputStream out = new FileOutputStream(file);
            
            byte[] buf = new byte[1024];
            
            int len;
            
            while((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
            
            out.close();
            in.close();
        }
        catch(Exception e)
        {
            this.getLogger().warning("Error copying resource: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void onDisable()
    {
        this.manager.disablePlugin(this);
    }
}
