package com.fast.cloud.react;

import com.fast.cloud.react.domain.AppGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@Slf4j
public class MongoReactApplication implements CommandLineRunner {
    // 请求总数
    public static int clientTotal = 30;

    // 同时并发执行的线程数
    public static int threadTotal = 5;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        SpringApplication.run(MongoReactApplication.class, args);
    }

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("test mongo ordinary .....");
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        stopWatch.start("reading .....");
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    List<AppGoods> block = mongoTemplate.findAll(AppGoods.class).collectList().block();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        executorService.shutdown();
    }

    private static void add() {
        count.incrementAndGet();
    }
}
