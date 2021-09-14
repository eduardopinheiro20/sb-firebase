package com.example.firebase.sbfirebasedemo.service;

import com.example.firebase.sbfirebasedemo.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private static final String COLLECTION_NAME = "products";

    public String saveProduct(Product pProduct) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture =  dbFirestore.collection(COLLECTION_NAME).document(pProduct.getName()).set(pProduct);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Product getProductDetails(String name) throws ExecutionException, InterruptedException {

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
