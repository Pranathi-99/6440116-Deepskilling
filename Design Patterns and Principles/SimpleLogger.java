/**
 Exercise 1: Implementing the Singleton Pattern

 SimpleLogger: A basic implementation of the Singleton design pattern.
 This class ensures that only one instance of SimpleLogger can exist throughout the application's runtime. 
 It uses a lazy initialization approach.
 */
public final class SimpleLogger { 

    private static SimpleLogger instance;

    private SimpleLogger() {
        System.out.println ("SimpleLogger : A new logger instance has been created. ");
    }

    public static SimpleLogger getInstance() {
        if (instance == null) {
            instance = new SimpleLogger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("LOG MESSAGE : " + message);
    }

    // Main method to demonstrate the Singleton pattern
    public static void main(String[] args) {

        System.out.println("Attempt 1: Getting logger instance 'a'");
        SimpleLogger a = SimpleLogger.getInstance();
        a.log("Application started.");

        System.out.println("\nAttempt 2: Getting logger instance 'b'");
        SimpleLogger b = SimpleLogger.getInstance();
        b.log("User logged in.");

        System.out.println("\n-- Verifying Singleton Property --");
        // Check if 'a' and 'b' refer to the exact same object
        System.out.println("HashCode of 'a': " + a.hashCode());
        System.out.println("HashCode of 'b': " + b.hashCode());
    }
}


/*
OUTPUT:
Attempt 1: Getting logger instance 'a'
SimpleLogger : A new logger instance has been created.
LOG MESSAGE : Application started.

Attempt 2: Getting logger instance 'b'
LOG MESSAGE : User logged in.

-- Verifying Singleton Property --
HashCode of 'a': 1431467659
HashCode of 'b': 1431467659
*/