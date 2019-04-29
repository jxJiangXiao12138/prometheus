package com.demo.spring.prometheus;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;

public class PrometheusConfig {
    public void init() throws IOException {
        new HTTPServer(9090, true);
        DefaultExports.initialize();
    }
}