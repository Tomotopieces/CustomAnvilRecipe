package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGUI;
import tomoto.customanvilrecipe.guiinventory.gui.MaterialSettingsGUI;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGUI;

public class CreateClickEvent {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(CreateGUI.getGUIName())) {
            int slot = event.getRawSlot();
            if(slot < event.getInventory().getSize()) {
                if(slot != 2 && slot != 4 && slot != 6) {
                    event.setCancelled(true);
                }
            }
        }
        else {
            return;
        }

        String buttonName = null;
        if(event.getCurrentItem() != null) {
            buttonName = event.getCurrentItem().getItemMeta().getDisplayName();
        }
        else {
            return;
        }

        if(buttonName.equals(CreateGUI.getRightSettingsButtonName())) {
            new MaterialSettingsGUI((Player)event.getWhoClicked(), MaterialSettingsGUI.Position.RIGHT).openInventoryGUI();
        }
        else if(buttonName.equals(CreateGUI.getLeftSettingsButtonName())) {
            new MaterialSettingsGUI((Player)event.getWhoClicked(), MaterialSettingsGUI.Position.LEFT).openInventoryGUI();
        }
        else if(buttonName.equals(CreateGUI.getSaveButtonName())) {

        }
        else if(buttonName.equals(CreateGUI.getBackButtonName())) {
            new MenuGUI((Player)event.getWhoClicked()).openInventoryGUI();
        }
    }
}
