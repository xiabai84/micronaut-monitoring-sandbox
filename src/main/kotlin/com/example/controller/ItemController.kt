package com.example.controller

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import org.jetbrains.annotations.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Controller("/item")
open class ItemController(private val meterRegistry: MeterRegistry) {

    private val logger: Logger = LoggerFactory.getLogger(ItemController::class.java)
    private var counter: Int = 0
    private val itemList = mutableListOf<String>()

    @Post("/{name}")
    fun createItem(@NotNull name: String): String {
        itemList.add(name)
        counter += 1

        // grafana micrometer dashboard doesn't display any metrics under this name
//        meterRegistry.config().commonTags("application", "micronaut-monitoring-sandbox")

        meterRegistry
                .counter("item", "param", name)
                .increment()
        logger.info("{}", mapOf("item" to name))

        return "new item: $name"
    }

    @Get("/all")
    fun items(): String {
        // each api call increases the counter including call from prometheus
        meterRegistry
                .counter("counter", "param", counter.toString())
                .increment()

        return "items $itemList \ntotal items: $counter"
    }

}