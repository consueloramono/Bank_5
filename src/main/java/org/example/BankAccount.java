package org.example;

import org.example.exception.InsufficientFundsException;
import org.example.exception.NegativeAmountException;

public class BankAccount {

    private String accountNumber;
    private String accountName;
    private double balance;

    public BankAccount(String accountNumber, String accountName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = initialBalance;
    }

    public void deposit(double amount) throws NegativeAmountException {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            throw new NegativeAmountException("Invalid deposit amount. Please enter a positive amount.");
        }
    }

    public void withdraw(double amount) throws NegativeAmountException, InsufficientFundsException {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: " + balance);
            } else {
                throw new InsufficientFundsException("Insufficient funds for withdrawal.");
            }
        } else {
            throw new NegativeAmountException("Invalid withdrawal amount. Please enter a positive amount.");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void getAccountSummary() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Name: " + accountName);
        System.out.println("Balance: " + balance);
    }
}
