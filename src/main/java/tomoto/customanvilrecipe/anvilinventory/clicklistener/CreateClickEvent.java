package tomoto.customanvilrecipe.anvilinventory.clicklistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import tomoto.customanvilrecipe.anvilinventory.gui.CreateGUI;

public class CreateClickEvent {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(CreateGUI.getGUIName())) {
            if(event.getSlot() == 11 ||
                    event.getSlot() == 13 ||
                    event.getSlot() == 15
                    //Material and player Inventory ready to judge
            ) {

            }
            else {
                event.setCancelled(true);
            }
        }
    }
}
