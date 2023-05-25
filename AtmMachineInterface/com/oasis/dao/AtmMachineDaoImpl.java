package com.oasis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.oasis.factory.ConnectionFactory;


public class AtmMachineDaoImpl implements AtmMachineDao {
	public String logIn(int accno,int pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs1 = null;
		int password = 0;
		try {
			con = ConnectionFactory.getConnectionObject();
			if(con!=null) {
				st = con.createStatement();
			}
			if(st!= null) 
				rs1 = st.executeQuery("SELECT * FROM ACCOUNTSTABLE WHERE ACCNO = "+accno);
			boolean b = rs1.next();	
			if(rs1 != null && b == true) {
				password = rs1.getInt(5);
				if(password == pwd)
					return "Success";
				else {
					return "Failure";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs1!=null)
					rs1.close();
			} catch (SQLException sqe) {
				// TODO: handle exception
				sqe.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			} catch (SQLException sqe) {
				// TODO: handle exception
				sqe.printStackTrace();
			}
		}
		return "Not existed";
	}

	@Override
	public void tranHistory(int accno) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String id = null;
		
		try {
			con = ConnectionFactory.getConnectionObject();
			if(con!=null) {
				st = con.createStatement();
			}
			if(st!=null) {
				rs1 = st.executeQuery("SELECT TRANSANCTIONID FROM ACCOUNTSTABLE WHERE ACCNO = "+accno);
				boolean b = rs1.next();
				if(b==true){
					id = rs1.getString(1);
					rs2 = st.executeQuery("SELECT * FROM "+ id);
					System.out.println("The transanction history is::");
					System.out.println("****************************************");
					while(rs2.next())
						System.out.println(rs2.getInt(1)+"  "+rs2.getString(2)+"  "+rs2.getString(3)+"  "+rs2.getFloat(4)+"  "+rs2.getFloat(5)+"  "+rs2.getFloat(6) );
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs1!=null)
					rs1.close();
			} catch (SQLException sqe) {
				// TODO: handle exception
				sqe.printStackTrace();
			}
			try {
				if(rs2!=null)
					rs2.close();
			} catch (SQLException sqe) {
				// TODO: handle exception
				sqe.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			} catch (SQLException sqe) {
				// TODO: handle exception
				sqe.printStackTrace();
			}
		}
		return;
	}

	@Override
	public String withdraw(int accno,int amt) {
		String status = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String tid= "";
		
		try {
			con = ConnectionFactory.getConnectionObject();
			int slno = 0, balance = 0;
			if(con != null)
				st = con.createStatement();
			if(st != null)
				rs1 = st.executeQuery("SELECT * FROM ACCOUNTSTABLE WHERE ACCNO = " + accno);
			boolean b = rs1.next();
			if(b == true && rs1 != null) {
				tid = rs1.getString(4);
				if(st != null)
					rs2 = st.executeQuery("SELECT MAX(SERIALNO) FROM " + tid);
				if(rs2 != null) {
					if(rs2.next())
						slno = rs2.getInt(1);
				}				
				if(st != null)
					rs3 = st.executeQuery("SELECT BALANCE FROM "+ tid +" WHERE SERIALNO = " + slno);
				if(rs3 != null) {
					if(rs3.next())
						balance = rs3.getInt(1);
				}
				if(balance >= amt) {
					if(st != null) {
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDateTime now = LocalDateTime.now();
						String datestr = dtf.format(now);
						//INSERT INTO TR1111 VALUES(2,"2005-06-2","CASH WITHDRAWN",500,0,500);
						st.executeUpdate("INSERT INTO "+ tid +" VALUES(" +(slno+1) +",'"+ datestr +"','CASH WITHDRAWN' ,0, "+ amt +", "+ (balance - amt) +")");
					}
					status =  "success";
				}
				else {
					status =  "Transanction not possible";
				}
			}
			else {
				status =  "Account does not exist";
			}
		} catch (SQLException sqe) {
			if(sqe.getErrorCode()==1)
				System.out.println("Duplicate cannot be inserted to primary key");
			if(sqe.getErrorCode()==1400)
				System.out.println("Null cannot be inserted to primary key");
			if(sqe.getErrorCode()>=900 && sqe.getErrorCode()<=999)
				System.out.println("Invalid Column names or table names or sql query");
			if(sqe.getErrorCode()==12899)
				System.out.println("Do not insert more than column size data to table");
			status = "failure";
			sqe.printStackTrace();
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}finally {
			try {
				if(rs1 != null && rs2 != null&& rs3 != null) {
					rs1.close();
					rs2.close();
					rs3.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public String deposit(int accno,int amt) {
		String status = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String tid= "";
		
		try {
			con = ConnectionFactory.getConnectionObject();
			int slno = 0, balance = 0;
			if(con != null)
				st = con.createStatement();
			if(st != null)
				rs1 = st.executeQuery("SELECT * FROM ACCOUNTSTABLE WHERE ACCNO = " + accno);
			boolean b = rs1.next();
			if(b == true && rs1 != null) {
				tid = rs1.getString(4);
				if(st != null)
					rs2 = st.executeQuery("SELECT MAX(SERIALNO) FROM " + tid);
				if(rs2 != null) {
					if(rs2.next())
						slno = rs2.getInt(1);
				}				
				if(st != null)
					rs3 = st.executeQuery("SELECT BALANCE FROM "+ tid +" WHERE SERIALNO = " + slno);
				if(rs3 != null) {
					if(rs3.next())
						balance = rs3.getInt(1);
				}
				if(st != null) {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime now = LocalDateTime.now();
					String datestr = dtf.format(now);
					st.executeUpdate("INSERT INTO "+ tid +" VALUES(" +(slno+1) +",'"+ datestr +"','CASH DEPOSIT' , "+ amt +",0, "+ (balance + amt) +")");
					status =  "success";
				}
			}
			else {
				status =  "Account does not exist";
			}
		} catch (SQLException sqe) {
			if(sqe.getErrorCode()==1)
				System.out.println("Duplicate cannot be inserted to primary key");
			if(sqe.getErrorCode()==1400)
				System.out.println("Null cannot be inserted to primary key");
			if(sqe.getErrorCode()>=900 && sqe.getErrorCode()<=999)
				System.out.println("Invalid Column names or table names or sql query");
			if(sqe.getErrorCode()==12899)
				System.out.println("Do not insert more than column size data to table");
			status = "failure";
			sqe.printStackTrace();
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}finally {
			try {
				if(rs1 != null && rs2 != null&& rs3 != null) {
					rs1.close();
					rs2.close();
					rs3.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return status;
}

	@Override
	public String transfer(int from, int to,int amt) {
		// TODO Auto-generated method stub
		String status = "";
		status = this.withdraw(from,amt);
		if(status.equalsIgnoreCase("Success")) {
			status = this.deposit(to,amt);
			if(!status.equalsIgnoreCase("Success")){
				this.deposit(from, amt);
				return "Transfer Failed";
			}
		}
		else {
			return "Transfer Failed";
		}
		return "Success";
	}

}
