package com.devlhse.wiremockcep.controller;

import com.devlhse.wiremockcep.service.CepService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
@Slf4j
public class CepController {

    private final CepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity findAddress(@PathVariable("cep") String cep) throws JsonProcessingException {
        log.debug("Init search with cep code: {}", cep);

        return new ResponseEntity(cepService.findAddressByCep(cep), HttpStatus.OK);
    }


}
