package com.psms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.psms.entity.Shopkeeper;
import com.psms.model.ShopkeeperDTO;

@Component
public class ShopKeeperConverter {
	
	//converts from Shopkeeper entity to shopkeeperDTO
	public ShopkeeperDTO convertEntityTOShopKeeperDTO(Shopkeeper shopkeeper)
	{
		ShopkeeperDTO shokeeperDTO=new ShopkeeperDTO();
		if(shopkeeper!=null)
		{
			BeanUtils.copyProperties(shopkeeper, shokeeperDTO);
		}
		return shokeeperDTO;
	}
	
	//converts from shopkeeperDTO to shopkeeper entity
	public Shopkeeper convertDTOTOShopkeeper(ShopkeeperDTO shokeeperDTO)
	{
		Shopkeeper shopkeeper=new Shopkeeper();
		if(shokeeperDTO!=null)
		{
			BeanUtils.copyProperties(shokeeperDTO, shopkeeper);
			
		}
		return shopkeeper;
	}

}
