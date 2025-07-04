import java.util.*;
public class ECommerce {
    private ShippingService shippingService = new ShippingService();

    public void checkout(Customer customer, Cart cart) {
        try {
            //is cart empty
            if (cart.isEmpty()) {
                throw new IllegalStateException("Cart is empty");
            }
            //products not expired and in stock
            for (CartItem item : cart.getItems()) {
                Products product = item.getProduct();
                if (product.isExpired()) {
                    throw new IllegalStateException("Product " + product.getProductName() + " is expired");
                }
                if (item.getItemQuantity() > product.getProductQuantity()) {
                    throw new IllegalStateException("Product " + product.getProductName() + " is out of stock");
                }
            }

            double subtotal = 0;
            for (CartItem item : cart.getItems()) {
                subtotal += item.getTotalPrice();
            }

            //items that will need shipping
            List<ShippingItemsInterface> shippableItems = new ArrayList<>();
            for (CartItem item : cart.getItems()) {
                Products product = item.getProduct();
                if (product.shipping()) {
                    for (int i = 0; i < item.getItemQuantity(); i++) {
                        shippableItems.add(new ShipItems(product.getProductName(), product.getWeight()));
                    }
                }
            }


            double shippingFee = shippingService.calculateShippingFee(shippableItems);
            double totalAmount = subtotal + shippingFee;

            //calculate customer balance
            if (customer.getBalance() < totalAmount) {
                throw new IllegalStateException("Customer's balance is insufficient");
            }

            //calculate shipping fee
            if (!shippableItems.isEmpty()) {
                shippingService.processShipment(shippableItems);
            }

            customer.reduceBalance(totalAmount);
            for (CartItem item : cart.getItems()) {
                item.getProduct().reduceQuantity(item.getItemQuantity());
            }

            System.out.println("** Checkout receipt **");
            for (CartItem item : cart.getItems()) {
                System.out.printf("%dx %s %.0f%n", item.getItemQuantity(), item.getProduct().getProductName(), item.getTotalPrice());}
            System.out.println("----------------------");
            System.out.printf("Subtotal %.0f%n", subtotal);
            System.out.printf("Shipping %.0f%n", shippingFee);
            System.out.printf("Amount %.0f%n", totalAmount);
            System.out.printf("Customer balance after payment: $%.2f%n", customer.getBalance());
            System.out.println();

            cart.clearCart();

        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
            System.out.println();
        }
    }
}
