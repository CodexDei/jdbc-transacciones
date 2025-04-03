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

    public static void main(String[] args) throws SQLException {

        try (Connection conn = ConexionBaseDatos.getConnection()) {

            //Para empezar aplicar transacciones debemos dejar en false
            //por seguridad lo mejor es siempre preguntar

            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }

            try {

                Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
                System.out.println("============= listar =============");
                repositorio.listar().forEach(System.out::println);


                System.out.println(repositorio.buscarId(1L));

                System.out.println("============= insertar nuevo producto =============");
                Producto producto = new Producto();
                producto.setNombre("Mouse Lenovo");
                producto.setPrecio(1550);
                producto.setFechaRegistro(new Date());
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(3L);
                producto.setCategoria(categoria);
                producto.setSku("abcde12345789");
                repositorio.guardar(producto);
                System.out.println("Producto guardado con éxito");

                System.out.println("============= editar producto =============");
                producto = new Producto();
                producto.setId(3L);
                producto.setNombre("Teclado retroiluminado lenovo");
                producto.setPrecio(1000);
                categoria = new Categoria();
                categoria.setIdCategoria(2L);
                producto.setCategoria(categoria);
                producto.setSku("abcd777777");
                repositorio.guardar(producto);
                System.out.println("Producto editado con éxito");
                repositorio.listar().forEach(System.out::println);

                //confirma la transaccion, es decir realizara todas las operaciones anteriores
                // Si alguna falla se aplicara el catch, se ejecutan todas correctamente  o ninguna
                conn.commit();
            }catch (SQLException exception){
                exception.printStackTrace();
                //si se presenta un error en la transaccion la base de datos queda igual
                conn.rollback();
            }

        }
    }
}
