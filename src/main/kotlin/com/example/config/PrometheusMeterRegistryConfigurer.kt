package com.example.config

import io.micrometer.prometheus.PrometheusMeterRegistry
import io.micronaut.configuration.metrics.aggregator.MeterRegistryConfigurer
import io.micronaut.configuration.metrics.annotation.RequiresMetrics
import jakarta.inject.Singleton

@Singleton
@RequiresMetrics
class PrometheusMeterRegistryConfigurer: MeterRegistryConfigurer<PrometheusMeterRegistry> {

    override fun configure(meterRegistry: PrometheusMeterRegistry) {
        meterRegistry.config().commonTags("application", "micronaut-monitoring-sandbox")
    }

    override fun getType(): Class<PrometheusMeterRegistry> {
        return PrometheusMeterRegistry::class.java
    }

}