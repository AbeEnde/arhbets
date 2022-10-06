package com.ahb.ahbets.web.rest;

import com.ahb.ahbets.domain.Choice;
import com.ahb.ahbets.domain.ReqHosp;
import com.ahb.ahbets.repository.ChoiceRepo;
import com.ahb.ahbets.repository.RequestRepository;
import com.ahb.ahbets.service.ChoiceServ;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
public class ChoiceRes {

    private static final String ENTITY_NAME = "Choice";


    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChoiceRepo choiceRepo;
    private final ChoiceServ choiceServ;

    public ChoiceRes(ChoiceRepo choiceRepo,ChoiceServ choiceServ){
        this.choiceRepo = choiceRepo;
        this.choiceServ = choiceServ;
    }

    @PostMapping("/addChoice")
    public ResponseEntity<Choice> addChoice(@RequestBody Choice choice) throws URISyntaxException {
        Choice reslt =choiceServ.save(choice);
        return ResponseEntity
            .created(new URI("/api/requests/" + reslt.getChoicId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, reslt.getChoicId().toString()))
            .body(reslt);
    }

//    @GetMapping("/getAllData")
//    public List<ReqHosp> getDbdata(){
//        return choiceRepo.getJoinData();
//    }

}
