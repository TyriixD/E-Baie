package ca.ulaval.glo2003.ebaie.Datastore;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.util.Optional;

public class DatastoreSingleton {

    private static final DatastoreSingleton instance = new DatastoreSingleton();
    private final Datastore datastore;


    private DatastoreSingleton() {
        String databaseUrl = Optional.ofNullable(System.getenv("MONGODB_URL")).orElse("mongodb://localhost:27017");
        String databaseName = Optional.ofNullable(System.getenv("PROFILE")).orElse("ebaie");

        MongoClient mongoClient = MongoClients.create(databaseUrl);
        datastore = Morphia.createDatastore(mongoClient, databaseName);
        datastore.getMapper().mapPackage("ca.ulaval.glo2003.ebaie");
        datastore.ensureIndexes();
    }

    public static DatastoreSingleton getInstance() {
        return instance;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}