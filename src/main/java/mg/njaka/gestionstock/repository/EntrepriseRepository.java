package mg.njaka.gestionstock.repository;

import mg.njaka.gestionstock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {

}
