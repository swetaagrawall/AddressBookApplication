package com.example.addressbookapplication.controller;


import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBook;
import com.example.addressbookapplication.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

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

    // Get Contact By ID with Exception Handling
    @GetMapping("/contacts/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable Long id) {
        log.info("Received request to fetch contact with ID: " + id);
        AddressBook contact = addressBookService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    // Add New Contact with Validation
    @PostMapping("/contacts")
    public ResponseEntity<?> addContact(@Valid @RequestBody AddressBookDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (var error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        log.info("Received request to add new contact: " + dto);
        AddressBook savedContact = addressBookService.addContact(dto);
        return ResponseEntity.ok(savedContact);
    }

    // Update Contact By ID with Exception Handling
    @PutMapping("/contacts/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @Valid @RequestBody AddressBookDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (var error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        log.info("Received request to update contact with ID: " + id);
        AddressBook updatedContact = addressBookService.updateContact(id, dto);
        return ResponseEntity.ok(updatedContact);
    }

    // Delete Contact By ID with Exception Handling
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        log.info("Received request to delete contact with ID: " + id);
        addressBookService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}