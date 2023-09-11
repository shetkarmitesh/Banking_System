package pro_ject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class bank_details {
    Scanner sc=new Scanner(System.in);

    private int acc_no;
    private String acc_type;
    private String acc_HolderName;
    private int acc_balance;
    private long deposit_amount;
    private long withdrawal_amount;

    void add_userInfo() throws ClassNotFoundException, SQLException{

        try {
            // connection setup with database
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_sys","root","zxcvbnm0987654321@");  
            Statement stmt=con.createStatement();  

            System.out.println("\nEnter your account number :");
            acc_no=sc.nextInt();

            System.out.println("\nEnter account type : ");
            acc_type=sc.next();

            System.out.println("\nEnter account holder Name :");
            acc_HolderName=sc.next();

            System.out.println("\nEnter Balance :");
            acc_balance=sc.nextInt();

            stmt.executeUpdate("insert into account_details (acc_no,acc_type,name,balance) Values("+acc_no+",'" +acc_type+"','"+acc_HolderName+"',"+acc_balance+")");
            // notifing that info inserted
            System.out.println("\nSuccessfully inserted record!!!!\n");
            con.close();

            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
    void display_userInfo(){
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_sys","root","zxcvbnm0987654321@");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from account_details"); 
            while (rs.next()) {
                System.out.println("\nName of account holder: " + rs.getString("name"));  
                System.out.println("Account no.: " + rs.getInt("acc_no"));  
                System.out.println("Account type: " + rs.getString("acc_type"));  
                System.out.println("Balance: " + rs.getInt("balance"));  
                
            }
            rs.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

    void Search_account(int search_no) throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_sys","root","zxcvbnm0987654321@");  
            Statement stmt=con.createStatement(); 

            ResultSet rs=stmt.executeQuery("select * from account_details where acc_no="+search_no); 
            if (rs.next()) {
                System.out.println("\nName of account holder: " + rs.getString("name"));  
                System.out.println("Account no.: " + rs.getInt("acc_no"));  
                System.out.println("Account type: " + rs.getString("acc_type"));  
                System.out.println("Balance: " + rs.getInt("balance")); 
            } 
            else {
                System.out.println("Account number Invalid.....!");
            }
            rs.close();

        } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
        }

    }

    void Deposit() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_sys","root","zxcvbnm0987654321@");  
            Statement stmt=con.createStatement(); 
            
            System.out.println("\nEnter the Account number :");
            acc_no=sc.nextInt();
            
            ResultSet rs=stmt.executeQuery("select * from account_details where acc_no="+acc_no); 
            
            if (rs.next()){
                        
                System.out.println("\nEnter the Amount :");
                deposit_amount=sc.nextInt(); 
                if (deposit_amount<0){
                    System.out.println("Please enter valid....!");
                
                }
                else{
                    stmt.executeUpdate("update account_details set balance ="+(rs.getInt("balance")+deposit_amount)+" where acc_no="+acc_no); 
                    
                    System.out.println("Deposit Successfully.....!");
                }
                
                                   
            }
            else {
                System.out.println("Account number Invalid.....!");
            }
            rs.close();


        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }


    }

    void Withdraw() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_sys","root","zxcvbnm0987654321@");  
            Statement stmt=con.createStatement(); 

            System.out.println("\nEnter the Account number :");
            acc_no=sc.nextInt();

            ResultSet rs=stmt.executeQuery("select * from account_details where acc_no="+acc_no); 
            if (rs.next()){
                        
                System.out.println("\nEnter the Amount :");
                withdrawal_amount=sc.nextInt();
                
                if (withdrawal_amount<=rs.getInt("balance")){

                    System.out.println("Account Type : "+rs.getString("acc_type"));

                    if(rs.getString("acc_type").equalsIgnoreCase("saving")){
                        if((rs.getInt("balance")-withdrawal_amount)>=1000){
                            
                            stmt.executeUpdate("update account_details set balance ="+(rs.getInt("balance")-withdrawal_amount)+" where acc_no="+acc_no); 
                            // System.out.println("Balance : "+rs.getInt("balance"));
                            System.out.println("Withdraw Successfully.....!");
                        }
                        else{
                            System.out.println("Insufficient Balance.....!");
                        }
                    }
                    else if (rs.getString("acc_type").equalsIgnoreCase("current")){
                        if((rs.getInt("balance")-withdrawal_amount)>=5000){
    
                            stmt.executeUpdate("update account_details set balance ="+(rs.getInt("balance")-withdrawal_amount)+" where acc_no="+acc_no); 
                            // System.out.println("Balance : "+rs.getInt("balance"));
                            System.out.println("Withdraw Successfully.....!");
                        }
                        else{
                            System.out.println("Insufficient Balance.....!");
                        }

                    }
                    else {
		        		System.out.println("Please Select Correct Account Type");
		        	}

                }
                else{
                    System.out.println("Insufficient Balance.....!");
                }
                                   
            }
            else {
                System.out.println("Account number Invalid.....!");
            }
            
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }
    void Remove_user() throws Exception{
        try{
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_sys","root","zxcvbnm0987654321@");  
        Statement stmt=con.createStatement(); 

        System.out.println("\nEnter Account number : ");
        acc_no=sc.nextInt();
        ResultSet rs = stmt.executeQuery("select * from account_details where acc_no="+acc_no);
        if(rs.next()){

            stmt.executeUpdate("delete from account_details where acc_no="+acc_no); 
            System.out.println("\nAccount removed successfully.....!");
        }
        else{
            System.out.println("Invalid Account Number");
        }

        
        }

        catch(Exception e){
            System.out.println(e);
        }
    }

}
