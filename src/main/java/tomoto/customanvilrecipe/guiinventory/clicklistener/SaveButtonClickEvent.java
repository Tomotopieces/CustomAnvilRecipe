package tomoto.customanvilrecipe.guiinventory.clicklistener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListeningWhitelist;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGui;

public class SaveButtonClickEvent implements PacketListener {
    @Override
    public void onPacketSending(PacketEvent event) {

    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        PacketType packetType = event.getPacketType();

        if(!packetType.equals(PacketType.Play.Server.WINDOW_ITEMS)) {
            return;
        }

        ItemStack item = packet.getItemModifier().read(0);
        if(item.getItemMeta().getDisplayName().equals(CreateGui.saveButtonName)) {

        }
    }

    @Override
    public ListeningWhitelist getSendingWhitelist() {
        return null;
    }

    @Override
    public ListeningWhitelist getReceivingWhitelist() {
        return null;
    }

    @Override
    public Plugin getPlugin() {
        return null;
    }
}
