package me.M0dii.CraftBlocker;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class CraftListener implements Listener
{
    private final CraftBlocker plugin;
    
    public CraftListener(CraftBlocker plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void cancelCraft(PrepareItemCraftEvent e)
    {
        Recipe recipe = e.getRecipe();
    
        if(recipe != null)
        {
            HumanEntity pl = e.getView().getPlayer();
            
            if(!pl.hasPermission("m0craftblocker.bypass"))
                return;
    
            if(pl.hasPermission("m0craftblocker.*.deny"))
            {
                e.getInventory().setResult(new ItemStack(Material.AIR));
        
                pl.sendMessage(Config.CANNOT_CRAFT);
        
                return;
            }
            
            Material itemType = recipe.getResult().getType();
            String itemName = itemType.name().toLowerCase().trim();
    
            String allowPerm = "m0craftblocker." + itemName + ".allow";
            String denyPerm = "m0craftblocker." + itemName + ".deny";
    
            if(pl.hasPermission(allowPerm))
                return;
            
            if(pl.hasPermission(denyPerm))
            {
                e.getInventory().setResult(new ItemStack(Material.AIR));
    
                pl.sendMessage(Config.CANNOT_CRAFT);
                
                return;
            }
            
            if(Config.WHITELIST)
            {
                if(!Config.BLOCKED_ITEMS.contains(itemType))
                {
                    for(HumanEntity p : e.getViewers())
                    {
                        if(p instanceof Player)
                        {
                            e.getInventory().setResult(new ItemStack(Material.AIR));
                
                            p.sendMessage(Config.CANNOT_CRAFT);
                        }
                    }
                }
            }
            else
            {
                if(Config.BLOCKED_ITEMS.contains(itemType))
                {
                    for(HumanEntity p : e.getViewers())
                    {
                        if(p instanceof Player)
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
