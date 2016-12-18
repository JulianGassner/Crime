package me.seemslegit.crime.items;

import me.seemslegit.crime.managment.ItemManager;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemFunctions implements Listener{

	//Drugs
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onFurnaceInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
		
		Block b = e.getClickedBlock();
		
		if(b.getType() != Material.FURNACE && b.getType() != Material.BURNING_FURNACE) return;
		e.setCancelled(true);
		
		ItemStack hand = p.getItemInHand();
		
		if(hand == null || hand.getType() == Material.AIR) return;
		
		String name = CrimeItem.getCrimeName(hand);
		
		if(name == null) return;
		
		ItemManager im = Main.instance.getItemManager();
		
		if(name.equalsIgnoreCase("rawdrugs")) {
			
			ItemStack bread = im.getItem("drugs");
			bread.setAmount(hand.getAmount());
			
			p.setItemInHand(bread);
			
		}else if(name.equalsIgnoreCase("wheat")) {
			
			ItemStack bread = im.getItem("bread");
			bread.setAmount(hand.getAmount());
			
			p.setItemInHand(bread);
			
		}
		
	}
	
	/**
	 * 
	 * @param e {@link PlayerInteractEvent}
	 */
	@EventHandler
	public void onConsume(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		ItemStack hand = p.getItemInHand();
		
		if(hand == null || hand.getType() == Material.AIR) return;
		
		String name = CrimeItem.getCrimeName(hand);
		
		if(name == null) return;
		
		if(name.equals("drugs")){
			p.playSound(p.getLocation(), Sound.GHAST_SCREAM, 1, 1);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5*20, 5), true);
			UserBase b = new User(p);
			b.addCrime(101);
			if(hand.getAmount() == 1) {
				p.setItemInHand(null);
				p.updateInventory();
				return;
			}
			hand.setAmount(hand.getAmount() - 1);
			p.setItemInHand(hand);
			p.updateInventory();
		}
		
	}
	
}
