package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGui;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGui;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.matchAnvilRecipe;
import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeList;

public class CreateClickEvent implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        int slot;
        Player player;
        if(event.getWhoClicked().getOpenInventory().getTitle().equals(CreateGui.GUI_NAME)) {
            player = (Player)event.getWhoClicked();
            slot = event.getRawSlot();
            if(slot < event.getInventory().getSize()) {
                if(!CreateGui.isMaterialSlot(slot)) {
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
            case CreateGui.SAVE_BUTTON_NAME:
                CreateNewRecipe(player, event.getClickedInventory());
                break;
            case CreateGui.BACK_BUTTON_NAME:
                new MenuGui().openGui(player);
                break;
            default:
                break;
        }
    }

    private void CreateNewRecipe(Player player, Inventory create) {
        if(create.getItem(2) == null ||
                create.getItem(4) == null ||
                create.getItem(6) == null) {
            return;
        }

        ItemStack leftItem = create.getItem(2);
        ItemStack rightItem = create.getItem(4);
        ItemStack resultItem = create.getItem(6);

        AnvilRecipe recipe = new AnvilRecipe();
        recipe.setLeftItem(leftItem);
        recipe.setRightItem(rightItem);
        recipe.setResultItem(resultItem);

        if(matchAnvilRecipe(leftItem, rightItem) == null) {
            if(recipe.saveToFile()) {
                player.closeInventory();
                recipeList.add(recipe);
                player.sendMessage("[CustomAnvilRecipe]: " + ChatColor.GREEN + "Recipe saved.");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 1);
            }
            else {
                player.sendMessage("[CustomAnvilRecipe]: " + ChatColor.RED + "All three slots must be filled.");
            }
        }
        else {
            player.sendMessage("[CustomAnvilRecipe]: " + ChatColor.RED + "A recipe using these materials already exists.");
        }
    }
}
