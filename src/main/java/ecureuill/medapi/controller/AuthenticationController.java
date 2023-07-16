package ecureuill.medapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecureuill.medapi.domain.user.User;
import ecureuill.medapi.domain.user.UserAuthRecord;
import ecureuill.medapi.infra.security.TokenRecord;
import ecureuill.medapi.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid UserAuthRecord data) {
        var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authenticate = manager.authenticate(token);
        var tokenJwt = tokenService.gerarToken((User)authenticate.getPrincipal());
        
        return ResponseEntity.ok(new TokenRecord(tokenJwt));
    }
}
