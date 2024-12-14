/*
 * Class: CMSC203 
 * Instructor: Gary Thai
 * Description: This class Beverage serves as a base class for different types of beverages.
 * Due: 12/13/2024 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Kidus Yohanness
 */

package application;
public abstract class Beverage {

    // Instance variables
    private String bevName;
    private Type type;
    private Size size;

    // Constant attributes
    private static final double BASE_PRICE = 2.0;
    private static final double SIZE_PRICE = 0.5;

    /**
     * Parameterized constructor to create a Beverage object.
     *
     * @param bevName The name of the beverage.
     * @param type    The type of the beverage.
     * @param size    The size of the beverage.
     */
    public Beverage(String bevName, Type type, Size size) {
        this.bevName = bevName;
        this.type = type;
        this.size = size;
    }

    /**
     * Calculates a new price by adding the size price to the base price.
     *
     * @return The calculated price based on the size.
     */
    public double addSizePrice() {
        switch (size) {
            case MEDIUM:
                return BASE_PRICE + SIZE_PRICE;
            case LARGE:
                return BASE_PRICE + 2 * SIZE_PRICE;
            default: // SMALL
                return BASE_PRICE;
        }
    }

    /**
     * Abstract method to calculate the beverage price.
     *
     * @return The calculated price of the beverage.
     */
    public abstract double calcPrice();

    /**
     * Gets the base price.
     *
     * @return The base price of the beverage.
     */
    public double getBasePrice() {
        return BASE_PRICE;
    }

    /**
     * Gets the name of the beverage.
     *
     * @return The beverage name.
     */
    public String getBevName() {
        return bevName;
    }

    /**
     * Sets the name of the beverage.
     *
     * @param bevName The new beverage name.
     */
    public void setBevName(String bevName) {
        this.bevName = bevName;
    }

    /**
     * Gets the type of the beverage.
     *
     * @return The beverage type.
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of the beverage.
     *
     * @param type The new beverage type.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets the size of the beverage.
     *
     * @return The beverage size.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the size of the beverage.
     *
     * @param size The new beverage size.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Provides a string representation of the Beverage object.
     *
     * @return A string in the format "bevName, size".
     */
    @Override
    public String toString() {
        return bevName + ", " + size;
    }

    /**
     * Checks if this Beverage is equal to another Beverage.
     *
     * @param anotherBev The object to compare with this Beverage.
     * @return True if the beverages are equal based on name, type, and size; otherwise false.
     */
    @Override
    public boolean equals(Object anotherBev) {
        if (this == anotherBev) return true;
        if (anotherBev == null || getClass() != anotherBev.getClass()) return false;
        Beverage beverage = (Beverage) anotherBev;
        return bevName.equals(beverage.bevName) &&
               type == beverage.type &&
               size == beverage.size;
    }
}
