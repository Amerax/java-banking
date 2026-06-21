import java.util.Scanner;
import java.util.HashMap;

public class main{
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, Bank> accounts = new HashMap<>();
    public static void main(String[] args){
        System.out.println("Hello welcome to java banking! \nLoading...");
        
        boolean running = true;
        while(running){
            System.out.println("""
            Make your selection by pressing a key
            Quit: 0
            Login: 1
            Create Account: 2 """);

            int input = sc.nextInt();
            sc.nextLine(); // consume left over

            switch(input){
                case 0:
                    running = false; 
                    break;
                case 1:{
                    System.out.println("Input your username: ");
                    String user = sc.nextLine();
                    System.out.println("Input your password: ");
                    String pass = sc.nextLine();

                    signIn(user, pass);
                    break;
                }

                case 2: {
                    System.out.println("Enter in your username: ");
                    String user = sc.nextLine();
                    System.out.println("Enter in a password: ");
                    String pass = sc.nextLine();

                    accounts.put(user, new Bank(user, pass));
                    break;
                }
            
                default:
                    System.out.println("Invalid input! Please input a number 0-2");
                    break;
            }
        }

    }

    public static void signIn(String user, String pass){
        if(accounts.containsKey(user)){
            if(accounts.get(user).getPass().equals(pass)){
                Bank account = accounts.get(user);
                System.out.println(account);
                
                Boolean loggedIn = true;
                while(loggedIn){
                    System.out.println("You are currently logged in, would you like to log out and go back to the main menu (n) or perform an action (y)");
                    if(sc.nextLine().equals("y")){
                        System.out.println("Would you like to withdraw or deposit (w/d)");
                        if(sc.nextLine().equals("w")){
                            System.out.println("Withdraw from savings (s) or checking (c) balance? ");
                            boolean temp = sc.nextLine().equals("s");

                            withdraw(temp, account);
                        }
                        else{
                            System.out.println("Deposit into savings (s) or checking (c) balance? ");
                            boolean temp = sc.nextLine().equals("s");

                            deposit(temp, account);
                        }
                    }
                    else{
                        System.out.println("Logging out...");
                        loggedIn = false; 
                    }
                }
            }
            else{
                System.out.println("The password is wrong!");
            }
        }
        else{
            System.out.println("There is no account on our records with that username!");
        }
    }

    public static void withdraw(boolean temp, Bank account){
        System.out.println("How much would you like to withdraw? (enter an integer number)");
        int amt = sc.nextInt();
        sc.nextLine(); // consume next line
        int result = 0;

        if(temp){
            result = account.withdraw(temp, amt);

        }
        else{
            result = account.withdraw(!temp, amt);
        }

        if(result == -1){
            System.out.println("Transaction failed! Not enough balance.");
        }
        else{
            System.out.println("Success! You have this much left: $" + result);
        }
    }

    public static void deposit(boolean temp, Bank account){
        System.out.println("How much would you like to deposit? (enter an integer number)");
        int amt = sc.nextInt();
        sc.nextLine(); // consume next line
        int result = 0;

         if(temp){
            result = account.deposit(temp, amt);
        }
        else{
            result = account.deposit(!temp, amt);
        }

        System.out.println("Success! You have this much left: $" + result);
    }
}
