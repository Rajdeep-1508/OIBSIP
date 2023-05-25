package com.oasis.dao;

public interface AtmMachineDao {
	public void tranHistory(int accno);
	public String withdraw(int accno,int amt);
	public String deposit(int accno,int amt);
	public String transfer(int from,int to,int amt);
	public String logIn(int accno,int pwd);
}
