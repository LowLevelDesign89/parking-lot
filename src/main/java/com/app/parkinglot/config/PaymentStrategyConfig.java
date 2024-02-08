package com.app.parkinglot.config;

import com.app.parkinglot.service.strategy.PaymentStrategy;
import com.app.parkinglot.service.strategy.impl.PaymentByParkingSpotSizeAndHourStrategy;
import com.app.parkinglot.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaymentStrategyConfig {
    @Autowired
    private PaymentByParkingSpotSizeAndHourStrategy paymentByParkingSpotSizeAndHourStrategy;

    @Bean
    public Map<String, PaymentStrategy> paymentStrategyMap() {
        Map<String, PaymentStrategy> paymentStrategyMap = new HashMap<>();
        paymentStrategyMap.put(AppConstants.PAYMENT_STRATEGY_BY_HOUR_AND_SPOT_SIZE,
                paymentByParkingSpotSizeAndHourStrategy);
        return paymentStrategyMap;
    }
}
