package com.dao;

import com.entity.*;
import com.util.*;

public class VotanteDao extends ConnectionJPA<Votante>{
	private static final long serialVersionUID = 1L;
	
	public VotanteDao() {
		super(Votante.class);
	}
}
