package com.psms.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.psms.entity.Pet;
import com.psms.entity.Shopkeeper;
import com.psms.model.PetDTO;
import com.psms.model.ShopkeeperDTO;
import com.psms.repository.ShopKeeperRepository;
import com.psms.service.ShopKeeperService;
import com.psms.util.ShopKeeperConverter;

@SpringBootTest
public class ShopKeeperServiceTest {

	@Autowired
	private ShopKeeperService shopKeeperService;
	
	@Autowired
	private ShopKeeperConverter shopKeeperConverter;
	
	@MockBean
	private ShopKeeperRepository ShopKeeperRepository;
	
	@Test
	@Order(1)
	void testCreateShopKeeper()
	{
		Shopkeeper shopkeeper=Shopkeeper.builder().shopkName("Aakash").build();
		Mockito.when(ShopKeeperRepository.save(shopkeeper)).thenReturn(shopkeeper);
		assertEquals("Shopkeeper details added successfully",
				shopKeeperService.createShopKeeper(shopkeeper));
	}

	@Test
	@Order(2)
	void testgetAllShopKeeper()
	{
		Shopkeeper shopkeeper=Shopkeeper.builder().shopkName("Aakash").build();
		
		Shopkeeper shopkeeper1=Shopkeeper.builder().shopkName("Raj Gupta").build();
		
		List<Shopkeeper> list=new ArrayList<>();
		list.add(shopkeeper1);
		list.add(shopkeeper);
		
		Mockito.when(ShopKeeperRepository.findAll()).thenReturn(list);
		
		List<ShopkeeperDTO> dto = shopKeeperService.getAllShopkeeper();
		List<Shopkeeper> shopkeepers=new ArrayList<>();
			dto.forEach(shopkeeperDTO->
			shopkeepers.add(shopKeeperConverter.convertDTOTOShopkeeper(shopkeeperDTO)));
			
			assertThat(shopkeepers.get(0).getShopkName()).isEqualTo(list.get(0).getShopkName()
		);
	}
	
	@Test
	@Order(3)
	void testUpdateShopKeeper()
	{
		Shopkeeper shopKeeper =  Shopkeeper.builder().shopkName("Aakash").build();
		Optional<Shopkeeper> opshopkeeper = Optional.of(shopKeeper)	;	

		Mockito.when(ShopKeeperRepository.findById(shopKeeper.getShopk_id())).thenReturn(opshopkeeper);

		Shopkeeper s = opshopkeeper.get();
		shopKeeper.setShopkName("Aakash");
		
		Mockito.when(ShopKeeperRepository.save(s)).thenReturn(s);		
		ShopkeeperDTO dto = shopKeeperService.updateShopkeeper(s.getShopk_id(),s);		
		assertEquals(dto.getShopkName(),s.getShopkName());
	}
	
	
}
