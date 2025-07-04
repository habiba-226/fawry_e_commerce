public class GroupItems {
    String name;
    double totalWeight;
    int count;

    public GroupItems(String name, double weight) {
        this.name = name;
        this.totalWeight = weight;
        this.count = 1;
    }

    void addWeight(double weight) {
        this.totalWeight += weight;
        this.count++;
    }
}