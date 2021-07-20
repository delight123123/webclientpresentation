package com.presentation.webclient.webclient.service;

import com.presentation.webclient.webclient.SearchFoundException;
import com.presentation.webclient.webclient.domain.WCPEntity;
import com.presentation.webclient.webclient.domain.repository.WCPRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WCPService {

    final WCPRepository wcpRepository;
    private final static Logger logger= LoggerFactory.getLogger(WCPService.class);

    public Object searchByMap(WCPEntity map){

        if(map.getId()!=null){
            return Optional.ofNullable(wcpRepository.findByid(map.getId())).orElse(new WCPEntity());
        }else if(map.getTitle()!=null){
            return wcpRepository.findAllByTitle(map.getTitle());
        }else if(map.getContent()!=null){
            return wcpRepository.findAllByContent(map.getContent());
        }

        return Optional.empty();
    }

    public Object save(WCPEntity map){

        WCPEntity entity=Optional.ofNullable(wcpRepository.findByid(map.getId())).orElse(new WCPEntity());
        logger.info("##### entity={} #####",entity.toString());
        if(entity.getId()==null || entity.getId()==0) return false;

        wcpRepository.save(map);

        return true;
    }

    public Object del(Long id){
        wcpRepository.deleteById(id);
        return true;
    }

    public Object insert(WCPEntity wcp){
        return wcpRepository.save(wcp);
    }

}
