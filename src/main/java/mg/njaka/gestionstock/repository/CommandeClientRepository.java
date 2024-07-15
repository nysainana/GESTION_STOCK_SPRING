package mg.njaka.gestionstock.repository;

import mg.njaka.gestionstock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {

}
