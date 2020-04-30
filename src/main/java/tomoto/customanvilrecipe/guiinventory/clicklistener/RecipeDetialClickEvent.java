package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;
import tomoto.customanvilrecipe.guiinventory.gui.RecipeDetialGui;
import tomoto.customanvilrecipe.guiinventory.gui.RecipeListGui;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.*;

public class RecipeDetialClickEvent implements Listener {
    @EventHandler
    public void onDetialClick(InventoryClickEvent event) {
        Player player;
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(RecipeDetialGui.GUI_NAME)) {
            player = (Player) event.getWhoClicked();
            event.setCancelled(true);
        }
        else {
            return;
        }

        String buttonName;
        if(event.getCurrentItem() != null) {
            buttonName = event.getCurrentItem().getItemMeta().getDisplayName();
        }
        else {
            return;
        }

        switch(buttonName) {
            case RecipeDetialGui.BACK_BUTTON_NAME:
                new RecipeListGui().openGui(player);
                break;
            case RecipeDetialGui.DELETE_BUTTON_NAME:
                removeRecipe(matchAnvilRecipe(getLeftItem(event.getClickedInventory()), getRightItem(event.getClickedInventory())));
                new RecipeListGui().openGui(player);
                break;
        }
    }

    private void removeRecipe(AnvilRecipe recipe) {
        recipeList.removeIf(r ->
                r.getLeftItem().equals(recipe.getLeftItem()) ||
                r.getRightItem().equals(recipe.getRightItem()));
        resetFileData();
    }

    private static ItemStack getLeftItem(Inventory detials) {
        return detials.getItem(2);
    }

    private static ItemStack getRightItem(Inventory detials) {
        return detials.getItem(4);
    }
}
