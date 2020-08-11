package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.Settings;
import org.kitteh.vanish.SpigotUtils;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;

public class CheckCMD {
	
	public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		if(VanishPerms.canCheck(s)) {
			if(arg.length > 1 && VanishPerms.canCheckOther(s)) {
				Player p = Bukkit.getPlayer(arg[1]);
				if(p == null) {
					s.sendMessage(Settings.getMessagePrefix()+"§cPlayer %player% is not online".replaceAll("%player%", arg[1]));
					return true;
				}
				if(pl.getManager().isVanished(p)) s.sendMessage(Settings.getMessagePrefix()+"%player% is invisible".replaceAll("%player%", p.getName()));
				else s.sendMessage(Settings.getMessagePrefix()+"%player% is visible".replaceAll("%player%", p.getName()));
				return true;
			}
			if(s instanceof Player) {
				Player p = (Player)s;
				if (pl.getManager().isVanished(p)) p.sendMessage(Settings.getMessagePrefix()+"You are invisible.");
				else p.sendMessage(Settings.getMessagePrefix()+"You are visible.");
				return true;
			}else {
				SpigotUtils.sendUsage(s, "/"+str+" check <player>");
				return true;
			}
		}
		
		
		return false;
	}
	
	
	public static List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg){
		List<String> list = new ArrayList<String>();
		if(arg.length == 2 && VanishPerms.canCheckOther(s)) return null;
		return list;
	}

}
