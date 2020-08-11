package org.kitteh.vanish.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class VanishTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender s, Command cmd, String str, String[] arg) {
		if (str.equals("np")) return new ArrayList<String>();
		if (str.equals("nf")) return new ArrayList<String>();
		if (str.equals("nh")) return new ArrayList<String>();
		if (str.equals("ni")) return new ArrayList<String>();
		if (str.equals("nc")) return new ArrayList<String>();
		// Plain /vanish
		if (arg.length == 1) {
			return PlainCMD.onTabComplete(s, cmd, str, arg);
		}
		// /vanish <goal> [maybe stuff here]
		final String goal = arg[0];
		/* RELOAD */
		if (goal.equalsIgnoreCase("reload")) return ReloadCMD.onTabComplete(s, cmd, str, arg);
		/* HELP */
		if(goal.equalsIgnoreCase("help")) return HelpCMD.onTabComplete(s, cmd, str, arg);
		/* LIST */
		if (goal.equalsIgnoreCase("list")) return ListCMD.onTabComplete(s, cmd, str, arg);
		/* CHECK */
		if (goal.equalsIgnoreCase("check")) return CheckCMD.onTabComplete(s, cmd, str, arg);
		/* TOGGLE */
		if (goal.equalsIgnoreCase("toggle") || goal.equalsIgnoreCase("t")) return ToggleCMD.onTabComplete(s, cmd, str, arg);
		/* EFFECT */
		if (goal.equalsIgnoreCase("effects") || goal.equalsIgnoreCase("e")) return EffectCMD.onTabComplete(s, cmd, str, arg);
		/* VANISH */
		if (goal.equalsIgnoreCase("on") || goal.equalsIgnoreCase("off")) return VanishCMD.onTabComplete(s, cmd, str, arg);
		/* FAKE ANNOUNCEMENT */
		if ((goal.equalsIgnoreCase("fakequit") || goal.equalsIgnoreCase("fq") || goal.equalsIgnoreCase("fakejoin") || goal.equalsIgnoreCase("fj"))) return FakeCMD.onTabComplete(s, cmd, str, arg);
		return new ArrayList<String>();
	}

}
