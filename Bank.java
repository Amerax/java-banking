public class Bank{
    private int checkingBal = 0;
    private int savingsBal = 0;
    private String accountID;
    private String username;
    private String password;


    public Bank(String user, String pass){
        username = user;
        password = pass;

        accountID = genID();
    }

    public String genID(){
        String temp = "";

        for(int i = 0; i < 5; i++){
            double random = Math.random();
            if(random < .5){
                temp += (char) ((int)(Math.random()*26) + 65);
            }
            else{
                temp += (int) (Math.random()*10);
            }
        }

        return temp;
    }

    public void addSavingsBal(int amt){
        savingsBal += amt;
    }

    public void addCheckingBal(int amt){
        checkingBal += amt;
    }

    public int withdraw(boolean fromSavings, int amt){
        if(fromSavings){
            if(savingsBal - amt < 0){
                return - 1;
            }
            else{
                int val = savingsBal - amt;
                savingsBal = val;
                return val; 
            }
        }
        else{
            if(checkingBal - amt < 0){
                return - 1;
            }
            else{
                int val = checkingBal - amt; 
                checkingBal = val;
                return val; 
            }
        }
    }

    public int deposit(boolean fromSavings, int amt){
        if(fromSavings){
            savingsBal += amt;
            return savingsBal;
        }
        else{
            checkingBal += amt;
            return checkingBal;
        }
    }

    public String getPass(){
        return this.password;
    }

    public String getID(){
        return this.accountID;
    }

    @Override
    public String toString(){
        return "Account ID: " + accountID + " Username: " + username + " Checking Balance: " + checkingBal + " Savings Balance: " + savingsBal;
    }

} 
