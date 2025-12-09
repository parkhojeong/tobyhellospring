package tobyspring.tobyhellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tobyspring.tobyhellospring.exrate.CachedExRateProvider;
import tobyspring.tobyhellospring.exrate.RestTemplateExRateProvider;
import tobyspring.tobyhellospring.payment.ExRateProvider;
import tobyspring.tobyhellospring.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }


}
