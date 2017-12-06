package com.officepreview.officepreview.configure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartUpOrderOne implements CommandLineRunner{
    @Override
    public void run(String... strings) throws Exception {
    }
}
