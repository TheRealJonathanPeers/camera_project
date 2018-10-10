package be.kdg.processor.config;

import be.kdg.processor.models.proxy.LicensePlate;
import be.kdg.processor.models.violations.EmissionViolation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

@Configuration
public class ObserverConfig {
//    @Bean
//    public Set<EmissionViolation> emissionViolations(){
//        return new HashSet<>();
//    }

    @Bean
    public HashMap<LicensePlate, ArrayList<EmissionViolation>> emissionViolations(){
        return new HashMap<>();
    }
}