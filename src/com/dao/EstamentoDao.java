package com.dao;

import com.entity.*;
import com.util.*;

public class EstamentoDao extends ConnectionJPA<Estamento>{
	private static final long serialVersionUID = 1L;
	
	public EstamentoDao() {
		super(Estamento.class);
	}
}
