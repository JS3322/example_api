package com.cleancode.example_api.CustomUtility.CustomException;

/**
 * example
 *         try {
 *             convertToDouble(123.45);  // This will not throw an exception
 *             convertToDouble("A String"); // This will throw an exception
 *         } catch (NotDoubleException e) {
 *             System.out.println("Error: " + e.getMessage());
 *         }
 */
public class NotDoubleException extends Exception {

    public NotDoubleException(String typeName) {
        super("The provided type " + typeName + " is not double. Please convert it to double.");
    }
}
