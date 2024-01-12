package org.example;


import org.example.exception.AccountNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.exception.NegativeAmountException;

public class Main {
    public static void main(String[] args) throws NegativeAmountException, InsufficientFundsException, AccountNotFoundException {
        BankAccount myAccount = new BankAccount("123456789", "John Doe", 1000.0);
        myAccount.getAccountSummary();
        myAccount.deposit(500.0);
        myAccount.withdraw(200.0);
        myAccount.getAccountSummary();
        System.out.println("\n-----------------------------------------------------\n");
        Bank bank = new Bank();
        BankAccount account1 = bank.createAccount("John Doe", 1000.0);
        BankAccount account2 = bank.createAccount("Jane Smith", 1500.0);
        bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), 200.0);
        account1.getAccountSummary();
        account2.getAccountSummary();
    }
}
