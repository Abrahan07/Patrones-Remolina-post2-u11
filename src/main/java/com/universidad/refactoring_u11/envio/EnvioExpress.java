package com.universidad.refactoring_u11.envio;

import com.universidad.refactoring_u11.domain.Pedido;
import org.springframework.stereotype.Component;

@Component("EXPRESS")
public class EnvioExpress implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {
        return 12.99;
    }
}