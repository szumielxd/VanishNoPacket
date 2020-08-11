package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.Settings;
import org.kitteh.vanish.SpigotUtils;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;
import org.kitteh.vanish.metrics.MetricsOverlord;

public class EffectCMD {
	
public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		if (arg.length == 1) {
			if(s instanceof Player) SpigotUtils.sendEffectList((Player)s, str, s);
			else SpigotUtils.sendUsage(s, "/"+str+" effects <options> <player>");
			return true;
		} else if (arg.length == 2) {
			if(s instanceof Player) {
				if(!canToggle(s, arg[1])) {
					SpigotUtils.sendEffectList((Player)s, str, s);
					return true;
				}
				if(isOption(arg[1].toLowerCase())) s.sendMessage(Settings.getMessagePrefix()+toggle((Player)s, arg[1].toLowerCase(), pl));
				else SpigotUtils.sendEffectList((Player)s, str, s);
			}else SpigotUtils.sendUsage(s, "/"+str+" effects <options> <player>");
			return true;
		}else {
			if(VanishPerms.canToggleOther(s)) {
				Player p = Bukkit.getPlayer(arg[2]);
				if(p == null) {
					s.sendMessage(Settings.getMessagePrefix()+"§cPlayer %player% is not online".replaceAll("%player%", arg[2]));
					return true;
				}
				if(!canToggle(s, arg[1].toLowerCase())) {
					SpigotUtils.sendEffectList(p, str, s);
					return true;
				}
				if(isOption(arg[1].toLowerCase())) s.sendMessage(Settings.getMessagePrefix()+toggle(p, arg[1].toLowerCase(), pl));
				else SpigotUtils.sendEffectList(p, str, s);
				return true;
			}
			return false;
		}
	}
	
	
	private static boolean isOption(String option) {
		return (option.equals("smoke") || option.equals("explode") || option.equals("lightning") || option.equals("flames") || option.equals("bats"));
	}
	
	
	private static boolean canToggle(CommandSender s, String toggle) {
		if (s instanceof ConsoleCommandSender) return true;
		if (toggle.equalsIgnoreCase("smoke") && VanishPerms.canToggleEffectSmoke(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("explode") && VanishPerms.canToggleEffectExplode(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("lightning") && VanishPerms.canToggleEffectLightning(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("flames") && VanishPerms.canToggleEffectFlames(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("bats") && VanishPerms.canToggleEffectBats(s)) {
			return true;
		}
		return false;
	}
	
	
	private static String toggle(Player player, String toggle, VanishPlugin pl) {
		final StringBuilder message = new StringBuilder();
		MetricsOverlord.getToggleTracker().increment();
		boolean status = false;
		if (toggle.equalsIgnoreCase("smoke")) {
			status = VanishPerms.toggleEffectSmoke(player);
			message.append("smoke effect");
		} else if (toggle.equalsIgnoreCase("explode")) {
			status = VanishPerms.toggleEffectExplode(player);
			message.append("explosion effect");
		} else if (toggle.equalsIgnoreCase("lightning")) {
			status = VanishPerms.toggleEffectLightning(player);
			message.append("lightning effect");
		} else if (toggle.equalsIgnoreCase("flames")) {
			status = VanishPerms.toggleEffectFlames(player);
			message.append("flames effect");
		} else if (toggle.equalsIgnoreCase("bats")) {
			status = VanishPerms.toggleEffectBats(player);
			message.append("bats effect");
		}
		if (message.length() > 0) {
			message.insert(0, ChatColor.DARK_AQUA + "Status: ");
			message.append(": ");
			if (status) {
				message.append("enabled");
			} else {
				message.append("disabled");
			}
			return message.toString();
		} else if (VanishPerms.canVanish(player)) {
			return ChatColor.DARK_AQUA + "You can't toggle that!";
		}
		else return "";
	}
	
	
	public static List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg){
		List<String> list = new ArrayList<String>();
		if(arg.length == 2) {
			if("bats".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleEffectBats(s):true)) list.add("bats");
			if("explode".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleEffectExplode(s):true)) list.add("explode");
			if("flames".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleEffectFlames(s):true)) list.add("flames");
			if("lightning".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleEffectLightning(s):true)) list.add("lightning");
			if("smoke".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleEffectSmoke(s):true)) list.add("smoke");
		}else if(arg.length == 3 && VanishPerms.canToggleEffectOther(s)) return null;
		return list;
	}
	
}
