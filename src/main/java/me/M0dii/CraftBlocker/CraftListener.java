package me.M0dii.CraftBlocker;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
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
        
            for(String item : Config.BLOCKED_ITEMS)
            {
                if(itemType == Material.getMaterial(item))
                {
                    e.getInventory().setResult(new ItemStack(Material.AIR));
                
                    for(HumanEntity p : e.getViewers())
                    {
                        if(p instanceof Player)
                        {
                            if(!p.hasPermission("m0craftblocker.bypass"))
                            {
                                p.sendMessage(Config.CANNOT_CRAFT);
                            }
                        }
                    }
                }
            }
        }
    }
}
