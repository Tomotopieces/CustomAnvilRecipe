package tomoto.customanvilrecipe.anvillistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.matchAnvilRecipe;

/**
 * Called when a player prepare to use a custom anvil recipe.
 */
public class AnvilSetItemEvent implements Listener {
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        if(inventory.getItem(0) == null || inventory.getItem(1) == null) {
            return;
        }

        ItemStack leftItem = inventory.getItem(0);
        ItemStack rightItem = inventory.getItem(1);

        if(matchAnvilRecipe(leftItem, rightItem) == null) {
            return;
        }

        Optional.of(matchAnvilRecipe(leftItem, rightItem)).map(recipe -> {
            Optional.of(new ItemStack(recipe.getResultItem()))
                    .map(item -> {
                        event.setResult(item);
                        event.getInventory().setItem(2, item);
                        return item;});
            return recipe;
        });
    }
}
