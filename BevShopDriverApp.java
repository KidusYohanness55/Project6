 /*
 * Class: CMSC203 
 * Instructor: Gary Thai
 * Description: This class is the driver application for the BevShop system.
 * Due: 12/13/2024 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or   
 * any source. I have not given my code to any student.
 * Print your Name here: Kidus Yohanness
 */

package application;

import java.util.Scanner;

public class BevShopDriverApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BevShop bevShop = new BevShop();

        // Inform the user of restrictions and rules for ordering alcohol
        System.out.println("The current order in process can have at most 3 alcoholic beverages.");
        System.out.println("The minimum age to order alcohol drink is 21");

        while (true) { // Main loop for processing orders
            System.out.println("Start please a new order:");
            System.out.println("Your Total Order for now is 0.0");

            // Collect user details for the order
            System.out.print("Would you please enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Would you please enter your age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            // Get and validate the day of the order
            System.out.print("Enter the day of the week (e.g., MONDAY, TUESDAY): ");
            String dayInput = scanner.nextLine().toUpperCase();
            Day day;
            try {
                day = Day.valueOf(dayInput); // Parse the day input
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid day entered. Defaulting to MONDAY.");
                day = Day.MONDAY;
            }

            // Get the time of the order in 24-hour format
            System.out.print("Enter the time of the order (in 24-hour format, e.g., 14 for 2 PM): ");
            int time = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            // Start a new order
            bevShop.startNewOrder(time, day, name, age);

            // Check if the user is eligible to order alcohol
            if (bevShop.isValidAge(age)) {
                System.out.println("Your age is above 20 and you are eligible to order alcohol");

                // Loop to add alcohol drinks until maximum is reached or user declines
                while (bevShop.isEligibleForMore()) {
                    System.out.print("Would you like to add an alcohol drink? (yes/no) ");
                    String response = scanner.nextLine();
                    if (!response.equalsIgnoreCase("yes")) {
                        break;
                    }

                    // Collect details for the alcohol drink
                    System.out.print("Enter alcohol drink name: ");
                    String drinkName = scanner.nextLine();

                    System.out.print("Enter drink size (SMALL, MEDIUM, LARGE): ");
                    Size size = Size.valueOf(scanner.nextLine().toUpperCase());

                    // Process the alcohol order
                    bevShop.processAlcoholOrder(drinkName, size);
                    System.out.println("The current order of drinks is " + bevShop.getCurrentOrder().getTotalItems());
                    System.out.printf("The Total price on the Order is %.1f%n", bevShop.getCurrentOrder().calcOrderTotal());
                }

                // Notify user if they reach the alcohol limit
                if (!bevShop.isEligibleForMore()) {
                    System.out.println("You have reached the maximum alcohol drinks for this order.");
                }
            } else {
                System.out.println("Your age is not appropriate for alcohol drink!!");
            }

            // Loop to add non-alcoholic drinks
            while (true) {
                System.out.print("Would you like to add a non-alcoholic drink? (yes/no) ");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    break;
                }

                // Collect details for non-alcoholic drinks (coffee or smoothie)
                System.out.print("Enter drink type (COFFEE/SMOOTHIE): ");
                String type = scanner.nextLine().toUpperCase();

                if (type.equals("COFFEE")) {
                    // Collect details for coffee
                    System.out.print("Enter coffee name: ");
                    String coffeeName = scanner.nextLine();

                    System.out.print("Enter coffee size (SMALL, MEDIUM, LARGE): ");
                    Size size = Size.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Do you want extra shot? (true/false): ");
                    boolean extraShot = scanner.nextBoolean();

                    System.out.print("Do you want extra syrup? (true/false): ");
                    boolean extraSyrup = scanner.nextBoolean();
                    scanner.nextLine(); // Consume leftover newline

                    // Process the coffee order
                    bevShop.processCoffeeOrder(coffeeName, size, extraShot, extraSyrup);
                } else if (type.equals("SMOOTHIE")) {
                    // Collect details for smoothie
                    System.out.print("Enter smoothie name: ");
                    String smoothieName = scanner.nextLine();

                    System.out.print("Enter smoothie size (SMALL, MEDIUM, LARGE): ");
                    Size size = Size.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Enter the number of fruits: ");
                    int numOfFruits = scanner.nextInt();

                    System.out.print("Do you want protein powder? (true/false): ");
                    boolean proteinPowder = scanner.nextBoolean();
                    scanner.nextLine(); // Consume leftover newline

                    // Process the smoothie order
                    bevShop.processSmoothieOrder(smoothieName, size, numOfFruits, proteinPowder);
                } else {
                    System.out.println("Invalid drink type. Please enter COFFEE or SMOOTHIE.");
                }

                // Display current order details
                System.out.println("The current order of drinks is " + bevShop.getCurrentOrder().getTotalItems());
                System.out.printf("The Total price on the Order is %.1f%n", bevShop.getCurrentOrder().calcOrderTotal());
            }

            // Display total price for the current order
            System.out.printf("Total price on the current Order: %.1f%n", bevShop.getCurrentOrder().calcOrderTotal());

            // Prompt user to start a new order
            System.out.print("Would you like to start a new order? (yes/no) ");
            String newOrderResponse = scanner.nextLine();
            if (!newOrderResponse.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Display the total sales for all orders
        System.out.printf("Total amount for all Orders: %.1f%n", bevShop.totalMonthlySale());
        scanner.close();
    }
}
