package com.demo.spring.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Summary;


public class PrometheusType {
    public static final Summary responseSummary = Summary.build()
    .name("method_latency_seconds")
    .labelNames("name", "method")
    .quantile(0.1, 0.01)
    .quantile(0.5, 0.01)
    .quantile(0.9, 0.01)
    .quantile(0.99, 0.01)
    .help("Request latency in seconds.")
    .register();

    public static final Counter requestTotal = Counter.build()
    .name("method_req_total")
    .labelNames("name", "method")
    .help("Total requests.")
    .register();


    public static final Counter requestError = Counter.build()
    .name("method_error_total")
    .labelNames("name", "method")
    .help("Total requests.")
    .register();


    public static final Counter testPrometheus = Counter.build()
            .name("test_prometheus")
            .help("test rometheus.")
            .register();



}

