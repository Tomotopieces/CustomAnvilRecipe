package tomoto.customanvilrecipe.anvilrecipe;

import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeFile;
import static tomoto.customanvilrecipe.CustomAnvilRecipe.saveRecipeFile;

/**
 * Custom anvil recipe.
 */
public class AnvilRecipe {
    private ItemStack leftItem;
    private ItemStack rightItem;
    private ItemStack resultItem;

    /**
     *
     * @param leftItem The left item of the recipe.
     * @param rightItem The right item of the recipe.
     * @param resultItem The result item of the recipe.
     */
    public AnvilRecipe(ItemStack leftItem, ItemStack rightItem, ItemStack resultItem) {
        this.leftItem = leftItem;
        this.rightItem = rightItem;
        this.resultItem = resultItem;
    }

    /**
     * Create an empty anvil recipe.
     */
    public AnvilRecipe() {
        this(null, null, null);
    }

    /**
     * Save the recipe to file.
     * @return The recipe.
     */
    public boolean saveToFile() {
        if(leftItem == null ||
                rightItem == null ||
                resultItem == null) {
            return false;
        }
        else {
            LocalDateTime time = LocalDateTime.now();
            String key = time.toString().replace('.', ' ');
            recipeFile.set(key + ".LeftMaterial", leftItem.toString());
            recipeFile.set(key + ".RightMaterial", rightItem.toString());
            recipeFile.set(key + ".ResultItem", resultItem.toString());
            saveRecipeFile();
            return true;
        }
    }

    /**
     * Get the left material of the recipe.
     * @return The left material of the recipe.
     */
    public ItemStack getLeftItem() {
        return leftItem;
    }

    /**
     * Get the right material of the recipe.
     * @return The right material of the recipe.
     */
    public ItemStack getRightItem() {
        return rightItem;
    }

    /**
     * Get the result material of the recipe.
     * @return The result material of the recipe.
     */
    public ItemStack getResultItem() {
        return resultItem;
    }

    /**
     * Set the left material of the recipe.
     * @param leftItem The left material of the recipe.
     */
    public void setLeftItem(ItemStack leftItem) {
        this.leftItem = leftItem;
    }

    /**
     * Set the right material of the recipe.
     * @param rightItem The right material of the recipe.
     */
    public void setRightItem(ItemStack rightItem) {
        this.rightItem = rightItem;
    }

    /**
     * Set the result item of the recipe.
     * @param resultItem The result item of the recipe.
     */
    public void setResultItem(ItemStack resultItem) {
        this.resultItem = resultItem;
    }
}
