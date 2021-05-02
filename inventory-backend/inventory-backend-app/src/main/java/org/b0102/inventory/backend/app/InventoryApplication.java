package org.b0102.inventory.backend.app;



import org.b0102.inventory.backend.app.dao.CategoryDao;
import org.b0102.inventory.backend.app.entity.CategoryBean;
import org.b0102.inventory.backend.app.filter.CorsFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableAutoConfiguration(exclude = {GroovyTemplateAutoConfiguration.class
        , GsonAutoConfiguration.class
        , ErrorMvcAutoConfiguration.class
        , JmxAutoConfiguration.class
        , MultipartAutoConfiguration.class
        , FreeMarkerAutoConfiguration.class
        , WebSocketServletAutoConfiguration.class
        , TaskSchedulingAutoConfiguration.class
        , TaskExecutionAutoConfiguration.class
        , RestTemplateAutoConfiguration.class
})
@EnableJpaRepositories(basePackageClasses = {CategoryDao.class})
@EntityScan(basePackageClasses = {CategoryBean.class})
@ComponentScan
public class InventoryApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application)
    {
        return application.sources(InventoryApplication.class);
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilterFilterRegistration(final CorsFilter corsFilter)
    {
        final FilterRegistrationBean<CorsFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(corsFilter);
        return reg;
    }

}
