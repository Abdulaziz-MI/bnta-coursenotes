import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        bankAccount = new BankAccount("Eoan", "Odea", LocalDate.of(1985, 10, 4), 00126, "current", -50.00);
    }

    @Test
    public void canDeposit(){
        bankAccount.deposit(100);
        assertThat(bankAccount.getBalance()).isEqualTo(100);
    }

    @Test
    public void canWithdraw__true(){
        bankAccount.deposit(100);
        bankAccount.withdraw(50);
        assertThat(bankAccount.getBalance()).isEqualTo(50);
    }

    @Test
    public void canWithdrawInOverdraft__true(){
        bankAccount.deposit(100);
        bankAccount.withdraw(125);
        assertThat(bankAccount.getBalance()).isEqualTo(-25);
    }

    @Test
    public void canWithdraw__false(){
        bankAccount.deposit(100);
        bankAccount.withdraw(200);
        assertThat(bankAccount.getBalance()).isEqualTo(100);
    }

    @Test
    public void canPayInterest(){
        bankAccount.deposit(100);
        bankAccount.payInterest(0.02, 0.01);
        assertThat(bankAccount.getBalance()).isEqualTo(101);
    }

//    TESTS FOR GETTERS & SETTERS
    @Test
    public void hasFirstName(){
        assertThat(bankAccount.getFirstName()).isEqualTo("Eoan");
    }

    @Test
    public void canSetFirstName(){
        bankAccount.setFirstName("Daniel");
        assertThat(bankAccount.getFirstName()).isEqualTo("Daniel");
    }

    @Test
    public void hasLastName(){
        assertThat(bankAccount.getLastName()).isEqualTo("Odea");
    }

    @Test
    public void canSetLastName(){
        bankAccount.setLastName("DeVito");
        assertThat(bankAccount.getLastName()).isEqualTo("DeVito");
    }

    @Test
    public void hasDateOfBirth(){
        assertThat(bankAccount.getDateOfBirth()).isEqualTo("1985-10-04");
    }

    @Test
    public void canSetDateOfBirth(){
        bankAccount.setDateOfBirth(LocalDate.of(1985, 9, 5));
        assertThat(bankAccount.getDateOfBirth()).isEqualTo("1985-09-05");
    }

    @Test
    public void hasAccountNumber(){
        assertThat(bankAccount.getAccountNumber()).isEqualTo(00126);
    }

    @Test
    public void canSetAccountNumber(){
        bankAccount.setAccountNumber(00127);
        assertThat(bankAccount.getAccountNumber()).isEqualTo(00127);
    }

    @Test
    public void hasAccountType(){
        assertThat(bankAccount.getAccountType()).isEqualTo("current");
    }

    @Test
    public void canSetAccountType(){
        bankAccount.setAccountType("savings");
        assertThat(bankAccount.getAccountType()).isEqualTo("savings");
    }

    @Test
    public void hasOverdraft(){
        assertThat(bankAccount.getOverdraft()).isEqualTo(-50);
    }

    @Test
    public void canSetOverdraft(){
        bankAccount.setOverdraft(-100);
        assertThat(bankAccount.getOverdraft()).isEqualTo(-100);
    }

}
