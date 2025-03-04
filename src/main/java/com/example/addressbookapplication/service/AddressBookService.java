package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBook;
import com.example.addressbookapplication.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
        Optional<AddressBook> contact = addressBookRepository.findById(id);
        if (contact.isPresent()) {
            log.info("Contact found: {}", contact.get());
        } else {
            log.warn("Contact with ID {} not found", id);
        }
        return contact;
    }

    // Add New Contact
    public AddressBook addContact(AddressBookDTO dto) {
        AddressBook contact = new AddressBook();
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());

        AddressBook savedContact = addressBookRepository.save(contact);
        log.info("New contact added: {}", savedContact);
        return savedContact;
    }

    // Update Contact By ID
    public Optional<AddressBook> updateContact(Long id, AddressBookDTO dto) {
        log.info("Updating contact with ID: {}", id);
        Optional<AddressBook> contactOptional = addressBookRepository.findById(id);

        if (contactOptional.isPresent()) {
            AddressBook contact = contactOptional.get();
            contact.setName(dto.getName());
            contact.setPhone(dto.getPhone());
            contact.setEmail(dto.getEmail());

            AddressBook updatedContact = addressBookRepository.save(contact);
            log.info("Updated contact: {}", updatedContact);
            return Optional.of(updatedContact);
        } else {
            log.warn("Update failed - Contact with ID {} not found", id);
            return Optional.empty();
        }
    }

    // Delete Contact By ID
    public boolean deleteContact(Long id) {
        log.info("Deleting contact with ID: {}", id);
        Optional<AddressBook> contactOptional = addressBookRepository.findById(id);

        if (contactOptional.isPresent()) {
            addressBookRepository.deleteById(id);
            log.info("Contact with ID {} deleted successfully", id);
            return true;
        } else {
            log.warn("Delete failed - Contact with ID {} not found", id);
            return false;
        }
    }
}