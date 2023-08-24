package ru.homework.springboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.homework.springboot.profiles.DevProfile;
import ru.homework.springboot.profiles.ProductionProfile;
import ru.homework.springboot.profiles.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(value = "netology.profile.dev", matchIfMissing = true,
            havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}