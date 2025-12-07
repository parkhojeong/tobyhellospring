package tobyspring.tobyhellospring;

import java.math.BigDecimal;
import java.util.Map;

public record ExRateData(String result, Map<String, BigDecimal> rates) {
}
