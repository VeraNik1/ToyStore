package src;
import java.util.*;
public class ToyStore {
    ArrayList<Toy> toys_list;
    HashMap<String, Integer> count = new HashMap<>();
    HashMap<String, Integer> weights = new HashMap<>();

    public ToyStore() {
        this.clear();
    }

    public void clear() {
        toys_list = new ArrayList<>();
        count = new HashMap<>();
        weights = new HashMap<>();
    }

    public Toy get_toy_by_id(int id) {
        for (Toy toy : this.toys_list) {
            if (toy.getId() == id) {
                return toy;
            }
        }
        return null;
    }
    public void add(Toy toy) {
        toys_list.add(toy);
        Collections.shuffle(toys_list);
        count.merge(toy.getName(), 1, Integer::sum);
        weights.put(toy.getName(), toy.getWeight());
    }
    public void addAmount(int id, int amount) {
        Toy toy = this.get_toy_by_id(id);
        toy.setAmount(toy.getAmount() + amount);
    }
    public void decreaseAmount(int id) {
        get_toy_by_id(id).decreaseAmount();
    }
    public int size() {
        int result = 0;
        for (Toy toy:
             this.toys_list) {
            result += toy.getAmount();
        }
        return result;
    }
    public int get_ID_by_weight(int marker, ToysPrizeList toysPrizeList) {
        for (Toy toy : toys_list) {
            int weight = toy.getWeight();
            if (weight < marker) continue;
            int id = toy.getId();
            if (toysPrizeList == null || toysPrizeList.count_equal_toys(toy)
                    < toy.getAmount()) return id;
        }
        return 0;
    }
    public void update_statistic() {
        this.weights = new HashMap<>();
        this.count = new HashMap<>();
        for (Toy toy : toys_list) {
            count.merge(toy.getName(), 1, Integer::sum);
            weights.put(toy.getName(), toy.getWeight());
        }
    }
    public ArrayList<Toy> get_toys_list() {
        return this.toys_list;
    }
    public static void add_toys_to_store(ToyStore store) {
        System.out.println("Add toys available for the lottery");
        System.out.println("Press enter to cancel adding");
        Toy temp;
        String name = null;
        int weight = 0;
        int amount = 0;
        try {
            name = KeyScanner.getText("Toy's name: ");
            if (name.isEmpty() || name.isBlank()) {
                throw new Exception();
            }

            weight = Integer.parseInt(KeyScanner.getText("Weight in lottery store %: "));
            if (weight > 100 || weight <= 0) {
                throw new NumberFormatException();
            }
            amount = Integer.parseInt(KeyScanner.getText("amount: "));
            if (amount <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input. Canceled");
        } catch (Exception e) {
            System.out.println("Canceled");
            return;
        }
        store.add(new Toy(name, amount, weight));

        System.out.println("Toys have been added");
        System.out.printf("Amount of toys available for lottery: %d \n", store.size());

    }
    public static void add_toys_by_id(ToyStore store) {
        System.out.println("Add toys to the store by id");
        System.out.println("Press enter to cancel adding");
        int id = 0;
        int amount_add = 0;
        try {
            id = Integer.parseInt(KeyScanner.getText("Toy's ID: "));
            if (id == 0) {
                throw new NumberFormatException();
            }
            if (store.get_toy_by_id(id) != null) {
                amount_add = Integer.parseInt(KeyScanner.getText("Enter amount of toys you want to add: "));
                if (amount_add <= 0) {
                    throw new NumberFormatException();
                }
                else{
                    store.addAmount(id, amount_add);
                }
            } else {
                System.out.println("this id hasn't been found");
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input. Canceled");
            return;
        } catch (Exception e) {
            System.out.println("Canceled");
            return;
        }

        System.out.println("Toys have been added");
        System.out.printf("Amount of toys available for lottery: %d \n", store.size());
    }
    public static void edit_Toys_weight(ToyStore store) {
        System.out.println("Change \"weight\" for the toy");
        viewStore(store);
        String text = KeyScanner.getText("Choose toy ID for changing 'weight' parameter " +
                "or press 0 / Enter to cancel: ");
        if ("0".equals(text)) return;
        int num;
        try {
            num = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input. Canceled");
            return;
        }
        if (num > store.toys_list.size()) {
            System.out.printf("There is no ID %d in the store. Canceled\n", num);
            return;
        }
        Toy toy = store.get_toy_by_id(num);
        LotteryFunction.view_toy(toy);
        int new_weight;
        try {
            new_weight = Integer.parseInt(KeyScanner.getText("Enter new weight for the toy: "));
            if (new_weight > 100 || new_weight < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input.\nYou need to choose numbers from 1 to 100.\nCanceled");
            return;
        }
        String toy_name = toy.getName();
        for (Toy _toy: store.get_toys_list()) {
            if (toy_name.equals(_toy.getName())){
                _toy.setWeight(new_weight);
            }
        }
        store.update_statistic();
        System.out.println("Successfully changed");
        viewStore(store);
    }
    public static void viewStore(ToyStore store) {
        System.out.printf("Amount of toys available for lottery: %d\n", store.size());
        if (store.size() < 1) {
            return;
        }
        ArrayList<Toy> output = store.get_toys_list();
        int n = 1;
        for (Toy toy: output) {
            System.out.printf("%d - %s\n", n++,toy);
        }
    }
}



