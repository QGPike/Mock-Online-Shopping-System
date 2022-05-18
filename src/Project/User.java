package Project;

public class User {
	String id; //Pretty sure this is enough space for Account storage for now. subject to change.
	String password;	
	String name;
	String address;
	String phoneNum;
	String creditNum;
	String isPremium;
	String isSupplier;
	PaymentInformation paymentCard;
	
	User(String id, String password, String name, String address, String phoneNum, String creditNum, String isPremium, String isSupplier, PaymentInformation paymentCard) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.creditNum = creditNum;
		this.isPremium = isPremium;
		this.isSupplier = isSupplier;
		this.paymentCard = paymentCard;
	}
	

	/// Creates a `User` from a string
	public static User fromString(String s) {
		String[] line = s.strip().split(",");
		return new User(line[0],line[1],line[2],line[3],line[4],line[5],line[6],line[7], PaymentInformation.fromString(line[8]));
	}

	/// Creates a string from `User` object
	public String toString() {
		return this.id + "," + this.password + "," + this.name + "," + this.address + "," + this.phoneNum + "," + this.creditNum + "," + this.isPremium + "," + this.isSupplier + "," +this.paymentCard.toString();
	}
}