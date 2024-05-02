package com.lawoffice.managementapi.security;

import com.lawoffice.managementapi.entity.LawUser;
import com.lawoffice.managementapi.entity.Role;
import com.lawoffice.managementapi.repository.LawUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailService implements UserDetailsService {

    private LawUserRepository lawUserRepository;
    public CustomUserDetailService(LawUserRepository lawUserRepository) {
        this.lawUserRepository = lawUserRepository;
    }

    /* 3. Tercero
     * Este método convierte un conjunto de roles en una colección
     * de autoridades concedidas. Itera a través de los roles y asigna
     * cada nombre de rol a una instancia de SimpleGrantedAuthority,
     * luego devuelve la colección resultante.
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    /* 4. Cuarto
    * Este método carga los detalles del usuario a partir del nombre de usuario
    * proporcionado. Busca el usuario en el repositorio de usuarios
    * de la aplicación y, si existe, devuelve un UserDetails con el nombre de usuario,
    * contraseña y roles asociados al usuario encontrado. Si el usuario no se encuentra, se lanza una excepción UsernameNotFoundException.
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LawUser user = lawUserRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("username not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

}
