package com.psms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psms.entity.Shopkeeper;
import com.psms.exception.ResourceNotFoundException;
import com.psms.model.ShopkeeperDTO;
import com.psms.repository.ShopKeeperRepository;
import com.psms.service.ShopKeeperService;
import com.psms.util.ShopKeeperConverter;

@Service
public class ShopKeeperServiceImpl implements ShopKeeperService {
	
	@Autowired
	private ShopKeeperRepository shopkeeperrepository;
	
	@Autowired
	private ShopKeeperConverter shopkeeperconverter;

	@Override
	public String createShopKeeper(Shopkeeper shopkeeper) {
		String message=null;
		
		shopkeeper.setShopkName(shopkeeper.getShopkName());
		
		shopkeeperrepository.save(shopkeeper);
		
		if(shopkeeper!=null)
		{
			message= "Shopkeeper details added successfully";
		}
		return message;
	}

	@Override
	public ShopkeeperDTO getShopkeeperById(int shopk_id) {
		Shopkeeper shopkeeper=shopkeeperrepository.findById(shopk_id).get();
		
		return shopkeeperconverter.convertEntityTOShopKeeperDTO(shopkeeper);
	}

	@Override
	public List<ShopkeeperDTO> getAllShopkeeper() {
		List<Shopkeeper> shopkeepers=shopkeeperrepository.findAll();
		List<ShopkeeperDTO> shopkeeperDTO=new ArrayList<>();
		for(Shopkeeper s:shopkeepers)
		{
			shopkeeperDTO.add(shopkeeperconverter.convertEntityTOShopKeeperDTO(s));
		}
		return shopkeeperDTO;
	}

	@Override
	public ShopkeeperDTO updateShopkeeper(int shopk_id, Shopkeeper shopkeeper) {
		
		Shopkeeper existingShopkeeper=shopkeeperrepository.findById(shopk_id).get();
		
		existingShopkeeper.setShopkName(shopkeeper.getShopkName());
		
		shopkeeperrepository.save(existingShopkeeper);
		
		return shopkeeperconverter.convertEntityTOShopKeeperDTO(existingShopkeeper);
	}

	
	@Override
	public String deleteShopkeeperById(int shopk_id) throws ResourceNotFoundException {
		String msg=null;
		Optional<Shopkeeper> shopkeeper=shopkeeperrepository.findById(shopk_id);
		
		if(shopkeeper.isPresent())
		{
			shopkeeperrepository.deleteById(shopk_id);
			msg= "Shopkeeper deleted successfully";
		}
		else
		{
			throw new ResourceNotFoundException("Shopkeeper","shopk_id",shopk_id);
		}
		
		return msg;
	}
	
	@Override
	public void deleteAllShopkeepers() {
		shopkeeperrepository.deleteAll();
		
	}

	@Override
	public List<ShopkeeperDTO> getShopkeeperByshopk_name(String shopk_name) {
		List<Shopkeeper> shopkeeper1=shopkeeperrepository.findByShopkName(shopk_name);
		List<ShopkeeperDTO> shopkeeperDTO =new ArrayList<>();
		for(Shopkeeper s: shopkeeper1)
		{
			shopkeeperDTO.add(shopkeeperconverter.convertEntityTOShopKeeperDTO(s));
		}
		return shopkeeperDTO;
	}
	
	}

