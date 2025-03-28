package org.codexdei.java.jdbc;

import org.codexdei.java.jdbc.modelo.Categoria;
import org.codexdei.java.jdbc.modelo.Producto;
import org.codexdei.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.codexdei.java.jdbc.repositorio.Repositorio;
import org.codexdei.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcTrx {

    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getConnection()) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("============= listar =============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============= obtener por id =============");
            System.out.println(repositorio.buscarId(1L));

            System.out.println("============= insertar nuevo producto =============");
            Producto producto = new Producto();
            producto.setNombre("Teclado IBM Mecanico");
            producto.setPrecio(1550);
            producto.setFechaRegistro(new Date());
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(3L);
            producto.setCategoria(categoria);
            producto.setSku("abcde12345");
            repositorio.guardar(producto);
            System.out.println("Producto guardado con éxito");

            System.out.println("============= editar producto =============");
            producto = new Producto();
            producto.setId(3L);
            producto.setNombre("Teclado Razer mecánico");
            producto.setPrecio(1000);
            categoria = new Categoria();
            categoria.setIdCategoria(2L);
            producto.setCategoria(categoria);
            producto.setSku("abcd123456");
            repositorio.guardar(producto);
            System.out.println("Producto editado con éxito");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
