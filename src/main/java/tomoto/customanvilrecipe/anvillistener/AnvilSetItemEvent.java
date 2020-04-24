package tomoto.customanvilrecipe.anvillistener;

import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
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
        AnvilInventory inventory = (AnvilInventory) event.getInventory();
        if(inventory.getItem(0) == null || inventory.getItem(1) == null) {
            return;
        }

        ItemStack leftMaterial = event.getInventory().getItem(0);
        NbtCompound leftNbt = NbtFactory.asCompound(NbtFactory.fromItemTag(inventory.getItem(0)));
        ItemStack rightMaterial = event.getInventory().getItem(1);
        NbtCompound rightNbt = NbtFactory.asCompound(NbtFactory.fromItemTag(inventory.getItem(1)));

        if(matchAnvilRecipe(leftMaterial, leftNbt, rightMaterial, rightNbt) != null) {
            event.getViewers().forEach(viewer -> viewer.sendMessage("[CustomAnvilRecipe]: Existing recipe."));
        }
        else {
            event.getViewers().forEach(viewer -> viewer.sendMessage("[CustomAnvilRecipe]: Recipe does not exist."));
        }
        Optional.of(matchAnvilRecipe(leftMaterial, leftNbt, rightMaterial, rightNbt)).map(r -> {
            Optional.of(new ItemStack(r.getResultMaterial()))
                    .map(item -> {
                        NbtFactory.setItemTag(MinecraftReflection.getBukkitItemStack(item), r.getResultNbt());
                        return item;})
                    .map(item -> {
                        event.setResult(item);
                        event.getInventory().setItem(2, item);
                        return item;});
            return r;
        });
    }
}
