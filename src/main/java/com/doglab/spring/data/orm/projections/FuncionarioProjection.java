package com.doglab.spring.data.orm.projections;

import java.math.BigDecimal;

public interface FuncionarioProjection {

	Long getId();
	String getName();
	BigDecimal getIncome();
	
}
