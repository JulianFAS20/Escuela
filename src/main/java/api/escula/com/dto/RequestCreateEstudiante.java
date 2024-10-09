package api.escula.com.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class RequestCreateEstudiante implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "El nombre no puede ser nulo")
	@NotEmpty(message = "El nombre de usuario no puede estar vacio")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo debe contener letras")
	private String nombre;
	
	@NotNull(message = "La especialidad no puede ser nula")
	@NotEmpty(message = "La especialidad no puede estar vacia")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "La especialidad solo debe contener letras")
	private String especialidad;
	
	@NotNull(message = "El grado no puede ser nulo")
	@NotEmpty(message = "El grado no puede estar vacio")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El grado solo debe contener letras")
	private String grado;
}
