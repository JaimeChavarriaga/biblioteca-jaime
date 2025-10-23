package com.ingesoft.biblioteca.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesoft.biblioteca.model.CopiaLibro;
import com.ingesoft.biblioteca.model.Prestamo;
import com.ingesoft.biblioteca.model.Usuario;
import com.ingesoft.biblioteca.repository.CopiaLibroRepository;
import com.ingesoft.biblioteca.repository.PrestamoRepository;
import com.ingesoft.biblioteca.repository.UsuarioRepository;

// Clase encargada de los casos de uso
// es decir, de la lógica de negocio
// es decir, de las validaciones, las reglas, los cálculos 
// que define el negocio para sus entidades

@Service
public class BibliotecaService {

    @Autowired
    CopiaLibroRepository copiaLibroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PrestamoRepository prestamoRepository;

    // CU01
    // 1. Actor ingresa codigo de la copia del libro
    // 4. Actor ingresa codigo del usuario
    public void prestarCopiaDeLibro(Long codigoCopia, Long codigoUsuario) throws Exception {

        // 2. Sistema valida que exista una copia con ese codigo
        Optional<CopiaLibro> copiaOpcional = copiaLibroRepository.findById(codigoCopia);
        if (copiaOpcional.isEmpty())
            throw new Exception("No existe una copia con ese código");

        // 3. Sistema valida que la copia este disponible
        CopiaLibro copia = copiaOpcional.get();
        if (copia.getDisponible() == false) {
            throw new Exception("La copia no está disponible");
        }

        // 5. Sistema valida que exista un usuario con ese código
        Optional<Usuario> usrOpcional = usuarioRepository.findById(codigoUsuario);
        if (usrOpcional.isEmpty()) {
            throw new Exception("No existe un usuario con ese código");
        }

        Usuario usr = usrOpcional.get();

        // 6. Almacena un nuevo prestamo con el codigo de la copia, el código del libro y la fecha actual

        Prestamo prestamo = new Prestamo();
        
        // atributos
        prestamo.setFechaPrestamo(Instant.now());
        prestamo.setFechaVencimiento(Instant.now());
        prestamo.setFechaDevolucion(null);

        // asociaciones
        prestamo.setCopia(copia);
        prestamo.setUsuario(usr);

        prestamoRepository.save(prestamo);


    }

}
