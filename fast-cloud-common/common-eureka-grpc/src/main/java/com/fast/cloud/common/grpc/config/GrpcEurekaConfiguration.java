package com.fast.cloud.common.grpc.config;

import com.fast.cloud.common.grpc.internal.EurekaNameResolverProvider;
import com.netflix.discovery.EurekaClientConfig;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.util.RoundRobinLoadBalancerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcEurekaConfiguration {

    @Value("${grpc.eureka.service-id}")
    private String eurekaServiceId;

    @Autowired
    EurekaClientConfig eurekaClientConfig;

    @Bean
    @ConditionalOnMissingBean(ManagedChannel.class)
    public ManagedChannel defaultManagedChannel() {

        return ManagedChannelBuilder
                .forTarget("eureka://" + eurekaServiceId)
                .nameResolverFactory(new EurekaNameResolverProvider(eurekaClientConfig, "grpc.port"))
                .loadBalancerFactory(RoundRobinLoadBalancerFactory.getInstance())
                .usePlaintext(true)
                .build();

    }

}
