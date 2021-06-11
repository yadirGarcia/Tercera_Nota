package com.dao;

import com.entity.*;
import com.util.*;

public class TipoDocumentoDao  extends ConnectionJPA<Tipodocumento>{
	private static final long serialVersionUID = 1L;
	
	public TipoDocumentoDao() {
		super(Tipodocumento.class);
	}
}
