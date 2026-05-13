package com.universidad.refactoring_u11.cliente;

import com.universidad.refactoring_u11.domain.Pedido;
import org.springframework.stereotype.Service;

@Service
public class CreditoService {

    // Switch Statement smell — CC = 5
    public double calcularEnvio(Pedido pedido, String tipoEnvio) {
        switch (tipoEnvio) {
            case "ESTANDAR": return pedido.getTotal() > 50 ? 0 : 5.99;
            case "EXPRESS": return 12.99;
            case "MISMO_DIA": return 24.99;
            case "GRATIS": return 0;
            default: throw new IllegalArgumentException(
                    "Tipo de envio desconocido: " + tipoEnvio);
        }
    }

    // Arrow code — CC = 6
    public String aprobarCredito(Cliente c, double monto) {
        if (c != null) {
            if (c.isActivo()) {
                if (c.getScore() >= 600) {
                    if (monto > 0) {
                        if (monto <= c.getLimiteCredito()) {
                            return "APROBADO";
                        }
                    }
                }
            }
        }
        return "RECHAZADO";
    }
}