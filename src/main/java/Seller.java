import java.util.ArrayList;

public class Seller {
    private int sellerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String appName;
    private ArrayList<Product> items;
    private String Description;
    private String InstaHandle;
    private String FbHandle;

    public Seller(int sellerID, String firstName, String lastName,
                  //String about,
                  String email, String phoneNumber, String appName, String Description) {
        this.sellerID = sellerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.appName = appName;
        this.Description = Description;
        this.items = new ArrayList<>();
        this.InstaHandle = null;
        this.FbHandle = null;
    }



    public ArrayList<Product> getItems() {
        return items;
    }

    public String getDescription() {
        return Description;
    }

    public String getAppName() {
        return appName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInstaHandle() {
        return InstaHandle;
    }

    public void setInstaHandle(String instaHandle) {
        InstaHandle = instaHandle;
    }

    public String getFbHandle() {
        return FbHandle;
    }

    public void setFbHandle(String fbHandle) {
        FbHandle = fbHandle;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDescription(String about) {
        this.Description = about;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setItems(ArrayList<Product> items) {
        this.items = items;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
}
