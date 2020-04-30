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
    private int requiredLevel;

    /**
     * Create an anvil recipe.
     * @param leftItem The left item of the recipe.
     * @param rightItem The right item of the recipe.
     * @param resultItem The result item of the recipe.
     * @param requiredLevel The required level of the recipe.
     */
    public AnvilRecipe(ItemStack leftItem, ItemStack rightItem, ItemStack resultItem, int requiredLevel) {
        this.leftItem = leftItem;
        this.rightItem = rightItem;
        this.resultItem = resultItem;
        this.requiredLevel =requiredLevel;
    }

    /**
     * Create an anvil recipe with required level 0.
     * @param leftItem The left item of the recipe.
     * @param rightItem The right item of the recipe.
     * @param resultItem The result item of the recipe.
     */
    public AnvilRecipe(ItemStack leftItem, ItemStack rightItem, ItemStack resultItem) {
        this(leftItem, rightItem, resultItem, 0);
    }

    /**
     * Create an empty anvil recipe.
     */
    public AnvilRecipe() {
        this(null, null, null);
    }

    /**
     * Save the recipe to file.
     * @return Whether the recipe was saved successfully.
     */
    public void saveToFile() {
        LocalDateTime time = LocalDateTime.now();
        String key = time.toString().replace('.', '-');
        recipeFile.set(key + ".LeftItem", leftItem.serialize());
        recipeFile.set(key + ".RightItem", rightItem.serialize());
        recipeFile.set(key + ".ResultItem", resultItem.serialize());
        recipeFile.set(key + ".RequiredLevel", requiredLevel);
        saveRecipeFile();
    }

    /**
     * Get the left item of the recipe.
     * @return The left item of the recipe.
     */
    public ItemStack getLeftItem() {
        return leftItem;
    }

    /**
     * Get the right item of the recipe.
     * @return The right item of the recipe.
     */
    public ItemStack getRightItem() {
        return rightItem;
    }

    /**
     * Get the result item of the recipe.
     * @return The result item of the recipe.
     */
    public ItemStack getResultItem() {
        return resultItem;
    }

    /**
     * Get the required level of the recipe.
     * @return The required level of the recipe.
     */
    public int getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Set the left item of the recipe.
     * @param leftItem The new left item of the recipe.
     */
    public void setLeftItem(ItemStack leftItem) {
        this.leftItem = leftItem;
    }

    /**
     * Set the right item of the recipe.
     * @param rightItem The new right item of the recipe.
     */
    public void setRightItem(ItemStack rightItem) {
        this.rightItem = rightItem;
    }

    /**
     * Set the result item of the recipe.
     * @param resultItem The new result item of the recipe.
     */
    public void setResultItem(ItemStack resultItem) {
        this.resultItem = resultItem;
    }

    /**
     * Set the required level of the recipe.
     * @param requiredLevel The new required level of the recipe.
     */
    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }
}
