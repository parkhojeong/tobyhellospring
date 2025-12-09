package tobyspring.tobyhellospring.exrate;

import tobyspring.tobyhellospring.api.ApiTemplate;
import tobyspring.tobyhellospring.api.ErApiExRateExtractor;
import tobyspring.tobyhellospring.api.SimpleApiExecutor;
import tobyspring.tobyhellospring.payment.ExRateProvider;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, uri -> {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            try(HttpClient client = HttpClient.newBuilder().build()) {
                return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } , new ErApiExRateExtractor());
    }
}
