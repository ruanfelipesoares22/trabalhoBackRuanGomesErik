package br.unipar.devbackend.bloguerinho.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FutebolService {
    private final RestTemplate restTemplate;

    public FutebolService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object buscarTimesBrasil() {
        String url = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?s=Soccer&c=Brazil";
        return restTemplate.getForObject(url, Object.class);
    }
}

