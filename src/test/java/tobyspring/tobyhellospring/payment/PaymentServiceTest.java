package tobyspring.tobyhellospring.payment;

import org.junit.jupiter.api.Test;
import tobyspring.tobyhellospring.exrate.WebApiExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    void prepare() throws IOException {
        // 준비
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(500)));

        // 실행
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 검증
        // 환율정보
        assertThat(payment.getExRate()).isEqualTo(BigDecimal.valueOf(500));

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount())
                .isEqualTo(BigDecimal.TEN.multiply(BigDecimal.valueOf(500)));

        // 원화환산금액 유효시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());

    }
}