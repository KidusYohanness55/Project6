/*
 * Class: CMSC203 
 * Instructor: Gary Thai
 * Description: This class represents a Coffee beverage, which inherits from the Beverage class.
 * Due: 12/13/2024 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Kidus Yohanness
 */

package application;

public class Coffee extends Beverage {

    // Instance variables
    private boolean extraShot;
    private boolean extraSyrup;

    // Constants
    private static final double EXTRA_SHOT_COST = 0.5;
    private static final double EXTRA_SYRUP_COST = 0.5;

    /**
     * Parameterized constructor to create a Coffee object.
     *
     * @param bevName   The name of the coffee.
     * @param size      The size of the coffee.
     * @param extraShot Indicates if extra shot is added.
     * @param extraSyrup Indicates if extra syrup is added.
     */
    public Coffee(String bevName, Size size, boolean extraShot, boolean extraSyrup) {
        super(bevName, Type.COFFEE, size);
        this.extraShot = extraShot;
        this.extraSyrup = extraSyrup;
    }

    /**
     * Calculates the price of the coffee.
     *
     * @return The total price of the coffee.
     */
    @Override
    public double calcPrice() {
        double price = addSizePrice();
        if (extraShot) {
            price += EXTRA_SHOT_COST;
        }
        if (extraSyrup) {
            price += EXTRA_SYRUP_COST;
        }
        return price;
    }

    /**
     * Indicates whether or not an extra shot is added.
     *
     * @return True if extra shot is added, otherwise false.
     */
    public boolean getExtraShot() {
        return extraShot;
    }

    /**
     * Sets whether or not an extra shot is added.
     *
     * @param extraShot True to add extra shot, otherwise false.
     */
    public void setExtraShot(boolean extraShot) {
        this.extraShot = extraShot;
    }

    /**
     * Indicates whether or not extra syrup is added.
     *
     * @return True if extra syrup is added, otherwise false.
     */
    public boolean getExtraSyrup() {
        return extraSyrup;
    }

    /**
     * Sets whether or not extra syrup is added.
     *
     * @param extraSyrup True to add extra syrup, otherwise false.
     */
    public void setExtraSyrup(boolean extraSyrup) {
        this.extraSyrup = extraSyrup;
    }

    /**
     * Provides a string representation of the Coffee object.
     *
     * @return A string in the format "name, size, extra shot: yes/no, extra syrup: yes/no, price".
     */
    @Override
    public String toString() {
        return super.getBevName() + ", " + getSize() + ", extra shot: " + (extraShot ? "yes" : "no") + ", extra syrup: " + (extraSyrup ? "yes" : "no") + ", price: " + calcPrice();
    }

    /**
     * Checks if this Coffee object is equal to another object.
     *
     * @param anotherBev The object to compare with this Coffee object.
     * @return True if the Coffee objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object anotherBev) {
        if (!super.equals(anotherBev)) return false;
        if (!(anotherBev instanceof Coffee)) return false;
        Coffee coffee = (Coffee) anotherBev;
        return extraShot == coffee.extraShot && extraSyrup == coffee.extraSyrup;
    }
}
