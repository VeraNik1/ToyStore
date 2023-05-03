package src;

public class Toy {
    private int id;
    private static int num = 1;
    private String name;
    private int amount;
    private int weight;

    public Toy(String name, int amount, int weight) {
        this.id = num++;
        this.name = name;
        this.amount = amount;
        this.weight = weight;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getAmount() {
        return this.amount;
    }

    public int getWeight() {
        return this.weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void decreaseAmount() {
        --this.amount;
    }

    public String toString() {
        int var10000 = this.getId();
        return "ID: " + var10000 + ", Name: " + this.getName() + ", Amount: " + this.getAmount()
                + ", Weight " + this.getWeight();
    }
}
