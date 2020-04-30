package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.stream.Stream;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeList;

public class RecipeListGui extends InventoryGui {
    public static final String GUI_NAME = "Recipe List";
    public static final String PREVIOUS_BUTTON_AVAILABLE_NAME = "§r§b§l§oPrevious page";
    public static final String PREVIOUS_BUTTON_UNAVAILABLE_NAME = "§r§8§l§oPrevious page";
    public static final String NEXT_BUTTON_AVAILABLE_NAME = "§r§b§l§oNext page";
    public static final String NEXT_BUTTON_UNAVAILABLE_NAME = "§r§8§l§oNext page";
    public static final String PAGE_BUTTON_NAME = "§r§b§l§oPage Number";

    @Override
    public RecipeListGui openGui(Player player) {
        Optional.of(player)
                .map(p -> {
                    super.openGui(p);
                    p.closeInventory();
                    p.openInventory(Optional.of(Bukkit.createInventory(p, 54, GUI_NAME))
                            .map(inv -> showPage(inv, 1)).get());
                    return p;
        });
        return this;
    }

    /**
     * Show the page of recipeList by pageNumber.
     * @param list Current RecipeListGui.
     * @param pageNumber Page number.
     */
    public static Inventory showPage(Inventory list, int pageNumber) {
        if(pageNumber > pageSize() || pageNumber <= 0) {
            Bukkit.getPluginManager().getPlugin("CustomAnvilRecipe").getLogger().info("Page out of bounds.");
            return list;
        }
        Optional.of(new ItemStack(Material.MAP)).map(b -> {
            if(pageNumber == 1) {
                setButton(b, PREVIOUS_BUTTON_UNAVAILABLE_NAME, "§r§8Here is the first page.");
            }
            else {
                setButton(b, PREVIOUS_BUTTON_AVAILABLE_NAME, "§r§eClick to turn to the previous page.");
            }
            list.setItem(45, b);
            if (pageNumber == pageSize()) {
                setButton(b, NEXT_BUTTON_UNAVAILABLE_NAME, "§r§8Here is the last page.");
            }
            else {
                setButton(b, NEXT_BUTTON_AVAILABLE_NAME, "§r§eClick to turn to the next page.");
            }
            list.setItem(53, b);
            b.setType(Material.PAPER);
            setButton(b, PAGE_BUTTON_NAME, Integer.toString(pageNumber));
            b.setAmount(pageNumber);
            list.setItem(49, b);
            b.setType(Material.BOOK);
            setButton(b, BACK_BUTTON_NAME, "§r§eClick to return to the menu.");
            list.setItem(52, b);
            return b;
        });
        Optional.of(list)
                .map(inv -> {
                    Stream.iterate((pageNumber - 1) * 45, i -> i + 1)
                            .limit(Math.min(recipeList.size() - (pageNumber - 1) * 45, 45))
                            .forEach(i -> inv.setItem(i, recipeList.get(i).getResultItem()));
                    return inv;
                })
                .map(inv -> {
                    Optional.of(getPageButton(inv)).map(b -> {
                        setButton(b, b.getItemMeta().getDisplayName(), Integer.toString(pageNumber));
                        return b;
                    });
                    return inv;
                });
        return list;
    }

    /**
     * Get the number of pages.
     * @return The number of pages.
     */
    public static int pageSize() {
        return recipeList.size() / 45 + 1;
    }

    /**
     * Get the PageButton of the gui.
     * @param list Current RecipeListGui.
     * @return The PageButton of current gui.
     */
    public static ItemStack getPageButton(Inventory list) {
        return list.getItem(49);
    }

    /**
     * Get current pageNumber by PageButton.
     * @param pageButton Current PageButton.
     * @return The pageNumber of current gui.
     */
    public static int getPageNumber(ItemStack pageButton) {
        return Integer.parseInt(pageButton.getItemMeta().getLore().get(0));
    }
}
