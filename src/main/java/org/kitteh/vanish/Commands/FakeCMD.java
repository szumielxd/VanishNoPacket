package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;

public class FakeCMD {
	
	public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		if(s instanceof Player) {
			Player p = (Player)s;
			boolean join = (arg[0].equalsIgnoreCase("fakejoin") || arg[0].equalsIgnoreCase("fj"));
			if (VanishPerms.canFakeAnnounce(p)) {
				if (join == pl.getManager().isVanished(p)) {
					pl.getManager().toggleVanish(p);
				} else {
					s.sendMessage("§3Already "+(join?"":"in")+"visible :)");
				}
				boolean forced = false;
				if ((arg.length > 1) && (arg[1].equalsIgnoreCase("f") || arg[1].equalsIgnoreCase("force"))) {
					forced = true;
				}
				if(join)pl.getManager().getAnnounceManipulator().fakeJoin(p, forced);
				else pl.getManager().getAnnounceManipulator().fakeQuit(p, forced);
				return true;
			}
		}
		return false;
	}
	
	
	public static List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg){
		List<String> list = new ArrayList<String>();
		if(arg.length == 2 && s instanceof Player) {
			if(!VanishPerms.canFakeAnnounce((Player)s)) return list;
			if("f".startsWith(arg[1])) list.add("f");
			if("force".startsWith(arg[1])) list.add("force");
		}
		return list;
	}

}
