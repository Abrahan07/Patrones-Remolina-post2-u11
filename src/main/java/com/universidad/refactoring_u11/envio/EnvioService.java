package com.universidad.refactoring_u11.envio;

import com.universidad.refactoring_u11.domain.Pedido;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class EnvioService {

    private final Map<String, EstrategiaEnvio> estrategias;

    public EnvioService(Map<String, EstrategiaEnvio> estrategias) {
        this.estrategias = estrategias;
    }

    public double calcularEnvio(Pedido pedido, String tipo) {
        return Optional.ofNullable(estrategias.get(tipo))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Tipo de envio desconocido: " + tipo))
                .calcularCosto(pedido);
    }
}