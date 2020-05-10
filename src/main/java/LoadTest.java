import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Slf4j
public class LoadTest {
    /**
     * 원자타입 동시성처리에 효율적
     * Synconized : 순서보장 X -> 기아상태 발생할수있음.,
     * Concurrent 패키지: 내부적으로 syncronized를 사용하며 체계적으로 동시처리.
     */
    public static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(100);

        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8080/async/dr/event";

        StopWatch main = new StopWatch();
        main.start();

        Stream.iterate(1, i -> i + 1).limit(100).forEach((i) -> {
            es.execute(() -> {
                int idx = counter.addAndGet(1);
                log.info("Thread " + idx);

                StopWatch sw = new StopWatch();
                sw.start();

                rt.getForObject(url, String.class);

                sw.stop();
                log.info("Elapsed: " + idx + " -> " + sw.getTotalTimeSeconds());
            });
        });

        es.shutdown();
        es.awaitTermination(100, TimeUnit.SECONDS);

        main.stop();
    }
}
