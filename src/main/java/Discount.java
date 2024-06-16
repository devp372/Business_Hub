public class Discount {
    private String discountCode;
    private int discountID;
    private boolean isPercentage;
    private int amount;
    private int sellerID;

    public Discount(String discountCode, int discountID, boolean isPercentage, int amount, int sellerID) {
        this.discountCode = discountCode;
        this.discountID = discountID;
        this.isPercentage = isPercentage;
        this.amount = amount;
        this.sellerID = sellerID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public boolean isPercentage() {
        return isPercentage;
    }

    public int getAmount() {
        return amount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public void setPercentage(boolean percentage) {
        isPercentage = percentage;
    }
}
