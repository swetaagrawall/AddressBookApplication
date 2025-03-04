package com.example.addressbookapplication.controller;


import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBook;
import com.example.addressbookapplication.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/addressbook")
public class AddressBookController {

    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    // GET All Contacts
    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        log.info("GET /addressbook - Fetching all contacts from DB");
        List<AddressBook> contacts = addressBookService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    // GET Contact By ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable Long id) {
        log.info("GET /addressbook/{} - Fetching contact from DB", id);
        Optional<AddressBook> contactOptional = addressBookService.getContactById(id);
        if (contactOptional.isPresent()) {
            return ResponseEntity.ok(contactOptional.get());
        } else {
            log.warn("Contact with ID {} not found in DB", id);
            return ResponseEntity.notFound().build();
        }
    }

    // POST Add New Contact
    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBookDTO dto) {
        log.info("POST /addressbook - Adding new contact to DB: {}", dto);
        AddressBook newContact = addressBookService.addContact(dto);
        log.info("New contact added successfully: {}", newContact);
        return ResponseEntity.ok(newContact);
    }

    // PUT Update Contact By ID
    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateContact(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        log.info("PUT /addressbook/{} - Updating contact in DB: {}", id, dto);
        Optional<AddressBook> updatedContactOptional = addressBookService.updateContact(id, dto);
        if (updatedContactOptional.isPresent()) {
            return ResponseEntity.ok(updatedContactOptional.get());
        } else {
            log.warn("Update failed - Contact with ID {} not found in DB", id);
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE Contact By ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        boolean deleted = addressBookService.deleteContact(id);
        if (deleted) {
            log.info("Contact with ID {} deleted successfully from DB", id);
            return ResponseEntity.ok("Deleted Successfully");
        } else {
            log.warn("Delete failed - Contact with ID {} not found in DB", id);
            return ResponseEntity.notFound().build();
        }
    }
}