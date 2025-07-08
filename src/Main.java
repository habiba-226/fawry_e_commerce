import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create products
        Products cheese = new ProductsExpire("Cheese", 100, 10, LocalDate.now().plusDays(5), true, 0.4);
        Products biscuits = new ProductsExpire("Biscuits", 150, 5, LocalDate.now().plusDays(10), true, 0.7);
        Products tv = new ProductsNotExpire("TV", 500, 3, true, 15.0);
        Products mobile = new ProductsNotExpire("Mobile", 800, 2, true, 0.5);
        Products scratchCard = new ProductsNotExpire("Mobile Scratch Card", 50, 20, false, 0);
        Products expiredProduct = new ProductsExpire("Milk", 80, 5, LocalDate.now().minusDays(1), true, 1.0);

        Customer customer = new Customer("Habiba Ahmed", 10000);

        ECommerce ecommerce = new ECommerce();

        //normal checkout
        Cart cart1 = new Cart();
        cart1.add(cheese, 2);
        cart1.add(biscuits, 1);
        cart1.add(scratchCard, 1);
        ecommerce.checkout(customer, cart1);

        //products with shipping
        Cart cart2 = new Cart();
        cart2.add(tv, 1);
        cart2.add(mobile, 1);
        cart2.add(scratchCard, 2);
        ecommerce.checkout(customer, cart2);

        //empty cart
        Cart cart3 = new Cart();
        ecommerce.checkout(customer, cart3);

        //insufficient balance
        Customer customer2 = new Customer("Customer", 100);
        Cart cart4 = new Cart();
        cart4.add(tv, 1);
        ecommerce.checkout(customer2, cart4);

        //out of stock
        Cart cart5 = new Cart();
        cart5.add(cheese, 20);  // Internally prints error if fails


        // expired product
        Cart cart6 = new Cart();
        cart6.add(expiredProduct, 1);
        ecommerce.checkout(customer, cart6);

        //non-shippable items
        Cart cart7 = new Cart();
        cart7.add(scratchCard, 3);
        ecommerce.checkout(customer, cart7);

        //large order
        Cart cart8 = new Cart();
        cart8.add(cheese, 3);
        cart8.add(biscuits, 2);
        cart8.add(tv, 1);
        cart8.add(scratchCard, 5);
        ecommerce.checkout(customer, cart8);

    }
}