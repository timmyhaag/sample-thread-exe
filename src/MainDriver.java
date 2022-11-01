import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainDriver {
    public static void main(String[] args) {
        SampleThread sampleThread;
        ExecutorService threadExecutor = Executors.newFixedThreadPool(7);
        List< Future<?> > threadFutures = new ArrayList<Future<?>>();

        System.out.println("Part A output:\n");

        for (int i = 0; i < 50; i++) {
            SampleThread temp = new SampleThread("Thread " + i);
            Future <?> f = threadExecutor.submit(temp);
            threadFutures.add(f);
        }

        for (Future<?> curFuture : threadFutures) {
            try {
                curFuture.get();
            }
            catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        threadExecutor.shutdown();

        System.out.println("\nPart B output:\n");
        List<Future<Integer>> callableFutures = new ArrayList<Future<Integer>>();
        ExecutorService callableExecutor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            SampleCallable temp = new SampleCallable(i);
            Future<Integer> f = callableExecutor.submit(temp);
            callableFutures.add(f);
        }

        for (int i = 0; i < 200; i++) {
            Future<Integer> curFuture = callableFutures.get(i);
            try {
                int result = curFuture.get();
                System.out.println("Result from thread " + i + ": " + result);
            }
            catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        callableExecutor.shutdown();

        try {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
