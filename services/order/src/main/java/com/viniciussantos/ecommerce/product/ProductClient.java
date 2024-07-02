package com.viniciussantos.ecommerce.product;

import com.viniciussantos.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// A anotação @Service indica que esta classe é um componente de serviço do Spring.
// Isso significa que o Spring gerenciará esta classe e você pode injetá-la em outras classes.
@Service
@RequiredArgsConstructor
public class ProductClient {

    // A anotação @Value é usada para injetar valores de propriedades no Spring Boot.
    // Neste caso, estamos injetando a URL do serviço de produto.
    @Value("${application.config.product-url}")
    private String productUrl;

    // O RestTemplate é injetado via construtor (graças à anotação @RequiredArgsConstructor do Lombok)
    // e é usado para fazer chamadas HTTP.
    private final RestTemplate restTemplate;

    // Este método é usado para comprar produtos.
    // Ele faz uma chamada HTTP POST para a URL do serviço de produto.
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        // Configurando os cabeçalhos HTTP para a solicitação.
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        // Criando a entidade de solicitação HTTP com o corpo e os cabeçalhos.
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Definindo o tipo de resposta esperado.
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
        };

        // Fazendo a chamada HTTP POST e obtendo a resposta.
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                POST,
                requestEntity,
                responseType
        );

        // Verificando se a resposta contém um código de status de erro.
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }

        // Retornando o corpo da resposta, que é uma lista de PurchaseResponse.
        return  responseEntity.getBody();
    }
}