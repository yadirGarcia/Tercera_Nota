package com.dao;

import com.entity.*;
import com.util.*;

public class CandidatoDao extends ConnectionJPA<Candidato>{
	private static final long serialVersionUID = 1L;
	
	public CandidatoDao() {
		super(Candidato.class);
	}
}
