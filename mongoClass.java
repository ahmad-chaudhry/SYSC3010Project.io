package mongoConnector;


import java.net.UnknownHostException;

import com.mongodb.client.*;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class mongoClass {

	public static void main(String[] args) {
		
	
			
	
	try {
	MongoClient mongoClient = new MongoClient("localhost",27017);
	
	DB db = mongoClient.getDB("BusNumber");
	DBCollection coll = db.getCollection("BusNumber");
	DBCursor cursor = coll.find();
	
	while(cursor.hasNext())
	{
		int i =1;
		System.out.println(cursor.next());
		i++;
	}

	System.out.println();
	System.out.println();
	System.out.println();
	
	DB db2 = mongoClient.getDB("CapacityZones");
	DBCollection coll2 = db2.getCollection("CapacityZones");
	DBCursor cursor2 = coll2.find();
	
	while(cursor2.hasNext())
	{
		int j =1;
		System.out.println(cursor2.next());
		j++;
	}
	
	
	System.out.println();
	System.out.println();
	System.out.println();
	
	DB db3 = mongoClient.getDB("TotalPassengers");
	DBCollection coll3 = db3.getCollection("TotalPassengers");
	DBCursor cursor3 = coll3.find();
	
	while(cursor3.hasNext())
	{
		int k =1;
		System.out.println(cursor3.next());
		k++;
	}
	

	
	
	
	
	
	} 
		catch(UnknownHostException e)
	{
		e.printStackTrace();
	}
	
}
}
		
	


