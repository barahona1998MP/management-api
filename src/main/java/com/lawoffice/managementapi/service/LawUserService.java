package com.lawoffice.managementapi.service;

import com.lawoffice.managementapi.entity.LawUser;
import com.lawoffice.managementapi.repository.LawUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LawUserService {
    @Autowired
    private LawUserRepository lawUserRepository;

    public List<LawUser> findAll() {
        return (List<LawUser>) lawUserRepository.findAll();
    }

    public Optional<LawUser> findById(Integer id) {
        return lawUserRepository.findById(id);
    }

    public Optional<LawUser> findByUsername(String username) {
        return lawUserRepository.findByUsername(username);
    }

    public void save(LawUser lawUser) {
        lawUserRepository.save(lawUser);
    }

    public void delete(Integer id) {
        lawUserRepository.deleteById(id);
    }

    public boolean existById(Integer id) {
        return lawUserRepository.existsById(id);
    }

    public boolean existsByUserName(String userName) {
        return lawUserRepository.existsByUsername(userName);
    }


    public boolean existsByEmail(String email) {
        Optional<LawUser> user = lawUserRepository.findByEmail(email);
        return user.isPresent();
    }


    private boolean isValidPassword(String password) {
        // Implementar lógica para validar la fortaleza de la contraseña
        // Por ejemplo, puedes usar expresiones regulares para verificar los requisitos
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(passwordPattern);
    }
}
