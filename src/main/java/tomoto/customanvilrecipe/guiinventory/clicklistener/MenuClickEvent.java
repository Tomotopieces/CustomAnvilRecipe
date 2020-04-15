package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGUI;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGUI;

public class MenuClickEvent implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(MenuGUI.getGUIName())) {
            if(event.getRawSlot() < event.getInventory().getSize()) {
                event.setCancelled(true);
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

        if(buttonName.equals(MenuGUI.getCreateButtonName())) {
            new CreateGUI((Player)event.getWhoClicked()).openInventoryGUI();
        }
    }
}
