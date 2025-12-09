package tobyspring.tobyhellospring.exrate;

import tobyspring.tobyhellospring.api.ApiTemplate;
import tobyspring.tobyhellospring.api.ErApiExRateExtractor;
import tobyspring.tobyhellospring.api.SimpleApiExecutor;
import tobyspring.tobyhellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }
}
