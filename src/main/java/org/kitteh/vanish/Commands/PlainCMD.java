package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;

public class PlainCMD {
	
public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		if (s instanceof Player) {
			Player p = (Player) s;
            if(VanishPerms.canVanish(p)) {
            	pl.getManager().toggleVanish(p);
            	return true;
            }
            return false;
        }
		else {
			HelpCMD.onCommand(s, cmd, str, arg, pl);
			return true;
		}
	}


	public static List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg){
		List<String> list = new ArrayList<String>();
		if("check".startsWith(arg[0]) && VanishPerms.canCheck(s))list.add("check");
		if("effects".startsWith(arg[0]) && (s instanceof ConsoleCommandSender || VanishPerms.canToggleEffectBats(s)||VanishPerms.canToggleEffectExplode(s)||VanishPerms.canToggleEffectFlames(s)||VanishPerms.canToggleEffectLightning(s)||VanishPerms.canToggleEffectSmoke(s)))list.add("effects");
		if("fakejoin".startsWith(arg[0]) && s instanceof Player && VanishPerms.canFakeAnnounce((Player)s))list.add("fakejoin");
		if("fakequit".startsWith(arg[0]) && s instanceof Player && VanishPerms.canFakeAnnounce((Player)s))list.add("fakequit");
		if("help".startsWith(arg[0]) && VanishPerms.canSeeHelp(s))list.add("help");
		if("list".startsWith(arg[0]) && VanishPerms.canList(s))list.add("list");
		if("reload".startsWith(arg[0]) && VanishPerms.canReload(s))list.add("reload");
		if("toggle".startsWith(arg[0]) && (s instanceof ConsoleCommandSender || VanishPerms.canToggleDamageIn(s)||VanishPerms.canToggleDamageOut(s)||VanishPerms.canToggleNoChat(s)||VanishPerms.canToggleNoFollow(s)||VanishPerms.canToggleNoHunger(s)||VanishPerms.canToggleNoInteract(s)||VanishPerms.canToggleNoPickup(s)||VanishPerms.canToggleSee(s)||VanishPerms.canToggleSilentChestReads(s)))list.add("toggle");
		if("off".startsWith(arg[0]) && ((s instanceof Player)?VanishPerms.canVanishOff((Player)s):true))list.add("off");
		if("on".startsWith(arg[0]) && ((s instanceof Player)?VanishPerms.canVanishOn((Player)s):true))list.add("on");
		return list;
	}

}
