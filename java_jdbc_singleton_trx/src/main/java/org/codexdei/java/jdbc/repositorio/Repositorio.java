package org.codexdei.java.jdbc.repositorio;

import java.util.List;

public interface Repositorio<T> {

    List<T> listar();
    T buscarId(Long id);
    void guardar (T t);
    void eliminar(Long id);
}
