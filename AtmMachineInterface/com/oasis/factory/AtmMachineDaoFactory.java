package com.oasis.factory;

import com.oasis.dao.AtmMachineDao;
import com.oasis.dao.AtmMachineDaoImpl;

public class AtmMachineDaoFactory {
	private static AtmMachineDao atmDao;
	static {
		atmDao = new AtmMachineDaoImpl();
	}
	public static AtmMachineDao getAtmMachineDao() {
		return atmDao;
	}
}
