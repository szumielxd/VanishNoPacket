package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.kitteh.vanish.Settings;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;

public class ReloadCMD {
	
	
	public static boolean onCommand(CommandSender s, Command cmd, String str, String[] arg, VanishPlugin pl) {
		
		if (VanishPerms.canReload(s)) {
            pl.reload();
            s.sendMessage(Settings.getMessagePrefix()+"Users reloaded");
            s.sendMessage(Settings.getMessagePrefix()+"Some settings refreshed");
            return true;
        }
		return false;
	}
	
	
	public static List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg){
		List<String> list = new ArrayList<String>();
		return list;
	}
	

}
