package com.greatersum.rental;

import java.math.BigDecimal;

public class RentalAmount {

    private int frequentRenterPoints;
    private String result;
    private BigDecimal totalAmount;

    public RentalAmount() {
    }

    public RentalAmount(int frequentRenterPoints, String result, BigDecimal totalAmount) {
        this.frequentRenterPoints = frequentRenterPoints;
        this.result = result;
        this.totalAmount = totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }


    public String getResult() {
        return result;
    }


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }


    public void incRenterPoints() {
        frequentRenterPoints ++;
    }

    public void addTotalAmount(BigDecimal amount) {
        totalAmount = totalAmount.add(amount);
    }

    public void addToResult(String newResult) {
        result += newResult;
    }
}
