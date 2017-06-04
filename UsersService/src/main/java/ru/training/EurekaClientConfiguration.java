package ru.training;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by ARnikev on 04/06/17.
 */
@Profile("!test")
@Configuration
@EnableDiscoveryClient
public class EurekaClientConfiguration {
}
