package tomoto.customanvilrecipe.anvillistener;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

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
                ItemStack leftMaterial = event.getInventory().getItem(0);
                NbtCompound leftNbt = NbtFactory.asCompound(NbtFactory.fromItemTag(event.getInventory().getItem(0)));
                ItemStack rightMaterial = event.getInventory().getItem(1);
                NbtCompound rightNbt = NbtFactory.asCompound(NbtFactory.fromItemTag(event.getInventory().getItem(1)));
                if(matchAnvilRecipe(leftMaterial, leftNbt, rightMaterial, rightNbt) == null) {
                    player.sendMessage("[CustomAnvilRecipe]: This is not a custom anvil recipe.");
                    return;
                }
                event.getWhoClicked().setItemOnCursor(event.getInventory().getItem(2));
                Optional.of(event.getInventory()).map(inv -> {
                    Stream.iterate(0, i -> i + 1)
                            .limit(3)
                            .forEach(i -> inv.removeItem(Optional.of(inv.getItem(i))
                                    .map(item -> {
                                        item.setAmount(1);
                                        return item;
                                    }).get()));
                    return inv;
                });
                player.playSound(event.getInventory().getLocation(), Sound.BLOCK_ANVIL_USE, 100, 1);
            }
        }
    }
}
