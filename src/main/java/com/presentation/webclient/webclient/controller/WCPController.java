package com.presentation.webclient.webclient.controller;

import com.presentation.webclient.webclient.domain.WCPEntity;
import com.presentation.webclient.webclient.service.WCPService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class WCPController {

    private static final Logger logger= LoggerFactory.getLogger(WCPController.class);
    private final WCPService wcpService;

    @GetMapping("/getdispacthok/{variables}")
    public ResponseEntity getDispacthok(@PathVariable("variables") Long id){
        logger.info("##### WebClient method GET OK#####");
        WCPEntity map=new WCPEntity();
        map.setId(id);
        logger.info("##### map ={} #####",map.toString());
        return ResponseEntity.ok(wcpService.searchByMap(map));
    }

    @GetMapping("/getdispacthfail")
    public ResponseEntity getDispacthFail(){
        logger.info("##### WebClient method GET FAIL#####");
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/postdispatchok")
    public ResponseEntity postDispatchok(@RequestBody WCPEntity map){
        logger.info("##### WebClient method POST OK#####");
        logger.info("entity={}",map.toString());
        return ResponseEntity.ok(wcpService.searchByMap(map));
    }

    @PostMapping("/postdispatchinsert")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity postDispatchinsertok(@RequestBody WCPEntity wcp){
        logger.info("##### WebClient method POST Insert#####");
        logger.info("##### parameter wcp={} #####",wcp);
        var qw=wcpService.insert(wcp);
        return ResponseEntity.ok(wcpService.insert(wcp));
    }

    @PostMapping("/postdispatchfail")
    public ResponseEntity postDispatchFail(){
        logger.info("##### WebClient method POST FAIL#####");
        return new ResponseEntity(HttpStatus.GATEWAY_TIMEOUT);
    }

    @PutMapping("/putdispatchok")
    public ResponseEntity putDispatchok(@ModelAttribute WCPEntity map){
        logger.info("##### WebClient method PUT OK#####");
        return ResponseEntity.ok(wcpService.save(map));
    }

    @PutMapping("/putdispatchfail")
    public ResponseEntity putDispatchFail(){
        logger.info("##### WebClient method PUT FAIL#####");
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

    @PatchMapping("/patchdispatchok")
    public ResponseEntity patchDispatchok(@ModelAttribute WCPEntity map){
        logger.info("##### WebClient method PACTH OK#####");
        return ResponseEntity.ok(wcpService.save(map));
    }

    @PatchMapping("/patchdispatchfail")
    public ResponseEntity patchDispatchFail(){
        logger.info("##### WebClient method PACTH FAIL#####");
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/deletedispatchok/{id}")
    public ResponseEntity deleteDispatchok(@PathVariable("id") Long id){
        logger.info("##### WebClient method DELETE OK#####");
        return ResponseEntity.ok(wcpService.del(id));
    }

    @DeleteMapping("/deletedispatchfail")
    public ResponseEntity deleteDispatchFail(){
        logger.info("##### WebClient method DELETE FAIL#####");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/postdispatchinsert2")
    @ResponseStatus(HttpStatus.OK)
    public Object postDispatchinsertok2(@RequestBody WCPEntity wcp){
        logger.info("##### WebClient method POST Insert#####");
        logger.info("##### parameter wcp={} #####",wcp);
        Map res=new HashMap();
        res.put("res",wcp.getTitle()+"123123");
        return res;
    }


}
