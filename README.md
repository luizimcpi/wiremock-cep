# wiremock-cep

## Request Example
```
curl --location --request GET 'localhost:8080/addresses/01001000'
```
## Response Example
```
{
    "cep": "01001-000",
    "logradouro": "Praça da Sé",
    "complemento": "lado ímpar",
    "bairro": "Sé",
    "localidade": "São Paulo",
    "uf": "SP",
    "ibge": "3550308",
    "gia": "1004",
    "ddd": "11",
    "siafi": "7107"
}
```

## Tests 
```
./mvnw test
```