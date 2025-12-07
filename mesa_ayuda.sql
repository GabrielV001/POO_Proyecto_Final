CREATE DATABASE mesa_ayuda;
USE mesa_ayuda;

CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(256) NOT NULL,
    sal VARCHAR(64) NOT NULL,
    telefono VARCHAR(20),
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE departamentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    descripcion TEXT,
    correo_contacto VARCHAR(100),
    extension_telefonica VARCHAR(20)
);

CREATE TABLE tickets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    asunto VARCHAR(200) NOT NULL,
    descripcion TEXT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'nuevo',
    usuario_id INT,
    departamento_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
);

CREATE TABLE diccionario_emocional (
    palabra VARCHAR(50) PRIMARY KEY,
    emocion VARCHAR(50) NOT NULL
);

CREATE TABLE diccionario_tecnico (
    palabra VARCHAR(50) PRIMARY KEY,
    categoria VARCHAR(50) NOT NULL
);