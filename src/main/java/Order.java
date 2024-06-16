import java.util.ArrayList;

enum orderAccept {
    ACCEPTED, REJECTED;
}

enum orderState {
    PLACED, COMPLETED;
}

public class Order {
    private int buyerId;
    private ArrayList<Product> products;
    private ArrayList<Integer> quantities;
    private double totalPrice;
    private int orderId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNum;
    private orderState state;
    private orderAccept accept;


    public Order(int buyerId, ArrayList<Product> products, ArrayList<Integer> quantities,
                 double totalPrice, int orderId, String firstName, String lastName, String email,
                 String mobileNum, orderState state, orderAccept accept) {
        this.buyerId = buyerId;
        this.products = products;
        this.quantities = quantities;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNum = mobileNum;
        this.state = state;
        this.accept = accept;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public orderAccept getAccept() { return accept; }

    public orderState getState() { return state; }

    public String getFirstName() { return firstName; }

    public String getEmail() { return email; }

    public String getLastName() { return lastName; }

    public String getMobileNum() { return mobileNum; }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setQuantities(ArrayList<Integer> quantities) {
        this.quantities = quantities;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAccept(orderAccept accept) { this.accept = accept; }

    public void setEmail(String email) { this.email = email; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setMobileNum(String mobileNum) { this.mobileNum = mobileNum; }

    public void setState(orderState state) { this.state = state; }
}
