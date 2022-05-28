package fr.ulco.feedbacks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RestController
@RequestMapping("/extern")
@RequiredArgsConstructor
public class ExternController {

    // nothing so serious just to try CRUD calls on external APIs
    // no services because will be delete

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/quotes")
    public String getQuotes(){
        String uri = "https://api.quotable.io/random";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    // https://documenter.getpostman.com/view/11369613/SzmjyaXZ#36513318-38a2-4a99-bcdc-63428d0f3f20

    @PostMapping("/users")
    public void addUser() throws JSONException {
        String uri = "https://web-api-test1.herokuapp.com/users";
        RestTemplate restTemplate = new RestTemplate();
        JSONObject user = new JSONObject();
        user.put("id",1);
        user.put("username","Haovn");
        user.put("name","vungochao");
        user.put("password", "123456");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(user.toString(), headers);
        restTemplate.postForLocation(uri, request);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody String name) throws JSONException {
        String uri = "https://web-api-test1.herokuapp.com/users/";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(name, headers);
        restTemplate.exchange(uri+id,HttpMethod.PUT, request, Void.class);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        String uri = "https://web-api-test1.herokuapp.com/users/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri+id);
    }

}
