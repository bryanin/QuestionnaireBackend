package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.entity.project.Address;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.AddressRepository;

import java.util.Optional;

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

//    public Address addNewAddress(Address address) {
//        Optional<Address> addressOptional = addressRepository.findById(address.getId());
//        Address addressFromDB = null;
//        if(addressOptional.isPresent()) {
//            return addressOptional.get();
//        } else {
//            addressRepository.save(address);
//
//        }
//        Optional<Address> optionalAddress = addressRepository.findIfExist(
//                address.getPostalCode(),
//                address.getCountry(),
//                address.getRegion(),
//                address.getCity(),
//                address.getSettlement(),
//                address.getStreet(),
//                address.getHouse(),
//                address.getBlock()
//        );
//        if(optionalAddress.isPresent()) {
//            return optionalAddress.get();
//        } else {
//            addressRepository.save(address);
//        }
//
//    }

}
