import java.util.ArrayList;
import java.util.Scanner;

class Loan {
    private double loanAmount;
    private double annualInterestRate;
    private int loanTermYears;
    private double monthlyPayment;
    private double totalInterestPaid;

    // Constructor
    public Loan(double loanAmount, double annualInterestRate, int loanTermYears) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.loanTermYears = loanTermYears;
        calculateMonthlyPayment();
        calculateTotalInterestPaid();
    }

    // Method to calculate monthly payment
    private void calculateMonthlyPayment() {
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        int loanTermMonths = loanTermYears * 12;
        this.monthlyPayment = (loanAmount * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -loanTermMonths));
    }

    // Method to calculate total interest paid over the term
    private void calculateTotalInterestPaid() {
        int loanTermMonths = loanTermYears * 12;
        double totalPaid = monthlyPayment * loanTermMonths;
        this.totalInterestPaid = totalPaid - loanAmount;
    }

    // Getters for accessing loan details
    public double getLoanAmount() {
        return loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getLoanTermYears() {
        return loanTermYears;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalInterestPaid() {
        return totalInterestPaid;
    }
}

public class LoanInterestManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Loan> loanList = new ArrayList<>();  // Store multiple loans

        System.out.println("Welcome to the Federal Reserve Loan Interest Manager!");

        // Loop to handle multiple loan entries
        while (true) {
            // Input: Loan Amount
            System.out.print("Enter loan amount (or type -1 to finish): $");
            double loanAmount = scanner.nextDouble();
            if (loanAmount == -1) break;  // Exit the loop if the user is done

            // Input: Interest Rate
            System.out.print("Enter annual interest rate (in percentage): ");
            double annualInterestRate = scanner.nextDouble();

            // Input: Loan Term (years)
            System.out.print("Enter loan term in years: ");
            int loanTermYears = scanner.nextInt();

            // Create a new loan object and add it to the list
            Loan loan = new Loan(loanAmount, annualInterestRate, loanTermYears);
            loanList.add(loan);

            System.out.println("Loan added successfully!");
        }

        // Display a summary of all loans
        System.out.println("\nSummary of All Loans:");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", "Loan#", "Amount ($)", "Interest Rate (%)", "Monthly Payment ($)", "Total Interest Paid ($)");
        int loanCount = 1;
        double totalInterest = 0;

        for (Loan loan : loanList) {
            System.out.printf("%-10d %-15.2f %-15.2f %-15.2f %-15.2f%n", loanCount, loan.getLoanAmount(), loan.getAnnualInterestRate(), loan.getMonthlyPayment(), loan.getTotalInterestPaid());
            totalInterest += loan.getTotalInterestPaid();
            loanCount++;
        }

        // Display total interest paid for all loans
        System.out.println("-----------------------------------------------");
        System.out.printf("Total Interest Paid for All Loans: $%.2f%n", totalInterest);

        System.out.println("Thank you for using the Federal Reserve Loan Interest Manager!");

        scanner.close();
    }
}

