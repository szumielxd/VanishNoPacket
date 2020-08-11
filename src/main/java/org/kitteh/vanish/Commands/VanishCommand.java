package org.kitteh.vanish.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kitteh.vanish.Settings;
import org.kitteh.vanish.SpigotUtils;
import org.kitteh.vanish.VanishPlugin;
import org.kitteh.vanish.metrics.MetricsOverlord;

public final class VanishCommand implements CommandExecutor {
	private final VanishPlugin plugin;

	public VanishCommand(VanishPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String str, String[] arg) {
		MetricsOverlord.getCommandTracker().increment();
		// First, the short aliases
		if (str.length() == 2) {
			if (s instanceof Player) {
				if (str.equals("np")) {
					if(!ToggleCMD.onCommand(s, cmd, str, new String[] {"","nopickup"}, this.plugin)) this.denied(s);
					return true;
				}
				if (str.equals("nf")) {
					if(!ToggleCMD.onCommand(s, cmd, str, new String[] {"","nofollow"}, this.plugin)) this.denied(s);
					return true;
				}
				if (str.equals("nh")) {
					if(!ToggleCMD.onCommand(s, cmd, str, new String[] {"","nohunger"}, this.plugin)) this.denied(s);
					return true;
				}
				if (str.equals("ni")) {
					if(!ToggleCMD.onCommand(s, cmd, str, new String[] {"","nointeract"}, this.plugin)) this.denied(s);
					return true;
				}
				if (str.equals("nc")) {
					if(!ToggleCMD.onCommand(s, cmd, str, new String[] {"","nochat"}, this.plugin)) this.denied(s);
					return true;
				}
			}
			return true;
		}
		// Plain /vanish
		if (arg.length == 0) {
			if(PlainCMD.onCommand(s, cmd, str, arg, this.plugin))
			return true;
		}
		// /vanish <goal> [maybe stuff here]
		final String goal = arg[0];
		/* RELOAD */
		if (goal.equalsIgnoreCase("reload")) {
			if (!ReloadCMD.onCommand(s, cmd, str, arg, this.plugin)) this.denied(s);	
			return true;
		}
		/* HELP */
		if(goal.equalsIgnoreCase("help")){
			if(!HelpCMD.onCommand(s, cmd, str, arg, this.plugin)) this.denied(s);
			return true;
		}
		/* LIST */
		if (goal.equalsIgnoreCase("list")) {
			if(!ListCMD.onCommand(s, cmd, str, arg, this.plugin))this.denied(s);
			return true;
		}
		/* CHECK */
		if (goal.equalsIgnoreCase("check")) {
			if(!CheckCMD.onCommand(s, cmd, str, arg, this.plugin))this.denied(s);
			return true;
		}
		/* TOGGLE */
		if (goal.equalsIgnoreCase("toggle") || goal.equalsIgnoreCase("t")) {
			if(!ToggleCMD.onCommand(s, cmd, str, arg, this.plugin)) this.denied(s);
			return true;
		}
		/* EFFECT */
		if (goal.equalsIgnoreCase("effects") || goal.equalsIgnoreCase("e")) {
			if(!EffectCMD.onCommand(s, cmd, str, arg, this.plugin)) this.denied(s);
			return true;
		}
		/* VANISH */
		if (goal.equalsIgnoreCase("on") || goal.equalsIgnoreCase("off")) {
			if(!VanishCMD.onCommand(s, cmd, str, arg, this.plugin)) this.denied(s);
			return true;
		}
		/* FAKE ANNOUNCEMENT */
		if ((goal.equalsIgnoreCase("fakequit") || goal.equalsIgnoreCase("fq") || goal.equalsIgnoreCase("fakejoin") || goal.equalsIgnoreCase("fj"))) {
			if(!FakeCMD.onCommand(s, cmd, str, arg, this.plugin)) this.denied(s);
			return true;
		}
		
		SpigotUtils.sendTryHelp(s, str);

		// Continue? 

		// 3

		// 2

		// 1

		return true;
	}

	private void denied(CommandSender s) {
		s.sendMessage(Settings.getMessagePrefix()+"§3Access denied.");
	}

	
}