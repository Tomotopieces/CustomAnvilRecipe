package tomoto.customanvilrecipe.commandexecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tomoto.customanvilrecipe.guiinventory.gui.CreateGui;
import tomoto.customanvilrecipe.guiinventory.gui.MenuGui;

public class AnvilExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(args.length == 0) {
                new MenuGui().openGui(player);
            }
            else if(args.length == 1) {
                String parameter = args[0];
                if(parameter.equalsIgnoreCase("menu")) {
                    new MenuGui().openGui(player);
                }
                if(parameter.equalsIgnoreCase("create")) {
                    new CreateGui().openGui(player);
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
