package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGui;
import tomoto.customanvilrecipe.guiinventory.gui.RecipeListGui;

import static tomoto.customanvilrecipe.guiinventory.gui.InventoryGui.setButton;
import static tomoto.customanvilrecipe.guiinventory.gui.RecipeListGui.getPage;
import static tomoto.customanvilrecipe.guiinventory.gui.RecipeListGui.getPageButton;

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
            case RecipeListGui.PREVIOUS_BUTTON_NAME_UNAVAILABLE:
            case RecipeListGui.NEXT_BUTTON_NAME_UNAVAILABLE:

                break;
            case RecipeListGui.BACK_BUTTON_NAME:
                new MenuGui().openGui(player);
                break;
            default:
                //new RecipeCheckGui().openGui(player);
                break;
        }
    }

    private void turnPage(Inventory list, String operation) {
        if(operation.equals(RecipeListGui.PREVIOUS_BUTTON_NAME_UNAVAILABLE)) {
            ItemStack pageButton = getPageButton(list);
            if(getPage(pageButton) != 1) {
                setButton(pageButton, pageButton.getItemMeta().getDisplayName(), Integer.toString(getPage(pageButton) - 1));
            }
            else {
                return;
            }
        }
        else if(operation.equals(RecipeListGui.NEXT_BUTTON_NAME_UNAVAILABLE)) {
            //
        }
    }
}
