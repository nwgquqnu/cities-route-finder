package com.crfinder.apigateway.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(GatewaySwaggerProperties.class)
public class ProxyApi {

    @Autowired
    private ZuulProperties properties;
    
    @Autowired
    private GatewaySwaggerProperties swaggerProperties;

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> properties.getRoutes()
                .values()
                .stream()
                .filter(this::filterOutServiceRoutes)
                .map(this::createResourceFromRoute)
                .collect(Collectors.toList());

    }

    private SwaggerResource createResourceFromRoute(ZuulRoute route) {
        return createResource(route.getServiceId(), route.getId(), "2.0");
    }

    private SwaggerResource createResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation("/" + location + "/v2/api-docs");
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

    private boolean filterOutServiceRoutes(ZuulRoute route) {
        return !swaggerProperties.getExcludedRouteIds().contains(route.getId());
    }
}
