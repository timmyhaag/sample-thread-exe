import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class SampleCallable implements Callable<Integer> {
    int threadID;

    public SampleCallable(int newID) {
        threadID = newID;
    }

    @Override
    public Integer call() throws Exception {
        int randomTime = ThreadLocalRandom.current().nextInt(100, 200);
        Thread.sleep(randomTime);

        Random random = new Random();
        int randomNum = random.nextInt(30) + 1;
        Integer squaredResult = randomNum * randomNum;

        System.out.println("Thread " + threadID + " calculating square of " + randomNum);
        return squaredResult;
    }
}
