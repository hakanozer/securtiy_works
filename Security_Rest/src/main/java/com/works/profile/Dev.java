package com.works.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("dev")
@PropertySource("classpath:application.properties")
public class Dev implements IConfig {

    @Value("${site.appname}")
    private String title;

    @Override
    public Map<EConfig, Object> config() {
        Map<EConfig, Object> hm = new LinkedHashMap<>();
        hm.put(EConfig.apiKey, "dev_23534534");
        hm.put(EConfig.url, "dev_urlfghfg");
        hm.put(EConfig.passwordKey, "dev_pass344234");
        hm.put(EConfig.apptitle, title);
        return hm;
    }

}
