package pro_ject;
import java.util.Scanner;


public class banking_sys {
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String args[]) throws Exception
    {
        System.out.println("******** Banking System *********\n");

        System.out.println("How many number of user you want to add ?");
        int no_of_user=sc.nextInt();

        bank_details user_info=new bank_details();
        for (int i =1; i<=no_of_user;i++){
            user_info.add_userInfo();
        }

        int choice;
        do {
            System.out.println("\n***Banking System Application***\n");  
            System.out.println("\n 1. Display all account details \n 2. Search by Account number\n 3. Deposit the amount \n 4. Withdraw the amount \n 5. Remove the user \n 6. Exit ");  
            System.out.println("\n Enter your choice: ");  
            choice = sc.nextInt();  
            
            switch (choice) {
                case 1:
                    user_info.display_userInfo();                    
                    break;
                case 2:
                    System.out.println("\nEnter account number :");
                    int search_no=sc.nextInt();
                    
                    user_info.Search_account(search_no);
                    
                    
                    break;

                case 3:
                    user_info.Deposit();
                    break;
                
                case 4:
                    user_info.Withdraw();
                    break;
                case 5:
                    user_info.Remove_user();
                    break;
                case 6 :
                    System.out.println("See you soon......!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice!=6);



    }
    
   

}
