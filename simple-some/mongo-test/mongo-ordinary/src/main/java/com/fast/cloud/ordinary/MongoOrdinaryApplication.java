package com.fast.cloud.ordinary;

import com.fast.cloud.ordinary.domain.AppGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@SpringBootApplication
public class MongoOrdinaryApplication implements CommandLineRunner {

    // 请求总数
    public static int clientTotal = 100;

    // 同时并发执行的线程数
    public static int threadTotal = 20;

    public static void main(String[] args) {
        SpringApplication.run(MongoOrdinaryApplication.class, args);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

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
                    mongoTemplate.findAll(AppGoods.class);
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
}
