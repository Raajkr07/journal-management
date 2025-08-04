package com.example.journalapp.controller;

import com.example.journalapp.entity.JournalEntry;
import com.example.journalapp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        try {
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_GATEWAY));
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("Id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalEntryService.findById(myId).orElse(null);

        if(old != null) {
            old.setContent(newEntry.getContent() != null && !newEntry.getTitle().isEmpty() ? newEntry.getContent() : old.getContent());
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
