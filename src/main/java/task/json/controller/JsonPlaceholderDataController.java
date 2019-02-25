package task.json.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import task.json.model.JsonPlaceholderData;
import task.json.repository.JsonPlaceholderDataRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/JsonPlaceholder")
public class JsonPlaceholderDataController {


    private final JsonPlaceholderDataRepository jsonPlaceholderDataRepository;

    @Autowired
    public JsonPlaceholderDataController(JsonPlaceholderDataRepository jsonPlaceholderDataRepository) {
        this.jsonPlaceholderDataRepository = jsonPlaceholderDataRepository;
    }

    //pobieramy liste postow w formacie Json, zapisujemy kazdy plik z osobna i wyswietlamy liste w przegladarce
    @GetMapping("/")
    public List<JsonPlaceholderData> JsonPlaceholderDataListDownload() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<List<JsonPlaceholderData>> response = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts",// adres skad pobieramy liste postow
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<JsonPlaceholderData>>(){});
        // pobrana lista 100 postow
        List<JsonPlaceholderData> posts = response.getBody();

        //petla zapisujaca kazdy post do pliku w formacie json, licznik "i" dodany do odroznienia nazw plikow
        int i =1;
        for (JsonPlaceholderData post : posts) {
            //pliki zapisywanie w domyslnej lokalizacji
            String fileName = i+"post.json";
            try (FileWriter out = new FileWriter(fileName, false)) {
                out.append(post.toString());
            } catch (IOException ex) {
                System.out.println("wrong file!");
            }
            i++;
        }
        return posts;
    }
}
