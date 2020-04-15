package tomoto.customanvilrecipe.anvilinventory.clicklistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import tomoto.customanvilrecipe.anvilinventory.gui.CreateGUI;

import javax.swing.*;

public class CreateClickEvent {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(CreateGUI.getGUIName())) {
            String buttonName = null;
            if(event.getCurrentItem() != null) {
                buttonName = event.getCurrentItem().getItemMeta().getDisplayName();
            }
            int slot = event.getRawSlot();
            if(slot < event.getInventory().getSize()) {
                if(slot != 2 && slot != 4 && slot != 6) {
                    event.setCancelled(true);
                    if(buttonName != null) {
                        if(buttonName.equals(CreateGUI.backButtonName)) {

                        }
                        else if(buttonName.equals(CreateGUI.saveButtonName)) {

                        }
                        else if(buttonName.equals(CreateGUI.leftSettingsButtonName)) {

                        }
                        else if(buttonName.equals(CreateGUI.rightSettingsButtonName)) {

                        }
                    }
                }
            }
        }
    }
}
