package com.example.shop.service.interaction;

import com.example.shop.currency.request_filter.ExchangeRateValue;
import com.example.shop.persist.entity.UserEntity;
import com.example.shop.persist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig
public class ExchangeServiceClientImpl implements ExchangeServiceClient {

    private final RestTemplate restTemplate;

    private final UserRepository userRepository;

    @Override
    @Nullable
    @Cacheable(unless = "#result == null", value = "exchangeValue")
    public ExchangeRateValue getExchangeRate() {
        try {
            final String uri = "/exchangeValue";

            return restTemplate.getForObject(uri, ExchangeRateValue.class);
        } catch (Exception ex) {
            log.error("Ошибка при подключению к сервису: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public Map<String, String> getAllInnByEmail(List<String> email) {
        final String uri = "/user/inn";
        try {
            final HttpEntity<List<String>> httpEntity = new HttpEntity<>(email);

            return restTemplate.postForObject(uri, httpEntity, Map.class);
        } catch (Exception ex) {
            log.error("Ошибка при подключению к сервису: " + ex.getMessage());

            return Map.of();
        }
    }

    @Override
    public String getInnByEmail(String email) {
        final Map<String, String> allInnByEmail =
                getAllInnByEmail(userRepository.findAll().stream()
                        .map(UserEntity::getEmail)
                        .toList());

        return allInnByEmail.get(email);
    }
}
