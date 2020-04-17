package tomoto.customanvilrecipe.anvilrecipe;

import com.comphenix.protocol.wrappers.nbt.NbtWrapper;
import org.bukkit.inventory.ItemStack;

/**
 * Custom anvil recipe.
 */
public class AnvilRecipe {
    private ItemStack leftMaterial;
    private NbtWrapper<?> leftNbt;
    private ItemStack rightMaterial;
    private NbtWrapper<?> rightNbt;
    private ItemStack resultItem;
    private NbtWrapper<?> resultNbt;

    /**
     * Create a new anvil recipe.
     * @param leftMaterial Left material of the recipe.
     * @param leftNbt NBT data of the left material.
     * @param rightMaterial Right material of the recipe.
     * @param rightNbt NBT data of the right material.
     * @param resultItem Result item of the recipe.
     * @param resultNbt NBT of the result item.
     */
    public AnvilRecipe(ItemStack leftMaterial, NbtWrapper<?> leftNbt,
                       ItemStack rightMaterial, NbtWrapper<?> rightNbt,
                       ItemStack resultItem, NbtWrapper<?> resultNbt) {
        this.leftMaterial = leftMaterial;
        this.leftNbt = leftNbt;
        this.rightMaterial = rightMaterial;
        this.rightNbt = rightNbt;
        this.resultItem = resultItem;
        this.resultNbt = resultNbt;
    }

    /**
     * Create a empty anvil recipe.
     */
    public AnvilRecipe() {
        this(null, null, null, null, null, null);
    }

    /**
     * Save the recipe to file.
     */
    public void saveToFile() {
    }

    /**
     * Get the left material (not NBT data) of the recipe.
     * @see AnvilRecipe#getLeftNbt()
     * @return The left material (not NBT data) of the recipe.
     */
    public ItemStack getLeftMaterial() {
        return leftMaterial;
    }

    /**
     * Get the NBT data (not item meta) of the left material.
     * @see AnvilRecipe#getLeftMaterial()
     * @return The NBT data (not item meta) of the left material.
     */
    public NbtWrapper<?> getLeftNbt() {
        return leftNbt;
    }

    /**
     * Get the right material (not NBT data) of the recipe.
     * @see AnvilRecipe#getRightNbt()
     * @return The right material (not NBT data) of the recipe.
     */
    public ItemStack getRightMaterial() {
        return rightMaterial;
    }

    /**
     * Get the NBT data (not item meta) of the right material.
     * @see AnvilRecipe#getRightMaterial()
     * @return The NBT data (not item meta) of the right material.
     */
    public NbtWrapper<?> getRightNbt() {
        return rightNbt;
    }

    /**
     * Get the result material (not NBT data) of the recipe.
     * @see AnvilRecipe#getResultNbt()
     * @return The result material (not NBT data) of the recipe.
     */
    public ItemStack getResultItem() {
        return resultItem;
    }

    /**
     * Get the NBT data (not item meta) of the result item.
     * @see AnvilRecipe#getResultItem() 
     * @return The NBT data (not item meta) of the result item.
     */
    public NbtWrapper<?> getResultNbt() {
        return resultNbt;
    }

    /**
     * Set the left material (not NBT data) of the recipe.
     * @see AnvilRecipe#setLeftNbt(NbtWrapper)  
     * @param leftMaterial The left material of the recipe.
     */
    public void setLeftMaterial(ItemStack leftMaterial) {
        this.leftMaterial = leftMaterial;
    }

    /**
     * Set the NBT data (not item meta) of the lest material.
     * @see AnvilRecipe#setLeftMaterial(org.bukkit.inventory.ItemStack)
     * @param leftNbt The NBT data of the lest material.
     */
    public void setLeftNbt(NbtWrapper<?> leftNbt) {
        this.leftNbt = leftNbt;
    }

    /**
     * Set the right material (not NBT data) of the recipe.
     * @see AnvilRecipe#setRightNbt(NbtWrapper)
     * @param rightMaterial The right material of the recipe.
     */
    public void setRightMaterial(ItemStack rightMaterial) {
        this.rightMaterial = rightMaterial;
    }

    /**
     * Set the right NBT data (not item meta) of the right material.
     * @see AnvilRecipe#setRightMaterial(ItemStack)
     * @param rightNbt The right NBT data of the right material.
     */
    public void setRightNbt(NbtWrapper<?> rightNbt) {
        this.rightNbt = rightNbt;
    }

    /**
     * Set the result item (not NBT data) of the recipe.
     * @see AnvilRecipe#setResultNbt(NbtWrapper)
     * @param resultItem The result item of the recipe.
     */
    public void setResultItem(ItemStack resultItem) {
        this.resultItem = resultItem;
    }

    /**
     * Set the NBT data (not item meta) of the result item.
     * @see AnvilRecipe#setResultItem(ItemStack)
     * @param resultNbt The NBT data of the result item.
     */
    public void setResultNbt(NbtWrapper<?> resultNbt) {
        this.resultNbt = resultNbt;
    }
}
