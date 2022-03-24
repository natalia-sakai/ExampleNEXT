package br.com.ibm.example.repository;

import br.com.ibm.example.domain.ClientDto;
import br.com.ibm.example.entity.Client;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

public class FirebaseImpl implements FirebaseRepository {

    private final Class<Client> entityClass = Client.class;
    private final CollectionReference collectionReference;
    private String collectionName = "clients";

    public FirebaseImpl() {
        Firestore db = FirestoreClient.getFirestore();
        this.collectionReference = db.collection(this.collectionName);
    }

//    public List<Client> GetAll() {
//        try {
//            ApiFuture<QuerySnapshot> future = collectionReference.get();
//
//            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//            List<Client> entities = new ArrayList<>();
//            for (QueryDocumentSnapshot document : documents) {
//                entities.add(document.toObject(entityClass));
//            }
//            return entities;
//        } catch (Exception e){
//            return null;
//        }
//    }
//
//    public Client GetById(String id) {
//        try {
//            DocumentReference documentReference = collectionReference.document(id);
//            ApiFuture<DocumentSnapshot> future = documentReference.get();
//
//            DocumentSnapshot document = future.get();
//
//            if (document.exists()) {
//                return document.toObject(entityClass);
//            } else {
//                return null;
//            }
//        } catch (Exception e){
//            return null;
//        }
//    }
//
//    public List<Client> GetByName(String name) {
//        try {
//            Object obj = name;
//            ApiFuture<QuerySnapshot> future = collectionReference.whereEqualTo("name", obj).get();
//
//            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//            List<Client> entities = new ArrayList<>();
//            for (QueryDocumentSnapshot document : documents) {
//                entities.add(document.toObject(entityClass));
//            }
//            return entities;
//        } catch (Exception e){
//            return null;
//        }
//    }
//
//    public Client Update(ClientDto entity, String id) {
//        try {
//            ApiFuture<WriteResult> collectionsApiFuture = collectionReference.document(id).set(entity);
//
//            return new Client(entity.getId(), entity.getName());
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public void Delete(String id) {
//        try {
//            ApiFuture<WriteResult> writeResult = collectionReference.document(id).delete();
//        } catch (Exception e) {
//            return;
//        }
//    }
//
//    public Client Save(ClientDto entity) {
//        try {
//            ApiFuture<WriteResult> collectionsApiFuture = collectionReference.document(entity.getId().toString()).set(entity);
//
//            return new Client(entity.getId(), entity.getName());
//        } catch (Exception e) {
//            return null;
//        }
//    }
}