package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private Long idCounter = 1L; // Auto-increment ID

    // Get All Contacts
    public List<AddressBook> getAllContacts() {
        return addressBookList;
    }

    // Get Contact By ID
    public AddressBook getContactById(Long id) {
        for (AddressBook contact : addressBookList) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }
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
        addressBookList.add(contact); // Store in memory
        return contact;
    }

    // Update Contact By ID
    public AddressBook updateContact(Long id, AddressBookDTO dto) {
        for (AddressBook contact : addressBookList) {
            if (contact.getId().equals(id)) {
                contact.setName(dto.getName());
                contact.setPhone(dto.getPhone());
                contact.setEmail(dto.getEmail());
                return contact;
            }
        }
        return null;
    }

    // Delete Contact By ID
    public boolean deleteContact(Long id) {
        return addressBookList.removeIf(contact -> contact.getId().equals(id));
    }
}