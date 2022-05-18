package Project;

public class PaymentInformation {
    // TODO if you edit the fields, make sure to modify the constructor,  fromString() and toString()!
    String cardNumber;
    String secCode;
    String expDate;
    float balance;

    PaymentInformation(String cardNumber, String secCode, String expDate, float balance) {
        this.cardNumber = cardNumber;
        this.secCode = secCode;
        this.expDate = expDate;
        this.balance = balance;
    }

    /// Creates a `PaymentInformation` object from a comma seperated string
    public static PaymentInformation fromString(String commaSeperatedString) {
        String line[] = commaSeperatedString.strip().split(",");
        return new PaymentInformation(line[0], line[1], line[2], Float.parseFloat(line[3]));
    }

    /// Creates a semicolon seperated string from fields
    public String toString() {
        return this.cardNumber + ',' + this.secCode + ',' + this.expDate + ',' + this.balance;
    }
}