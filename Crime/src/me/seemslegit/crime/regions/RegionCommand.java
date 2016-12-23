package me.seemslegit.crime.regions;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Location;
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
			p.sendMessage("§8> §e/region create");
			p.sendMessage("§8> §e/region delete");
			
			return true;
		}
		
		RegionManager rm = Main.instance.getRegionManager();
		String arg = args[0];
		
		if(arg.equalsIgnoreCase("pos1")) {
			
			rm.setPos1(p, p.getLocation());
			
		}else if(arg.equalsIgnoreCase("pos2")){
			
			rm.setPos2(p, p.getLocation());
		
		}else if(arg.equalsIgnoreCase("create")) {
			
			if(args.length < 2) {
				
				p.sendMessage("§8> §e/region create <Name>");
				
				return true;
			}
			
			arg = args[1];
			
			if(!rm.hasBothPositions(p)) {
				p.sendMessage("§8> §cYou've to select a region (pos1/pos2)!");
				return true;
			}
			
			Location loc1 = rm.getPos1(p);
			Location loc2 = rm.getPos2(p);
			
			Region r = new Region(arg, loc1.getWorld().getUID());
			r.setLoc1(loc1);
			r.setLoc2(loc2);
			
			p.sendMessage("§dRegion '" + arg + "' created.");
			
		}else if(arg.equalsIgnoreCase("delete")) {
			
			if(args.length < 2) {
				
				p.sendMessage("§8> §e/region delete <Name>");
				
				return true;
			}
			
			arg = args[1];
			
			WorldRegionPool wrp = rm.getWorldRegionPool(p.getWorld());
			
			for(Region r : wrp.getRegions()) {
				if(r.getID().equalsIgnoreCase(arg)) {
					
					r.delete();
					
					p.sendMessage("§dRegion '" + r.getID() + "' deleted.");
					return true;
				}
			}
			
			p.sendMessage("§8> §cRegion not found!");
			
		}else{
			p.sendMessage(Messages.invalid_argument);
		}
		
		return true;
	}

	
	
	
	
}
