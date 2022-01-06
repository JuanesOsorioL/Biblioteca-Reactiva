package co.com.Biblioteca.UseCase.Recurso;

import reactor.core.publisher.Mono;
@FunctionalInterface
public interface SaberDisponibilidad {
    Mono<String> get(String id);
}