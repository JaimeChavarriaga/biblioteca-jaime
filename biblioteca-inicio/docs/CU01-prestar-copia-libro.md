# CU01 - Prestar copia de libro

Actor: Usuarios

## Flujo normal de Eventos ("el guión")

1. Actor ingresa codigo de la copia del libro
2. Sistema valida que exista una copia con ese codigo
3. Sistema valida que la copia este disponible
4. Actor ingresa codigo del usuario
5. Sistema valida que exista un usuario con ese código
6. Almacena un nuevo prestamo con el codigo de la copia, el código del libro y la fecha actual

## Flujos alternos ("las excepciones")

### 2. Cuando no existe copia con ese código
2.1. Muestra mensaje "No existe copia con ese código"
2.2. Termina

### 3. Cuando la copia no esta disponible
3.1. Muestra mensaje "La copia no está disponible"
3.2. Termina

### 5. Cuando no existe un usuario con ese código
5.1. Muestra mensaje "No existe usuario con ese código"
5.2. Termina