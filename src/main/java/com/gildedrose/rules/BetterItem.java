package com.gildedrose.rules;

public record Item(String name, Expiration expiration, Quality quality) {



    // Métodos delegados (Encapsulamento)
    public void updateExpiration() { expiration.update(); }
    public Quality getQuality() { return quality; }
    public Expiration getExpiration() { return expiration; }
    public Item tick() {
        return new Item(name, expiration.nextDay(), quality);
    }
}
