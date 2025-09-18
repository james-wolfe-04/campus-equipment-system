package edu.cit.wolfe.james.campusequipmentloan.Service;

public interface LateFeeStrategy {
    double calculateFee(long daysLate);
}
