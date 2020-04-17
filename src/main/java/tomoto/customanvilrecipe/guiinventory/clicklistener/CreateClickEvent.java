package tomoto.customanvilrecipe.guiinventory.clicklistener;

import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import com.comphenix.protocol.wrappers.nbt.NbtWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGui;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGui;

public class CreateClickEvent implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        int slot;
        Player player;
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(CreateGui.guiName)) {
            player = (Player)event.getWhoClicked();
            slot = event.getRawSlot();
            if(slot < event.getInventory().getSize()) {
                if(!CreateGui.isMaterialSlot(slot)) {
                    event.setCancelled(true);
                }
                player.sendMessage(Integer.toString(slot));
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
            case CreateGui.saveButtonName:
                CreateNewRecipe(event.getClickedInventory());
                break;
            case CreateGui.backButtonName:
                new MenuGui().openGui(player);
                break;
            default:
                player.sendMessage("Meow~");
                break;
        }
    }

    private void CreateNewRecipe(Inventory create) {
        if(create.getItem(2) == null ||
                create.getItem(4) == null ||
                create.getItem(6) == null) {
            return;
        }

        ItemStack leftMaterial = create.getItem(2);
        ItemStack rightMaterial = create.getItem(4);
        ItemStack resultItem = create.getItem(6);

        NbtWrapper<?> leftNbt = NbtFactory.fromItemTag(leftMaterial);
        NbtWrapper<?> rightNbt = NbtFactory.fromItemTag(rightMaterial);
        NbtWrapper<?> resultNbt = NbtFactory.fromItemTag(resultItem);

        AnvilRecipe recipe = new AnvilRecipe();
        recipe.setLeftMaterial(leftMaterial);
        recipe.setRightMaterial(rightMaterial);
        recipe.setResultItem(resultItem);
        recipe.setLeftNbt(leftNbt);
        recipe.setRightNbt(rightNbt);
        recipe.setResultNbt(resultNbt);

        recipe.saveToFile();
    }
}
