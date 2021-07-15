package com.presentation.webclient.webclient.domain.repository;

import com.presentation.webclient.webclient.domain.WCPEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WCPRepository extends JpaRepository<WCPEntity,Long> {

    WCPEntity findByid(Long id);
    List findAllByTitle(String title);
    List findAllByContent(String content);

}
