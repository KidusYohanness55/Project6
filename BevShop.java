 /*
 * Class: CMSC203 
 * Instructor: Gary Thai
 * Description: This class implements the BevShopInterface and manages beverage orders for a shop. 
 * Due: 12/13/2024 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Kidus Yohanness
 */

package application;

import java.util.ArrayList;
import java.util.Collections;

public class BevShop implements BevShopInterface {

    // Tracks the number of alcoholic drinks in the current order
    private int numOfAlcoholDrink;

    // List to store all orders placed in the shop
    private ArrayList<Order> orders;

    // Current order being processed
    private Order currentOrder;

    /**
     * Default constructor: Initializes the BevShop with no orders and resets the alcohol drink counter.
     */
    public BevShop() {
        this.orders = new ArrayList<>();
        this.numOfAlcoholDrink = 0;
    }

    /**
     * Checks if the provided time is valid for placing an order.
     * 
     * @param time The time of the order in 24-hour format.
     * @return True if the time is within the shop's operating hours, otherwise false.
     */
    @Override
    public boolean isValidTime(int time) {
        if (time >= MIN_TIME && time <= MAX_TIME)
        	return true;
        else
        	return false;
    }

    /**
     * Retrieves the maximum number of fruits allowed for a smoothie.
     * 
     * @return The maximum number of fruits allowed.
     */
    @Override
    public int getMaxNumOfFruits() {
        return MAX_FRUIT;
    }

    /**
     * Retrieves the minimum age required to order alcoholic beverages.
     * 
     * @return The minimum age for alcohol purchases.
     */
    @Override
    public int getMinAgeForAlcohol() {
        return MIN_AGE_FOR_ALCOHOL;
    }

    /**
     * Checks if the number of fruits in a smoothie exceeds the maximum allowed.
     * 
     * @param numOfFruits The number of fruits in the smoothie.
     * @return True if the number exceeds the limit, otherwise false.
     */
    @Override
    public boolean isMaxFruit(int numOfFruits) {
        return numOfFruits > MAX_FRUIT;
    }

    /**
     * Retrieves the maximum number of alcoholic drinks allowed in a single order.
     * 
     * @return The maximum number of alcoholic drinks per order.
     */
    @Override
    public int getMaxOrderForAlcohol() {
        return MAX_ORDER_FOR_ALCOHOL;
    }

    /**
     * Checks if the customer is eligible to order more alcoholic beverages.
     * 
     * @return True if the customer can order more alcohol, otherwise false.
     */
    @Override
    public boolean isEligibleForMore() {
        return numOfAlcoholDrink < MAX_ORDER_FOR_ALCOHOL;
    }

    /**
     * Retrieves the current count of alcoholic drinks in the order.
     * 
     * @return The number of alcoholic beverages in the current order.
     */
    @Override
    public int getNumOfAlcoholDrink() {
        return numOfAlcoholDrink;
    }

    /**
     * Checks if a customer's age meets the minimum requirement for ordering alcohol.
     * 
     * @param age The age of the customer.
     * @return True if the age is valid, otherwise false.
     */
    @Override
    public boolean isValidAge(int age) {
        return age >= MIN_AGE_FOR_ALCOHOL;
    }

    /**
     * Starts a new order for a customer.
     * 
     * @param time         The time of the order in 24-hour format.
     * @param day          The day of the week for the order.
     * @param customerName The name of the customer.
     * @param customerAge  The age of the customer.
     */
    @Override
    public void startNewOrder(int time, Day day, String customerName, int customerAge) {
        if (!isValidTime(time)) {
            throw new IllegalArgumentException("Invalid order time. Must be between " + MIN_TIME + " and " + MAX_TIME);
        }
        this.currentOrder = new Order(time, day, new Customer(customerName, customerAge));
        this.orders.add(currentOrder);
        this.numOfAlcoholDrink = 0;
    }

    /**
     * Processes a coffee order for the current order.
     * 
     * @param bevName    The name of the coffee beverage.
     * @param size       The size of the coffee beverage.
     * @param extraShot  Indicates if an extra shot is added.
     * @param extraSyrup Indicates if extra syrup is added.
     */
    @Override
    public void processCoffeeOrder(String bevName, Size size, boolean extraShot, boolean extraSyrup) {
        if (currentOrder == null) {
            throw new IllegalStateException("No current order available to process.");
        }
        currentOrder.addNewBeverage(bevName, size, extraShot, extraSyrup);
    }

    /**
     * Processes an alcohol order for the current order.
     * 
     * @param bevName The name of the alcoholic beverage.
     * @param size    The size of the alcoholic beverage.
     */
    @Override
    public void processAlcoholOrder(String bevName, Size size) {
        if (currentOrder == null) {
            throw new IllegalStateException("No current order available to process.");
        }
        if (!isEligibleForMore()) {
            throw new IllegalArgumentException("Cannot add more alcohol beverages to this order.");
        }
        currentOrder.addNewBeverage(bevName, size);
        numOfAlcoholDrink++;
    }

    /**
     * Processes a smoothie order for the current order.
     * 
     * @param bevName     The name of the smoothie.
     * @param size        The size of the smoothie.
     * @param numOfFruits The number of fruits in the smoothie.
     * @param addProtein  Indicates if protein powder is added.
     */
    @Override
    public void processSmoothieOrder(String bevName, Size size, int numOfFruits, boolean addProtein) {
        if (currentOrder == null) {
            throw new IllegalStateException("No current order available to process.");
        }
        if (isMaxFruit(numOfFruits)) {
            throw new IllegalArgumentException("Cannot add more fruits than the maximum allowed.");
        }
        currentOrder.addNewBeverage(bevName, size, numOfFruits, addProtein);
    }

    /**
     * Finds the index of an order by its order number.
     * 
     * @param orderNo The order number to find.
     * @return The index of the order, or -1 if not found.
     */
    @Override
    public int findOrder(int orderNo) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNo() == orderNo) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Calculates the total price of an order based on the order number.
     *
     * @param orderNo The order number to find.
     * @return The total price of the order.
     * @throws IllegalArgumentException If the order with the given number is not found.
     */
    @Override
    public double totalOrderPrice(int orderNo) {
        int index = findOrder(orderNo);
        if (index == -1) {
            throw new IllegalArgumentException("Order not found with number: " + orderNo);
        }
        return orders.get(index).calcOrderTotal();
    }

    /**
     * Calculates the total sales from all orders for the month.
     *
     * @return The total revenue from all orders.
     */
    @Override
    public double totalMonthlySale() {
        double total = 0;
        for (Order order : orders) {
            total += order.calcOrderTotal();
        }
        return total;
    }

    /**
     * Retrieves the total number of orders placed in the current month.
     *
     * @return The total number of orders.
     */
    @Override
    public int totalNumOfMonthlyOrders() {
        return orders.size();
    }

    /**
     * Retrieves the current active order.
     *
     * @return The current order being processed.
     */
    @Override
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Retrieves the order at a specific index in the list of orders.
     *
     * @param index The index of the order in the list.
     * @return The order object at the specified index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    @Override
    public Order getOrderAtIndex(int index) {
        if (index < 0 || index >= orders.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return orders.get(index);
    }

    /**
     * Sorts the list of orders in ascending order based on their order numbers.
     */
    @Override
    public void sortOrders() {
        Collections.sort(orders);
    }

    /**
     * Provides a string representation of all orders in the shop and the total monthly sales.
     *
     * @return A formatted string containing all orders and the total sales.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order.toString()).append("\n");
        }
        sb.append("Total Monthly Sale: $").append(String.format("%.2f", totalMonthlySale()));
        return sb.toString();
    }
}
