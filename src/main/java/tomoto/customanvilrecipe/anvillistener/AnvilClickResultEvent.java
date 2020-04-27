package tomoto.customanvilrecipe.anvillistener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;

import java.util.Optional;
import java.util.stream.Stream;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.matchAnvilRecipe;

/**
 * Called when a player click the result slot of an anvil.
 */
public class AnvilClickResultEvent implements Listener {
    @EventHandler
    public void onClickResult(InventoryClickEvent event) {
        if(event.getInventory() instanceof AnvilInventory) {
            AnvilInventory inventory = (AnvilInventory) event.getInventory();
            if(Stream.iterate(0, i -> i + 1)
                    .limit(inventory.getSize())
                    .anyMatch(i -> inventory.getItem(i) == null)) {
                return;
            }
            if(event.getSlotType().equals(InventoryType.SlotType.RESULT)) {
                Player player = (Player) event.getWhoClicked();
                ItemStack leftItem = event.getInventory().getItem(0);
                ItemStack rightItem = event.getInventory().getItem(1);
                int requiredLevel;

                AnvilRecipe recipe = matchAnvilRecipe(leftItem, rightItem);
                if(recipe == null) {
                    return;
                }
                else {
                    requiredLevel = recipe.getRequiredLevel();
                }

                if(player.getLevel() < requiredLevel) {
                    return;
                }

                player.setItemOnCursor(event.getInventory().getItem(2));
                player.setLevel(player.getLevel() - requiredLevel);
                Optional.of(event.getInventory()).map(inv -> {
                    Stream.iterate(0, i -> i + 1)
                            .limit(2)
                            .forEach(i -> inv.removeItem(Optional.of(inv.getItem(i))
                                    .map(item -> {
                                        item.setAmount(1);
                                        return item;
                                    }).get()));
                    return inv;
                });
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 100, 1);
            }
        }
    }
}
