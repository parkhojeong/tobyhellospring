package tobyspring.tobyhellospring.payment;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    void convertedAmount() throws IOException {
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(1000), valueOf(10_000));
        testAmount(valueOf(10000), valueOf(100_000));

        // 원화환산금액 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());

    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        // 준비
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        // 실행
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 검증
        // 환율정보
        assertThat(payment.getExRate()).isEqualTo(exRate);

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);
    }
}