package org.autotesting.model;

public class Account {
    public int id;

    public String username;

    public String accountNumber;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
