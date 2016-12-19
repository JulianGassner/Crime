package me.seemslegit.crime.cop;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.api.ItemAPI;
import me.seemslegit.crime.api.PlayerCache;
import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.managment.ItemManager;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

public class CopItems implements Listener {

	/**
	 * 
	 * @param p
	 *            {@link UserBase}
	 */
	public static void giveCopItems(UserBase u) {

		Player p = u.getPlayer();
		if(p == null) return;
		
		ItemManager im = Main.instance.getItemManager();

		ItemStack copsword = im.getItem("copsword");
		ItemStack handcuffs = im.getItem("handcuffs");
		ItemStack shears = im.getItem("uncuff");
		ItemStack tortch = im.getItem("cit");
		ItemStack tnt = im.getItem("c4");
		ItemStack book = im.getItem("book");
		ItemStack helmet = new ItemAPI().material(Material.CHAINMAIL_HELMET).build();
		ItemStack chestplate = new ItemAPI().material(Material.CHAINMAIL_CHESTPLATE).build();
		ItemStack leggins = new ItemAPI().material(Material.CHAINMAIL_LEGGINGS).build();
		ItemStack boots = new ItemAPI().material(Material.CHAINMAIL_BOOTS).build();

		new PlayerCache(p).clearPlayer();
		
		p.getInventory().setItem(0, copsword);
		p.getInventory().setItem(1, handcuffs);
		p.getInventory().setItem(2, shears);
		p.getInventory().setItem(3, tortch);
		p.getInventory().setItem(4, tnt);
		p.getInventory().setItem(5, book);
		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggins);
		p.getInventory().setBoots(boots);
		p.updateInventory();

	}

	/**
	 * 
	 * @param e {@link PlayerInteractEntityEvent}
	 */
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player)) return;

		Player t = (Player) e.getRightClicked();
		Player p = e.getPlayer();
		ItemStack inhand = p.getItemInHand();
		if(inhand == null || inhand.getType() == Material.AIR) return;
		String name = CrimeItem.getCrimeName(inhand);
		if (name == null)
			return;

		if (name.equalsIgnoreCase("handcuffs")) {
			if(new User(t).isCop()) {
				p.sendMessage(Messages.prefix + "§cYou cant cuff another cop!");
				return;
			}
			p.sendMessage(Messages.prefix+"§aYou successfully cuffed §6"+t.getName());
			Main.instance.getCopManager().cuff(new User(t));
		} else if (name.equalsIgnoreCase("copsword")) {
			if(Main.instance.getCopManager().isCuffed(new User(t)) == true){
				UserBase b = new User(t);
				if(!b.hasCrime()) {
					p.sendMessage(Messages.prefix + "§cThis player has no crime. You cant lock him.");
					return;
				}
				Main.instance.getJailManager().sendToJail(b);
			}

		} else if (name.equalsIgnoreCase("uncuff")) {
			p.sendMessage(Messages.prefix+"§cYou successfully uncuffed §6"+t.getName());
			Main.instance.getCopManager().uncuff(new User(t));
			
		}else if (name.equalsIgnoreCase("cit")){
			if(new User(t).isCop()) {
				p.sendMessage(Messages.prefix + "§cYou can't clear a inventory of another cop!");
				return;
			}
			p.sendMessage(Messages.prefix+"§aYou successfully §cremoved all illegal items §aof §6"+t.getName());
			Main.instance.getCopManager().removeIllegalItems(new User(t));
			
		}else if (name.equalsIgnoreCase("book")){
			UserBase a = new User(p);
			UserBase b = new User(t);
			p.sendMessage("§e-------------------------------");
			p.sendMessage("§eStats of Player "+t.getDisplayName());
			if(a.isCop()){
				p.sendMessage("§eMoney: "+b.getCoins());
				p.sendMessage("§eCrime: §c"+b.getCrime());
			}
			if(b.hasCrime()){
				p.sendMessage("§eHe is being §csearched§e !");
			}else{
				if(b.isInJail()){
					p.sendMessage("§eJail-Time: "+b.getJailTime());
					p.sendMessage("§3He is a §1Prisoner");
				}else p.sendMessage("§eHe is a §cinnocent.");
			}
		}
	}
	
	/**
	 * 
	 * @param e {@link BlockPlaceEvent}
	 */
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent e){
		if(e.isCancelled()) return;
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		Player p = e.getPlayer();
		ItemStack im = p.getItemInHand();
		if(e.getBlockPlaced().getType().equals(Material.TNT) && im.getType() == Material.TNT){
			e.setCancelled(true);
			if(im.getAmount() == 1) {
				im.setType(Material.AIR);
			}else{
				im.setAmount(im.getAmount() - 1);
			}
			e.getBlock().setType(e.getBlock().getType());
			e.getPlayer().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.PRIMED_TNT);
			UserBase u = new User(p);
			if(!u.isCop()){
				u.addCrime(50);
			}
			p.setItemInHand(im);
			p.updateInventory();
		}
	}
	
	
	
	

}
