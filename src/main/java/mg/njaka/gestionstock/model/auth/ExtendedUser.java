package mg.njaka.gestionstock.model.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ExtendedUser extends User {

    @Getter
    @Setter
    private int idEntreprise;

    public ExtendedUser(String username, String password,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ExtendedUser(String username, String password, int idEtreprise,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.idEntreprise = idEtreprise;
    }
}
