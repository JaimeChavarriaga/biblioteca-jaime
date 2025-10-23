package com.ingesoft.biblioteca.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingesoft.biblioteca.model.Autor;
import com.ingesoft.biblioteca.model.CopiaLibro;
import com.ingesoft.biblioteca.model.Libro;
import com.ingesoft.biblioteca.model.Usuario;

import jakarta.transaction.Transactional;


@SpringBootTest
@Transactional
public class BibliotecaServiceTest {

    @Autowired
    TestDataGenerator testDataGenerator;

    @Autowired
    BibliotecaService bibliotecaService;

    @BeforeEach
    public void borraBaseDatos() throws Exception {
        testDataGenerator.borrarBaseDatos();
    }

    @Test
    public void prestamoExitoso() {
        try {
            // Arrange -- prepara la prueba
            Autor a = testDataGenerator.crearAutor("Gabriel", "García Marquez");
            Libro l = testDataGenerator.crearLibro("Cien años de Soledad", "012", a);
            CopiaLibro c = testDataGenerator.crearCopiaLibro("12345", "Estante 1", l);
            Usuario u = testDataGenerator.crearUsuario("Bart", "Simpson", "bart@simpsons.com");

            Long codigoCopia = c.getId();
            Long codigoUsuario = u.getId(); 

            // Act -- realiza la acción
            bibliotecaService.prestarCopiaDeLibro(codigoCopia, codigoUsuario);

            // Assert -- valida que el resultado sea el esperado
            
        } catch (Exception e) {
            Assertions.fail();            
        }
    }


    @Test
    public void copiaNoExiste_PrestamoFalla() {
        try {
            // Arrange -- prepara la prueba

            // Act -- realiza la acción
            bibliotecaService.prestarCopiaDeLibro(-10L, 1L);

            // Assert -- valida que el resultado sea el esperado
            Assertions.fail();                
            
        } catch (Exception e) {
        
        }
    }

}
