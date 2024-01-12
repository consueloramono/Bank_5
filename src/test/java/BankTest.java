import org.example.Bank;
import org.example.BankAccount;
import org.example.exception.AccountNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.exception.NegativeAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    void testCreateAccount() {
        Bank bank = new Bank();
        BankAccount account = bank.createAccount("John Doe", 100.0);

        assertNotNull(account);
        assertEquals("John Doe", account.getAccountName());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testFindExistingAccount() {
        Bank bank = new Bank();
        BankAccount account = bank.createAccount("Jane Doe", 200.0);

        try {
            BankAccount foundAccount = bank.findAccount(account.getAccountNumber());
            assertNotNull(foundAccount);
            assertEquals(account.getAccountNumber(), foundAccount.getAccountNumber());
        } catch (AccountNotFoundException e) {
            fail("AccountNotFoundException should not be thrown for an existing account");
        }
    }

    @Test
    void testFindNonExistingAccount() {
        Bank bank = new Bank();
        assertThrows(AccountNotFoundException.class, () -> bank.findAccount("NonExistingAccount"));
    }

    @Test
    void testTransferMoney() throws NegativeAmountException, InsufficientFundsException, AccountNotFoundException {
        Bank bank = new Bank();
        BankAccount fromAccount = bank.createAccount("Sender", 300.0);
        BankAccount toAccount = bank.createAccount("Receiver", 100.0);

        bank.transferMoney(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), 50.0);

        assertEquals(250.0, fromAccount.getBalance());
        assertEquals(150.0, toAccount.getBalance());
    }

    @Test
    void testTransferMoneyWithInsufficientFunds() {
        Bank bank = new Bank();
        BankAccount fromAccount = bank.createAccount("Sender", 50.0);
        BankAccount toAccount = bank.createAccount("Receiver", 100.0);

        assertThrows(InsufficientFundsException.class, () ->
                bank.transferMoney(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), 60.0));
    }

    @Test
    void testDepositWithNegativeAmount() {
        Bank bank = new Bank();
        BankAccount account = bank.createAccount("John Doe", 100.0);

        assertThrows(NegativeAmountException.class, () -> account.deposit(-50.0));
    }

    @Test
    void testWithdrawWithNegativeAmount() {
        Bank bank = new Bank();
        BankAccount account = bank.createAccount("John Doe", 100.0);

        assertThrows(NegativeAmountException.class, () -> account.withdraw(-50.0));
    }
}

