package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class MenuGui extends InventoryGui {
    private static final String createButtonName = "§lCreate";
    private static final String listButtonName = "§lList";

    private Inventory menu = Bukkit.createInventory(player, 1*9, guiName);

    public MenuGui(Player player) {
        super(player);
        guiName = "Anvil Menu";
    }

    @Override
    public void openGui() {
        ItemStack createButton = new ItemStack(Material.ANVIL);
        ItemMeta meta = createButton.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§eClick to creat new anvil recipe.");
        meta.setLore(lore);
        meta.setDisplayName(createButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        createButton.setItemMeta(meta);

        ItemStack listButton = new ItemStack(Material.PAPER);
        meta = listButton.getItemMeta();
        lore.clear();
        lore.add("§eClick to check custom anvil recipes list.");
        meta.setLore(lore);
        meta.setDisplayName(listButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        listButton.setItemMeta(meta);

        for(int i = 0; i < 9; ++i) {
            menu.setItem(i, grayGlassPane);
        }
        menu.setItem(0, createButton);
        menu.setItem(1, listButton);

        player.closeInventory();
        player.openInventory(menu);
    }

    public static String getCreateButtonName() {
        return createButtonName;
    }

    public static String getListButtonName() {
        return listButtonName;
    }

    @Override
    public int getSize() {
        return menu.getSize();
    }

    @Override
    public int getMaxStackSize() {
        return menu.getMaxStackSize();
    }

    @Override
    public void setMaxStackSize(int size) {
        menu.setMaxStackSize(size);
    }

    @Override
    public ItemStack getItem(int index) {
        return menu.getItem(index);
    }

    @Override
    public void setItem(int index, ItemStack item) {
        menu.setItem(index, item);
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        return menu.addItem(items);
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        return menu.removeItem(items);
    }

    @Override
    public ItemStack[] getContents() {
        return menu.getContents();
    }

    @Override
    public void setContents(ItemStack[] items) throws IllegalArgumentException {
        menu.setContents(items);
    }

    @Override
    public ItemStack[] getStorageContents() {
        return menu.getStorageContents();
    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        menu.setStorageContents(items);
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return menu.contains(material);
    }

    @Override
    public boolean contains(ItemStack item) {
        return menu.contains(item);
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        return menu.contains(material, amount);
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        return menu.contains(item, amount);
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        return menu.containsAtLeast(item, amount);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return menu.all(material);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        return menu.all(item);
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        return menu.first(material);
    }

    @Override
    public int first(ItemStack item) {
        return menu.first(item);
    }

    @Override
    public int firstEmpty() {
        return menu.firstEmpty();
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        menu.remove(material);
    }

    @Override
    public void remove(ItemStack item) {
        menu.remove(item);
    }

    @Override
    public void clear(int index) {
        menu.clear(index);
    }

    @Override
    public void clear() {
        menu.clear();
    }

    @Override
    public List<HumanEntity> getViewers() {
        return menu.getViewers();
    }

    @Override
    public InventoryType getType() {
        return menu.getType();
    }

    @Override
    public InventoryHolder getHolder() {
        return menu.getHolder();
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return menu.iterator();
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return menu.iterator(index);
    }

    @Override
    public Location getLocation() {
        return menu.getLocation();
    }
}
