package MongoDBGetStarted.Program;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * Simple application that shows how to use Azure Cosmos DB with the MongoDB API and Java.
 *
 */
public class Program {

    public static void main(String[] args)
    {
	/*
	* Replace connection string from the Azure Cosmos Portal
        */
        MongoClientURI uri = new MongoClientURI("mongodb://arramac-mongo:5ALlMbFYrNLbd7p7LtDe6ioTNbrzv6TwqFwoyEvt919mNaLYQavwAqEjEKnTY82sy8D5s6D0OhSQdve6Ka6Peg==@arramac-mongo.documents.azure.com:10255/?ssl=true&sslInvalidHostNameAllowed=true");

        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(uri);        
            
            // Get database
            MongoDatabase database = mongoClient.getDatabase("db");

            // Get collection
            MongoCollection<Document> collection = database.getCollection("coll");

            // Insert documents
            Document document1 = new Document("fruit", "apple");
            collection.insertOne(document1);
            
            Document document2 = new Document("fruit", "mango");
            collection.insertOne(document2);            

            // Find fruits by name
            Document queryResult = collection.find(Filters.eq("fruit", "apple")).first();
            System.out.println(queryResult.toJson());    	
        	
            System.out.println( "Completed successfully" );  
            
        } finally {
        	if (mongoClient != null) {
        		mongoClient.close();
        	}
        }
    }
}
