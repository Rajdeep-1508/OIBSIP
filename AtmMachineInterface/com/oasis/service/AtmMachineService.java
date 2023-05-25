package com.oasis.service;

public interface AtmMachineService {
	public void transHistoryService(int accno);
	public String withdrawService(int accno,int amt);
	public String depositService(int accno,int amt);
	public String transferService(int from,int to,int amt);
	public String logIn(int accno,int pwd);
}
