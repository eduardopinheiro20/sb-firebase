package com.example.firebase.sbfirebasedemo.service;

import com.example.firebase.sbfirebasedemo.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private static final String COLLECTION_NAME = "products";

    public String saveProduct(Product pProduct) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture =  dbFirestore.collection(COLLECTION_NAME).document(pProduct.getName()).set(pProduct);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Product getProductByName(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentoReference  =  dbFirestore.collection(COLLECTION_NAME).document(name);

        ApiFuture <DocumentSnapshot> future = documentoReference.get();

        DocumentSnapshot documentSnapshot = future.get();

        Product product = null;
        if( documentSnapshot.exists() ) {
           product = documentSnapshot.toObject(Product.class);
           return product;
        } else {
            return null;
        }
    }

    public List<Product> getProducts() throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentReference  =  dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Product> productsList = new ArrayList<>();
        Product product = null;

        while (iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture <DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot documentSnapshot = future.get();

            product = documentSnapshot.toObject(Product.class);
            productsList.add(product);
        }

        return productsList;
    }

    public String updateProduct(Product pProduct) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture =  dbFirestore.collection(COLLECTION_NAME).document(pProduct.getName()).set(pProduct);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteProduct(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture =  dbFirestore.collection(COLLECTION_NAME).document(name).delete();

        return "Document with Product ID " + name + " has been deleted successfully";
    }


}
