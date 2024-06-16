import java.util.ArrayList;

public class Buyer {
    private String firstName;
    private String lastName;
    private int sellerId;
    private String customerId;
    private String phoneNumber;
    private String email;
    private ArrayList<Order> order;

    public Buyer(String firstName,String lastName, int sellerId, String customerId, String phoneNumber, String email, ArrayList<Order> order) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.order = order;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setOrder(ArrayList<Order> order) {
        this.order = order;
    }
}
