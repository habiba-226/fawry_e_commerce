import java.util.*;

public class ShippingService {
    double totalWeight = 0;
    private static final double shippingRate = 50; //50 EGP/kg

    public double calculateShippingFee(List<ShippingItemsInterface> items) {

        if (items.isEmpty()) {
            return 0;
        }
        for (ShippingItemsInterface item : items) {
            totalWeight += item.getWeight();
        }
        return Math.ceil(totalWeight * shippingRate); //round up
    }

    public void processShipment(List<ShippingItemsInterface> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");

        List<GroupItems> groupedItems = new ArrayList<>();

        for (ShippingItemsInterface item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            //look for the grouped items
            GroupItems match = null;
            for (GroupItems gi : groupedItems) {
                if (gi.name.equals(name)) {
                    match = gi;
                    break;
                }
            }

            if (match != null) {
                match.addWeight(weight);
            } else {
                groupedItems.add(new GroupItems(name, weight));
            }
        }

        double totalWeight = 0;
        for (GroupItems gi : groupedItems) {
            System.out.printf("%dx %s %.0fg%n", gi.count, gi.name, gi.totalWeight * 1000);
            totalWeight += gi.totalWeight;
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
        System.out.println();
    }
}
