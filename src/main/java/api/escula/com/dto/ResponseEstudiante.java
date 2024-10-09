package api.escula.com.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEstudiante implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer eid;
	private String nombre;
	private String especialidad;
	private String grado;
	@JsonIgnore
	private String messageError;
}
