package com.oasis.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.oasis.factory.AtmMachineServiceFactory;
import com.oasis.factory.ConnectionFactory;
import com.oasis.service.AtmMachineService;


public class AtmInterface {
	static {
		ConnectionFactory.getConnectionObject();
	}
	public static void main(String[] args) {
		int ch,accno,to,password;
		int amt;
		String confirm = null;
		AtmMachineService atmService = AtmMachineServiceFactory.getAtmMachineService();
		BufferedReader br = null;
		System.out.println("******************************************");
		System.out.println("WELCOME!!! To OASIS INFOBYTE ATM INTEFACE");
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			do{
				System.out.print("Enter UserName::");
				accno = Integer.parseInt(br.readLine());
				System.out.print("Enter Password::");
				password = Integer.parseInt(br.readLine());
				String status = atmService.logIn(accno, password);
				if(status.equalsIgnoreCase("Success")) {
					break;
				}
				else {
					if(status.equalsIgnoreCase("Failure")){
						do {
							System.out.print("Do you want to re-try(Yes\\No)::");
							confirm = br.readLine();
							if(confirm.equalsIgnoreCase("No")) {
								System.out.println("Thank You!! Visit Again...");
								System.exit(0);
							}
							else if(confirm.equalsIgnoreCase("Yes")){
								break;
							}
							else {
								System.out.println("Please enter either Yes or No...");
							}
						}while(true);
					}
					if(status.equalsIgnoreCase("Not Existed")) {
						System.out.println("The account does not exist...");
					}
				}
			}while(true);
			do {
				System.out.println("Select an option::");
				System.out.println("1) Transanction History");
				System.out.println("2) Withdraw");
				System.out.println("3) Deposit");
				System.out.println("4) Transfer");
				System.out.println("Enter 0 to QUIT");
				System.out.print("Enter your choice::");
				ch = Integer.parseInt(br.readLine());
				switch(ch) {
				case 0:
					System.out.println("Thank you!!! Visit Again");
					System.exit(0);
				case 1:
					//Transanction History
					System.out.println("********************Transanction History********************");
					//System.out.print("Enter the account number::");
					//accno = Integer.parseInt(br.readLine());
					atmService.transHistoryService(accno);
					break;
				case 2:
					//Withdrawl 
					System.out.println("********************Withdrawl********************");
					System.out.println("Enter the amount to withdraw::");
					amt = Integer.parseInt(br.readLine());
					String status = atmService.withdrawService(accno,amt);
					
					if(status.equalsIgnoreCase("Account does not exist")) {
						System.out.println("Account does not exist");
					}
					else if(status.equalsIgnoreCase("Transanction not possible"))
						System.out.println("Transanction not possible due to insufficient balance");
					else if(status.equalsIgnoreCase("Success"))
						System.out.println("Successful Withdrawl");
					else if(status.equalsIgnoreCase("Failure"))
						System.out.println("Withdrawl Failed");
					break;
				case 3:
					//Deposit
					System.out.println("********************Deposit********************");
					System.out.println("Enter the amount to deposit::");
					amt = Integer.parseInt(br.readLine());
					String status1 = atmService.depositService(accno,amt);
					if(status1.equalsIgnoreCase("Account does not exist")) 
						System.out.println("Account does not exist");
					else if(status1.equalsIgnoreCase("Success"))
						System.out.println("Successful Deposit");
					else if(status1.equalsIgnoreCase("Failure"))
						System.out.println("Deposit Failed");
					break;
				case 4:
					//Transfer
					System.out.println("********************Transfer********************");
					System.out.print("Enter the account number to which to transfer:: ");
					to = Integer.parseInt(br.readLine());
					System.out.println("Enter the amount to transfer::");
					amt = Integer.parseInt(br.readLine());
					status1 = atmService.transferService(accno,to,amt);
					if(status1.equalsIgnoreCase("Success"))
						System.out.println("Successful Transfer");
					else if(status1.equalsIgnoreCase("Transfer Failed"))
						System.out.println("Transfer Failed");
					break;
				default:
					System.out.println("WRONG CHOICE");
				}
			}while(true);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
