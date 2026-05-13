package com.universidad.refactoring_u11.envio;

import com.universidad.refactoring_u11.domain.Pedido;
import org.springframework.stereotype.Component;

@Component("MISMO_DIA")
public class EnvioMismoDia implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {
        return 24.99;
    }
}