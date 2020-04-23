package tomoto.customanvilrecipe.anvillistener;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;

import java.util.Optional;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeFile;
import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeList;

public class AnvilClickEvent implements Listener {
    private static boolean isInitialized = false;

    /**
     * Read recipes from file.
     */
    public AnvilClickEvent() {
        if(isInitialized) {
            return;
        }
        for(String key : recipeFile.getKeys(false)) {
            AnvilRecipe recipe = new AnvilRecipe();
            recipe.setLeftMaterial(Material.getMaterial(recipeFile.getString(key + ".LeftMaterial")));
            recipe.setLeftNbt(NbtFactory.ofCompound(recipeFile.getString(key + ".LeftNbt")));
            recipe.setRightMaterial(Material.getMaterial(recipeFile.getString(key + ".RightMaterial")));
            recipe.setLeftNbt(NbtFactory.ofCompound(recipeFile.getString(key + ".RightNbt")));
            recipe.setRightMaterial(Material.getMaterial(recipeFile.getString(key + ".ResultMaterial")));
            recipe.setLeftNbt(NbtFactory.ofCompound(recipeFile.getString(key + ".ResultNbt")));
            recipeList.add(recipe);
        }
        isInitialized = true;
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        if(event.getInventory().getItem(0) == null || event.getInventory().getItem(1) == null) {
            return;
        }
        ItemStack leftMaterial = event.getInventory().getItem(0);
        NbtCompound leftNbt = NbtFactory.asCompound(NbtFactory.fromItemTag(event.getInventory().getItem(0)));
        ItemStack rightMaterial = event.getInventory().getItem(1);
        NbtCompound rightNbt = NbtFactory.asCompound(NbtFactory.fromItemTag(event.getInventory().getItem(1)));

        recipeList.stream()
                .filter(recipe -> recipe.getLeftMaterial().equals(leftMaterial.getType()))
                .filter(recipe -> recipe.getLeftNbt().toString().equals(leftNbt.toString()))
                .filter(recipe -> recipe.getRightMaterial().equals(rightMaterial.getType()))
                .filter(recipe -> recipe.getRightNbt().toString().equals(rightNbt.toString()))
                .forEach(recipe -> event.setResult(Optional.of(new ItemStack(recipe.getResultMaterial()))
                        .map(item -> {
                            NbtFactory.setItemTag(item, recipe.getResultNbt());
                            return item;
                        }).get()));
    }

    @EventHandler
    public void onClickResult(InventoryClickEvent event) {

    }
}
