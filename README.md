# tcsBackend
Prueba TCS

Ricardo Avendaño Casas

Para ejecutar el proyecto se deben realizar los siguientes pasos
1. descargar fuente de github: git clone https://github.com/ricardoavendano/tcsBackend.git
2. ir al directorio donde se encuentra el fuente y crear jar: mvn clean install (se crea la carpeta target)
3. ir al directorio donde se encuentra el fuente y dirigirse a la carpeta target; por linea de comandos ejecutar jar: java -jar tcsBackend-0.0.1-SNAPSHOT.jar
4. La aplicacion ya se encuentra desplegada localmente en la url (http://localhost:8081)
5. endpoint de la aplicacion (puede usar swagger si desea consumier los endpoints desde ahi URL: http://localhost:8081/tcs/swagger-ui.html#/)
	1. GET http://localhost:8081/tcs/listar/usuario Lista los usuarios del sistema
	2. POST http://localhost:8081/tcs/login acceso a la aplicacion desde el frontend, el password esta encriptado en base64 pero es el mismo que el idUsuario
			body ejemplo:
			{"idUsuario":"usuario1","password":"dXN1YXJpbzE="}
	3. GET http://localhost:8081/tcs/departamento/cargar/ Craga los departamentos existentes con la lista de empleados asociados y sus funciones
	4. GET http://localhost:8081/tcs/departamento/pago/promedio/{idDepartamento} se encarga de obtener el promedio por departamento
	5. GET http://localhost:8081/tcs/empleado/activar-inactivar/{idEmpleado}/{estado} cambia el estado de un empleado indicando si esta activo o inactivo
	6. GET http://localhost:8081/tcs/empleado/cargar carga los empleados existentes con el departamento al cual pertenece y sus funciones
	7. POST http://localhost:8081/tcs/empleado/registro se encarga de crear un nuevo empleado
			body ejemplo:
			{
			  "activo": true,
			  "apellidos": "string",
			  "correo": "string",
			  "departamento": {
				"codigo": 0,
				"descripcion": "string",
				"idDepartamento": 0,
				"nombre": "string"
			  },
			  "funcionList": [
				{
				  "descripcion": "string",
				  "idFuncion": 0,
				  "nombre": "string"
				}
			  ],
			  "idEmpleado": 0,
			  "nombre": "string",
			  "numeroDocumento": "string",
			  "salario": 0,
			  "telefono": "string"
			}
	8. POST http://localhost:8081/tcs/empleado/registro/departamento Se encarga de asociarle un departamento a un empleado
			body ejemplo:
			{
			  "idDepartamento": 0,
			  "numeroDocumento": "string"
			}
	9. POST http://localhost:8081/tcs/funcion/registro/empleado Asigna las funciones a un empleado dependiente del departamento donde este
			body ejemplo:
			{
			  "idDepartamento": 0,
			  "idFuncion": 0,
			  "numeroDocumento": "string"
			}
	10. POST http://localhost:8081/tcs/persona/click 
			body ejemplo:
			{
			  "contador": 0,
			  "idPersona": 0,
			  "idPlaneta": 0,
			  "name": "string"
			}
			guarda registros en la BD a la persona y su planeta asiciado al que se le hace click
			
	11. POST http://localhost:8081/tcs/planeta/click 
			body ejemplo:
			{
			  "contador": 0,
			  "idPlaneta": 0,
			  "name": "string"
			}
			guarda registros en la BD al planeta que se le hace click
6. Ingreso a la BD H2
	url: http://localhost:8081/tcs/h2-console/login.jsp
	JDBC URL: jdbc:h2:mem:tcs
	User name: tcs
	Password: tcs
	
	Tablas: DEPARTAMENTO, EMPLEADO, FUNCION, USUARIO 
7. Pruebas unitarias con JUnit	 
8. FrontEnd esta en la ruta: https://github.com/ricardoavendano/tcsFrontend
