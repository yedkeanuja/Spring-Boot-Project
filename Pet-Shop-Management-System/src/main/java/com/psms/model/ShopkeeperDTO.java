package com.psms.model;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopkeeperDTO {
	
	private int shopk_id;
	
	@NotNull
	private String shopkName;
	
	private List<CustomerDTO> customers;

}
