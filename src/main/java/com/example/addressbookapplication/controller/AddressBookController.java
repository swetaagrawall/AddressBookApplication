package com.example.addressbookapplication.controller;


import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBook;
import com.example.addressbookapplication.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/addressbook")
@Slf4j
public class AddressBookController {

    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    // Get All Contacts
    @GetMapping("/contacts")
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        log.info("Received request to fetch all contacts");
        List<AddressBook> contacts = addressBookService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    // Get Contact By ID
    @GetMapping("/contacts/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable Long id) {
        log.info("Received request to fetch contact with ID: {}", id);
        Optional<AddressBook> contact = addressBookService.getContactById(id);
        if (contact.isPresent()) {
            return ResponseEntity.ok(contact.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add New Contact
    @PostMapping("/contacts")
    public ResponseEntity<AddressBook> addContact(@Valid @RequestBody AddressBookDTO dto) {
        log.info("Received request to add new contact: {}", dto);
        AddressBook savedContact = addressBookService.addContact(dto);
        return ResponseEntity.ok(savedContact);
    }

    // Update Contact By ID
    @PutMapping("/contacts/{id}")
    public ResponseEntity<AddressBook> updateContact(@PathVariable Long id, @Valid @RequestBody AddressBookDTO dto) {
        log.info("Received request to update contact with ID: {}", id);
        Optional<AddressBook> updatedContact = addressBookService.updateContact(id, dto);
        if (updatedContact.isPresent()) {
            return ResponseEntity.ok(updatedContact.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Contact By ID
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        log.info("Received request to delete contact with ID: {}", id);
        boolean isDeleted = addressBookService.deleteContact(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}