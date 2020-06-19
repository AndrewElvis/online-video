package zust.xyt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author AndrewElvis
 * @date 2020-06-19-20:38
 * @description
 */
@Configuration
public class ViewsMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("form-login");
        registry.addViewController("/index.html").setViewName("form-login");
        registry.addViewController("/main.html").setViewName("home");
    }
}
