package org.codexdei.java.jdbc.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {

    List<T> listar() throws SQLException;
    T buscarId(Long id) throws SQLException;
    void guardar (T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
