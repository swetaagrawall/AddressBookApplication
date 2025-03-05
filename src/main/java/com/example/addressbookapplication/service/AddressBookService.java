package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBook;
import com.example.addressbookapplication.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Validated
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;

    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    // Get All Contacts
    public List<AddressBook> getAllContacts() {
        log.info("Fetching all contacts from the database");
        return addressBookRepository.findAll();
    }

    // Get Contact By ID
    public Optional<AddressBook> getContactById(Long id) {
        log.info("Fetching contact with ID: {}", id);
        return addressBookRepository.findById(id);
    }

    // Add New Contact with Validation
    public AddressBook addContact(@Valid AddressBookDTO dto) {
        log.info("Adding new contact: {}", dto);
        AddressBook contact = new AddressBook();
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());

        return addressBookRepository.save(contact);
    }

    // Update Contact By ID with Validation
    public Optional<AddressBook> updateContact(Long id, @Valid AddressBookDTO dto) {
        log.info("Updating contact with ID: {}", id);
        Optional<AddressBook> contactOptional = addressBookRepository.findById(id);

        if (contactOptional.isPresent()) {
            AddressBook contact = contactOptional.get();
            contact.setName(dto.getName());
            contact.setPhone(dto.getPhone());
            contact.setEmail(dto.getEmail());

            return Optional.of(addressBookRepository.save(contact));
        } else {
            log.warn("Update failed - Contact with ID {} not found", id);
            return Optional.empty();
        }
    }

    // Delete Contact By ID
    public boolean deleteContact(Long id) {
        log.info("Deleting contact with ID: {}", id);
        if (addressBookRepository.existsById(id)) {
            addressBookRepository.deleteById(id);
            log.info("Contact with ID {} deleted successfully", id);
            return true;
        } else {
            log.warn("Delete failed - Contact with ID {} not found", id);
            return false;
        }
    }
}