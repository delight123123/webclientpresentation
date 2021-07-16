package com.presentation.webclient.webclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component
public class WebClientConfig implements WebClientCustomizer {

    @Override
    public void customize(WebClient.Builder webClientBuilder) {
        webClientBuilder
                .filter(logRequest())
                .filter(logResponse())
                //.filter(urlSetting())
        ;
    }

    private ExchangeFilterFunction logRequest(){
        return (clientRequest,next)->{
            log.info("### Request Hearder ### url={},method={}",clientRequest.url(),clientRequest.method());
            clientRequest.headers().forEach((name,values)->values.forEach(value->log.info("{} : {}",name,value)));
            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction logResponse(){
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("### Response Header ###");
            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{} : {}", name, value)));
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction urlSetting(){
        return (clientRequest, next)->{
            URI baseUrl=URI.create("http://localhost:8888");
            URI url=URI.create("http://localhost:8888/postdispatchok");
            ClientRequest filteredRequest=ClientRequest.from(clientRequest)
                    .url(baseUrl)
                    .build();
            return next.exchange(filteredRequest);
        };
    }
}
