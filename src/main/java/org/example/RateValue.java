package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class RateValue {

    public String dollarSell;
    public String dollarBuy;
    public String euroSell;
    public String euroBuy;
    Document documentDollar;

    {
        try {
            documentDollar = Jsoup.connect("https://www.banki.ru/products/currency/cash/usd/ekaterinburg/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Document documentEuro;

    {
        try {
            documentEuro = Jsoup.connect("https://www.banki.ru/products/currency/cash/eur/ekaterinburg/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String GetDollarSellRate() {
        return documentDollar.getElementsByClass("currency-table__large-text").get(2).text();
    }

    public String GetDollarBuyRate() {
        return documentDollar.getElementsByClass("currency-table__large-text").get(3).text();
    }

    public String GetEuroSellRate() {
        return documentEuro.getElementsByClass("currency-table__large-text").get(2).text();
    }

    public String GetEuroBuyRate() {
        return documentEuro.getElementsByClass("currency-table__large-text").get(3).text();
    }
}
