package org.example.dialog;
import org.example.RateValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;



public class DialogRate extends Dialog{

    @Override
    public String execute() throws IOException{
        RateValue rate = new RateValue();
        return "Покупка доллара: " + rate.GetDollarSellRate() + System.lineSeparator()
                + "Продажа доллара: " + rate.GetDollarBuyRate() + System.lineSeparator()
                + "Покупка евро: " + rate.GetEuroSellRate() + System.lineSeparator()
                + "Продажа евро: " + rate.GetEuroBuyRate();
    }
}
