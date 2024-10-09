package api.escula.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import api.escula.com.domain.Estudiante;

public interface EstudianteDao extends JpaRepository<Estudiante, Integer>{
	Estudiante findByNombre(String usuario);
	
	void deleteByNombre(String usuario);
}
