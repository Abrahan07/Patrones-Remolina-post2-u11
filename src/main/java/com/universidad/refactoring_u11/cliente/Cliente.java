package com.universidad.refactoring_u11.cliente;

public class Cliente {

    private Long id;
    private String nombre;
    private boolean activo;
    private int score;
    private double limiteCredito;

    public Cliente() {}

    public Cliente(Long id, String nombre, boolean activo,
                   int score, double limiteCredito) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.score = score;
        this.limiteCredito = limiteCredito;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public double getLimiteCredito() { return limiteCredito; }
    public void setLimiteCredito(double limiteCredito) { this.limiteCredito = limiteCredito; }
}