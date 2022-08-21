package com.devlhse.wiremockcep.service;

import com.devlhse.wiremockcep.dto.AddressResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViaCepService implements CepService {

//    private final ViaCepFeignClient viaCepFeignClient;
    private final ObjectMapper objectMapper;


    @Override
    public AddressResponse findAddressByCep(String cep) throws JsonProcessingException {

        String response = Unirest.get("https://viacep.com.br/ws/{cep}/json")
                .routeParam("cep", cep)
                .asString().getBody();

        log.info("response unirest {}", response);

        return objectMapper.readValue(response, AddressResponse.class);

//        return viaCepFeignClient.getAddressByCepCode(cep);
    }
}
