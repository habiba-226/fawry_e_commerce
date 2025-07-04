import java.util.*;

abstract class Products {
    private String productName;
    private double productPrice;
    private int productQuantity;

    public Products(String productName, double productPrice, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getProductName() { return productName; }
    public double getProductPrice() {  return productPrice;  }
    public int getProductQuantity() { return productQuantity; }

    public abstract boolean isExpired();
    public abstract boolean shipping();
    public abstract double getWeight(); //no weight -> return 0

    public void reduceQuantity(int amount) {
        if (amount > productQuantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        this.productQuantity -= amount;
    }
}
