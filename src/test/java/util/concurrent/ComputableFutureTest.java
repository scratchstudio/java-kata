package util.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ComputableFutureTest {
    @Test
    public void getShopAsync() {
        Shop shop = new Shop("Best Shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.printf("Invocation returned after %s msecs", invocationTime);

        doSomethingElse();

        try {
            Double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.printf("Price returned after %s msecs", retrievalTime);
    }

    private void doSomethingElse() {
        System.out.println("Execute doSomethingElse()");
    }

    private Double doSomeLongComputation() {
        delay();
        return Double.MIN_VALUE;
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Shop {
        private String name;

        public Shop(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public double getPrice(String product) {
            return calculatePrice(product);
        }

        public Future<Double> getPriceAsync(String product) {
            CompletableFuture<Double> futurePrice = new CompletableFuture<>();
            new Thread(() -> {
                // 별도 스레드에서 비동기적으로 계산 수행
                try {
                    double price = calculatePrice(product);
                    futurePrice.complete(price);
                } catch (Exception e) {
                    futurePrice.completeExceptionally(e);
                }
            }).start();
            return futurePrice; // 계산 완료 기다리지 않고 future 반환
        }

        // getPriceAsync 와 동일하게 동작
        public Future<Double> getPriceAsync2(String product) {
            return CompletableFuture.supplyAsync(() -> calculatePrice(product));
        }

        private double calculatePrice(String product) {
            delay();
            return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
        }
    }
}
