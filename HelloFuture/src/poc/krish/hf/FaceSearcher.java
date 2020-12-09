package poc.krish.hf;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.Face;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.rekognition.model.FaceMatch;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.SearchFacesByImageRequest;
import com.amazonaws.services.rekognition.model.SearchFacesByImageResult;
//import com.amazonaws.util.IOUtils;


public class FaceSearcher {

	public static final String collectionId = "face_recognition_collection";

	/*public static void main(String[] args) throws Exception {

		String photo="C:\\Users\\Krish\\eclipse-workspace\\HelloFuture\\WebContent\\input.jpg";

        ByteBuffer imageBytes;
        try (InputStream inputStream = new FileInputStream(new File(photo))) {
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

		ObjectMapper objectMapper = new ObjectMapper();
		// Search collection for faces similar to the largest face in the image.
		SearchFacesByImageRequest searchFacesByImageRequest = new SearchFacesByImageRequest()
				.withCollectionId(collectionId)
				.withImage(new Image()
						.withBytes(imageBytes))
				.withFaceMatchThreshold(70F)
				.withMaxFaces(2);

		SearchFacesByImageResult searchFacesByImageResult = 
				rekognitionClient.searchFacesByImage(searchFacesByImageRequest);

		System.out.println("Faces matching largest face in image from" + photo);
		List < FaceMatch > faceImageMatches = searchFacesByImageResult.getFaceMatches();
		for (FaceMatch face: faceImageMatches) {
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(face));
			System.out.println();
		}
	}*/
	
	public String matchFaceImage(ByteBuffer imageBytes) {

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
        //AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().build();
        SearchFacesByImageRequest searchFacesByImageRequest;
        SearchFacesByImageResult searchFacesByImageResult = null;

		// Search collection for faces similar to the provided image
        try {
        	searchFacesByImageRequest = new SearchFacesByImageRequest()
        			.withCollectionId(collectionId)
        			.withImage(new Image()
        					.withBytes(imageBytes))
        			.withFaceMatchThreshold(90F);

        	searchFacesByImageResult = 
        			rekognitionClient.searchFacesByImage(searchFacesByImageRequest);
		
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }

		List < FaceMatch > faceImageMatches = searchFacesByImageResult.getFaceMatches();
		Float maxConfi = Float.parseFloat("0");
		String faceId = null;
		for (FaceMatch fm: faceImageMatches) {
			Face f = fm.getFace();
			if (f.getConfidence() > maxConfi) {
				maxConfi = f.getConfidence();
				faceId = f.getFaceId();
			}			
		}
		
		return faceId;
	}


}
