package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Repository.BeneficiarRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BeneficiarDetailsService implements UserDetailsService {

    private final BeneficiarRepository beneficiarRepository;

    public BeneficiarDetailsService(BeneficiarRepository beneficiarRepository) {
        this.beneficiarRepository = beneficiarRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Beneficiar beneficiar = beneficiarRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Beneficiarul cu email-ul " + email + " nu a fost găsit."));

        return User.builder()
                .username(beneficiar.getEmail())
                .password(beneficiar.getParola())
                .build();
    }
}
