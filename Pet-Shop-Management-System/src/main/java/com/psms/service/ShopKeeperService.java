package com.psms.service;

import java.util.List;

import com.psms.entity.Shopkeeper;
import com.psms.model.ShopkeeperDTO;

public interface ShopKeeperService {
	
	String createShopKeeper(Shopkeeper shopkeeper);
	ShopkeeperDTO getShopkeeperById(int shopk_id);
	
	List<ShopkeeperDTO> getAllShopkeeper();
	ShopkeeperDTO updateShopkeeper(int shopk_id,Shopkeeper shopkeeper);
	
	
	String deleteShopkeeperById(int shopk_id);
	void deleteAllShopkeepers();
		
	List<ShopkeeperDTO> getShopkeeperByshopk_name(String shopk_name);

	
	
	

}
