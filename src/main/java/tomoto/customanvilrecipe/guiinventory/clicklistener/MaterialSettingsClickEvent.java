package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tomoto.customanvilrecipe.guiinventory.gui.MaterialSettingsGUI;

public class MaterialSettingsClickEvent implements Listener {
    @EventHandler
    public void onMaterialSettingsClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(MaterialSettingsGUI.getGUIName())) {
            if(event.getRawSlot() < event.getInventory().getSize()) {
                event.setCancelled(true);
            }
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

        if(buttonName.equals(MaterialSettingsGUI.getLoreSettingsButtonName())) {

        }
        else if(buttonName.equals(MaterialSettingsGUI.getNbtSettingsButtonName())) {

        }
        else if(buttonName.equals(MaterialSettingsGUI.getBackButtonName())) {

        }
    }
}
