package Project;

public class BankThread implements Runnable {
    MessageBuffer m;

    BankThread(MessageBuffer m) {
        this.m = m;
        new Thread(this, "bank").start();
    }

    public void run() {
        // Bank stuff
        // wait for MessageBuffer to have something to process
        while(true) {
            if (m.messageBufferFull) {
                String result = m.receive();
                m.reply(result);
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.print(e.toString());
                }
            }
        }
    }
}
