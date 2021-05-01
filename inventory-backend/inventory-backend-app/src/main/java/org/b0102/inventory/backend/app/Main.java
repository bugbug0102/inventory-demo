package org.b0102.inventory.backend.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

public class Main
{
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args)
    {
        final ApplicationContext context = SpringApplication.run(InventoryApplication.class, args);
        if(logger.isDebugEnabled())
        {
            Arrays.stream(context.getBeanDefinitionNames()).sorted().forEach(logger::debug);
        }
    }
}
