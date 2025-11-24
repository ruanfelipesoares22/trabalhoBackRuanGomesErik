package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Service.FutebolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/futebol")
public class FutebolController {

        private final FutebolService service;

        public FutebolController(FutebolService service) {
            this.service = service;
        }

        @GetMapping("/times-brasil")
        public ResponseEntity<?> getTimesBrasil() {
            return ResponseEntity.ok(service.buscarTimesBrasil());
        }
    }

