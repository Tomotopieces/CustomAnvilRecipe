package tomoto.customanvilrecipe.guiinventory.clicklistener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGui;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGui;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.matchAnvilRecipe;
import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeList;

public class CreateClickEvent implements Listener {
    private Player player = null;
    @EventHandler
    public void onCreateClick(InventoryClickEvent event) {
        int slot;
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
                CreateNewRecipe(event.getClickedInventory());
                break;
            case CreateGui.BACK_BUTTON_NAME:
                new MenuGui().openGui(player);
                break;
            case CreateGui.LEVEL_BUTTON_NAME:
                ChangeRequiredLevel(event.getClickedInventory(), event.getClick());
            default:
                break;
        }
    }

    private void ChangeRequiredLevel(Inventory create, ClickType type) {
        ItemStack requiredLevel = CreateGui.getLevelButton(create);
        if(type == ClickType.LEFT) {
            if(requiredLevel.getType().equals(Material.GLASS_BOTTLE)) {
                requiredLevel.setType(Material.EXPERIENCE_BOTTLE);
            }
            else {
                requiredLevel.setAmount(requiredLevel.getAmount() + 1);
            }
        }
        else if(type == ClickType.SHIFT_LEFT) {
            if(requiredLevel.getType().equals(Material.GLASS_BOTTLE)) {
                requiredLevel.setType(Material.EXPERIENCE_BOTTLE);
                requiredLevel.setAmount(5);
            }
            else {
                requiredLevel.setAmount(requiredLevel.getAmount() + 5);
            }
        }
        else if(type == ClickType.RIGHT) {
            if(requiredLevel.getType().equals(Material.GLASS_BOTTLE)) {
                return;
            }
            else {
                if (requiredLevel.getAmount() == 1) {
                    requiredLevel.setType(Material.GLASS_BOTTLE);
                }
                else {
                    requiredLevel.setAmount(requiredLevel.getAmount() - 1);
                }
            }
        }
        else if(type == ClickType.SHIFT_RIGHT) {
            if(requiredLevel.getAmount() <= 5) {
                requiredLevel.setType(Material.GLASS_BOTTLE);
                requiredLevel.setAmount(1);
            }
            else {
                requiredLevel.setAmount(requiredLevel.getAmount() - 5);
            }
        }
    }

    private void CreateNewRecipe(Inventory create) {
        if(create.getItem(2) == null ||
                create.getItem(4) == null ||
                create.getItem(6) == null ||
                player == null) {
            player.sendMessage("[CustomAnvilRecipe]: " + ChatColor.RED + "All three slots must be filled.");
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 100, 1);
            return;
        }

        ItemStack leftItem = create.getItem(2);
        ItemStack rightItem = create.getItem(4);
        ItemStack resultItem = create.getItem(6);
        int requiredLevel = create.getItem(5).getAmount();

        AnvilRecipe recipe = new AnvilRecipe();
        recipe.setLeftItem(leftItem);
        recipe.setRightItem(rightItem);
        recipe.setResultItem(resultItem);
        recipe.setRequiredLevel(requiredLevel);

        if(matchAnvilRecipe(leftItem, rightItem) == null) {
            if(recipe.saveToFile()) {
                player.closeInventory();
                recipeList.add(recipe);
                player.sendMessage("[CustomAnvilRecipe]: " + ChatColor.GREEN + "Recipe saved.");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 1);
            }
        }
        else {
            player.sendMessage("[CustomAnvilRecipe]: " + ChatColor.RED + "A recipe using these materials already exists.");
            player.sendMessage("[CustomAnvilRecipe]: You have to delete the old recipe if you want to create this recipe.");
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 100, 1);
        }
    }
}
