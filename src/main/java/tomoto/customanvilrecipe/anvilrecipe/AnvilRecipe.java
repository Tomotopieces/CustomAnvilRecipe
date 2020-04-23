package tomoto.customanvilrecipe.anvilrecipe;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import org.bukkit.Material;

import java.io.File;
import java.time.LocalDateTime;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.dataFolder;
import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeFile;

/**
 * Custom anvil recipe.
 */
public class AnvilRecipe {
    private Material leftMaterial;
    private NbtCompound leftNbt;
    private Material rightMaterial;
    private NbtCompound rightNbt;
    private Material resultMaterial;
    private NbtCompound resultNbt;

    /**
     * Create a new anvil recipe.
     * @param leftMaterial The left material of the recipe.
     * @param leftNbt The NBT data of the left material.
     * @param rightMaterial The right material of the recipe.
     * @param rightNbt The NBT data of the right material.
     * @param resultItem The result item of the recipe.
     * @param resultNbt The NBT of the result item.
     */
    public AnvilRecipe(Material leftMaterial, NbtCompound leftNbt,
                       Material rightMaterial, NbtCompound rightNbt,
                       Material resultItem, NbtCompound resultNbt) {
        this.leftMaterial = leftMaterial;
        this.leftNbt = leftNbt;
        this.rightMaterial = rightMaterial;
        this.rightNbt = rightNbt;
        this.resultMaterial = resultItem;
        this.resultNbt = resultNbt;
    }

    /**
     * Create an empty anvil recipe.
     */
    public AnvilRecipe() {
        this(null, null,
                null, null,
                null, null);
    }

    /**
     * Save the recipe to file.
     * @return The recipe.
     */
    public boolean saveToFile() {
        if(leftMaterial == null || leftNbt == null ||
                rightMaterial == null || rightNbt == null ||
                resultMaterial == null || resultNbt == null) {
            return false;
        }
        else {
            LocalDateTime time = LocalDateTime.now();
            String key = time.toString().replace('.', ' ');
            recipeFile.set(key + ".LeftMaterial", leftMaterial.toString());
            recipeFile.set(key + ".LeftNbt", leftNbt.toString());
            recipeFile.set(key + ".RightMaterial", rightMaterial.toString());
            recipeFile.set(key + ".RightNbt", rightNbt.toString());
            recipeFile.set(key + ".ResultItem", resultMaterial.toString());
            recipeFile.set(key + ".ResultNbt", resultNbt.toString());
            try {
                recipeFile.save(new File(dataFolder, "recipeData.yml"));
            }
            catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    /**
     * Get the left material (not NBT data) of the recipe.
     * @see AnvilRecipe#getLeftNbt()
     * @return The left material (not NBT data) of the recipe.
     */
    public Material getLeftMaterial() {
        return leftMaterial;
    }

    /**
     * Get the NBT data (not item meta) of the left material.
     * @see AnvilRecipe#getLeftMaterial()
     * @return The NBT data of the left material.
     */
    public NbtCompound getLeftNbt() {
        return leftNbt;
    }

    /**
     * Get the right material (not NBT data) of the recipe.
     * @see AnvilRecipe#getRightNbt()
     * @return The right material of the recipe.
     */
    public Material getRightMaterial() {
        return rightMaterial;
    }

    /**
     * Get the NBT data (not item meta) of the right material.
     * @see AnvilRecipe#getRightMaterial()
     * @return The NBT data of the right material.
     */
    public NbtCompound getRightNbt() {
        return rightNbt;
    }

    /**
     * Get the result material (not NBT data) of the recipe.
     * @see AnvilRecipe#getResultNbt()
     * @return The result material of the recipe.
     */
    public Material getResultMaterial() {
        return resultMaterial;
    }

    /**
     * Get the NBT data (not item meta) of the result item.
     * @see AnvilRecipe#getResultMaterial()
     * @return The NBT data of the result item.
     */
    public NbtCompound getResultNbt() {
        return resultNbt;
    }

    /**
     * Set the left material (not NBT data) of the recipe.
     * @see AnvilRecipe#setLeftNbt(NbtCompound)
     * @param leftMaterial The left material of the recipe.
     */
    public void setLeftMaterial(Material leftMaterial) {
        this.leftMaterial = leftMaterial;
    }

    /**
     * Set the NBT data (not item meta) of the lest material.
     * @see AnvilRecipe#setLeftMaterial(Material)
     * @param leftNbt The NBT data of the lest material.
     */
    public void setLeftNbt(NbtCompound leftNbt) {
        this.leftNbt = leftNbt;
    }

    /**
     * Set the right material (not NBT data) of the recipe.
     * @see AnvilRecipe#setRightNbt(NbtCompound)
     * @param rightMaterial The right material of the recipe.
     */
    public void setRightMaterial(Material rightMaterial) {
        this.rightMaterial = rightMaterial;
    }

    /**
     * Set the right NBT data (not item meta) of the right material.
     * @see AnvilRecipe#setRightMaterial(Material)
     * @param rightNbt The right NBT data of the right material.
     */
    public void setRightNbt(NbtCompound rightNbt) {
        this.rightNbt = rightNbt;
    }

    /**
     * Set the result item (not NBT data) of the recipe.
     * @see AnvilRecipe#setResultNbt(NbtCompound)
     * @param resultMaterial The result item of the recipe.
     */
    public void setResultMaterial(Material resultMaterial) {
        this.resultMaterial = resultMaterial;
    }

    /**
     * Set the NBT data (not item meta) of the result item.
     * @see AnvilRecipe#setResultMaterial(Material)
     * @param resultNbt The NBT data of the result item.
     */
    public void setResultNbt(NbtCompound resultNbt) {
        this.resultNbt = resultNbt;
    }
}
