package org.yearup.cardealership;

public class LeaseContract extends Contract{
    private final double leaseFinanceRate = 0.04;
    private double expectedEndValue;
    private double leaseFee;
    private double totalPrice;

    public LeaseContract(String contractDate, String cxName, String cxEmail, Vehicle vehicleSold) {
        super(contractDate, cxName, cxEmail, vehicleSold);
        this.totalPrice = totalPrice;
        this.expectedEndValue = totalPrice * 0.5;
        this.leaseFee = totalPrice * 0.07;

    }

    public double getExpectedEndValue() {
        double vehiclePrice = vehicleSold.getPrice();
        double expectedEndValue = vehiclePrice / 2;
        return expectedEndValue;
    }

    public double getLeaseFee() {
        double vehiclePrice = vehicleSold.getPrice();
        double leaseFee = vehiclePrice * 0.07;

        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return vehicleSold.getPrice() + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        //math.pow returns the value of argument a raised to the power of argument b
        double monthlyInterestRate = leaseFinanceRate / 12;
        double leaseAmount = getTotalPrice() - expectedEndValue;
        return (leaseAmount * monthlyInterestRate) / (1- Math.pow(1 + monthlyInterestRate, -36));
    }

    //create a string builder
    // append LEASE|
    //append date, cx name, cx email, vehicle sold, total price, monthly payment, expected end value, lease fee
    @Override
    public String getPersistenceString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LEASE").append("|");
        sb.append(getContractDate()).append("|");
        sb.append(getCxName()).append("|");
        sb.append(getCxEmail()).append("|");
        sb.append(getVehicleSold().getVin()).append("|");
        sb.append(getVehicleSold().getYear()).append("|");
        sb.append(getVehicleSold().getMake()).append("|");
        sb.append(getVehicleSold().getModel()).append("|");
        sb.append(getVehicleSold().getVehicleType()).append("|");
        sb.append(getVehicleSold().getColor()).append("|");
        sb.append(getVehicleSold().getOdometer()).append("|");
        sb.append(getVehicleSold().getPrice()).append("|");
        sb.append(getExpectedEndValue()).append("|");
        sb.append(getLeaseFee()).append("|");
        sb.append(getTotalPrice()).append("|");
        sb.append(getMonthlyPayment()).append("|");

        return sb.toString();
    }
}
