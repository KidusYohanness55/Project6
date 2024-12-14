/*
 * Class: CMSC203 
 * Instructor: Gary Thai
 * Description: This class represents an Alcohol beverage, which inherits from the Beverage class.
 * Due: 12/13/2024 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Kidus Yohanness
 */

package application;

public class Alcohol extends Beverage {

    // Instance variable
    private boolean isWeekend;

    // Constant
    private static final double WEEKEND_COST = 0.6;

    /**
     * Parameterized constructor to create an Alcohol object.
     *
     * @param bevName   The name of the alcohol.
     * @param size      The size of the alcohol.
     * @param isWeekend Indicates if the drink is offered on the weekend.
     */
    public Alcohol(String bevName, Size size, boolean isWeekend) {
        super(bevName, Type.ALCOHOL, size);
        this.isWeekend = isWeekend;
    }

    /**
     * Calculates the price of the alcohol beverage.
     *
     * @return The total price of the alcohol beverage.
     */
    @Override
    public double calcPrice() {
        double price = addSizePrice();
        if (isWeekend) {
            price += WEEKEND_COST;
        }
        return price;
    }

    /**
     * Checks if the alcohol is offered on the weekend.
     *
     * @return True if offered on the weekend, otherwise false.
     */
    public boolean isWeekend() {
        return isWeekend;
    }

    /**
     * Sets whether or not the alcohol is offered on the weekend.
     *
     * @param isWeekend True to indicate the drink is offered on the weekend, otherwise false.
     */
    public void setWeekend(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    /**
     * Provides a string representation of the Alcohol object.
     *
     * @return A string in the format "name, size, weekend: yes/no, price: X".
     */
    @Override
    public String toString() {
        return super.getBevName() + ", " + getSize() + ", weekend: " + (isWeekend ? "yes" : "no") + ", price: " + calcPrice();
    }

    /**
     * Checks if this Alcohol object is equal to another object.
     *
     * @param anotherBev The object to compare with this Alcohol object.
     * @return True if the Alcohol objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object anotherBev) {
        if (!super.equals(anotherBev)) return false;
        if (!(anotherBev instanceof Alcohol)) return false;
        Alcohol alcohol = (Alcohol) anotherBev;
        return isWeekend == alcohol.isWeekend;
    }
}
