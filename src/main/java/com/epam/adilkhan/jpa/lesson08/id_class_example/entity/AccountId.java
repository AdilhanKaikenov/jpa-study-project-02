package com.epam.adilkhan.jpa.lesson08.id_class_example.entity;

import java.io.Serializable;

public class AccountId implements Serializable {

    private String accountNumber;

    private String accountType;

    public AccountId() {
    }

    public AccountId(String accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountId accountId = (AccountId) o;

        if (accountNumber != null ? !accountNumber.equals(accountId.accountNumber) : accountId.accountNumber != null)
            return false;
        return accountType != null ? accountType.equals(accountId.accountType) : accountId.accountType == null;
    }

    @Override
    public int hashCode() {
        int result = accountNumber != null ? accountNumber.hashCode() : 0;
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        return result;
    }
}
