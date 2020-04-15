package tomoto.customanvilrecipe.commandexecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGUI;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGUI;

public class AnvilExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(args.length == 0) {
                new MenuGUI(player).openInventoryGUI();
            }
            else if(args.length == 1) {
                String parameter = args[0];
                if(parameter.equalsIgnoreCase("menu")) {
                    new MenuGUI(player).openInventoryGUI();
                }
                if(parameter.equalsIgnoreCase("create")) {
                    new CreateGUI(player).openInventoryGUI();
                }
                if(parameter.equalsIgnoreCase("list")) {
                    //openListGUI();
                }
                if(parameter.equalsIgnoreCase("help") ||
                        parameter.equalsIgnoreCase("?")) {
                    //sendHelpMessage();
                }
            }
        }
        return false;
    }
}
