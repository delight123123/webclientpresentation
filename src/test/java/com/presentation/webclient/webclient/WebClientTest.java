package com.presentation.webclient.webclient;

import com.presentation.webclient.webclient.controller.WCPController;
import com.presentation.webclient.webclient.domain.WCPEntity;
import com.presentation.webclient.webclient.domain.repository.WCPRepository;
import com.presentation.webclient.webclient.service.WCPService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = WCPController.class)
@Import(WCPService.class)
public class WebClientTest {

    @MockBean
    WCPRepository wcpRepository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testCreate(){
        WCPEntity entity=new WCPEntity();
        entity.setTitle("webfluxtest");
        entity.setContent("webfluxtest");
        entity.setId(13L);
        entity.setRegDate(LocalDateTime.now());

        Mockito.when(wcpRepository.save(entity)).thenReturn(entity);

        var wq= webClient
                .post()
                .uri("/postdispatchinsert")
                .body(BodyInserters.fromValue(entity))
                .exchange()
                .expectBody(WCPEntity.class)
                .returnResult()
                .toString();

        System.out.println(wq);

    }
}
