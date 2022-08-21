package com.devlhse.wiremockcep.service;

import com.devlhse.wiremockcep.dto.AddressResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class ViaCepServiceTest {

    private ViaCepService viaCepService;

    WireMockServer wireMockServer = new WireMockServer();

    @BeforeEach
    void setUp(){
        viaCepService = new ViaCepService(new ObjectMapper());
        configureFor("localhost", 8080);
        wireMockServer.start();
    }

    @AfterEach
    void tearDown(){
        wireMockServer.stop();
    }

    @Test
    void shouldFindAddressByCepSuccessWhenCepCodeIsValid() throws JsonProcessingException {

        stubFor(get(urlEqualTo("https://viacep.com.br/ws/{cep}/json")).willReturn(aResponse()
                .withBody(getSuccessCepReponse())
                .withStatus(HttpStatus.OK.value())));

        AddressResponse addressResponse = viaCepService.findAddressByCep("01001000");

        Assertions.assertAll(
            () -> assertNotNull(addressResponse),
            () -> assertEquals("01001-000", addressResponse.getCep()),
            () -> assertEquals("Praça da Sé", addressResponse.getLogradouro()),
            () -> assertEquals("lado ímpar", addressResponse.getComplemento()),
            () -> assertEquals("Sé", addressResponse.getBairro()),
            () -> assertEquals("São Paulo", addressResponse.getLocalidade()),
            () -> assertEquals("SP", addressResponse.getUf()),
            () -> assertEquals("3550308", addressResponse.getIbge()),
            () -> assertEquals("1004", addressResponse.getGia()),
            () -> assertEquals("11", addressResponse.getDdd()),
            () -> assertEquals("7107", addressResponse.getSiafi())
        );
    }

    private String getSuccessCepReponse() {
        return "{\n" +
                "    \"cep\": \"01001-000\",\n" +
                "    \"logradouro\": \"Praça da Sé\",\n" +
                "    \"complemento\": \"lado ímpar\",\n" +
                "    \"bairro\": \"Sé\",\n" +
                "    \"localidade\": \"São Paulo\",\n" +
                "    \"uf\": \"SP\",\n" +
                "    \"ibge\": \"3550308\",\n" +
                "    \"gia\": \"1004\",\n" +
                "    \"ddd\": \"11\",\n" +
                "    \"siafi\": \"7107\"\n" +
                "}";
    }
}
