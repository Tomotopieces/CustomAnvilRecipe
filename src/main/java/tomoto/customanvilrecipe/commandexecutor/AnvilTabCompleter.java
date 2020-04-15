package tomoto.customanvilrecipe.commandexecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class AnvilTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if(args.length == 1){
            list.add("menu");
            list.add("create");
            list.add("list");
            list.add("help");
            list.add("?");
        }
        return list;
    }
}
