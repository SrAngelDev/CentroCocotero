package srangeldev.centrococotero.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import srangeldev.centrococotero.models.Usuario;
import srangeldev.centrococotero.repositories.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== INTENTO DE LOGIN ===");
        System.out.println("Buscando usuario con email: " + username);
        
        Usuario usuario = userRepository.findFirstByEmail(username);

        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado en la base de datos");
            throw new UsernameNotFoundException("User no encontrado");
        }

        System.out.println("✅ Usuario encontrado: " + usuario.getEmail());
        System.out.println("Password hash almacenado: " + usuario.getPassword());
        System.out.println("Rol: " + usuario.getRol());
        
        return usuario;
    }
}
