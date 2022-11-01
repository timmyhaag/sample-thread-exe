public class SampleThread extends Thread {
    int randomNum;
    String threadName;

    public SampleThread(String newName) {
        threadName = newName;
    }

    @Override
    public void run() {
        randomNum = (int)(Math.random() * 1000);

        try {
            System.out.println(threadName + " is sleeping for " + randomNum + " milliseconds.");
            SampleThread.sleep(randomNum);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
