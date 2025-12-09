package tobyspring.tobyhellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.tobyhellospring.exrate.CachedExRateProvider;
import tobyspring.tobyhellospring.payment.ExRateProvider;
import tobyspring.tobyhellospring.exrate.WebApiExRateProvider;
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
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }


}
