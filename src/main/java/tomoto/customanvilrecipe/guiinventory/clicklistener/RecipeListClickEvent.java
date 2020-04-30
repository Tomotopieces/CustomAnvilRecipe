package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGui;
import tomoto.customanvilrecipe.guiinventory.gui.RecipeDetialGui;
import tomoto.customanvilrecipe.guiinventory.gui.RecipeListGui;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeList;
import static tomoto.customanvilrecipe.guiinventory.gui.RecipeListGui.getPageButton;
import static tomoto.customanvilrecipe.guiinventory.gui.RecipeListGui.getPageNumber;

public class RecipeListClickEvent implements Listener {
    @EventHandler
    public void onListClick(InventoryClickEvent event) {
        Player player;
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(RecipeListGui.GUI_NAME)) {
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
            case RecipeListGui.PREVIOUS_BUTTON_AVAILABLE_NAME:
            case RecipeListGui.NEXT_BUTTON_AVAILABLE_NAME:
                turnPage(event.getClickedInventory(), buttonName);
                break;
            case RecipeListGui.BACK_BUTTON_NAME:
                new MenuGui().openGui(player);
                break;
            default:
                new RecipeDetialGui(recipeList.get((getPageNumber(getPageButton(event.getClickedInventory())) - 1) * 45 + event.getRawSlot())).openGui(player);
                break;
        }
    }

    private void turnPage(Inventory list, String operation) {
        if(operation.equals(RecipeListGui.PREVIOUS_BUTTON_AVAILABLE_NAME)) {
            RecipeListGui.showPage(list, getPageNumber(getPageButton(list)) - 1);
        }
        else if(operation.equals(RecipeListGui.NEXT_BUTTON_AVAILABLE_NAME)) {
            RecipeListGui.showPage(list, getPageNumber(getPageButton(list)) + 1);
        }
    }
}
