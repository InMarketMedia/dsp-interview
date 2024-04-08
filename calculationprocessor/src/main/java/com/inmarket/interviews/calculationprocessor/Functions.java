package com.inmarket.interviews.calculationprocessor;

import com.inmarket.interviews.calculationprocessor.codegen.client.CalculateGraphQLQuery;
import com.inmarket.interviews.calculationprocessor.codegen.client.CalculateProjectionRoot;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.messaging.MessageHeaders;

import java.math.BigInteger;

public class Functions {

    public static Item csvRowToItem(String csvRow) {
        String[] parts = csvRow.toString().split(",");
        return new Item(
                Long.parseLong(parts[0]),
                ItemEntity.Type.valueOf(parts[1].toUpperCase()),
                Long.parseLong(parts[2])
        );
    }

    public static Item itemEnricher(GraphQlClient graphQlClient, Item item, MessageHeaders headers) {
        var projection = switch (item.type()) {
            case GOLDEN -> new CalculateProjectionRoot().golden().result().value();
            case TRIANGLE -> new CalculateProjectionRoot().triangle().result().value();
            case SQUARE -> new CalculateProjectionRoot().square().result().value();
        };
        var request = new GraphQLQueryRequest(
                new CalculateGraphQLQuery.Builder().n(item.value()).build(),
                projection
        );
        long result = graphQlClient.document(request.serialize())
                .retrieve("calculate." + item.type().toString().toLowerCase() + ".result.value")
                .toEntity(BigInteger.class)
                .block()
                .longValue();
        return new Item(item.id(), item.type(), result);
    }

    public static ItemEntity itemToItemEntity(Item item) {
        var ret = new ItemEntity();
        ret.setId(item.id());
        ret.setType(item.type());
        ret.setCurrent(item.value());
        return ret;
    }
}

