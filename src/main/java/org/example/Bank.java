package org.example;

import org.example.exception.AccountNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.exception.NegativeAmountException;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String accountName, double initialDeposit) {
        String accountNumber = generateAccountNumber();
        BankAccount newAccount = new BankAccount(accountNumber, accountName, initialDeposit);
        accounts.add(newAccount);
        System.out.println("Account created successfully. Account Number: " + accountNumber);
        return newAccount;
    }

    public BankAccount findAccount(String accountNumber) throws AccountNotFoundException {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account not found. Account Number: " + accountNumber);
    }

    public void transferMoney(String fromAccountNumber, String toAccountNumber, double amount) throws NegativeAmountException, InsufficientFundsException, AccountNotFoundException {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        if (fromAccount != null && toAccount != null) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            throw new AccountNotFoundException("Account not found.");
        }
    }

    private String generateAccountNumber() {
        return "ACC" + (accounts.size() + 1);
    }
}
