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
        registry.addViewController("/form-login.html").setViewName("form-login");
        registry.addViewController("/home.html").setViewName("home");
        registry.addViewController("/browse-channals.html").setViewName("browse-channals");
        registry.addViewController("/browse-catagroies.html").setViewName("browse-catagroies");
        registry.addViewController("/form-register.html").setViewName("form-register");
        registry.addViewController("/single-channal.html").setViewName("single-channal");
        registry.addViewController("/single-video.html").setViewName("single-video");
        registry.addViewController("/your-history.html").setViewName("your-history");
        registry.addViewController("/your-laked-videos.html").setViewName("your-laked-videos");
        registry.addViewController("/your-watch-later.html").setViewName("your-watch-later");
    }
}
