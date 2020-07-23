package zust.xyt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author AndrewElvis
 * @date 2020-06-19-20:32
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceViewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceViewsApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
