package com.quirotech.quirotech.repositories;

import com.quirotech.quirotech.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
