import java.util.Scanner;

public class FermatsTheorem {

    static int k, n; // Unknown Variables
    static int kUpperLimit = 50; // UpperLimit of Variable K

    static float smallestRelativeMiss = Float.MAX_VALUE; // Smallest relative miss

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Finding Fermat's Last Theorem Near Misses");

        // Get user input for n
        System.out.print("Enter the value of n (should be greater than 2 and less than 12): ");
        n = getInputInRange(scanner, 3, 11);

        // Get user input for k
        System.out.print("Enter the value of k (should be greater than 10 and less than " + kUpperLimit + "): ");
        k = getInputInRange(scanner, 11, kUpperLimit);

        scanner.close();

        findNearMisses();
    }

    // Rest of the code for the FermatsTheorem class...

    // Utility method to get user input in the specified range
    static int getInputInRange(Scanner scanner, int minVal, int maxVal) {
        int inputValue;
        do {
            inputValue = scanner.nextInt();
            if (inputValue < minVal || inputValue > maxVal) {
                System.out.print("Invalid value. Please enter a value between " + minVal + " and " + maxVal + ": ");
            }
        } while (inputValue < minVal || inputValue > maxVal);
        return inputValue;
    }

    static void findNearMisses() {
        for (int x = 10; x <= k; x++) {
            for (int y = x; y <= k; y++) {
                float closeVal = Float.MAX_VALUE;
                int z = (x + y) / 2;

                if (z > k) {
                    z = k;
                    printResultValues(x, y, z, findRelativeMiss(x, y, z));
                    continue;
                }

                while (z <= k) {
                    float near = findRelativeMiss(x, y, z);
                    if (near > closeVal)
                        break;

                    closeVal = near;
                    z++;
                }
                z--;

                printResultValues(x, y, z, closeVal);
                if (x != y)
                    printResultValues(y, x, z, closeVal);
            }
        }
    }

    static void printResultValues(int x, int y, int z, float closeVal) {
        float relMiss = closeVal;

        if (smallestRelativeMiss > relMiss) {
            smallestRelativeMiss = relMiss;
            System.out.println("\nNew Smallest Relative Miss:");
            System.out.println("x: " + x + ", y: " + y + ", z: " + z);
            System.out.println("Relative Miss (Ratio): " + (1 + closeVal));
            System.out.println("Actual Miss: " + findActualMiss(x, y, z));
        }
    }

    public static float findRelativeMiss(int x, int y, int z) {
        double zVal = Math.pow(z, n);
        double xyVal = Math.pow(x, n) + Math.pow(y, n);
        return (float) Math.abs(1.0 - xyVal / zVal);
    }

    public static long findActualMiss(int x, int y, int z) {
        double zVal = Math.pow(z, n);
        double xyVal = Math.pow(x, n) + Math.pow(y, n);
        return (long) (xyVal - zVal);
    }
}
