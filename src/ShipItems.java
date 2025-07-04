import java.util.*;

public class ShipItems implements ShippingItemsInterface {
    private String name;
    private double weight;

    public ShipItems(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getWeight() { return weight; }
}


