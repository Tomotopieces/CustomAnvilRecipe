package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;

import java.util.Optional;
import java.util.stream.Stream;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeList;
import static tomoto.customanvilrecipe.guiinventory.gui.CreateGui.LEVEL_BUTTON_NAME;

public class RecipeDetialGui extends InventoryGui {
    public static final String GUI_NAME = "Recipe Detials";
    public static final String DELETE_BUTTON_NAME = "§r§4§lDelete the Recipe";

    private int recipeIndex;

    public RecipeDetialGui(int recipeIndex) {
        this.recipeIndex = recipeIndex;
    }

    @Override
    public RecipeDetialGui openGui(Player player) {
        Optional.of(player)
                .map(p -> {
                    super.openGui(p);
                    p.closeInventory();
                    p.openInventory(Optional.of(Bukkit.createInventory(p, 9, GUI_NAME))
                            .map(inv -> {
                                Stream.iterate(0, i -> i + 1)
                                        .limit(inv.getSize())
                                        .forEach(i -> {
                                            AnvilRecipe recipe = recipeList.get(recipeIndex);
                                            switch (i) {
                                                case 0:
                                                    inv.setItem(i, Optional.of(new ItemStack(Material.BARRIER))
                                                            .map(b -> setButton(b, DELETE_BUTTON_NAME, "§r§8Click to delete the recipe.")).get());
                                                    return;
                                                case 2:
                                                    inv.setItem(i, recipe.getLeftItem());
                                                    return;
                                                case 4:
                                                    inv.setItem(i, recipe.getRightItem());
                                                    return;
                                                case 5:
                                                    if(recipe.getRequiredLevel() == 0) {
                                                        inv.setItem(i, Optional.of(new ItemStack(Material.GLASS_BOTTLE))
                                                                .map(eb -> setButton(eb, LEVEL_BUTTON_NAME)).get());
                                                    }
                                                    else {
                                                        inv.setItem(i, Optional.of(new ItemStack(Material.EXPERIENCE_BOTTLE))
                                                                .map(eb -> {
                                                                    setButton(eb, LEVEL_BUTTON_NAME);
                                                                    eb.setAmount(recipe.getRequiredLevel());
                                                                    return eb;
                                                                }).get());
                                                    }
                                                    return;
                                                case 6:
                                                    inv.setItem(i, recipeList.get(recipeIndex).getResultItem());
                                                    return;
                                                case 8:
                                                    inv.setItem(i, Optional.of(new ItemStack(Material.BOOK))
                                                            .map(sb -> setButton(sb, BACK_BUTTON_NAME, "§r§eClick to return to the list.")).get());
                                                    return;
                                                default:
                                                    inv.setItem(i, GRAY_GLASS_PANE);
                                                    return;
                                            }
                                        });
                                return inv;
                            }).get());
                    return p;
                });
        return this;
    }
}
