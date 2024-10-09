package api.escula.com.service.interfaces;

import java.util.List;

import api.escula.com.domain.Estudiante;
import api.escula.com.dto.RequestCreateEstudiante;
import api.escula.com.dto.RequestUpdateEstudiante;
import api.escula.com.dto.ResponseEstudiante;

public interface EstudianteService {
	List<Estudiante> getStudents();
	
	Estudiante getStudentsById(int id);
	
	ResponseEstudiante createStudent(RequestCreateEstudiante createStudent);
	
	ResponseEstudiante updateStudent(RequestUpdateEstudiante updateStudent);
	
	boolean deleteStudentById(int id);
	
	boolean deleteStudentByName(String name);
	
	boolean deleteStudent(RequestUpdateEstudiante deleteStudent);
}
