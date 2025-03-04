package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private Long idCounter = 1L; // Auto-increment ID

    // Get All Contacts
    public List<AddressBook> getAllContacts() {
        log.info("Fetching all contacts, total count: {}", addressBookList.size());
        return addressBookList;
    }

    // Get Contact By ID
    public AddressBook getContactById(Long id) {
        log.info("Fetching contact with ID: {}", id);
        for (AddressBook contact : addressBookList) {
            if (contact.getId().equals(id)) {
                log.info("Fetching contact with ID: {}", id);
                return contact;
            }
        }
        log.warn("Contact with ID {} not found", id);
        return null;
    }

    // Add New Contact (Store in Memory)
    public AddressBook addContact(AddressBookDTO dto) {
        AddressBook contact = new AddressBook(
                idCounter++,
                dto.getName(),
                dto.getPhone(),
                dto.getEmail()
        );
        addressBookList.add(contact);
        log.info("New contact added: {}", contact);
        return contact;
    }

    // Update Contact By ID
    public AddressBook updateContact(Long id, AddressBookDTO dto) {
        log.info("Updating contact with ID: {}", id);
        for (AddressBook contact : addressBookList) {
            if (contact.getId().equals(id)) {
                contact.setName(dto.getName());
                contact.setPhone(dto.getPhone());
                contact.setEmail(dto.getEmail());
                log.info("Updated contact: {}", contact);
                return contact;
            }
        }
        log.warn("Update failed - Contact with ID {} not found", id);
        return null;
    }

    // Delete Contact By ID
    public boolean deleteContact(Long id) {
        log.info("Deleting contact with ID: {}", id);
        boolean removed = addressBookList.removeIf(contact -> contact.getId().equals(id));
        if (removed) {
            log.info("Contact with ID {} deleted successfully", id);
        } else {
            log.warn("Delete failed - Contact with ID {} not found", id);
        }
        return removed;
    }
}