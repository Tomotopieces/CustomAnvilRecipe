package tomoto.customanvilrecipe.anvilinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MaterialSettingsGUI extends AnvilInventoryGUI{
    public enum Position {LEFT, RIGHT};
    MaterialSettingsGUI(Player player, Position position) {
        super(player);
        _GUIName = "Material Advanced Settings";
        if(position.equals(Position.LEFT)) {
            _GUIName = "Left " + _GUIName;
        }
        else if(position.equals(Position.RIGHT)) {
            _GUIName = "Right " + _GUIName;
        }
    }

    @Override
    public void openInventoryGUI() {
        Inventory MaterialSettings = Bukkit.createInventory(_player, 5*9, _GUIName);

    }
}
