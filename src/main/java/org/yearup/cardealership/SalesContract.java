package org.yearup.cardealership;

public class SalesContract extends Contract{
    private static final double salesTax = 0.05;
    private static final double recordingFee = 100.00;
    private static double processingFee;
    private static final double interestRateUnder = 0.0525;
    private static final double interestRateAbove = 0.0425;
    private static final int loanTermUnder = 24;
    private static final int loanTermAbove = 48;

    private double totalPrice;
    private double monthlyPayment;
    private boolean financeOption;


    public SalesContract(String date, String name, String cxEmail, Vehicle vehicleSold, boolean financeOption) {
        super(date, name, cxEmail, vehicleSold);
        this.financeOption = financeOption;
        this.totalPrice = totalPrice;
        this.processingFee = processingFee;
    }

    public double getSalesTaxAmount() {
        return totalPrice * salesTax;
    }
    public double getRecordingFee() {
        return recordingFee;
    }
    public double getProcessingFee() {
        //if the total price is below p.u if above p.a
        if (vehicleSold.getPrice() < 10000) {
            return 295.00;
        } else {
            return 495.00;
        }
    }
    public boolean isFinanceOption() {
        return financeOption;
    }
    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = 0;
        double totalPrice = 0;
        vehiclePrice = vehicleSold.getPrice();
        //calculating
        if (vehiclePrice < 10000) {
            totalPrice = vehiclePrice + 295.00;
        } else {
            totalPrice = vehiclePrice + 495.00;
        }
        totalPrice = totalPrice + recordingFee;
        totalPrice += (vehiclePrice + salesTax);

        return totalPrice;
    }

    // create a string builder
    //append SALE|
    // append date, cx name, cx email, vehicle sold, total price, monthly payment, sales tax, recording, processing, finance option
    @Override
    public String getPersistenceString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SALE").append("|");
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
        sb.append(getSalesTaxAmount()).append("|");
        sb.append(getRecordingFee()).append("|");
        sb.append(getProcessingFee()).append("|");
        sb.append(getTotalPrice()).append("|");
        sb.append(isFinanceOption() ? "YES" : "NO").append("|");
        sb.append(getMonthlyPayment()).append("|");

        return sb.toString();
    }

    @Override
    public double getMonthlyPayment() {
        //math.pow returns the value of argument a raised to the power of argument b
        if (!financeOption) {
            return 0;
        }

        double loanAmount = getTotalPrice();

        if (loanAmount >= 10000){
            double monthlyInterest = interestRateAbove / 12;
            int numberOfPayments = loanTermAbove;
            monthlyPayment = (loanAmount * monthlyInterest) / (1 - Math.pow(1+ monthlyInterest, -numberOfPayments));
            return monthlyPayment;
        } else if (loanAmount < 10000){
            double monthlyInterest = interestRateUnder/ 12;
            int numberOfPayments = loanTermUnder;
            monthlyPayment = (loanAmount * monthlyInterest) / (1 - Math.pow(1+ monthlyInterest, -numberOfPayments));
        } return monthlyPayment;



    }

// payment = (P * (r/n)) / (1 - (1 + (r/n)))^-(t*n)

}
