/*
 * Class: CMSC203 
 * Instructor: Gary Thai
 * Description: The Order class represents a customer's order in the beverage shop.
 * Due: 12/13/2024 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Kidus Yohanness
 */

package application;
import java.util.ArrayList;
import java.util.Random;

public class Order implements OrderInterface, Comparable<Order> {

    // Instance variables
    private int orderNumber; // Unique order number
    private int orderTime;   // The time the order was placed
    private Day orderDay;    // The day the order was placed
    private Customer customer; // The customer who placed the order
    private ArrayList<Beverage> beverages; // List of beverages in the order

    /**
     * Parameterized constructor to create an Order object.
     *
     * @param orderTime The time of the order.
     * @param orderDay  The day of the order.
     * @param customer  The customer placing the order.
     */
    public Order(int orderTime, Day orderDay, Customer customer) {
        this.orderNumber = generateOrder();
        this.orderTime = orderTime;
        this.orderDay = orderDay;
        this.customer = new Customer(customer); // Deep copy of the customer
        this.beverages = new ArrayList<>();
    }

    /**
     * Generates a random order number between 10000 and 90000.
     *
     * @return A randomly generated order number.
     */
    private int generateOrder() {
        Random random = new Random();
        return 10000 + random.nextInt(80001);
    }

    /**
     * Adds a new alcohol beverage to the order.
     *
     * @param bevName The name of the beverage.
     * @param size    The size of the beverage.
     */
    @Override
    public void addNewBeverage(String bevName, Size size) {
        beverages.add(new Alcohol(bevName, size, isWeekend()));
    }

    /**
     * Adds a new coffee beverage to the order.
     *
     * @param bevName   The name of the coffee.
     * @param size      The size of the coffee.
     * @param extraShot True if an extra shot is added, false otherwise.
     * @param extraSyrup True if extra syrup is added, false otherwise.
     */
    @Override
    public void addNewBeverage(String bevName, Size size, boolean extraShot, boolean extraSyrup) {
        beverages.add(new Coffee(bevName, size, extraShot, extraSyrup));
    }

    /**
     * Adds a new smoothie beverage to the order.
     *
     * @param bevName     The name of the smoothie.
     * @param size        The size of the smoothie.
     * @param numOfFruits The number of fruits in the smoothie.
     * @param addProtein  True if protein is added, false otherwise.
     */
    @Override
    public void addNewBeverage(String bevName, Size size, int numOfFruits, boolean addProtein) {
        beverages.add(new Smoothie(bevName, size, numOfFruits, addProtein));
    }

    /**
     * Calculates the total price of all beverages in the order.
     *
     * @return The total price of the order.
     */
    @Override
    public double calcOrderTotal() {
        double total = 0.0;
        for (Beverage beverage : beverages) {
            total += beverage.calcPrice();
        }
        return total;
    }

    /**
     * Finds the number of beverages in the order of a specific type.
     *
     * @param type The type of beverage (e.g., COFFEE, SMOOTHIE, ALCOHOL).
     * @return The count of beverages of the specified type.
     */
    @Override
    public int findNumOfBeveType(Type type) {
        int count = 0;
        for (Beverage beverage : beverages) {
            if (beverage.getType() == type) {
                count++;
            }
        }
        return count;
    }

    /**
     * Retrieves a beverage from the order by its index.
     *
     * @param itemNo The index of the beverage in the list.
     * @return The beverage object, or null if the index is invalid.
     */
    @Override
    public Beverage getBeverage(int itemNo) {
        if (itemNo >= 0 && itemNo < beverages.size()) {
            return beverages.get(itemNo); // Shallow copy as specified
        }
        return null;
    }

    /**
     * Checks if the order was placed on a weekend.
     *
     * @return True if the order day is Saturday or Sunday, false otherwise.
     */
    @Override
    public boolean isWeekend() {
        return orderDay == Day.SATURDAY || orderDay == Day.SUNDAY;
    }

    /**
     * Retrieves the unique order number.
     *
     * @return The order number.
     */
    public int getOrderNo() {
        return orderNumber;
    }

    /**
     * Retrieves the time the order was placed.
     *
     * @return The order time.
     */
    public int getOrderTime() {
        return orderTime;
    }

    /**
     * Retrieves the day the order was placed.
     *
     * @return The order day.
     */
    public Day getOrderDay() {
        return orderDay;
    }

    /**
     * Retrieves the customer who placed the order (deep copy).
     *
     * @return The customer object.
     */
    public Customer getCustomer() {
        return new Customer(customer); // Deep copy of the customer
    }

    /**
     * Retrieves the total number of beverages in the order.
     *
     * @return The total number of beverages.
     */
    public int getTotalItems() {
        return beverages.size();
    }

    /**
     * Compares this order to another order based on their order numbers.
     *
     * @param anotherOrder The other order to compare.
     * @return A negative value, zero, or a positive value if this order is less than, 
     * equal to, or greater than the other order.
     */
    @Override
    public int compareTo(Order anotherOrder) {
        return Integer.compare(this.orderNumber, anotherOrder.getOrderNo());
    }

    /**
     * Returns a string representation of the order.
     *
     * @return A string containing the order details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Number: ").append(orderNumber).append("\n");
        sb.append("Order Time: ").append(orderTime).append("\n");
        sb.append("Order Day: ").append(orderDay).append("\n");
        sb.append("Customer: ").append(customer.toString()).append("\n");
        sb.append("Beverages: \n");
        for (Beverage beverage : beverages) {
            sb.append(beverage.toString()).append("\n");
        }
        return sb.toString();
    }
}
