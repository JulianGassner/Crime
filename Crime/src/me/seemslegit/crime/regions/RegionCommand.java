package me.seemslegit.crime.regions;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		
		if(!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if(!p.hasPermission(Main.admin_permission)) {
			p.sendMessage(Messages.no_permissions);
			return true;
		}
		
		if(args.length == 0) {
		
			p.sendMessage("§8> §e/region pos1");
			p.sendMessage("§8> §e/region pos2");
		
			return true;
		}
		
		RegionManager rm = Main.instance.getRegionManager();
		String arg = args[0];
		
		if(arg.equalsIgnoreCase("pos1")) {
			
			rm.setPos1(p, p.getLocation());
			
		}else if(arg.equalsIgnoreCase("pos2")){
			
			rm.setPos2(p, p.getLocation());
			
		}else{
			p.sendMessage(Messages.invalid_argument);
		}
		
		return true;
	}

	
	
	
	
}
