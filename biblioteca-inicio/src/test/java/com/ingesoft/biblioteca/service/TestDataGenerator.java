package com.ingesoft.biblioteca.service;

import org.springframework.stereotype.Service;

import com.ingesoft.biblioteca.model.Autor;
import com.ingesoft.biblioteca.model.CopiaLibro;
import com.ingesoft.biblioteca.model.Libro;
import com.ingesoft.biblioteca.model.Usuario;
import com.ingesoft.biblioteca.repository.AutorRepository;
import com.ingesoft.biblioteca.repository.CopiaLibroRepository;
import com.ingesoft.biblioteca.repository.LibroRepository;
import com.ingesoft.biblioteca.repository.PrestamoRepository;
import com.ingesoft.biblioteca.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TestDataGenerator {

    private final AutorRepository autorRepo;
    private final LibroRepository libroRepo;
    private final CopiaLibroRepository copiaRepo;
    private final UsuarioRepository usuarioRepo;
    private final PrestamoRepository prestamoRepo;

    public TestDataGenerator(AutorRepository autorRepo, LibroRepository libroRepo,
                           CopiaLibroRepository copiaRepo, UsuarioRepository usuarioRepo,
                           PrestamoRepository prestamoRepo) {
        this.autorRepo = autorRepo;
        this.libroRepo = libroRepo;
        this.copiaRepo = copiaRepo;
        this.usuarioRepo = usuarioRepo;
        this.prestamoRepo = prestamoRepo;
    }

    public void borrarBaseDatos() 
        throws Exception {

        prestamoRepo.deleteAll();
        usuarioRepo.deleteAll();
        copiaRepo.deleteAll();
        libroRepo.deleteAll();
        autorRepo.deleteAll();
    }

    public Autor crearAutor(String nombre, String apellido) 
        throws Exception {

        Autor a = new Autor();
        a.setNombre("Isabel");
        a.setApellido("Allende");
        autorRepo.save(a);

        return a;
    }

    public Libro crearLibro(String titulo, String isbn, Autor autor)
        throws Exception {

        Libro l = new Libro();
        l.setTitulo(titulo);
        l.setIsbn(isbn);
        l.setAutor(autor);
        libroRepo.save(l);

        return l;
    }

    public CopiaLibro crearCopiaLibro(
            String codigoBarras,
            String ubicacion,
            Libro libro)
        throws Exception {

        CopiaLibro c = new CopiaLibro();
        c.setCodigoBarras(codigoBarras);
        c.setUbicacion(ubicacion);
        c.setLibro(libro);
        copiaRepo.save(c);

        return c;
    }

    public Usuario crearUsuario(
            String nombre,
            String apellido,
            String email)    
        throws Exception {

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        usuarioRepo.save(u);

        return u;
    }

}
