package tobyspring.tobyhellospring.api;

import tobyspring.tobyhellospring.exrate.ExRateData;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class ErApiExRateExtractor implements ExRateExtractor{

    @Override
    public BigDecimal extract(String response) throws JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
