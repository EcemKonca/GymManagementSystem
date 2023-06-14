public class PaymentHistory {
    private String registeredDate;

    private String billDate;

    private boolean isPayed;

    private double payedPrice;

    private Membership membership; // Composition -> to get price info we need membership object

    public PaymentHistory(Membership membership) { // DEFAULT
        this.registeredDate = String.valueOf(java.time.LocalDate.now());
        this.billDate = String.valueOf(java.time.LocalDate.now());
        this.isPayed = true;
        this.payedPrice = membership.getPrice();
        this.membership = membership;
    }
    public PaymentHistory(int i, int i1, boolean isPayed) {
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String isPayed() {
        if (isPayed) {
            return "Bill payed.";
        }
        else {
            return "Please pay your bill";
        }
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }


    public double getPayedPrice() {
        return payedPrice;
    }

    public void setPayedPrice() {
        payedPrice = membership.getPrice();
    }

    @Override
    public String toString() {
        return "PaymentHistory: " + "registeredDate='" + registeredDate + '\'' + ", billDate='" + billDate + '\'' +
                ", isPayed=" + isPayed + '\'' +
                ", payedPrice=" + payedPrice ;
    }
}
