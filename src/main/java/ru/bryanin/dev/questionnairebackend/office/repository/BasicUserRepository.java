package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.model.user.BasicUser;

import java.util.Optional;

@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {

    @Query("SELECT e from BasicUser e where e.email = ?1")
    Optional<BasicUser> findBasicUserByEmail(String email);
}