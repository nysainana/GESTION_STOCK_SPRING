package mg.njaka.gestionstock.repository;

import mg.njaka.gestionstock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {


}
