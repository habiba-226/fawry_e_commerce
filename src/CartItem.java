public class CartItem {
    private Products product;
    private int itemQuantity; // how many of the single product

    public CartItem(Products product, int itemQuantity) {
        this.product = product;
        this.itemQuantity = itemQuantity;
    }

    public Products getProduct() { return product; }
    public int getItemQuantity() { return itemQuantity; }
    public double getTotalPrice() { return product.getProductPrice() * itemQuantity; }
}