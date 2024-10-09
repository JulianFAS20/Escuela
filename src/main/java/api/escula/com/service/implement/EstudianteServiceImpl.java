package api.escula.com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.escula.com.dao.EstudianteDao;
import api.escula.com.domain.Estudiante;
import api.escula.com.dto.RequestCreateEstudiante;
import api.escula.com.dto.RequestUpdateEstudiante;
import api.escula.com.dto.ResponseEstudiante;
import api.escula.com.service.interfaces.EstudianteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EstudianteServiceImpl implements EstudianteService {

	@Autowired
	private EstudianteDao estudianteDao;
	
	@Override
	public List<Estudiante> getStudents() {
		return estudianteDao.findAll();
	}

	@Override
	public Estudiante getStudentsById(int id) {
		return estudianteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public ResponseEstudiante createStudent(RequestCreateEstudiante createStudent) {
		ResponseEstudiante responseStudent = ResponseEstudiante.builder().build();
		Estudiante student = convertRequestCreateEstudianteToEstudiante(createStudent);
		Estudiante studentExist = estudianteDao.findByNombre(student.getNombre());
		
		if (studentExist != null) {
			log.error("El nombre de usuario ya existe");
			responseStudent.setMessageError("El nombre de usuario ya existe");
			return responseStudent;
		}
		
		try {
			estudianteDao.save(student);
			return convertEstudianteToResponseEstudiante(estudianteDao.findByNombre(student.getNombre()));
		} catch (Exception e) {
			log.error("Ocurrio un error durante el guardado del estudiante");
			responseStudent.setMessageError("Ocurrio un error durante el guardado del estudiante");
			return responseStudent;
		}
	}

	@Override
	@Transactional
	public ResponseEstudiante updateStudent(RequestUpdateEstudiante updateStudent) {
		ResponseEstudiante responseStudent = ResponseEstudiante.builder().build();
		Estudiante studentExist = estudianteDao.findById(updateStudent.getEid()).orElse(null);
		
		if (studentExist == null) {
			log.error("El usuario no fue encontrado en la BD por su Id, por favor verifique su existencia");
			responseStudent.setMessageError("El usuario no fue encontrado en la BD por su Id, por favor verifique su existencia");
			return responseStudent;
		}
		
		studentExist = estudianteDao.findByNombre(updateStudent.getNombre());
		
		if (studentExist != null) {
			log.error("El nombre de usuario ya existe");
			responseStudent.setMessageError("El nombre de usuario ya existe");
			return responseStudent;
		}
		
		try {
			Estudiante studentValid = convertRequestUpdateEstudianteToEstudiante(updateStudent);
			estudianteDao.save(studentValid);
			return convertEstudianteToResponseEstudiante(studentValid);
		} catch (Exception e) {
			log.error("Ocurrio un error durante el guardado del estudiante");
			responseStudent.setMessageError("Ocurrio un error durante el guardado del estudiante");
			return responseStudent;
		}
	}

	@Override
	@Transactional
	public boolean deleteStudentById(int id) {
		try {
			estudianteDao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteStudentByName(String name) {
		try {
			estudianteDao.deleteByNombre(name);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteStudent(RequestUpdateEstudiante deleteStudent) {
		try {
			estudianteDao.delete(convertRequestUpdateEstudianteToEstudiante(deleteStudent));;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Estudiante convertRequestCreateEstudianteToEstudiante(RequestCreateEstudiante estudianteReq) {
	    try {
	        Estudiante estudiante = new Estudiante();
	        estudiante.setNombre(estudianteReq.getNombre());
	        estudiante.setEspecialidad(estudianteReq.getEspecialidad());
	        estudiante.setGrado(estudianteReq.getGrado());
	        return estudiante;
	    } catch (Exception e) {
	        return null;
	    }
	}
	
	public ResponseEstudiante convertEstudianteToResponseEstudiante(Estudiante student) {
		try {
			return ResponseEstudiante.builder().eid(student.getEid())
					.nombre(student.getNombre())
					.especialidad(student.getEspecialidad())
					.grado(student.getGrado())
					.build();
		} catch (Exception e) {
			return ResponseEstudiante.builder().messageError("Algo fallo durante la conversion de la respuesta del estudiante")
					.build();
		}
	}
	
	public Estudiante convertRequestUpdateEstudianteToEstudiante(RequestUpdateEstudiante requestEst) {
		try {
			Estudiante estudiante = new Estudiante();
			estudiante.setEid(requestEst.getEid());
	        estudiante.setNombre(requestEst.getNombre());
	        estudiante.setEspecialidad(requestEst.getEspecialidad());
	        estudiante.setGrado(requestEst.getGrado());
	        return estudiante;
		} catch (Exception e) {
			return null;
		}
	}
}
