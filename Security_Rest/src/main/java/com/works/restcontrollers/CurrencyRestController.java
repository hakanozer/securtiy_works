package com.works.restcontrollers;

import com.works.xml.XmlCurrency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class CurrencyRestController {

    @GetMapping("/currencies")
    public ResponseEntity currencies() {
        XmlCurrency currency = new XmlCurrency();
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("result", currency.allCurrency() );
        return new ResponseEntity( hm, HttpStatus.OK );
    }


}
