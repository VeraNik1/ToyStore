package src;

import java.util.UUID;

public class Customer {
    private String name;
    private double spent;
    private UUID id;

    public Customer(String name, int spent) {
        this.name = name;
        this.spent = spent;
        this.id = UUID.randomUUID();
    }
    public String get_name() {
        return name;
    }
    public double get_spent() {
        return spent;
    }
    public UUID get_id() {
        return id;
    }
    public void set_spent(int newCost) {
        this.spent = newCost;
    }
}
