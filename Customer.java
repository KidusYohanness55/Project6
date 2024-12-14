/*
 * Class: CMSC203 
 * Instructor: Gary Thai
 * Description: The Customer class represents a customer with a name and age.
 * Due: 12/13/2024 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Kidus Yohanness
 */

package application;

public class Customer {

    // Instance variables
    private String name;
    private int age;

    /**
     * Parameterized constructor to create a Customer object.
     *
     * @param name The name of the customer.
     * @param age  The age of the customer.
     */
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Copy constructor to create a Customer object by copying another Customer.
     *
     * @param other The Customer object to copy.
     */
    public Customer(Customer other) {
        this.name = other.name;
        this.age = other.age;
    }

    /**
     * Gets the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The new name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the customer.
     *
     * @return The age of the customer.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the customer.
     *
     * @param age The new age of the customer.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Provides a string representation of the Customer object.
     *
     * @return A string in the format "Customer{name='name', age=age}".
     */
    @Override
    public String toString() {
        return "Customer{name='" + name + "', age=" + age + "}";
    }
}
