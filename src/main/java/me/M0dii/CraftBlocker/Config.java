package me.M0dii.CraftBlocker;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config
{
    public static String CANNOT_CRAFT;
    public static String NO_PERMISSION;
    public static String CONFIG_RELOADED;
    public static List<String> BLOCKED_ITEMS;

    public static void load(Main plugin)
    {
        FileConfiguration cfg = plugin.getConfig();
    
        CANNOT_CRAFT = format(cfg.getString("M0-CraftBlocker.CraftBlocked"));
        CONFIG_RELOADED = format(cfg.getString("M0-CraftBlocker.ConfigReloaded"));
        NO_PERMISSION = format(cfg.getString("M0-CraftBlocker.NoPermission"));
        BLOCKED_ITEMS = cfg.getStringList("M0-CraftBlocker.BlockedItems");
    }
    
    private static String format(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
