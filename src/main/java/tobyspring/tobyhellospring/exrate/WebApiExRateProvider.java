package tobyspring.tobyhellospring.exrate;

import org.springframework.boot.json.JsonParseException;
import tobyspring.tobyhellospring.api.ApiExecutor;
import tobyspring.tobyhellospring.api.ErApiExRateExtractor;
import tobyspring.tobyhellospring.api.ExRateExtractor;
import tobyspring.tobyhellospring.api.SimpleApiExecutor;
import tobyspring.tobyhellospring.payment.ExRateProvider;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        }catch (JsonParseException e){
            throw new RuntimeException(e);
        }
    }
}
