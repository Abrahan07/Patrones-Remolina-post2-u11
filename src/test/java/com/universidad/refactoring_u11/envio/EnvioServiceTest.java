package com.universidad.refactoring_u11.envio;

import com.universidad.refactoring_u11.domain.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EnvioServiceTest {

    private EnvioService service;

    @BeforeEach
    void setUp() {
        service = new EnvioService(Map.of(
                "ESTANDAR", new EnvioEstandar(),
                "EXPRESS",  new EnvioExpress(),
                "MISMO_DIA", new EnvioMismoDia(),
                "GRATIS",   new EnvioGratis()
        ));
    }

    @Test
    void calcularEnvio_estandar_totalAlto_debeSerGratis() {
        Pedido p = new Pedido(); p.setTotal(60.0);
        assertEquals(0.0, service.calcularEnvio(p, "ESTANDAR"), 0.001);
    }

    @Test
    void calcularEnvio_estandar_totalBajo_debeCobrar() {
        Pedido p = new Pedido(); p.setTotal(30.0);
        assertEquals(5.99, service.calcularEnvio(p, "ESTANDAR"), 0.001);
    }

    @Test
    void calcularEnvio_express_debeCobrar1299() {
        Pedido p = new Pedido(); p.setTotal(100.0);
        assertEquals(12.99, service.calcularEnvio(p, "EXPRESS"), 0.001);
    }

    @Test
    void calcularEnvio_mismoDia_debeCobrar2499() {
        Pedido p = new Pedido(); p.setTotal(100.0);
        assertEquals(24.99, service.calcularEnvio(p, "MISMO_DIA"), 0.001);
    }

    @Test
    void calcularEnvio_gratis_debeSerCero() {
        Pedido p = new Pedido(); p.setTotal(100.0);
        assertEquals(0.0, service.calcularEnvio(p, "GRATIS"), 0.001);
    }

    @Test
    void calcularEnvio_tipoDesconocido_lanzaExcepcion() {
        Pedido p = new Pedido(); p.setTotal(100.0);
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularEnvio(p, "DESCONOCIDO"));
    }
}