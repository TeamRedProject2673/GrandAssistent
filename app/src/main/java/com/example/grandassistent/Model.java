package com.example.grandassistent;

public class Model {

    private String m_nombre,m_telfono,m_especializacion;

    public Model(){

    }

    public Model(String m_nombre, String m_telfono, String m_especializacion) {
        this.m_nombre = m_nombre;
        this.m_telfono = m_telfono;
        this.m_especializacion = m_especializacion;
    }

    public String getM_nombre() {
        return m_nombre;
    }

    public String getM_telfono() {
        return m_telfono;
    }

    public String getM_especializacion() {
        return m_especializacion;
    }

    public void setM_nombre(String m_nombre) {
        this.m_nombre = m_nombre;
    }

    public void setM_telfono(String m_telfono) {
        this.m_telfono = m_telfono;
    }

    public void setM_especializacion(String m_especializacion) {
        this.m_especializacion = m_especializacion;
    }
}
