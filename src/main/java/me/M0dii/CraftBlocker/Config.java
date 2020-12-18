package me.M0dii.CraftBlocker;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config
{
    public static String CANNOT_CRAFT;
    public static List<String> BLOCKED_ITEMS;

    public static void load(Main plugin)
    {
        FileConfiguration cfg = plugin.getConfig();
    
        CANNOT_CRAFT = format(cfg.getString("M0-CraftBlocker.CraftBlocked"));
        BLOCKED_ITEMS = cfg.getStringList("M0-CraftBlocker.BlockedItems");
    }
    
    private static String format(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
