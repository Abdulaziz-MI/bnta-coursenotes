import java.time.LocalDate;

public class BankAccount {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int accountNumber;
    private double balance;
    private String accountType;
    private double overdraft;

    public BankAccount(String firstName, String lastName, LocalDate dateOfBirth, int accountNumber, String accountType, double overdraft) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.accountType = accountType;
        this.overdraft = overdraft; // assume to be negative
    }

    public void deposit(double amount){
        this.balance += amount;
    }

    public void withdraw(double amount){
        if ((this.balance - amount) >= overdraft){ // only withdraw if amount doesn't put you over overdraft
            this.balance -= amount;
        }
    }

    public double calculateInterest(double percentage){
        double interestToPay = this.balance * percentage;
        return interestToPay;
    }

    public void payInterest(double savingsAccInterest, double currentAccInterest){
        if (accountType.equals("savings")){
            this.balance += calculateInterest(savingsAccInterest);
        } else if (accountType.equals("current")) {
            this.balance += calculateInterest(currentAccInterest);
        }
    }

    //    GETTERS & SETTERS
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
}
