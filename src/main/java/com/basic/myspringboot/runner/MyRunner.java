package com.basic.myspringboot.runner;

import com.basic.myspringboot.property.MybootProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.username}")
    private String username;

    @Autowired
    private MybootProperty property;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("Username ==> {} ", username);
        log.debug("Prop Username {} ", property.getUsername());
        log.debug("Prop Password {} ", property.getPassword());
    }
}
