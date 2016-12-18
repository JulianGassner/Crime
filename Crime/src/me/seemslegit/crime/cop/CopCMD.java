package me.seemslegit.crime.cop;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.playerapi.User;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CopCMD implements CommandExecutor{

	
	private HashMap<String, Long> delay = new HashMap<String, Long>();
	
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(!(cs instanceof Player)){
			cs.sendMessage("You have to be a player !");
			return true;
		}
		Player p = (Player) cs;
		if(args[0].equalsIgnoreCase("switch")){
			p.sendMessage(Messages.prefix+"§aYou switched successfully to §e"+CopManager.switchCop(new User(p)) + "§a!");
			CopItems.giveCopItems(p);
		}if(args[0].equalsIgnoreCase("respawn")){
			if(hasCooldown(p.getUniqueId().toString())) {
				
			}else{
				
				
				
				delay.put(p.getUniqueId().toString(), System.currentTimeMillis() + 60*3 * 1000);
			}
		}
		return true;
	}
	
	
	private boolean hasCooldown(String uuid) {
		
		if(delay.containsKey(uuid)) {
			
			long t = delay.get(uuid);
			
			if(System.currentTimeMillis() > t) {
				delay.remove(uuid);
				return false;
			}
			return true;
		}
		
		return false;
	}
	

}
