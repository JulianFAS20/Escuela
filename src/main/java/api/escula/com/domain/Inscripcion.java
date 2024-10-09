package api.escula.com.domain;

import java.io.Serializable;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pruebainscripcion")
@NoArgsConstructor
public class Inscripcion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @ManyToOne
    @JoinColumn(name = "eid", referencedColumnName = "eid")
	@JsonBackReference
    private Estudiante estudiante; // Referencia al estudiante

    @Id
    @Column(name = "nombreclase")
    private String nombreClase; // Se mantendrá como un String para reflejar el VARCHAR(5)

    @Column(nullable = false)
    @Check(constraints = "Posicion BETWEEN 1 AND 9")
    private Integer posicion;
}
