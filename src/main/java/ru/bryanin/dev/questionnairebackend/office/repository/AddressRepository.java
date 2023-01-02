package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.model.project.Address;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT e from Address e where e.postalCode = ?1 and e.country = ?2 and e.region = ?3 and e.city = ?4 and e.settlement = ?5 and e.street = ?6 and e.house = ?7 and e.block = ?8")
    Optional<Address> findIfExist(int postalCode, String country, String region, String city, String settlement, String street, String house, String block);



}
