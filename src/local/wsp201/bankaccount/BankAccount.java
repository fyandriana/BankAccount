package local.wsp201.bankaccount;

import java.util.Scanner;
/**
 *  Bank Account Exercise
 Create a program loop that asks the user if they want to continue.
 If they do, ask them what kind of transaction, deposit or withdrawal
 Otherwise thank the user and end the program.
 They must first deposit money to withdrawal because there
 is no money in the bank. If they try to withdrawal and
 they have insufficient funds, tell them and restart
 only allow amounts > 0 to be withdrawn.
 only allow Y or N as input for continuing the program, if they give the wrong
 input, tell them and ask again.
 Ask for Deposit (D), Withdrawal (W) or Balance(B)
 */
public class BankAccount {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	     double balance = 0.0; //initialize balance value

        System.out.println(" ****** Welcome to your favorite bank! ****** ");

        while (true) {
            
            // loop for transaction type — Deposit (D), Withdrawal (W) or Balance(B)
            String transaction;
            while (true) {
                System.out.print("Choose: Deposit (D), Withdrawal (W), Balance (B), or Quit (Q): ");
                transaction = scanner.nextLine().trim().toUpperCase(); // get rid of unnecessary whitespace and uppercase the answer
                if (transaction.equals("D") || transaction.equals("W") || transaction.equals("B") || transaction.equals("Q")) break;
                System.out.println("Invalid input. Please enter D, W, or B.");
            }
            if(transaction.equals("Q")) {
            	System.out.println("Thank you, bye!");
            	break;
            }

            switch (transaction) {
                case "D": { // Deposit: read and add value to the balance
                    double amount = readPositiveNumber(scanner, "Enter deposit amount (> 0): ");
                    balance += amount;
                    System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, balance);
                    break;
                }
                case "W": { // withdraw amount
                    if (balance <= 0) {
                        System.out.println("You must deposit before withdraw any amount. Current balance: $0.00");
                        break;
                    }
                    double amount = readPositiveNumber(scanner, String.format("Enter withdrawal amount (<= %.2f): ", balance));
                    if (amount > balance) {
                        System.out.println("Insufficient funds. Transaction cancelled.");
                    } else {
                        balance -= amount; // deduct amount withdrawn 
                        System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, balance);
                    }
                    break;
                }
                case "B": {
                    System.out.printf("Current balance: $%.2f%n", balance);
                    break;
                }
            }
            
            String userAnswer;
            while (true) { // loop if the user still want to continue
                System.out.print("Do you want to continue? (Y/N): ");
                userAnswer = scanner.nextLine().trim().toUpperCase(); // get rid of unnecessary whitespace and uppercase the answer  
                if (userAnswer.equals("Y") || userAnswer.equals("N")) break;
                System.out.println("Invalid input. Please enter Y or N.");
            }
            if (userAnswer.equals("N")) { // user exits the program
                System.out.println("Thank you, bye!");
                break;
            }
        }
        scanner.close();
	}
	
	/**
	 * read number from input
	 * number must be strictly positive.
	 * loop ask if bad input
	 * @param sc
	 * @param prompt string message
	 * @return
	 */
    private static double readPositiveNumber(Scanner sc, String prompt) {
        while (true) { //loop until correct input
            System.out.print(prompt);
            String inputLine = sc.nextLine().trim();
            try {
                double inputValue = Double.parseDouble(inputLine); // parse input line to double
                if (inputValue > 0) return inputValue;
            } catch (NumberFormatException errror) {
            	
            }
            System.out.println("Invalid amount. Please enter a number greater than 0.");
        }
    }
}
