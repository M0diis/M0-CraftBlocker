package me.M0dii.CraftBlocker;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Config
{
    public static String CANNOT_CRAFT;
    public static String NO_PERMISSION;
    public static String CONFIG_RELOADED;
    public static List<Material> BLOCKED_ITEMS;
    
    public static boolean WHITELIST;

    public static void load(CraftBlocker plugin)
    {
        FileConfiguration cfg = plugin.getConfig();
        
        BLOCKED_ITEMS = new ArrayList<>();
    
        CANNOT_CRAFT = format(cfg.getString("M0-CraftBlocker.CraftBlocked"));
        CONFIG_RELOADED = format(cfg.getString("M0-CraftBlocker.ConfigReloaded"));
        NO_PERMISSION = format(cfg.getString("M0-CraftBlocker.NoPermission"));
        for(String bl : cfg.getStringList("M0-CraftBlocker.BlockedItems.Items"))
        {
            Material m = Material.getMaterial(bl);
            
            if(m != null)
                BLOCKED_ITEMS.add(m);

        }
        
        
        WHITELIST = cfg.getBoolean("M0-CraftBlocker.BlockedItems.Whitelist");
    }
    
    private static String format(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
