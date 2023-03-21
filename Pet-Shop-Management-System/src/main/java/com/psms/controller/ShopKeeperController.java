package com.psms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psms.entity.Shopkeeper;
import com.psms.model.ShopkeeperDTO;
import com.psms.service.ShopKeeperService;
import com.psms.util.ShopKeeperConverter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shopKeeper")
public class ShopKeeperController {
	
	@Autowired
	private ShopKeeperService shopkeeperservice;
	
	@Autowired
	private ShopKeeperConverter shopkeeperconverter;
	
	@PostMapping("/createShopKeeper")
	public String createShopKeeper(@Valid @RequestBody ShopkeeperDTO shopkeeperDTO)
	{
		final Shopkeeper shopkeeper=shopkeeperconverter.convertDTOTOShopkeeper(shopkeeperDTO);
		
		return shopkeeperservice.createShopKeeper(shopkeeper);
	}
	
	@GetMapping("/ShopkeeperDTO/{shopk_id}")
	public ShopkeeperDTO getShopkeeperById(@PathVariable("shopk_id") int shopk_id)
	{
		return shopkeeperservice.getShopkeeperById(shopk_id);	
	}
	
	@GetMapping("/getAllShopkeepers")
	public List<ShopkeeperDTO> getAllShopkeepers()
	{
		return shopkeeperservice.getAllShopkeeper();
		
	}
	
	@PutMapping("/updateShopkeeper/{shopk_id}")
	public ShopkeeperDTO updateShopkeeper(@PathVariable("shopk_id") int shopk_id,
			@Valid @RequestBody ShopkeeperDTO shopkeeperDTO )
	{
		Shopkeeper shopkeepers=shopkeeperconverter.convertDTOTOShopkeeper(shopkeeperDTO);
		
		return shopkeeperservice.updateShopkeeper(shopk_id, shopkeepers) ;
	}
	
	@DeleteMapping("/deleteShopkeeperById/{shopk_id}")
	public String deleteShopkeeperById(@PathVariable("shopk_id") int shopk_id)
	{
		return shopkeeperservice.deleteShopkeeperById(shopk_id);
		
	}
	
	@DeleteMapping("/deleteAllShopkeeper")
	public ResponseEntity<String> deleteAllShopkeeper()
	{
		shopkeeperservice.deleteAllShopkeepers();
		return new ResponseEntity<String>("All shopkeeers are got deleted!",
				HttpStatus.OK);
	}
	
	@GetMapping("/getShopkeeperByName/{shopk_name}")
	public List<ShopkeeperDTO> getShopkeeperByName(@PathVariable("shopk_name") String shopk_name)
	{
		return shopkeeperservice.getShopkeeperByshopk_name(shopk_name);
		
	}
	
	
}
