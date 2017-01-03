package me.seemslegit.crime.commands;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_config implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!sender.hasPermission(Main.admin_permission)) {
			sender.sendMessage(Messages.no_permissions);
			return true;
		}
		
		if(args.length == 0) {
			
			sender.sendMessage("§8> §e/config setjail");
			sender.sendMessage("§8> §e/config setspawn");
			
			return true;
		}
		
		String arg = args[0];
		
		if(arg.equalsIgnoreCase("setjail")) {
			
			if(!(sender instanceof Player)) {
				sender.sendMessage("§8> §cOnly players can execute this command.");
				return true;
			}
			
			Player p = (Player) sender;
			
			Main.instance.getJailManager().setLocation(p.getLocation());
			p.sendMessage(Messages.prefix + "§eLocation set.");
		}else if(arg.equalsIgnoreCase("setspawn")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("§8> §cOnly players can execute this command.");
				return true;
			}
			
			Player p = (Player) sender;
			
			Main.instance.sys_cfg.setLocation("spawn", p.getLocation());
			p.sendMessage(Messages.prefix + "§eLocation set.");
		}
		
		
		
		return true;
	}
	
}
