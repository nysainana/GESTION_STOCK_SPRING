package mg.njaka.gestionstock.controllers;

import mg.njaka.gestionstock.dto.auth.AuthenticationRequest;
import mg.njaka.gestionstock.dto.auth.AuthenticationResponse;
import mg.njaka.gestionstock.model.auth.ExtendedUser;
import mg.njaka.gestionstock.services.auth.ApplicationsUserDetailService;
import mg.njaka.gestionstock.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static mg.njaka.gestionstock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final ApplicationsUserDetailService userDetailService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            ApplicationsUserDetailService userDetailService,
            JwtUtils jwtUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPasword()
                )
        );

        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getLogin());

        final  String jwt = jwtUtils.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}
