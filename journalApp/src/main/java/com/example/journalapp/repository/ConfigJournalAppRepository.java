package com.example.journalapp.repository;

import com.example.journalapp.entity.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

public interface ConfigJournalAppRepository extends MongoRepository <ConfigJournalApp, ObjectId> {
}
