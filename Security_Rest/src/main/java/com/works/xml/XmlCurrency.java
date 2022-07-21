package com.works.xml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class XmlCurrency {

    public static void main(String[] args) {
        XmlCurrency xmlCurrency = new XmlCurrency();
        xmlCurrency.allCurrency();
    }

    public List<Currency> allCurrency() {
        List<Currency> ls = new ArrayList<>();
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String stData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse( stData, Parser.xmlParser() );
            Elements elements = doc.getElementsByTag("Currency");
            for (Element item : elements) {
                String CurrencyName = item.getElementsByTag("CurrencyName").text();
                String ForexBuying = item.getElementsByTag("ForexBuying").text();
                String ForexSelling = item.getElementsByTag("ForexSelling").text();

                Currency c = new Currency(CurrencyName, ForexBuying, ForexSelling);
                ls.add(c);
            }
        }catch (Exception ex) {
            System.err.println("Xml error : " + ex);
        }
        return ls;
    }

}
