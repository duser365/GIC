package poc.krish.hf;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;


public class NameFinder {
	public static final String tableName = "facialmatadatatable";
	public static final String primaryKey = "RekognitionId";
	public static final String attrName = "FullName";
	//public static final String faceId = "8487b203-0855-45ab-a323-ef5117c97f86";

	/*public static void main(String[] args) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable(tableName);
		
		String faceId = "8487b203-0855-45ab-a323-ef5117c97f86";
		GetItemSpec spec = new GetItemSpec().withPrimaryKey(primaryKey, faceId);

		try {
			System.out.println("Attempting to read the item...");
			Item outcome = table.getItem(spec);			
			System.out.println("GetItem succeeded: " + outcome);
			System.out.println(attrName + ": " + outcome.getString(attrName));

		}
		catch (Exception e) {
			System.err.println("Unable to read item: " + faceId);
			System.err.println(e.getMessage());
		}
	}*/
	
	public String lookupName(String faceId) {
		//AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withCredentials(new EnvironmentVariableCredentialsProvider()).build();

		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable(tableName);

		GetItemSpec spec = new GetItemSpec().withPrimaryKey(primaryKey, faceId);
		
		String result = null;

		try {
			Item outcome = table.getItem(spec);
			result = outcome.getString(attrName);
		}
		catch (Exception e) {
			System.err.println("Unable to read item: " + faceId);
			System.err.println(e.getMessage());
		}
		
		return result;

	}

}
