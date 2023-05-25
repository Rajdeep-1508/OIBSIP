package com.oasis.factory;

import com.oasis.service.AtmMachineService;
import com.oasis.service.AtmMachineServiceImpl;

public class AtmMachineServiceFactory {
	private static AtmMachineService atmService;
	static {
		atmService = new AtmMachineServiceImpl();
	}
	public static AtmMachineService getAtmMachineService() {
		return atmService;
	}
}
