/**
 * @author:Joseph Pham
 * @date: 19 September 2018
 * @description: nicely displays arrays, sorted or unsorted by the user's choice. other options like 
 *               highest grade, lowest grade, average grade, median grade. professor is disappointed
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> unsortedGrades = populateGrades(); // reads file and assigns to an array
        ArrayList<Integer> sortedGrades = sortGrades(unsortedGrades); // sorts the array and assigns
        printMenu();
        int menuChoice = getMenuChoice(); // gets user's valid choice
        while (menuChoice != 7) { // if user hasn't quit
            if (menuChoice == 1) { // display unsorted
                displayUnsortedGrades(unsortedGrades);
            } else if (menuChoice == 2) { // display sorted
                displaySortedGrades(sortedGrades);
            } else if (menuChoice == 3) { // display average
                displayAverage(unsortedGrades);
            } else if (menuChoice == 4) { // display highest
                displayHighestGrade(sortedGrades);
            } else if (menuChoice == 5) { // display lowest
                displayLowestGrade(sortedGrades);
            } else if (menuChoice == 6) { // display median
                displayMedian(sortedGrades);
            } // after this, will print menu and prompt for choice, if 7 then will end
            printMenu();
            menuChoice = getMenuChoice();
        } // professor is sad because test grades were horrible
        System.out.println("\n" + "I'm disappointed in most of you. You guys know who you are.");
    }

    /**
     * construct the ArrayList, reads in the file, add the grades to an arraylist
     * 
     * @return the list of the unsorted grades
     */
    public static ArrayList<Integer> populateGrades() {
        ArrayList<Integer> unsortedGrades = new ArrayList<Integer>(); // initialize
        try {
            Scanner read = new Scanner(new File("grades.txt")); // reads the file
            do { // as long as we're not at the end of the file
                int nextGrade = read.nextInt(); // reads the next integer and assigns it
                unsortedGrades.add(nextGrade); // adds the assigned int to the end of the list
            } while (read.hasNext());
            read.close();
        } catch (FileNotFoundException fnf) { // basically stops program from crashing if this specific error has been
                                              // encountered
            System.out.println("File was not found");
        }
        return unsortedGrades;
    }

    /**
     * displays the array of grades separated by spaces
     * @param passes in an array and its contents
     */
    public static void displayGrades(ArrayList<Integer> gradesList) {
        for (int index = 0; index < gradesList.size(); index++) { // iterate through entire list
            String currentGrade = (" " + gradesList.get(index)); // space out numbers
            if (currentGrade.length() == 4) { // if grade = 100, only needs 1 space
                displayGrade(currentGrade, index); // prints it out
            } else if (currentGrade.length() == 3) { // if grade 10-99
                currentGrade = (" " + currentGrade); // adds space to have even spacing
                displayGrade(currentGrade, index); // prints it out
            } else { // only here if grade 0-9
                currentGrade = ("  " + currentGrade); // adds two sapces for spacing
                displayGrade(currentGrade, index); // prints it out
            }
        }
        System.out.print("\n");
        System.out.print("\n"); // it looks better spaced out from menu
    }

    /**
     * takes in the unsorted array and returns back it sorted from lowest to highest
     * @param passes in the unsorted array and its contents
     * @return returns arraylist of the grades sorted
     */
    public static ArrayList<Integer> sortGrades(ArrayList<Integer> unsortedGrades) {
        ArrayList<Integer> sortedGrades = new ArrayList<>(unsortedGrades); // initialize
        for (int minIndex = 0; minIndex < sortedGrades.size(); minIndex++) { // index of the leftmost value, moves to
                                                                             // the right once after current lowest
                                                                             // value has been determined
            int minValue = sortedGrades.get(minIndex); // leftmost value to compare to the current index
            int oldValue = sortedGrades.get(minIndex); // remember value for swap
            int oldIndex = 0; // initialize
            for (int currentIndex = minIndex; currentIndex < sortedGrades.size(); currentIndex++) { // wont iterate
                                                                                                    // through past
                                                                                                    // indexes
                if (sortedGrades.get(currentIndex) <= minValue) { // if new value is lower than the current min
                    minValue = sortedGrades.get(currentIndex); // update minimum value
                    oldIndex = currentIndex; // update location of the lowest value
                }
            }
            sortedGrades.set(minIndex, minValue); // swap lowest number to the current lowest index
            sortedGrades.set(oldIndex, oldValue); // swap old value to the new index
        }
        return sortedGrades;
    }

    /**
     * takes all the values of the array and add them up
     * @param passes in an array and its contents
     * @return sum of all the values of the grades in the array
     */
    public static int calculateSum(ArrayList<Integer> gradesList) {
        int sum = 0; // initialize
        for (int i = 0; i < gradesList.size(); i++) { // iterate
            sum = sum + gradesList.get(i); // add through each value
        }
        return sum;
    }

    /**
     * finds the median index of the sorted array and finds the median value of the
     * array
     * @param passes in the sorted array and its contents
     * @return median value of the grades
     */
    public static float calculateMedian(ArrayList<Integer> sortedGrades) {
        float medianValue = 0; // intialize
        if (sortedGrades.size() % 2 == 0) { // if arraysize is even
            int medianIndex1 = (sortedGrades.size() / 2); // if arraysize is even, there will be two median values
            float medianValue1 = sortedGrades.get(medianIndex1); // first median value
            int medianIndex2 = ((sortedGrades.size() / 2) - 1);
            float medianValue2 = sortedGrades.get(medianIndex2); // second median value
            medianValue = ((medianValue1 + medianValue2) / 2); // add two middle values and divide by two
        } else { // if array size is odd
            int medianIndex = ((sortedGrades.size() - 1) / 2); // will find the median index, center number
            medianValue = sortedGrades.get(medianIndex); // gets value of median
        }
        return medianValue;
    }

    /**
     * prints menu
     */
    public static void printMenu() {
        System.out.println("  Menu");
        System.out.println("1. Display Unsorted Grades");
        System.out.println("2. Display Sorted Grades");
        System.out.println("3. Display Average Grade");
        System.out.println("4. Display Highest Grade");
        System.out.println("5. Display Lowest Grade");
        System.out.println("6. Display Median Grade");
        System.out.println("7. Quit");
    }

    // MY OWN FUNCTIONS

    /**
     * will skip to the next line after every 10 numbers displayed, and spaces out
     * evenly
     * @param passes in a string converted from an integer, and also passes in the
     *               index of the array
     */
    public static void displayGrade(String currentGrade, int index) {
        if (index % 10 == 0) { // every 10th number start a new line
            System.out.print("\n" + currentGrade); // print onto a new line if 10th value
        } else { // if not a 10th number, keep displaying
            System.out.print(currentGrade); // if not 10th, go ahead
        }
    }

    /**
     * displays the sorted array and is friendlier to the user
     * @param passes in the unsorted array and its contents
     */
    public static void displayUnsortedGrades(ArrayList<Integer> unsortedGrades) {
        System.out.println("\n" + "The grades unsorted are: "); // friendly
        displayGrades(unsortedGrades); // shows the array
    }

    /**
     * displays the sorted array and is friendlier to the user
     * @param passes in the sorted array and its contents
     */
    public static void displaySortedGrades(ArrayList<Integer> sortedGrades) {
        System.out.println("\n" + "The grades sorted are: "); // friendly
        displayGrades(sortedGrades); // shows array
    }

    /**
     * displays the average of the array calculated from its func
     * @param passes in the unsorted array and its contents
     */
    public static void displayAverage(ArrayList<Integer> unsortedGrades) {
        float gradeSum = calculateSum(unsortedGrades); // float to have a -.00 so it looks nicer
        float gradeAverage = (gradeSum / unsortedGrades.size()); // sum / size = average
        System.out.print("\n" + "The class average grade for the midterm is %");
        System.out.printf("%.1f", gradeAverage);
        System.out.print(". That's terrible just so you guys know." + "\n" + "\n"); // prints nicely
    }

    /**
     * displays the last value of the array, the highest value
     * @param passes in the sorted array and its contents
     */
    public static void displayHighestGrade(ArrayList<Integer> sortedGrades) {
        double highestGrade = sortedGrades.get((sortedGrades.size() - 1)); // last value of the array
        System.out.print("\n" + "The highest grade on the midterm is %");
        System.out.printf("%.1f", highestGrade); // so it's 2 decimal places
        System.out.print(". Good job." + "\n" + "\n"); // friendly
    }

    /**
     * gets the first value of the array, the lowest value
     * @param passes in the sorted array and its contents
     */
    public static void displayLowestGrade(ArrayList<Integer> sortedGrades) {
        double lowestGrade = sortedGrades.get(0); // first value of the array
        System.out.print("\n" + "The lowest grade on the midterm is %");
        System.out.printf("%.1f", lowestGrade); // 2 decimal places
        System.out.print(". Shame on you." + "\n" + "\n"); // friendly
    }

    /**
     * displays the median and is friendlier for the normies
     * @param passes in the sorted array and its contents
     */
    public static void displayMedian(ArrayList<Integer> sortedGrades) {
        float median = calculateMedian(sortedGrades); // float because might not break even
        System.out.print("\n" + "The median grade for the midterm is %");
        System.out.printf("%.1f.", median); // 2 decimals
        System.out.print("\n" + "\n"); // looks nicer
    }

    /**
     * prompts for the user's menu choice and converts it into an integer from a
     * string
     * @return user's choice on the menu
     */
    public static int getMenuChoice() {
        System.out.print("\n" + "Enter a number: "); // prompt
        int menuChoice = CheckInput.getIntRange(1, 7); // checks if in menu range and converts to int
        return menuChoice;
    }
}