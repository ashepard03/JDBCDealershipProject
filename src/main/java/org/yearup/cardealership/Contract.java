package org.yearup.cardealership;

public abstract class Contract {
    private String contractDate;
    private String cxName;
    private String cxEmail;
    Vehicle vehicleSold;

    public Contract(String contractDate, String cxName, String cxEmail, Vehicle vehicleSold) {
        this.contractDate = contractDate;
        this.cxName = cxName;
        this.cxEmail = cxEmail;
        this.vehicleSold = vehicleSold;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCxName() {
        return cxName;
    }

    public void setCxName(String cxName) {
        this.cxName = cxName;
    }

    public String getCxEmail() {
        return cxEmail;
    }

    public void setCxEmail(String cxEmail) {
        this.cxEmail = cxEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public abstract String getPersistenceString();
}
