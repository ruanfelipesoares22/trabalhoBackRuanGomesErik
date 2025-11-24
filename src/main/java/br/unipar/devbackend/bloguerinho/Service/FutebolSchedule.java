package br.unipar.devbackend.bloguerinho.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FutebolSchedule {
    private final FutebolService futebolService;

    public FutebolSchedule(FutebolService futebolService) {
        this.futebolService = futebolService;
    }
    @Scheduled(fixedRate = 21600000)
    public void atualizarTimesBrasil() {
        System.out.println("Iniciando atualização de times de futebol...");
        try {
            Object times = futebolService.buscarTimesBrasil();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar times de futebol: " + e.getMessage());
        }
        System.out.println("Atualização de times finalizada!");
    }
}
