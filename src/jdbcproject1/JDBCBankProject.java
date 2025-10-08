package jdbcproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCBankProject {
		
		private static Connection connection = null;
		private static Scanner scanner=new Scanner(System.in);
		private static ResultSet loginCheck;
		private static PreparedStatement pStatement;
		private static String depositQuery = "update users set bank_balance = bank_balance + ? where acc_number = ?";
		private static String withdrawQuery = "update users set bank_balance = bank_balance - ? where (acc_number = ? and bank_balance >= ?)";
		private static String checkBalanceQ = "select name, bank_balance from users where acc_number = ?";
		public static void main(String[] args) {
			
			String url = "jdbc:mysql://localhost:3306/project1_jdbc";
			String username = "root";
			String password  = "root";
			String query =  "select acc_number from users where acc_number = ? and password = ? ";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				connection = DriverManager.getConnection(url, username,password);
				
				PreparedStatement pStatement = connection.prepareStatement(query);
				
				Boolean isContinue = true;

				//Login Check
				while(isContinue)  {
					System.out.println("Enter Ur Account_number and Password to Login!!");
					long accNum = scanner.nextLong();
					String passwordChwck  = scanner.next();
					
					pStatement.setLong(1, accNum);
					pStatement.setString(2, passwordChwck);
					
					loginCheck = pStatement.executeQuery();
					
					if(loginCheck()) {
						
						//
						System.out.println("Enter your choices: 1) Deposit\n 2) Withdraw\n 3) Transfer\n 4) Check Balance\n 5) exit(Logout)");
						int menuChoice = scanner.nextInt();
						
						switch(menuChoice) {
							case 1: {
								deposit(accNum);
								isContinue = exit();
								break;
							}
							case 2: {
								withdraw(accNum);
								isContinue = exit();
								break;
							}
							case 3: {
								transfer();
								isContinue = exit();
								break;
							}
							case 4: {
								checkBalance(accNum);
								isContinue = exit();
								break;
							}
							case 5: {
								isContinue =  exit();
								break;
							}
							default: 
								System.out.println("Invalid Choices!!");
								isContinue = exit();
						}
						
					} 
					else {
						System.out.println("Incorrect AccNumber and Password");
						isContinue = exit();
					}
				}
				
				System.out.println();
				System.out.println("Thank You! Come Again ");
				
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
					e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			
			
			
			
		}
		
		public static boolean loginCheck() throws SQLException {
			
			if(loginCheck.next()) {
				System.out.println("Login Successful! Welcome ");	
				return true;
			} 
			return false;
			}
		
		
		private static void deposit(long accNum ) throws SQLException {
			System.out.println("Enter Amount to Deposit: " );
			int amount = scanner.nextInt();
			
			if(amount >0) {
				pStatement = connection.prepareStatement(depositQuery);
				pStatement.setInt(1, amount);
				pStatement.setLong(2, accNum);
				int res = pStatement.executeUpdate();
				System.out.println(res);	
				display();
			}
		}

		private static void withdraw(long accNum) throws SQLException {
			System.out.println("Enter Amount to withdraw: " );
			int amount = scanner.nextInt();
			
			pStatement = connection.prepareStatement(withdrawQuery);
			pStatement.setInt(1, amount);
			pStatement.setLong(2, accNum);
			pStatement.setInt(3, amount);
			int res = pStatement.executeUpdate();
			System.out.println(res);	
			display();
		}

		private static void transfer() {
			
		}
		

		private static void checkBalance(long accNum) throws SQLException {

			pStatement = connection.prepareStatement(checkBalanceQ);
			pStatement.setLong(1, accNum);
			pStatement.executeQuery();
			ResultSet res= pStatement.executeQuery();
			while(res.next()) {
			System.out.println( "Name: " + res.getString("name") + " Balance: " + res.getDouble("bank_balance"));
			}
		}


		private static boolean exit() {
			
			System.out.println("Do you want to contiue or not(yes/no)");
			String choices1 = scanner.next();
			
			if(choices1.equalsIgnoreCase("no")) {
					return false;
			}
			return true;
			
		}

		private static void  display() throws SQLException {
			Statement stmt  = connection.createStatement();
			ResultSet result = stmt.executeQuery("select * from users");
			
			while(result.next()) {
				System.out.println(result.getInt(1) + " " + result.getString(2) + " " +result.getInt(3) + " " + result.getString(4) + " "+ result.getDouble(5) );
			}
		}

	}