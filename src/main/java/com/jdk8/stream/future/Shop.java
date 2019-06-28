package com.jdk8.stream.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Shop {




    public static void main(String[] args) {

        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        long start = System.nanoTime();
//        System.out.println(findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    /**
     * 异步计算价格
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }


    /**
     * 使用工厂方法supplyAsync创建CompletableFuture
     * @param product 产品名称
     * @return
     */
    public  Future<Double> getPriceBySupplyAsync(String product){
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }
    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
    public void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findPrices(String product,List<Shop>shops){
        return shops.stream().map(shop->String.format("%s price is .2f%",shop.getProduct(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    private  String product;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    Shop(String product){
        this.product=product;
    }

}
