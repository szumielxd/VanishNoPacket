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

public class ToggleCMD {
	
	public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		if (arg.length == 1) {
			if(s instanceof Player) SpigotUtils.sendToggleList((Player)s, str, s);
			else SpigotUtils.sendUsage(s, "/"+str+" toggle <options> <player>");
			return true;
		} else if (arg.length == 2) {
			if(s instanceof Player) {
				if(!canToggle(s, arg[1])) {
					SpigotUtils.sendToggleList((Player)s, str, s);
					return true;
				}
				if(isOption(arg[1].toLowerCase())) s.sendMessage(Settings.getMessagePrefix()+toggle((Player)s, arg[1].toLowerCase(), pl));
				else SpigotUtils.sendToggleList((Player)s, str, s);
			}else SpigotUtils.sendUsage(s, "/"+str+" toggle <options> <player>");
			return true;
		}else {
			if(VanishPerms.canToggleOther(s)) {
				Player p = Bukkit.getPlayer(arg[2]);
				if(p == null) {
					s.sendMessage(Settings.getMessagePrefix()+"§cPlayer %player% is not online".replaceAll("%player%", arg[2]));
					return true;
				}
				if(!canToggle(s, arg[1])) {
					SpigotUtils.sendToggleList(p, str, s);
					return true;
				}
				if(isOption(arg[1].toLowerCase())) s.sendMessage(Settings.getMessagePrefix()+toggle(p, arg[1].toLowerCase(), pl));
				else SpigotUtils.sendToggleList(p, str, s);
				return true;
			}
			return false;
		}
	}
	
	
	private static boolean isOption(String option) {
		return (option.equals("see") || option.equals("nopickup") || option.equals("nofollow") || option.equals("damage-in") || option.equals("damage-out") || option.equals("nointeract") || option.equals("nochat") || option.equals("nohunger") || option.equals("chests"));
	}
	
	
	private static boolean canToggle(CommandSender s, String toggle) {
		if (s instanceof ConsoleCommandSender) return true;
		if (toggle.equalsIgnoreCase("see") && VanishPerms.canToggleSee(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("nopickup") && VanishPerms.canToggleNoPickup(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("nofollow") && VanishPerms.canToggleNoFollow(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("damage-in") && VanishPerms.canToggleDamageIn(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("damage-out") && VanishPerms.canToggleDamageOut(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("nointeract") && VanishPerms.canToggleNoInteract(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("nochat") && VanishPerms.canToggleNoChat(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("nohunger") && VanishPerms.canToggleNoHunger(s)) {
			return true;
		} else if (toggle.equalsIgnoreCase("chests") && VanishPerms.canToggleSilentChestReads(s)) {
			return true;
		}
		return false;
	}
	
	
	private static String toggle(Player player, String toggle, VanishPlugin pl) {
		final StringBuilder message = new StringBuilder();
		MetricsOverlord.getToggleTracker().increment();
		boolean status = false;
		if (toggle.equalsIgnoreCase("see")) {
			status = VanishPerms.toggleSeeAll(player);
			pl.getManager().resetSeeing(player);
			message.append("see all");
		} else if (toggle.equalsIgnoreCase("nopickup")) {
			status = VanishPerms.toggleNoPickup(player);
			message.append("no pickup");
		} else if (toggle.equalsIgnoreCase("nofollow")) {
			status = VanishPerms.toggleNoFollow(player);
			message.append("no mob follow");
		} else if (toggle.equalsIgnoreCase("damage-in")) {
			status = VanishPerms.toggleDamageIn(player);
			message.append("block incoming damage");
		} else if (toggle.equalsIgnoreCase("damage-out")) {
			status = VanishPerms.toggleDamageOut(player);
			message.append("block outgoing damage");
		} else if (toggle.equalsIgnoreCase("nointeract")) {
			status = VanishPerms.toggleNoInteract(player);
			message.append("no interact");
		} else if (toggle.equalsIgnoreCase("nochat")) {
			status = VanishPerms.toggleNoChat(player);
			message.append("no chat");
		} else if (toggle.equalsIgnoreCase("nohunger")) {
			status = VanishPerms.toggleNoHunger(player);
			message.append("no hunger");
		} else if (toggle.equalsIgnoreCase("chests")) {
			status = VanishPerms.toggleSilentChestReads(player);
			message.append("silent chest reads");
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
			if("chests".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleSilentChestReads(s):true)) list.add("chests");
			if("damage-in".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleDamageIn(s):true)) list.add("damage-in");
			if("damage-out".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleDamageOut(s):true)) list.add("damage-out");
			if("nochat".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleNoChat(s):true)) list.add("nochat");
			if("nofollow".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleNoFollow(s):true)) list.add("nofollow");
			if("nohunger".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleNoHunger(s):true)) list.add("nohunger");
			if("nointeract".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleNoInteract(s):true)) list.add("nointeract");
			if("nopickup".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleNoPickup(s):true)) list.add("nopickup");
			if("see".startsWith(arg[1]) && ((s instanceof Player)?VanishPerms.canToggleSee(s):true)) list.add("see");
		}else if(arg.length == 3 && VanishPerms.canToggleEffectOther(s)) return null;
		return list;
	}

}
