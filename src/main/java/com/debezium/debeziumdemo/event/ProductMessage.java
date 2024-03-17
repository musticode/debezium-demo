package com.debezium.debeziumdemo.event;

import com.debezium.debeziumdemo.event.debezium.After;
import com.debezium.debeziumdemo.event.debezium.Before;
import com.debezium.debeziumdemo.event.debezium.Source;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductMessage {

    @JsonProperty("before")
    private Before before = null;

    @JsonProperty("after")
    private After after;

    @JsonProperty("source")
    private Source source;

    @JsonProperty("op")
    private String op;

    @JsonProperty("ts_ms")
    private String ts_ms;

    @JsonProperty("transaction")
    private Object transaction;

}
