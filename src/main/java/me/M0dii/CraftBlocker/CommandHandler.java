package me.M0dii.CraftBlocker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class CommandHandler implements CommandExecutor
{
    private final CraftBlocker plugin;
    
    public CommandHandler(CraftBlocker plugin)
    {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd,
                             @Nonnull String alias, @Nonnull String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player)sender;
    
            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("reload"))
                {
                    if(p.hasPermission("m0craftblocker.reload"))
                    {
                        this.plugin.reloadConfig();
                        this.plugin.saveConfig();
    
                        Config.load(plugin);
    
                        p.sendMessage(Config.CONFIG_RELOADED);
                    }
                    else
                    {
                        p.sendMessage(Config.NO_PERMISSION);
                    }
    
                    return true;
                }
            }
        }
        
        return true;
    }
}
