package com.universidad.refactoring_u11.cliente;

import com.universidad.refactoring_u11.domain.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditoServiceTest {

    private CreditoService service;

    @BeforeEach
    void setUp() {
        service = new CreditoService();
    }

    // Pruebas de calcularEnvio
    @Test
    void calcularEnvio_estandar_conTotalAlto_debeSerGratis() {
        Pedido p = new Pedido();
        p.setTotal(60.0);
        assertEquals(0.0, service.calcularEnvio(p, "ESTANDAR"), 0.001);
    }

    @Test
    void calcularEnvio_estandar_conTotalBajo_debeCobrar() {
        Pedido p = new Pedido();
        p.setTotal(30.0);
        assertEquals(5.99, service.calcularEnvio(p, "ESTANDAR"), 0.001);
    }

    @Test
    void calcularEnvio_express_debeCobrar1299() {
        Pedido p = new Pedido();
        p.setTotal(100.0);
        assertEquals(12.99, service.calcularEnvio(p, "EXPRESS"), 0.001);
    }

    @Test
    void calcularEnvio_mismoDia_debeCobrar2499() {
        Pedido p = new Pedido();
        p.setTotal(100.0);
        assertEquals(24.99, service.calcularEnvio(p, "MISMO_DIA"), 0.001);
    }

    @Test
    void calcularEnvio_gratis_debeSerCero() {
        Pedido p = new Pedido();
        p.setTotal(100.0);
        assertEquals(0.0, service.calcularEnvio(p, "GRATIS"), 0.001);
    }

    @Test
    void calcularEnvio_tipoDesconocido_lanzaExcepcion() {
        Pedido p = new Pedido();
        p.setTotal(100.0);
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularEnvio(p, "DESCONOCIDO"));
    }

    // Pruebas de aprobarCredito
    @Test
    void aprobarCredito_clienteNulo_debeRechazar() {
        assertEquals("RECHAZADO", service.aprobarCredito(null, 1000));
    }

    @Test
    void aprobarCredito_clienteInactivo_debeRechazar() {
        Cliente c = new Cliente(1L, "Juan", false, 700, 5000.0);
        assertEquals("RECHAZADO", service.aprobarCredito(c, 1000));
    }

    @Test
    void aprobarCredito_scoreInsuficiente_debeRechazar() {
        Cliente c = new Cliente(1L, "Juan", true, 500, 5000.0);
        assertEquals("RECHAZADO", service.aprobarCredito(c, 1000));
    }

    @Test
    void aprobarCredito_montoNegativo_debeRechazar() {
        Cliente c = new Cliente(1L, "Juan", true, 700, 5000.0);
        assertEquals("RECHAZADO", service.aprobarCredito(c, -100));
    }

    @Test
    void aprobarCredito_montoExcedeLimite_debeRechazar() {
        Cliente c = new Cliente(1L, "Juan", true, 700, 5000.0);
        assertEquals("RECHAZADO", service.aprobarCredito(c, 6000));
    }

    @Test
    void aprobarCredito_datosValidos_debeAprobar() {
        Cliente c = new Cliente(1L, "Juan", true, 700, 5000.0);
        assertEquals("APROBADO", service.aprobarCredito(c, 3000));
    }
}