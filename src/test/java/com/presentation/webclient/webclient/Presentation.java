package com.presentation.webclient.webclient;

import com.presentation.webclient.webclient.constants.PathConstants;
import com.presentation.webclient.webclient.service.WCPService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class Presentation {

    private final static Logger logger= LoggerFactory.getLogger(Presentation.class);

    @Test
    public void methodGetokTest(){
        logger.info("##### GET OK TEST #####");

        var q=WebClient
                .create()
                .get()
                .uri(PathConstants.WEB_CLIENT_GETOK,1)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> { throw new RuntimeException("400 error : " + clientResponse);})
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> { throw new RuntimeException("500 error : " + clientResponse);})
                .bodyToMono(Map.class)
                .blockOptional().orElse(new HashMap());

        logger.info("통신 결과 key = {}, value={}","title",q.get("title"));

    }

    @Test
    public void methodGetFailTest(){
        logger.info("##### GET FAIL TEST #####");
        var res=WebClient
                .create()
                .get()
                .uri(PathConstants.WEB_CLIENT_GETFAIL)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> { throw new RuntimeException("400 error : " + clientResponse);})
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> { throw new RuntimeException("500 error : " + clientResponse);})
                .bodyToMono(Map.class)
                .blockOptional().orElse(new HashMap());
    }

    @Test
    public void methodPostokTest(){
        logger.info("##### POST OK TEST #####");
        //Map map=new HashMap();
        //map.put("title","a");

        MultiValueMap<String,String> map=new LinkedMultiValueMap();

        map.add("title","a");
        var wq=WebClient
                .create()
                .post()
                .uri(PathConstants.WEB_CLIENT_POSTOK)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(map))
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.OK)) return clientResponse.bodyToMono(List.class);
                    else return Mono.error(new RuntimeException("Http Status : "+clientResponse.statusCode()));
                })
                .timeout(Duration.ofSeconds(10))
                .block();

        wq.stream().forEach(System.out::println);

    }

    @Test
    public void methodPostFailTest(){
        logger.info("##### POST FAIL TEST #####");

        var res=WebClient
                .create()
                .post()
                .uri(PathConstants.WEB_CLIENT_POSTFAIL)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) return clientResponse.bodyToMono(Map.class);
                    else return  Mono.error(new RuntimeException("Http Status : "+clientResponse.statusCode()));
                })
                .timeout(Duration.ofSeconds(10))
                .block();
    }

    @Test
    public void methodPutokTest(){
        logger.info("##### PUT OK TEST #####");

        MultiValueMap<String,String> mvm=new LinkedMultiValueMap();
        mvm.add("id",1+"");
        mvm.add("title","다시");
        mvm.add("content","test");

        var res=WebClient
                .create()
                .put()
                .uri(PathConstants.WEB_CLIENT_PUTOK)
                .body(BodyInserters.fromFormData(mvm))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {throw  new RuntimeException("400 error : "+clientResponse);})
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {throw  new RuntimeException("500 error : "+clientResponse);})
                .toBodilessEntity()
                .block()
                .getStatusCode().equals(HttpStatus.OK);

        logger.info(res+"");

    }

    @Test
    public void methodPutfailTest(){
        logger.info("##### PUT FAIL TEST #####");

        var res=WebClient
                .create()
                .put()
                .uri(PathConstants.WEB_CLIENT_PUTFAIL)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {throw  new RuntimeException("400 error : "+clientResponse);})
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {throw  new RuntimeException("500 error : "+clientResponse);})
                .toBodilessEntity()
                .block()
                .getStatusCode().equals(HttpStatus.OK);

        logger.info(res+"");
    }

    @Test
    public void methodPatchokTest(){
        logger.info("##### PATCH OK TEST #####");

        MultiValueMap<String,String> mvm=new LinkedMultiValueMap();
        mvm.add("id",3+"");
        mvm.add("title","변경");
        mvm.add("content","변경");

        var res=WebClient
                .create()
                .patch()
                .uri(PathConstants.WEB_CLIENT_PATCHOK)
                .body(BodyInserters.fromFormData(mvm))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {throw  new RuntimeException("400 error : "+clientResponse);})
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {throw  new RuntimeException("500 error : "+clientResponse);})
                .toBodilessEntity()
                .block()
                .getStatusCode().equals(HttpStatus.OK);

        logger.info(res+"");

    }

    @Test
    public void methodPatchfailTest() {
        logger.info("##### PATCH FAIL TEST #####");

        var res=WebClient
                .create()
                .patch()
                .uri(PathConstants.WEB_CLIENT_PATCHFAIL)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {throw  new RuntimeException("400 error : "+clientResponse);})
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {throw  new RuntimeException("500 error : "+clientResponse);})
                .toBodilessEntity()
                .block()
                .getStatusCode().equals(HttpStatus.OK);

        logger.info(res+"");

    }

    @Test
    public void methodDeleteokTest(){
        logger.info("##### DELETE OK TEST #####");
        Long id=4L;

        var res=WebClient
                .create()
                .delete()
                .uri(PathConstants.WEB_CLIENT_DELETEOK,id)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {throw  new RuntimeException("400 error : "+clientResponse);})
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {throw  new RuntimeException("500 error : "+clientResponse);})
                .toBodilessEntity()
                .block()
                .getStatusCode().equals(HttpStatus.OK);

        logger.info("##### DELETE 결과 res={}",res);
    }

    @Test
    public void methodDeletefailTest() {
        logger.info("##### DELETE FAIL TEST #####");

        var res=WebClient
                .create()
                .delete()
                .uri(PathConstants.WEB_CLIENT_DELETEFAIL)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {throw  new RuntimeException("400 error : "+clientResponse);})
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {throw  new RuntimeException("500 error : "+clientResponse);})
                .toBodilessEntity()
                .block()
                .getStatusCode().equals(HttpStatus.OK);
    }



}
