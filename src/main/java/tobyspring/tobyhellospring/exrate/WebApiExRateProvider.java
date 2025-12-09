package tobyspring.tobyhellospring.exrate;

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

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                response = br.lines().collect(Collectors.joining());
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data;
        try {
            data = mapper.readValue(response, ExRateData.class);
        }catch (JacksonException e){
            throw new RuntimeException(e);
        }

        System.out.println("API ExRate: " + data.rates().get("KRW"));
        BigDecimal exRate = data.rates().get("KRW");
        return exRate;
    }
}
