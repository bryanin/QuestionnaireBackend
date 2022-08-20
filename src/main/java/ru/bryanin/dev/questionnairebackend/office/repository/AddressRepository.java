package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.model.project.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
