package tobyspring.tobyhellospring.api;

import tools.jackson.core.JacksonException;

import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal extract(String response) throws JacksonException;
}
