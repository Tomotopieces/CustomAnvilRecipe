package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGui;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGui;

public class CreateClickEvent {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        int slot;
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(CreateGui.getGUIName())) {
            slot = event.getRawSlot();
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

        switch (buttonName) {
            case CreateGui.leftLoreSettingButtonName:
            case CreateGui.leftNbtSettingButtonName:
            case CreateGui.rightLoreSettingButtonName:
            case CreateGui.rightNbtSettingButtonName:
                CreateGui.changeMatchMode(event.getInventory().getItem(slot));
                break;
            case CreateGui.saveButtonName:

                break;
            case CreateGui.backButtonName:
                new MenuGui((Player) event.getWhoClicked()).openInventoryGUI();
                break;
        }
    }
}
