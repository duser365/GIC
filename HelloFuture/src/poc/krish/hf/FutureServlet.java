package poc.krish.hf;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.amazonaws.util.IOUtils;

/**
 * Servlet implementation class FutureServlet
 */
@WebServlet("/futureServlet")
@MultipartConfig
public class FutureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FutureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Part filePart = request.getPart("inputPhoto");
	    InputStream inputStream = filePart.getInputStream();	    
	    ByteBuffer imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
	    
		FaceSearcher fs = new FaceSearcher();
		String faceId = fs.matchFaceImage(imageBytes);
		
		PrintWriter writer = response.getWriter();

		//writer.println(faceId);
		
		if (faceId != null) {		
			NameFinder nf = new NameFinder();
			String name = nf.lookupName(faceId);

			if (name != null) {
				//writer.println("Welcome, " + name);
				response.setContentType("text/html");
				writer.println("<!DOCTYPE html>" +
						"<html>\n" +
						"<head><title>Welcome</title></head>\n"+
						"<body>\n" +
						"<h1>Hello " + name + "</h1>\n" +
						"</body>\n" + 
						"</html>"
						);			
			}
			else
				writer.println("Authentication failed!");
		}
		else
			writer.println("Authentication failed!");
		
		writer.close();
	}

}
