//package com.authservice.config;

//import io.micrometer.core.aop.TimedAspect;
//import io.micrometer.core.instrument.MeterRegistry;
//import io.micrometer.core.instrument.Tag;
//import io.micrometer.core.instrument.Tags;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Collections;
//
//@Configuration
//public class PrometheusConfig {
//
//    @Bean
//    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
//        return registry -> registry.config()
//                .commonTags(
//                        Tags.of(
//                                Tag.of("application", "auth-service"),
//                                Tag.of("region", "europe-west")
//                        )
//                );
//    }
//
//    @Bean
//    public TimedAspect timedAspect(MeterRegistry registry) {
//        return new TimedAspect(registry);
//    }
//}
//
//interface MeterRegistryCustomizer<T extends MeterRegistry> {
//    void customize(T registry);
//}