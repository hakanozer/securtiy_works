package com.works.services;

import com.google.gson.Gson;
import com.works.props.JsonUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaceHolderService {

    public ResponseEntity allUser() {
        Map<String, Object> hm = new LinkedHashMap<>();
        String url = "https://jsonplaceholder.typicode.com/users";

        RestTemplate template = new RestTemplate();
        String stData = template.getForObject(url, String.class);

        Gson gson = new Gson();
        List<JsonUser> users = gson.fromJson( stData, List.class );

        for ( Object item : users ) {
            System.out.println( item );
        }

        hm.put("result", users);

        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
