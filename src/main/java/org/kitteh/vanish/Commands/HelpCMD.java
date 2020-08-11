package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.SpigotUtils;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;

public class HelpCMD {
	
	public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		if (VanishPerms.canSeeHelp(s)){
			if(s instanceof Player) SpigotUtils.sendHelp((Player)s, str);
			else {
				s.sendMessage("§3------------------ "+pl.getDescription().getFullName()+" ------------------");
				s.sendMessage(" §b/"+str+" [help] §7- §fDisplay help menu"); //
				s.sendMessage(" §b/"+str+" on|off <player> §7- §fToggle player's vanish");
				s.sendMessage(" §b/"+str+" list §7- §fList all vanished players");
				s.sendMessage(" §b/"+str+" toggle <option> <player> §7- §fToggle player's options");
				s.sendMessage(" §b/"+str+" effect <effect> <player> §7- §fToggle player's effects");
				s.sendMessage(" §b/"+str+" check <player> §7- §fCheck if player is vanished");
				s.sendMessage(" §b/"+str+" reload §7- §fReload configuration");
			}
			return true;
		}
		return false;
	}
	
	
	public static List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg){
		List<String> list = new ArrayList<String>();
		return list;
	}

}
