package tomoto.customanvilrecipe.anvilinventory.clicklistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tomoto.customanvilrecipe.anvilinventory.gui.CreateGUI;
import tomoto.customanvilrecipe.anvilinventory.gui.MenuGUI;

public class MenuClickEvent implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(MenuGUI.getGUIName())) {
            event.setCancelled(true);
            if(event.getCurrentItem() != null) {
                if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§lCreate")) {
                    new CreateGUI((Player)event.getWhoClicked()).openInventoryGUI();
                }
            }
        }
    }
}
