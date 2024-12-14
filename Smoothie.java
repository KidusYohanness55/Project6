/*
 * Class: CMSC203 
 * Instructor: Gary Thai
 * Description: This class represents a Smoothie beverage, which inherits from the Beverage class.
 * Due: 12/13/2024 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Kidus Yohanness
 */

package application;

public class Smoothie extends Beverage {

    // Instance variables
    private int numOfFruits;
    private boolean addProtein;

    // Constants
    private static final double PROTEIN_COST = 1.5;
    private static final double FRUIT_COST = 0.5;

    /**
     * Parameterized constructor to create a Smoothie object.
     *
     * @param bevName    The name of the smoothie.
     * @param size       The size of the smoothie.
     * @param numOfFruits The number of fruits in the smoothie.
     * @param addProtein Indicates if protein powder is added.
     */
    public Smoothie(String bevName, Size size, int numOfFruits, boolean addProtein) {
        super(bevName, Type.SMOOTHIE, size);
        this.numOfFruits = numOfFruits;
        this.addProtein = addProtein;
    }

    /**
     * Calculates the price of the smoothie.
     *
     * @return The total price of the smoothie.
     */
    @Override
    public double calcPrice() {
        double price = addSizePrice();
        price += numOfFruits * FRUIT_COST;
        if (addProtein) {
            price += PROTEIN_COST;
        }
        return price;
    }

    /**
     * Gets the number of fruits in the smoothie.
     *
     * @return The number of fruits.
     */
    public int getNumOfFruits() {
        return numOfFruits;
    }

    /**
     * Sets the number of fruits in the smoothie.
     *
     * @param numOfFruits The new number of fruits.
     */
    public void setNumOfFruits(int numOfFruits) {
        this.numOfFruits = numOfFruits;
    }

    /**
     * Indicates whether or not protein powder is added.
     *
     * @return True if protein is added, otherwise false.
     */
    public boolean getAddProtein() {
        return addProtein;
    }

    /**
     * Sets whether or not protein powder is added.
     *
     * @param addProtein True to add protein powder, otherwise false.
     */
    public void setAddProtein(boolean addProtein) {
        this.addProtein = addProtein;
    }

    /**
     * Provides a string representation of the Smoothie object.
     *
     * @return A string in the format "name, size, protein added: yes/no, number of fruits: X, price: Y".
     */
    @Override
    public String toString() {
        return super.getBevName() + ", " + getSize() + ", protein added: " + (addProtein ? "yes" : "no") + ", number of fruits: " + numOfFruits + ", price: " + calcPrice();
    }

    /**
     * Checks if this Smoothie object is equal to another object.
     *
     * @param anotherBev The object to compare with this Smoothie object.
     * @return True if the Smoothie objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object anotherBev) {
        if (!super.equals(anotherBev)) return false;
        if (!(anotherBev instanceof Smoothie)) return false;
        Smoothie smoothie = (Smoothie) anotherBev;
        return numOfFruits == smoothie.numOfFruits && addProtein == smoothie.addProtein;
    }
}
