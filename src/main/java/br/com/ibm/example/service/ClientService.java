package br.com.ibm.example.service;

import br.com.ibm.example.domain.ClientDto;
import br.com.ibm.example.entity.Client;
import br.com.ibm.example.repository.ClientRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ClientService {

    public static final String COL_NAME="clients";
    @Autowired
    private ClientRepository repository;

    public List<Client> get() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Client> clients = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            clients.add(document.toObject(Client.class));
        }
        return clients;
//        return repository.findAll();
    }

    public Client get(Long id) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id.toString());
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Client client = null;

        if(document.exists()) {
            client = document.toObject(Client.class);
            return client;
        }else {
            return null;
        }
//        return repository.getOne(id);
    }

    public List<Client> getByName(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Object obj = name;
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).whereEqualTo("name", obj).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Client> clients = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            clients.add(document.toObject(Client.class));
        }
        return clients;
//        return repository.findByName(name);
    }

    public Client save(ClientDto dto) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(dto.getId().toString()).set(dto);

        return new Client(dto.getId(), dto.getName());
//        Client client = new Client(dto.getId(), dto.getName());
//        return repository.save(client);
    }

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id.toString()).delete();
//        repository.deleteById(id);
    }

    public Client update(Long id, ClientDto newData) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id.toString()).set(newData);

        return new Client(newData.getId(), newData.getName());

//        Client entity = repository.getOne(id);
//        updateData(entity, newData);
//        return repository.save(entity);
    }

    private void updateData(Client entity, ClientDto newData) {
        entity.setName(newData.getName());
    }
}
