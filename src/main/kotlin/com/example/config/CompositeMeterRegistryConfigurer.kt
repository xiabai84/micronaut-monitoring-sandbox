package com.example.config

import io.micrometer.core.instrument.composite.CompositeMeterRegistry
import io.micronaut.configuration.metrics.aggregator.MeterRegistryConfigurer
import io.micronaut.configuration.metrics.annotation.RequiresMetrics
import jakarta.inject.Singleton

@Singleton
@RequiresMetrics
class CompositeMeterRegistryConfigurer: MeterRegistryConfigurer<CompositeMeterRegistry> {

    override fun configure(meterRegistry: CompositeMeterRegistry) {
        meterRegistry.config().commonTags("application", "micronaut-monitoring-sandbox")
    }

    override fun getType(): Class<CompositeMeterRegistry> {
        return CompositeMeterRegistry::class.java
    }
}