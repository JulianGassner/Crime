package me.seemslegit.crime.shops.api;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import me.seemslegit.crime.api.Reflections;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.shops.ShopManager;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;

import org.bukkit.entity.Player;

public class CustomChannelHandler extends MessageToMessageDecoder<PacketPlayInUseEntity>{

	private Player p;
	
	/**
	 * 
	 * @param p {@link Player}
	 */
	public CustomChannelHandler(Player p) {
		this.p = p;
	}
	
	/**
	 * 
	 * @return {@link Player}
	 */
	public Player getPlayer() {
		return p;
	}

	@Override
	protected void decode(ChannelHandlerContext arg0,
			PacketPlayInUseEntity packet, List<Object> arg2) throws Exception {

		if(packet.a() == EnumEntityUseAction.INTERACT) {
			int entityid = (Integer) Reflections.getValue(packet, "a");
			ShopManager sm = Main.instance.getShopManager();
			
			for(Shop s : sm.shops) {
				
				if(s.getEntity().getId() == entityid) {
					sm.onInteract(getPlayer(), s);
					break;
				}
				
			}
			
		}
		
		arg2.add(packet);
	}
	
	
}
