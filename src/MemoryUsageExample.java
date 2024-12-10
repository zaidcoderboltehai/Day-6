public class MemoryUsageExample {
    public static void main(String[] args) {
        // Before creating objects, get the memory usage
        Runtime runtime = Runtime.getRuntime();

        // Print total memory in the JVM
        long beforeTotalMemory = runtime.totalMemory();
        // Print free memory available in the JVM
        long beforeFreeMemory = runtime.freeMemory();

        System.out.println("Before object creation:");
        System.out.println("Total memory: " + beforeTotalMemory / (1024 * 1024) + " MB");
        System.out.println("Free memory: " + beforeFreeMemory / (1024 * 1024) + " MB");

        // Create 1 million integer objects
        int[] largeArray = new int[1000000]; // 1 million integers

        // After creating objects, get the memory usage
        long afterTotalMemory = runtime.totalMemory();
        long afterFreeMemory = runtime.freeMemory();

        System.out.println("\nAfter object creation:");
        System.out.println("Total memory: " + afterTotalMemory / (1024 * 1024) + " MB");
        System.out.println("Free memory: " + afterFreeMemory / (1024 * 1024) + " MB");

        // Calculate memory used by the objects
        long memoryUsed = beforeFreeMemory - afterFreeMemory;
        System.out.println("\nMemory used by the objects: " + memoryUsed / (1024 * 1024) + " MB");
    }
}

class A {
    static {
        System.out.println("Class A loaded.");
    }
}

class B {
    static {
        System.out.println("Class B loaded.");
    }
}

public class ClassLoadingExample {
    public static void main(String[] args) {
        System.out.println("Starting main method.");

        // Class A ko directly load karenge, jo automatically class B ko load karegi
        A a = new A();

        // Class B ko reflection se load karenge
        try {
            Class.forName("B");  // Reflection se class B ko load karna
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Ending main method.");
    }
}

public class JITCompilerExample {

    // Fibonacci method jo recursively Fibonacci number calculate karega
    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        // Time measurement start
        long startTime = System.nanoTime();

        // Fibonacci function call - calculation of Fibonacci number
        int n = 40;  // A large Fibonacci number
        System.out.println("Fibonacci of " + n + " is: " + fibonacci(n));

        // Time measurement end
        long endTime = System.nanoTime();

        // Calculating the total time spent on the computation
        long duration = endTime - startTime;

        System.out.println("Execution time: " + duration + " nanoseconds");
    }
}

public class GarbageCollectionExample {

    public static void main(String[] args) {

        // Loop to create objects continuously
        for (int i = 0; i < 10000; i++) {
            // Creating a new array of random numbers
            int[] randomNumbers = new int[1000000]; // 1 million elements

            // Filling the array with random numbers
            for (int j = 0; j < randomNumbers.length; j++) {
                randomNumbers[j] = (int) (Math.random() * 100); // Random number between 0 and 99
            }

            // Discarding the reference after using it
            randomNumbers = null;

            // Suggesting garbage collection manually
            if (i % 100 == 0) {
                System.gc(); // Manually calling garbage collector every 100 iterations
            }
        }

        System.out.println("Garbage collection simulation complete.");
    }
}

public class GarbageCollectionLogExample {

    public static void main(String[] args) {

        // Loop to create large arrays and trigger garbage collection
        for (int i = 0; i < 1000; i++) {
            // Creating large arrays repeatedly
            int[] largeArray = new int[1000000]; // 1 million elements in each array

            // Fill the array with some values (just to use the memory)
            for (int j = 0; j < largeArray.length; j++) {
                largeArray[j] = (int) (Math.random() * 100); // Random number between 0 and 99
            }

            // Discard the reference to the array after use
            largeArray = null;

            // Manually suggest garbage collection after each iteration
            if (i % 100 == 0) {
                System.gc(); // Suggesting garbage collection every 100 iterations
            }
        }

        System.out.println("Garbage collection log simulation complete.");
    }
}

public class MemoryOverflowExample {

    public static void main(String[] args) {
        try {
            // Continuously creating objects to simulate memory overflow
            while (true) {
                // Creating a large number of objects (each object will take up memory)
                String[] largeArray = new String[1000000]; // 1 million elements
                for (int i = 0; i < largeArray.length; i++) {
                    largeArray[i] = "Memory overflow simulation"; // Adding data to the array
                }
                // These objects will remain in memory, causing memory overflow
            }
        } catch (OutOfMemoryError e) {
            // Catching OutOfMemoryError and printing the error message
            System.out.println("OutOfMemoryError: Memory overflow occurred.");
        }
    }
}

public class StackOverflowExample {

    // Recursive function without a proper base case
    public static void causeStackOverflow() {
        // Calling the same function again and again without any stopping condition (base case)
        causeStackOverflow();
    }

    public static void main(String[] args) {
        try {
            // Calling the recursive function
            causeStackOverflow();
        } catch (StackOverflowError e) {
            // Catching the StackOverflowError and printing the message
            System.out.println("StackOverflowError: The stack has overflowed due to infinite recursion.");
        }
    }
}
