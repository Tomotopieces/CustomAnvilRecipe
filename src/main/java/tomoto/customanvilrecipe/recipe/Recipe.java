package tomoto.customanvilrecipe.recipe;

import com.comphenix.protocol.wrappers.nbt.NbtList;
import org.bukkit.inventory.ItemStack;

public class Recipe {
    private ItemStack leftItem;
    private NbtList<String> leftNbt;
    private ItemStack rightItem;
    private NbtList<String> rightNbt;
    private ItemStack resultItem;
    private NbtList<String> resultNbt;

    private MatchMode loreMatchMode = MatchMode.EXACT;
    private MatchMode nbtMatchMode = MatchMode.EXACT;

    public Recipe(ItemStack leftItem, NbtList<String> leftNbt,
                  ItemStack rightItem, NbtList<String> rightNbt,
                  ItemStack resultItem, NbtList<String> resultNbt) {
        this.leftItem = leftItem;
        this.leftNbt = leftNbt;
        this.rightItem = rightItem;
        this.rightNbt = rightNbt;
        this.resultItem = resultItem;
        this.rightNbt = resultNbt;
    }

    public void saveToFile() {
    }

    public ItemStack getLeftItem() {
        return leftItem;
    }

    public NbtList<String> getLeftNbt() {
        return leftNbt;
    }

    public ItemStack getRightItem() {
        return rightItem;
    }

    public NbtList<String> getRightNbt() {
        return rightNbt;
    }

    public ItemStack getResultItem() {
        return resultItem;
    }

    public NbtList<String> getResultNbt() {
        return resultNbt;
    }

    public MatchMode getLoreMatchMode() {
        return loreMatchMode;
    }

    public MatchMode getNbtMatchMode() {
        return nbtMatchMode;
    }

    public void setLeftItem(ItemStack leftItem) {
        this.leftItem = leftItem;
    }

    public void setLeftNbt(NbtList<String> leftNbt) {
        this.leftNbt = leftNbt;
    }

    public void setRightItem(ItemStack rightItem) {
        this.rightItem = rightItem;
    }

    public void setRightNbt(NbtList<String> rightNbt) {
        this.rightNbt = rightNbt;
    }

    public void setResultItem(ItemStack resultItem) {
        this.resultItem = resultItem;
    }

    public void setResultNbt(NbtList<String> resultNbt) {
        this.resultNbt = resultNbt;
    }

    public void setLoreMatchMode(MatchMode loreMatchMode) {
        this.loreMatchMode = loreMatchMode;
    }

    public void setNbtMatchMode(MatchMode nbtMatchMode) {
        this.nbtMatchMode = nbtMatchMode;
    }
}
