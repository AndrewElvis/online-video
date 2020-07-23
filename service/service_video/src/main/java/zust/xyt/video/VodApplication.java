package zust.xyt.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author AndrewElvis
 * @date 2020-06-25-21:55
 * @description
 */
@ComponentScan(basePackages = {"zust.xyt"})
@SpringBootApplication
@EnableDiscoveryClient
public class VodApplication {

    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}
