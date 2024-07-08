package com.zavgorodnyaya.springrest.service;


import com.zavgorodnyaya.springrest.dto.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Service
public class RestUserService {


    private final RestClient restClient;
    public String sessionId;

    public RestUserService() {
        restClient = RestClient.builder()
                .baseUrl("http://94.198.50.185:7081/api/users")
                .build();
        saveSessionId();
    }

    private void saveSessionId() {

        List<String> cookies = restClient.get()
                .retrieve()//Получаем доступ к ответу
                .toBodilessEntity()//Преобразуем ответ в ResponseEntity без тела
                .getHeaders().get(HttpHeaders.SET_COOKIE); //Получаем доступ к заголовку, достаем информацию из заголовка ответа set-cookie

        sessionId = cookies.get(0).split(";")[0];
    }

    public List<User> getAllUsers() {
        return restClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<User>>() {
                });
    }


    public String saveUser(User user) {

        return restClient.post()
                .header("Cookie", sessionId)
                .contentType(APPLICATION_JSON)
                .body(user)
                .retrieve()
                .body(String.class);
    }

    public String updateUser(User user) {

        return restClient.put()
                .header("Cookie", sessionId)
                .contentType(APPLICATION_JSON)
                .body(user)
                .retrieve()
                .body(String.class);
    }

    public String deleteUser(Long id){
        return restClient.delete()
                .uri("/{id}", id)
                .header("Cookie", sessionId)
                .retrieve()
                .body(String.class);
    }
}


