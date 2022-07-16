package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.user.BasicUser;
import ru.bryanin.dev.questionnairebackend.office.repository.BasicUserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BasicUserService {

    @Autowired
    private final BasicUserRepository basicUserRepository;

    public BasicUserService(BasicUserRepository basicUserRepository) {
        this.basicUserRepository = basicUserRepository;
    }

    public List<BasicUser> getAllUsers() {
        return basicUserRepository.findAll();
    }

    public BasicUser getUser(Long id) {
        return basicUserRepository.findById(id).orElseThrow(() -> new IllegalStateException("Сотрудник с id = " + id + " не существует"));
    }

    public BasicUser addNewUser(BasicUser basicUser) {
        Optional<BasicUser> basicUserOptional = basicUserRepository.findBasicUserByEmail(basicUser.getEmail());
        if(basicUserOptional.isPresent()) {
            throw new IllegalStateException("Сотрудник с данным email уже зарегистрирован");
        }
        basicUserRepository.save(basicUser);
        return basicUserRepository.findBasicUserByEmail(basicUser.getEmail()).get();
    }

    public void deleteUser(Long id) {
        boolean exists = basicUserRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Сотрудник с id = " + id + " не существует");
        }
        basicUserRepository.deleteById(id);
    }

    @Transactional
    public BasicUser updateUser(Long id, BasicUser basicUser) {
        BasicUser basicUserFromDB = basicUserRepository.findById(id).orElseThrow(() -> new IllegalStateException("Сотрудник с id = " + id + " не существует"));

        if(basicUser.getEmail() != null && basicUser.getEmail().length() > 0 && !Objects.equals(basicUser.getEmail(), basicUserFromDB.getEmail())) {
            Optional<BasicUser> basicUserOptional = basicUserRepository.findBasicUserByEmail(basicUser.getEmail());
            if(basicUserOptional.isPresent()) {
                throw new IllegalStateException("Сотрудник с данным email уже зарегистрирован");
            }
            basicUserFromDB.setEmail(basicUser.getEmail());
        }
        if(basicUser.getFirstName() != null && basicUser.getFirstName().length() > 0 && !Objects.equals(basicUser.getFirstName(), basicUserFromDB.getFirstName())) {
            basicUserFromDB.setFirstName(basicUser.getFirstName());
        }
        if(basicUser.getLastName() != null && basicUser.getLastName().length() > 0 && !Objects.equals(basicUser.getLastName(), basicUserFromDB.getLastName())) {
            basicUserFromDB.setLastName(basicUser.getLastName());
        }
        if(basicUser.getPhone() != null && basicUser.getPhone().length() > 0 && !Objects.equals(basicUser.getPhone(), basicUserFromDB.getPhone())) {
            basicUserFromDB.setPhone(basicUser.getPhone());
        }
        return basicUserFromDB;
    }
}
