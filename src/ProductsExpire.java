import java.time.LocalDate;

public class ProductsExpire extends Products {
    private LocalDate expiryDate;
    private boolean shipping;
    private double weight;

    public ProductsExpire(String productName, double productPrice, int productQuantity, LocalDate expiryDate, boolean shipping, double weight) {
        super(productName, productPrice, productQuantity);
        this.expiryDate = expiryDate;
        this.shipping = shipping;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        if (expiryDate.isBefore(LocalDate.now())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean shipping() {
        return shipping;
    }

    @Override
    public double getWeight() {
        return shipping ? weight : 0;
    }
}
