package edu.cit.wolfe.james.campusequipmentloan.Service;

public class FiftyPesoPerDayStrategy implements LateFeeStrategy {
    @Override
    public double calculateFee(long daysLate) {
        return daysLate * 50.0;
    }
}
