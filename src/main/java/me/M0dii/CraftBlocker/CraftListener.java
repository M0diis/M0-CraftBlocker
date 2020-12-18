package me.M0dii.CraftBlocker;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.permissions.Permission;

import java.util.List;

public class CraftListener implements Listener
{
    private final Main plugin;
    
    public CraftListener(Main plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void cancelCraft(PrepareItemCraftEvent e)
    {
        Recipe recipe = e.getRecipe();
    
        if(recipe != null)
        {
            Material itemType = recipe.getResult().getType();
            
            HumanEntity pl = e.getView().getPlayer();
            
            String itemName = itemType.name().toLowerCase().trim();
    
            String allowPerm = "m0craftblocker." + itemName + ".allow";
            String denyPerm = "m0craftblocker." + itemName + ".deny";
    
            if(pl.hasPermission(denyPerm))
            {
                e.getInventory().setResult(new ItemStack(Material.AIR));
    
                pl.sendMessage(Config.CANNOT_CRAFT);
                
                return;
            }
            
            if(pl.hasPermission(allowPerm)) return;
            
            for(String item : Config.BLOCKED_ITEMS)
            {
                if(itemType == Material.getMaterial(item))
                {
                    for(HumanEntity p : e.getViewers())
                    {
                        if(p instanceof Player)
                        {
                            if(!p.hasPermission("m0craftblocker.bypass"))
                            {
                                e.getInventory().setResult(new ItemStack(Material.AIR));

                                p.sendMessage(Config.CANNOT_CRAFT);
                            }
                        }
                    }
                }
            }
        }
    }
}
