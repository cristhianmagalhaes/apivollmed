package med.voll.api.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientesRepository extends JpaRepository<Paciente, Long> {

}
