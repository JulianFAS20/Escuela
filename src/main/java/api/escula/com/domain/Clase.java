package api.escula.com.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pruebaclase")
@NoArgsConstructor
public class Clase implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "nombreclase")
    private String nombreClase; // Nombre de la clase como clave primaria

    private String horario;
    private String aula;
}
