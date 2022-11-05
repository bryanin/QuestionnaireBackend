package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.project.Address;
import ru.bryanin.dev.questionnairebackend.office.repository.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public String getAddressAsStringById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new IllegalStateException("Адрес с id = " + id + " не существует"));
        return address.toString();
    }

}
