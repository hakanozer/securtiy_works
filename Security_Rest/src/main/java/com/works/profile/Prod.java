package com.works.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("prod")
@PropertySource("classpath:application.properties")
public class Prod implements IConfig {

    @Value("${site.appname}")
    private String title;

    @Override
    public Map<EConfig, Object> config() {
        Map<EConfig, Object> hm = new LinkedHashMap<>();
        hm.put(EConfig.apiKey, "prod_1233423423");
        hm.put(EConfig.url, "prod_urlasdas");
        hm.put(EConfig.passwordKey, "prod_pass1234");
        hm.put(EConfig.apptitle, title);
        return hm;
    }

}
