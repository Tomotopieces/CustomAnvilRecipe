package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class CreateGui extends InventoryGui {
    public static final String backButtonName = "§r§b§l§oBack";
    public static final String saveButtonName = "§r§b§l§oSave";

    private static ItemStack backButton = new ItemStack(Material.BOOK);
    private static ItemStack saveButton = new ItemStack(Material.MAP);

    private Inventory create = Bukkit.createInventory(player, 1*9, guiName);

    /**
     * Construct new Create gui for player.
     * @param player Player who clicked the "Create" button in menu or use the command "anvil create".
     */
    public CreateGui(Player player) {
        super(player);
        guiName = "Anvil Recipe Create";

        ItemMeta meta;
        ArrayList<String> lore = new ArrayList<>();

        meta = backButton.getItemMeta();
        meta.setDisplayName(backButtonName);
        lore.clear();
        lore.add("§r§eClick to return to menu.");
        meta.setLore(lore);
        backButton.setItemMeta(meta);

        meta = saveButton.getItemMeta();
        meta.setDisplayName(saveButtonName);
        lore.clear();
        lore.add("§r§eClick to save recipe.");
        meta.setLore(lore);
        saveButton.setItemMeta(meta);

        for(int i = 0; i < create.getSize(); ++i) {
            create.setItem(i, grayGlassPane);
        }
        create.setItem(2, null);
        create.setItem(4, null);
        create.setItem(6, null);
        create.setItem(0, saveButton);
        create.setItem(8, backButton);
    }

    /**
     * Open Create gui.
     */
    @Override
    public void openGui() {
        player.closeInventory();
        player.openInventory(create);
    }

    /**
     * Check if the slot is a material slot or a result slot.
     * @param index The raw slot index to check for.
     * @return true if the slot is a material or a result slot.
     */
    public boolean isMaterialSlot(int index) {
        return index == 2 || index == 4 || index == 6;
    }

    @Override
    public int getSize() {
        return create.getSize();
    }

    @Override
    public int getMaxStackSize() {
        return create.getMaxStackSize();
    }

    @Override
    public void setMaxStackSize(int size) {
        create.setMaxStackSize(size);
    }

    @Override
    public ItemStack getItem(int index) {
        return create.getItem(index);
    }

    @Override
    public void setItem(int index, ItemStack item) {
        create.setItem(index, item);
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        return create.addItem(items);
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        return create.removeItem(items);
    }

    @Override
    public ItemStack[] getContents() {
        return create.getContents();
    }

    @Override
    public void setContents(ItemStack[] items) throws IllegalArgumentException {
        create.setContents(items);
    }

    @Override
    public ItemStack[] getStorageContents() {
        return create.getStorageContents();
    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        create.setStorageContents(items);
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return create.contains(material);
    }

    @Override
    public boolean contains(ItemStack item) {
        return create.contains(item);
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        return create.contains(material, amount);
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        return create.contains(item, amount);
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        return create.containsAtLeast(item, amount);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return create.all(material);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        return create.all(item);
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        return create.first(material);
    }

    @Override
    public int first(ItemStack item) {
        return create.first(item);
    }

    @Override
    public int firstEmpty() {
        return create.firstEmpty();
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        create.remove(material);
    }

    @Override
    public void remove(ItemStack item) {
        create.remove(item);
    }

    @Override
    public void clear(int index) {
        create.clear(index);
    }

    @Override
    public void clear() {
        create.clear();
    }

    @Override
    public List<HumanEntity> getViewers() {
        return create.getViewers();
    }

    @Override
    public InventoryType getType() {
        return create.getType();
    }

    @Override
    public InventoryHolder getHolder() {
        return create.getHolder();
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return create.iterator();
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return create.iterator(index);
    }

    @Override
    public Location getLocation() {
        return create.getLocation();
    }
}
