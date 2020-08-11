package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;

public class ListCMD {
	
	
	public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		if (VanishPerms.canList(s)) {
			final StringBuilder list = new StringBuilder();
			for (final Player player : pl.getServer().getOnlinePlayers()) {
				if ((player != null) && pl.getManager().isVanished(player)) {
					if (list.length() > 0) {
						list.append("§3,");
					}
					list.append("§b"+player.getName());
				}
			}
			list.insert(0, "§3Vanished: ");
			s.sendMessage(list.toString());
			return true;
		}
		return false;
	}
	
	
	public static List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg){
		List<String> list = new ArrayList<String>();
		return list;
	}

}
