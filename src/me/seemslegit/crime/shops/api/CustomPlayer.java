package me.seemslegit.crime.shops.api;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;

import com.mojang.authlib.GameProfile;

public class CustomPlayer extends EntityPlayer{

	public CustomPlayer(MinecraftServer minecraftserver,
			WorldServer worldserver, GameProfile gameprofile,
			PlayerInteractManager playerinteractmanager) {
		super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
	}

	@Override
	public boolean damageEntity(DamageSource damagesource, float f) {
		return false;
	}
	
}
