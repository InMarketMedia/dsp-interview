package com.inmarket.interviews.calculationprocessor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLConfig {
    @Value("${serviceUrl}")
    private String calculationServiceUrl;

    @Bean
    public GraphQlClient graphQlClient() {
        return HttpGraphQlClient
                .builder(WebClient.builder().baseUrl(calculationServiceUrl))
                .build();
    }
}
