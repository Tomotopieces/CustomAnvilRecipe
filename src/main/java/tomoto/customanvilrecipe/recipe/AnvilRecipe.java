package tomoto.customanvilrecipe.recipe;

import com.comphenix.protocol.wrappers.nbt.NbtList;
import org.bukkit.inventory.ItemStack;

public class AnvilRecipe {
    private ItemStack leftItem;
    private NbtList<String> leftNbt;
    private MatchMode leftLoreMatchMode = MatchMode.EXACT;
    private MatchMode leftNbtMatchMode = MatchMode.EXACT;
    private ItemStack rightItem;
    private NbtList<String> rightNbt;
    private MatchMode rightLoreMatchMode = MatchMode.EXACT;
    private MatchMode rightNbtMatchMode = MatchMode.EXACT;
    private ItemStack resultItem;
    private NbtList<String> resultNbt;

    private MatchMode loreMatchMode = MatchMode.EXACT;
    private MatchMode nbtMatchMode = MatchMode.EXACT;

    public AnvilRecipe(ItemStack leftItem, NbtList<String> leftNbt,
                       ItemStack rightItem, NbtList<String> rightNbt,
                       ItemStack resultItem, NbtList<String> resultNbt) {
        this.leftItem = leftItem;
        this.leftNbt = leftNbt;
        this.rightItem = rightItem;
        this.rightNbt = rightNbt;
        this.resultItem = resultItem;
        this.resultNbt = resultNbt;
    }

    public AnvilRecipe() {
        this(null, null, null, null, null, null);
    }

    public void saveToFile() {
    }

    public ItemStack getLeftItem() {
        return leftItem;
    }

    public NbtList<String> getLeftNbt() {
        return leftNbt;
    }

    public MatchMode getLeftLoreMatchMode() {
        return leftLoreMatchMode;
    }

    public MatchMode getLeftNbtMatchMode() {
        return leftNbtMatchMode;
    }

    public ItemStack getRightItem() {
        return rightItem;
    }

    public NbtList<String> getRightNbt() {
        return rightNbt;
    }

    public MatchMode getRightLoreMatchMode() {
        return rightLoreMatchMode;
    }

    public MatchMode getRightNbtMatchMode() {
        return rightNbtMatchMode;
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

    public void setLeftLoreMatchMode(MatchMode leftLoreMatchMode) {
        this.leftLoreMatchMode = leftLoreMatchMode;
    }

    public void setLeftNbtMatchMode(MatchMode leftNbtMatchMode) {
        this.leftNbtMatchMode = leftNbtMatchMode;
    }

    public void setRightItem(ItemStack rightItem) {
        this.rightItem = rightItem;
    }

    public void setRightNbt(NbtList<String> rightNbt) {
        this.rightNbt = rightNbt;
    }

    public void setRightLoreMatchMode(MatchMode rightLoreMatchMode) {
        this.rightLoreMatchMode = rightLoreMatchMode;
    }

    public void setRightNbtMatchMode(MatchMode rightNbtMatchMode) {
        this.rightNbtMatchMode = rightNbtMatchMode;
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
