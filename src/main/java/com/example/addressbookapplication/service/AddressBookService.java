package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.exception.AddressBookException;
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

    // Get Contact By ID with Exception Handling
    public AddressBook getContactById(Long id) {
        log.info("Fetching contact with ID: {}", id);
        Optional<AddressBook> contact = addressBookRepository.findById(id);
        if (!contact.isPresent()) {
            throw new AddressBookException("Contact with ID " + id + " not found!");
        }
        return contact.get();
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

    // Update Contact By ID with Exception Handling
    public AddressBook updateContact(Long id, @Valid AddressBookDTO dto) {
        log.info("Updating contact with ID: {}", id);
        Optional<AddressBook> contactOptional = addressBookRepository.findById(id);
        if (!contactOptional.isPresent()) {
            throw new AddressBookException("Cannot update! Contact with ID " + id + " not found!");
        }

        AddressBook contact = contactOptional.get();
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());

        return addressBookRepository.save(contact);
    }

    // Delete Contact By ID with Exception Handling
    public boolean deleteContact(Long id) {
        log.info("Deleting contact with ID: {}", id);
        Optional<AddressBook> contact = addressBookRepository.findById(id);
        if (!contact.isPresent()) {
            throw new AddressBookException("Cannot delete! Contact with ID " + id + " not found!");
        }
        addressBookRepository.deleteById(id);
        log.info("Contact with ID {} deleted successfully", id);
        return false;
    }
}