package com.ahb.ahbets.service;

import com.ahb.ahbets.domain.Choice;
import com.ahb.ahbets.repository.ChoiceRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ChoiceServ {
    private final ChoiceRepo choiceRepo;

    public ChoiceServ(ChoiceRepo choiceRepo){this.choiceRepo = choiceRepo;}

    public Choice save(Choice cho){
        return choiceRepo.save(cho);
    }
}


