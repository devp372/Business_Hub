import java.util.ArrayList;

enum inStock {
    IN_STOCK, OUT_OF_STOCK;
}
public class Product {

    private String name;
    private String description;
    private String category;
    private boolean hasSubCategories;
    private double price;
    private int productID;
    private int unitsSold;
    private inStock in_Stock;
    private ArrayList<Product> subCategories;

    public Product(String name, String description, String category, boolean hasSubCategories, double price, int productID, inStock in_Stock, ArrayList<Product> subCategories) {
        this.subCategories = subCategories;
        this.name = name;
        this.description = description;
        this.category = category;
        this.hasSubCategories = hasSubCategories;
        this.price = price;
        this.productID = productID;
        this.in_Stock = in_Stock;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public boolean isHasSubCategories() {
        return hasSubCategories;
    }

    public inStock getIn_Stock() {
        return in_Stock;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getproductID() {
        return productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHasSubCategories(boolean hasSubCategories) {
        this.hasSubCategories = hasSubCategories;
    }

    public void setIn_Stock(inStock in_Stock) {
        this.in_Stock = in_Stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setproductID(int productID) {
        this.productID = productID;
    }

    public ArrayList<Product> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<Product> subCategories) {
        this.subCategories = subCategories;
    }
}
