package com.devlhse.wiremockcep.client;

import com.devlhse.wiremockcep.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "viacepclient", url = "https://viacep.com.br/ws")
public interface ViaCepFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json", produces = "application/json")
    AddressResponse getAddressByCepCode(@PathVariable("cep") String cep);
}
