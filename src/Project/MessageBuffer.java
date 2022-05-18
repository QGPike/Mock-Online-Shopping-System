package Project;

public class MessageBuffer {
    boolean messageBufferFull = false;
    boolean responseBufferFull = false;

    // message buffer
    PaymentInformation paymentInformation;
    float amount;

    // UUID or deny
    String responseBuffer;

    static MessageBuffer buffer = null;
    MessageBuffer() {

    }

    public static MessageBuffer getBuffer() {
        if (buffer == null) {
            buffer = new MessageBuffer();
        }
        return buffer;
    }

    public synchronized String send(PaymentInformation paymentInformation, float amount) {
        // place message in buffer
//        messageBuffer = message;
        this.paymentInformation = paymentInformation;
        this.amount = amount;
        messageBufferFull = true;

        System.out.println("Sending message: " + this.paymentInformation.toString() + " | " + this.amount);

        notify();
        while(!responseBufferFull) {
            try {
                wait();

            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }

        String response = responseBuffer;
        responseBuffer = ""; // empty response buffer
        responseBufferFull = false;
        return response;
    }


    public synchronized String receive() {
        while(!messageBufferFull) {
            try {
                wait();
                // remove message from buffer

            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }

//        String message = messageBuffer;

        //  TODO process response do stuff
        BankService service = new BankService();
        String message = service.charge(this.paymentInformation, this.amount);

//        messageBuffer = "";
        this.paymentInformation = null;
        this.amount = 0;
        messageBufferFull = false;
        return message;
    }

    public synchronized void reply(String response) {
        // place response in response buffer
        responseBuffer = response;
        responseBufferFull = true;
        notify();
    }


}
