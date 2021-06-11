package com.dao;

import com.entity.*;
import com.util.*;

public class EleccionDao extends ConnectionJPA<Eleccion>{
	private static final long serialVersionUID = 1L;
	
	public EleccionDao() {
		super(Eleccion.class);
	}
}
