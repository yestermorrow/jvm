import com.wyj.jvm.util.LocalCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalCacheTest implements Runnable {

    public void run() {
        LocalCache.isAccess("127.0.0.1", 3000L, 3);
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        LocalCacheTest test = new LocalCacheTest();
        for (int i = 0; i < 100; i++) {
            pool.execute(test);
        }
    }
}