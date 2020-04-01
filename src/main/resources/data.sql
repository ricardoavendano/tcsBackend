/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
  idusuario VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY (idusuario));
 
INSERT INTO usuario (idusuario, password) VALUES 
('usuario1', 'dXN1YXJpbzE='),
('usuario2', 'dXN1YXJpbzI=');

/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS departamento;
CREATE TABLE departamento (
  iddepartamento INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(100) NOT NULL,
  codigo INT NOT NULL,
  PRIMARY KEY (iddepartamento));
 
INSERT INTO departamento (nombre, descripcion, codigo) VALUES
('SIN DEFINIR', 'SIN DEFINIR', 1),
('DESARROLLO BACKEND', 'fabrica desarrollo backend', 2),
('DESARROLLO FRONTEND', 'fabrica desarrollo frontend', 3),
('QA', 'tester', 4);

/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS funcion;
CREATE TABLE funcion (
  idfuncion INT NOT NULL AUTO_INCREMENT,
  iddepartamentopk INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(100) NOT NULL,
  PRIMARY KEY (idfuncion));
 
ALTER TABLE funcion 
ADD CONSTRAINT iddepartamentopk
  FOREIGN KEY (iddepartamentopk)
  REFERENCES departamento (iddepartamento);
 
INSERT INTO funcion (iddepartamentopk, nombre, descripcion) VALUES
(1, 'NUEVO EMPLEADO', 'NUEVO EMPLEADO'),
(2, 'Desarrollo 1', 'desarrollador backend'),
(2, 'Despliegue', 'Despliegue'),
(3, 'Desarrollo 2', 'desarrollador frontend'),
(3, 'Despliegue', 'Despliegue'),
(4, 'Desarrollo 3', 'QA'),
(4, 'Despliegue', 'Despliegue'),
(4, 'DEVOPS', 'DEVOPS');
 
/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS empleado;
CREATE TABLE empleado (
  idempleado INT NOT NULL AUTO_INCREMENT,
  iddepartamentofk INT NOT NULL,
  idfuncionfk INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  numerodocumento VARCHAR(100) NOT NULL,
  correo VARCHAR(100) NOT NULL,
  telefono VARCHAR(100) NOT NULL,
  activo BOOLEAN NOT NULL,
  salario DECIMAL(100, 2),
  PRIMARY KEY (idempleado));

ALTER TABLE empleado 
ADD CONSTRAINT iddepartamentofk
  FOREIGN KEY (iddepartamentofk)
  REFERENCES departamento (iddepartamento);
 
ALTER TABLE empleado 
ADD CONSTRAINT idfuncionfk
  FOREIGN KEY (idfuncionfk)
  REFERENCES funcion (idfuncion);
 
INSERT INTO empleado (iddepartamentofk, idfuncionfk, nombre, apellidos, numerodocumento, correo, telefono, activo, salario) VALUES
(1, 1, 'Nuevo', 'Nuevo', '147852369', 'Nuevo@hotmail.com', '147852369', TRUE, 500.5),
(2, 2, 'Ricardo', 'Avendaño Casas', '1073236746', 'ricardoav543@hotmail.com', '3212091323', TRUE, 700.5),
(2, 3, 'Ricardo', 'Avendaño Casas', '1073236746', 'ricardoav543@hotmail.com', '3212091323', TRUE, 700.5),
(3, 4, 'front', 'front', '1073236745', 'front@hotmail.com', '3212091323', TRUE, 500.5),
(4, 6, 'QA', 'QA', '123456789', 'QA@hotmail.com', '123456789', TRUE, 600.5),
(2, 2,'BACKEND', 'BACKEND', '987654321', 'BACKEND@hotmail.com', '987654321', TRUE, 500.5);

/*-------------------------------------------------------------------------------------------*/

