package api.escula.com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import api.escula.com.domain.Estudiante;
import api.escula.com.dto.RequestCreateEstudiante;
import api.escula.com.dto.RequestUpdateEstudiante;
import api.escula.com.dto.ResponseEstudiante;
import api.escula.com.service.interfaces.EstudianteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/EscuelaApi") // Ruta base del controlador
public class EstudianteController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	@GetMapping("/obtenerEstudiantes") // Ruta especifica del endpoint de primer nivel
	public ResponseEntity<?> getStudents(){
		List<Estudiante> students = new ArrayList<>();
		try {
			log.info("Consultando la lista de estudiantes");
			students = estudianteService.getStudents();
			if (students.isEmpty()) {
				return new ResponseEntity<List<Estudiante>>(students, HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<List<Estudiante>>(students, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/obtenerEstudiantePorId/{id}")
	public ResponseEntity<?> getStudentsById(@PathVariable Integer id){
		// Validación del ID
        if (id == null || id <= 0) {
            return new ResponseEntity<String>("El ID debe ser un número entero positivo", HttpStatus.BAD_REQUEST);
        }
		
		Estudiante student = new Estudiante();
		try {
			log.info("Consultando estudiante");
			student = estudianteService.getStudentsById(id);
			if (student == null) {
				return new ResponseEntity<Estudiante>(student, HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<Estudiante>(student, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/crearEstudiante")
	public ResponseEntity<?> createTicket(@Valid @RequestBody RequestCreateEstudiante request, BindingResult result){
		if (result.hasErrors()) {
	        StringBuilder errorMessage = new StringBuilder("Errores de validación: ");
	        for (FieldError error : result.getFieldErrors()) {
	            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
	        }
	        return new ResponseEntity<String>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	    }
		
		try {
			ResponseEstudiante response = estudianteService.createStudent(request);
			if (response == null) {
				return new ResponseEntity<String>("Ocurrio un error durante la creacion del ticket", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else if(response.getMessageError() != null && !response.getMessageError().isEmpty()){
				return new ResponseEntity<String>(response.getMessageError(), HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<ResponseEstudiante>(response, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	@PutMapping("/actualizarEstudiante")
	public ResponseEntity<?> updateTicket(@Valid @RequestBody RequestUpdateEstudiante request, BindingResult result){
		if (result.hasErrors()) {
	        StringBuilder errorMessage = new StringBuilder("Errores de validación: ");
	        for (FieldError error : result.getFieldErrors()) {
	            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
	        }
	        return new ResponseEntity<String>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	    }
		
		try {
			ResponseEstudiante response = estudianteService.updateStudent(request);
			if (response == null) {
				return new ResponseEntity<String>("Ocurrio un error durante la actualizacion del ticket", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else if(response.getMessageError() != null && !response.getMessageError().isEmpty()){
				return new ResponseEntity<String>(response.getMessageError(), HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<ResponseEstudiante>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminarEstudiantePorId/{id}")
	public ResponseEntity<?> deleteTicketById(@PathVariable Integer id){
		try {
			log.info("Eliminando estudiante por id");
			boolean delete = estudianteService.deleteStudentById(id);
			if (delete) {
				return new ResponseEntity<String>("Estudiante Eliminado", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("No se pudo eliminar el estudiante", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminarEstudiantePorNombre/{name}")
	public ResponseEntity<?> deleteTicketByUsername(@PathVariable String name){
		try {
			log.info("Eliminando estudiante por usuario");
			boolean delete = estudianteService.deleteStudentByName(name);
			if (delete) {
				return new ResponseEntity<String>("Estudiante Eliminado", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("No se pudo eliminar el estudiante", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminarEstudiante")
	public ResponseEntity<?> deleteTicket(@Valid @RequestBody RequestUpdateEstudiante request, BindingResult result){
		if (result.hasErrors()) {
	        StringBuilder errorMessage = new StringBuilder("Errores de validación: ");
	        for (FieldError error : result.getFieldErrors()) {
	            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
	        }
	        return new ResponseEntity<String>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	    }
		
		try {
			log.info("Eliminando estudiante");
			boolean delete = estudianteService.deleteStudent(request);
			if (delete) {
				return new ResponseEntity<String>("Estudiante Eliminado", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("No se pudo eliminar el estudiante", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
