package tomoto.customanvilrecipe.command;

import org.bukkit.ChatColor;
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
                else if(parameter.equalsIgnoreCase("create")) {
                    new CreateGui().openGui(player);
                }
                else if(parameter.equalsIgnoreCase("list")) {
//                    new ListGui().openGui(player);
                }
                else if(parameter.equalsIgnoreCase("help") ||
                        parameter.equalsIgnoreCase("?")) {
                    player.sendMessage(ChatColor.AQUA + "===Custom Anvil Recipe Command List===");
                    player.sendMessage("| /anvil        -- Open recipe menu.");
                    player.sendMessage("| /anvil menu   -- Open recipe menu.");
                    player.sendMessage("| /anvil create -- Create new recipes.");
                    player.sendMessage("| /anvil list   -- Delete recipes. " + ChatColor.DARK_RED + "Not implemented.");
                    player.sendMessage("| /anvil help   -- Show this message.");
                }
                else if(parameter.equalsIgnoreCase("author")) {
                    player.sendMessage("Author: Tomoto");
                    player.sendMessage("Mcbbs: https://www.mcbbs.net/?240366");
                    player.sendMessage("GitHub: https://github.com/Tomotopieces/CustomAnvilRecipe");
                }
            }
        }
        return true;
    }
}
