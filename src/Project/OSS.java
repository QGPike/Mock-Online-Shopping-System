package Project;

//Main file for pulling everything together, or testing before pushing
public class OSS //Online Store Store TM, "We sell stores"
{
	public static void main(String[] args) 
	{
		// start seperate message thread
		MessageBuffer buffer = MessageBuffer.getBuffer();

		BankThread bank = new BankThread(buffer);

		AccountManager aM = new AccountManager();
		aM.login();


		
	}
}
