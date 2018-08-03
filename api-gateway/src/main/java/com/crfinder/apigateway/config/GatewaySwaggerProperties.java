package com.crfinder.apigateway.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("gateway.swagger")
public class GatewaySwaggerProperties {

    private List<String> excludedRouteIds;

    public List<String> getExcludedRouteIds() {
        return excludedRouteIds;
    }

    public void setExcludedRouteIds(List<String> excludedRouteIds) {
        this.excludedRouteIds = excludedRouteIds;
    }

}
