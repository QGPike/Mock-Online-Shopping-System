package Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BankService {

    HashMap<String, PaymentInformation> cards;

    /// Loads csv file lines into users
    private void loadFile() {
        try {
            Scanner in = new Scanner(new File("./Cards.csv"));

            while (in.hasNextLine()) {

                String line = in.nextLine().strip();

                if (line == "") { // Check for end of file
                    break;
                }

                PaymentInformation paymentInformation = PaymentInformation.fromString(line); // Create user
                this.cards.put(paymentInformation.cardNumber, paymentInformation);  // Store into hashmap, with its ID as the key
            }
            in.close();
        } catch (Exception exception) {
            System.out.println(exception.toString());

        }
    }

    /// Will refresh the file from STORAGE. Empties memory
    public void refreshFile() {
        this.cards.clear();
        this.loadFile();
    }

    /// Will refresh the file from STORAGE. Empties memory. `forceSave` will save file if true
    public void refreshFile(boolean forceSave) {
        if (forceSave) {
            this.saveFile();
        }
        this.cards.clear();
        this.loadFile();
    }

    /// Saves file when done
    public void saveFile() {
        try {
            FileWriter fw = new FileWriter("./Users.csv"); // true appends data

            Iterator usersIterator = this.cards.entrySet().iterator();

            while (usersIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)usersIterator.next();
                PaymentInformation card = (PaymentInformation)mapElement.getValue();
                fw.write( card.toString() + "\n");
            }

            fw.close();
        } catch(IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    BankService() {
        this.loadFile();
    }


    /// Returns true if card exists on server
    boolean verifyCard(PaymentInformation queryCard) {
        // First we check to see if the cardNumber exists in this.cards
        if (this.cards.containsKey(queryCard.cardNumber)) {
            // Then we check to see if the contents match
            PaymentInformation serverCard = this.cards.get(queryCard.cardNumber);
            return serverCard.equals(queryCard) ? true : false; // True if they match
        }
        // else
        return false;
    }

    /// Returns a string "deny" that means it was denied, otherwise returns an UUID auth string
    public String charge(PaymentInformation queryCard, float amount) {
        // First verify the card
        if (!this.verifyCard(queryCard)) {
            return "deny";
        }
        // Charge the amount

        // Get the matching card from the server
        PaymentInformation serverCard = this.cards.get(queryCard.cardNumber);

        // charge the card
        serverCard.balance -= amount;

        return UUID.randomUUID().toString().replace("-", "");
    }

    /// Returns a string "deny" that means it was denied, otherwise returns an UUID auth string
    /// Adds amount to users card
    public String refund(PaymentInformation queryCard, Invoice invoice) {
        // First verify the card
        if (!this.verifyCard(queryCard)) {
            return "deny";
        }
        // Charge the amount

        // Get the matching card from the server
        PaymentInformation serverCard = this.cards.get(queryCard.cardNumber);

        // charge the card
        serverCard.balance += Float.parseFloat(invoice.totalAmount);

        return UUID.randomUUID().toString().replace("-", "");
    }
}
