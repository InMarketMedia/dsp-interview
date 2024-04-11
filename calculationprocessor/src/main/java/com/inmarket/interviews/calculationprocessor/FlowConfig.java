package com.inmarket.interviews.calculationprocessor;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.ConsumerEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableIntegration
@EntityScan(basePackageClasses = ItemEntity.class)
public class FlowConfig {

    @Value("${inputPath}")
    private String csvSourcePath;
    final private static String CHANNEL_ITEMS_RAW = "items.raw";
    final private static String CHANNEL_ITEMS_ENRICHED = "items.enriched";

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public IntegrationFlow fileReadingFlow() {

        return IntegrationFlow
                .from(Files.inboundAdapter(new File(csvSourcePath)).patternFilter("*.csv"),
                        e -> e.poller(Pollers.fixedDelay(1000)))
                .split(Files.splitter().charset(StandardCharsets.UTF_8))
                .transform(Functions::csvRowToItem)
                .channel(CHANNEL_ITEMS_RAW)
                .get();
    }


    @Bean
    IntegrationFlow itemEnrichingFlow(GraphQlClient graphQlClient) {
        return IntegrationFlow
                .from(CHANNEL_ITEMS_RAW)
                .<Item>handle((i, h) -> Functions.itemEnricher(graphQlClient, i, h))
                .channel(CHANNEL_ITEMS_ENRICHED)
                .get();
    }


    @Bean
    public IntegrationFlow jpaWritingFlow() {
        return IntegrationFlow
                .from(CHANNEL_ITEMS_ENRICHED)
                .transform(Functions::itemToItemEntity)
                .handle(Jpa.outboundAdapter(this.entityManagerFactory)
                                .entityClass(ItemEntity.class)
                                .persistMode(PersistMode.PERSIST),
                        ConsumerEndpointSpec::transactional)
                .get();
    }
}
