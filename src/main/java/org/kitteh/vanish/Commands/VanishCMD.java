package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.Settings;
import org.kitteh.vanish.SpigotUtils;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;

public class VanishCMD {
	
	public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		boolean enable = arg[0].equalsIgnoreCase("on");
		if(arg.length == 1) {
			if(s instanceof Player) {
				Player p = (Player)s;
				if(enable?VanishPerms.canVanishOn(p):VanishPerms.canVanishOff(p))toggleV(p, enable, pl);
			}else SpigotUtils.sendUsage(s, "/"+str+" "+arg[0]+" <player>");
			return true;
		}else {
			if (arg[1].equalsIgnoreCase("-fake")) {
				if(s instanceof Player) {
					Player p = (Player)s;
					if(enable?VanishPerms.canVanishOn(p):VanishPerms.canVanishOff(p))toggleV(p, enable, pl);
					if(enable) pl.getManager().getAnnounceManipulator().fakeQuit(p, false);
					else pl.getManager().getAnnounceManipulator().fakeJoin(p, false);
				} else SpigotUtils.sendUsage(s, "/"+str+" "+arg[0]+" <player>");
				return true;
			}else {
				if(!VanishPerms.canVanishOther(s)) return false;
				Player p = Bukkit.getPlayer(arg[1]);
				if(p == null) {
					s.sendMessage(Settings.getMessagePrefix()+"§cPlayer %player% is not online".replaceAll("%player%", arg[1]));
					return true;
				}
				if(toggleV(p, enable, pl))s.sendMessage(Settings.getMessagePrefix()+("§3%player% has "+(enable?"vanished. Poof.":"become visible")).replaceAll("%player%", p.getName()));
				if(arg.length > 2 && arg[2].equalsIgnoreCase("-fake")) {
					if(enable) pl.getManager().getAnnounceManipulator().fakeQuit(p, false);
					else pl.getManager().getAnnounceManipulator().fakeJoin(p, false);
				}
				return true;
			}
		}
		
	}
	
	private static boolean toggleV(Player p, boolean enable, VanishPlugin pl) {
		if (enable != pl.getManager().isVanished(p)) {
			pl.getManager().toggleVanish(p);
			return true;
		}
		return false;
	}
	
	
	public static List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg){
		List<String> list = new ArrayList<String>();
		if(arg.length == 2) {
			if("-fake".startsWith(arg[1]) && ((arg[0].equalsIgnoreCase("on") && s instanceof Player && VanishPerms.canVanishOn((Player)s)) || (arg[0].equalsIgnoreCase("off") && s instanceof Player && VanishPerms.canVanishOff((Player)s))))list.add("-fake");
			if(VanishPerms.canVanishOther(s)){
				for(Player p : Bukkit.getOnlinePlayers()) {
					if((s instanceof Player)?((Player)s).canSee(p):true && p.getName().startsWith(arg[1])) list.add(p.getName());
				}
			}
		}
		Collections.sort(list);
		return list;
	}

}
