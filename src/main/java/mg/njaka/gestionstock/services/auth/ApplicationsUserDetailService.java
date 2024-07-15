package mg.njaka.gestionstock.services.auth;

import mg.njaka.gestionstock.dto.UtilisateurDto;
import mg.njaka.gestionstock.exception.EntityNotFoundException;
import mg.njaka.gestionstock.exception.ErrorCodes;
import mg.njaka.gestionstock.model.Utilisateur;
import mg.njaka.gestionstock.model.auth.ExtendedUser;
import mg.njaka.gestionstock.repository.UtilisateurRepository;
import mg.njaka.gestionstock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ApplicationsUserDetailService  implements UserDetailsService {

    private final UtilisateurService service;

    @Autowired
    public ApplicationsUserDetailService(UtilisateurService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto user = service.findUserByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getNomRole())));

        return new ExtendedUser(user.getEmail(), user.getMotDePasse(), user.getEntreprise().getId(), authorities);
    }

}
