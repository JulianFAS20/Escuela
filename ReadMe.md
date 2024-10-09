# El ejercicio de maquetacion y el documento con los demas ejercicios se encuentra en la carpeta denominada: EjercicioMaquetacion_Y_Doc

# Getting Started
El siguiente archivo tiene como finalidad dar instrucciones claras para la ejecución de este proyecto en un entorno local:
1. Debe tener instaladas herramientas tales como Postman, Eclipse, Docker Desktop, teniendo en cuenta que se requiere 
tener instaladas todas las herramientas necesarias para el despliegue de una aplicacion Java, 
como el JDK, Maven, y la herramienta de eclipse o SpringToolSuite4 debidamente configurada para abrir y ejecutar
un proyecto Spring Boot
2. Se requiere que el proyecto sea importado al entorno de desarrolo de eclipse como un proyecto Spring Boot
3. El proyecto debe ser limpiado y compilado con maven, para que primeramente elimine resultados de empaquetado y
compilaciones previas, y posteriormente reconstruya estos archivos, que me permitiran la ejecución del mismo
3. Dirigirse a esta ruta del proyecto "EscuelaApiRest\src\main\resources\DB" y alli ejecutarás el siguiente comando de docker
desde la linea de comandos:
	docker-compose up -d
4. Ejecutar el proyecto como una Spring Boot App
5. Importar la coleccion de postman que contiene todos los servicios del API Rest, esta se encuentra en la ruta:
"EscuelaApiRest\src\main\resources\PostmanCollection\EscuelaApiRest.postman_collection.json"