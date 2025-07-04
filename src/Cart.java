import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public boolean add(Products product, int itemQuantity) {
        try {
            if (itemQuantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive number");
            }
            if (itemQuantity > product.getProductQuantity()) {
                throw new IllegalArgumentException("Not enough stock available for " + product.getProductName());
            }

            //check if product already in cart and update
            for (CartItem item : items) {
                if (item.getProduct().equals(product)) {
                    int newQuantity = item.getItemQuantity() + itemQuantity;
                    if (newQuantity > product.getProductQuantity()) {
                        throw new IllegalArgumentException("Not enough stock available for " + product.getProductName());
                    }
                    items.remove(item);
                    items.add(new CartItem(product, newQuantity));
                    return true;
                }
            }
            //if not already in cart add it
            items.add(new CartItem(product, itemQuantity));
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Add to cart failed: " + e.getMessage());
            return false;
        }
    }

    public List<CartItem> getItems() { return new ArrayList<>(items); }
    public boolean isEmpty() { return items.isEmpty(); }
    public void clearCart() { items.clear(); }
}