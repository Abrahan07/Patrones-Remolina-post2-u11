package com.universidad.refactoring_u11.envio;

import com.universidad.refactoring_u11.domain.Pedido;
import org.springframework.stereotype.Component;

@Component("GRATIS")
public class EnvioGratis implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {
        return 0.0;
    }
}