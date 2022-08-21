package com.devlhse.wiremockcep.service;

import com.devlhse.wiremockcep.dto.AddressResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CepService {

    AddressResponse findAddressByCep(String cep) throws JsonProcessingException;
}
