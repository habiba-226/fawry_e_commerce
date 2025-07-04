public class ProductsNotExpire extends Products{
    private boolean shipping;
    private double weight;

    public ProductsNotExpire(String productName, double productPrice, int productQuantity, boolean shipping, double weight) {
        super(productName, productPrice, productQuantity);
        this.shipping = shipping;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
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
