package com.example.currencyconverter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConvertor {

    private static final String API_BASE_URL = "https://api.exchangerate.host/latest";

    public static Map<String, Double> getExchangeRates() throws Exception {
        OkHttpClient client = new OkHttpClient();
        String url = API_BASE_URL + "?base=USD";

        Request request = new Request.Builder()
                .url(url)

                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Failed to fetch exchange rate data.");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.body().byteStream());

            JsonNode ratesNode = root.get("rates");
            Map<String, Double> exchangeRates = new HashMap<>();
            ratesNode.fieldNames().forEachRemaining(currency -> {
                exchangeRates.put(currency, ratesNode.get(currency).asDouble());
            });

            return exchangeRates;
        }
    }
}

