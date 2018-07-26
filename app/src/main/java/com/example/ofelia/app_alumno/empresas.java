package com.example.ofelia.app_alumno;

public class empresas {
    private String nombre_empresa;
    private String pagoficial_empresa;

    public empresas(String nombre_empresa, String pagoficial_empresa) {
        this.nombre_empresa = nombre_empresa;
        this.pagoficial_empresa = pagoficial_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getPagoficial_empresa() {
        return pagoficial_empresa;
    }

    public void setPagoficial_empresa(String pagoficial_empresa) {
        this.pagoficial_empresa = pagoficial_empresa;
    }
}
