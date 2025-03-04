package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.AddressBookDTO;
import com.example.addressbookapplication.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private Long idCounter = 1L;

    // Get All Contacts
    public List<AddressBook> getAllContacts() {
        return addressBookList;
    }


    // Add New Contact
    public AddressBook addContact(AddressBookDTO dto) {
        AddressBook contact = new AddressBook(
                dto.getName(),
                dto.getPhone(),
                dto.getEmail()
        );
        contact.setId(idCounter++);  // Auto-incrementing ID
        addressBookList.add(contact);
        return contact;
    }
}
