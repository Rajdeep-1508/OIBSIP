package com.oasis.service;

import com.oasis.dao.AtmMachineDao;
import com.oasis.factory.AtmMachineDaoFactory;

public class AtmMachineServiceImpl implements AtmMachineService {

	@Override
	public void transHistoryService(int accno) {
		// TODO Auto-generated method stub
		AtmMachineDao atmDao = AtmMachineDaoFactory.getAtmMachineDao();
		atmDao.tranHistory(accno);
		return;
	}

	@Override
	public String withdrawService(int accno,int amt) {
		// TODO Auto-generated method stub
		AtmMachineDao atmDao = AtmMachineDaoFactory.getAtmMachineDao();
		String str = atmDao.withdraw(accno,amt);
		return str;
	}

	@Override
	public String depositService(int accno,int amt) {
		// TODO Auto-generated method stub
		AtmMachineDao atmDao = AtmMachineDaoFactory.getAtmMachineDao();
		String str = atmDao.deposit(accno,amt);
		return str;
	}

	@Override
	public String transferService(int from, int to,int amt) {
		// TODO Auto-generated method stub
		AtmMachineDao atmDao = AtmMachineDaoFactory.getAtmMachineDao();
		String status = atmDao.transfer(from,to,amt);
		return status;
	}
	
	public String logIn(int accno,int pwd) {
		String status = null;
		AtmMachineDao atmDao = AtmMachineDaoFactory.getAtmMachineDao();
		status = atmDao.logIn(accno,pwd);
		return status;
	}

}
