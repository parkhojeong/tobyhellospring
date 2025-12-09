package tobyspring.tobyhellospring.api;

import org.springframework.boot.json.JsonParseException;
import tobyspring.tobyhellospring.exrate.HttpClientApiExecutor;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {
    private final ApiExecutor apiExecutor;
    private final ExRateExtractor exRateExtractor;

    public ApiTemplate() {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExtractor = new ErApiExRateExtractor();
    }

    public BigDecimal getExRate(String url) {
        return getExRate(url, apiExecutor, exRateExtractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
        return getExRate(url, apiExecutor, exRateExtractor);
    }

    public BigDecimal getExRate(String url, ExRateExtractor exRateExtractor) {
        return getExRate(url, apiExecutor, exRateExtractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
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
