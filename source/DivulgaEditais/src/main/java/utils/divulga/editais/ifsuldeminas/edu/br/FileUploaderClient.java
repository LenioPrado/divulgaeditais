package utils.divulga.editais.ifsuldeminas.edu.br;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
/**
 * This example shows how to upload files using POST requests 
 * with encoding type "multipart/form-data".
 * For more details please read the full tutorial
 * on https://javatutorial.net/java-file-upload-rest-service
 * @author javatutorial.net
 */
public class FileUploaderClient {
	
	public static void main(String[] args) {
		
		// the file we want to upload
		File inFile = new File("D:\\Develop\\FileRepository\\60\\Como_Aprender_Ingles_O+guia+definitivo_1_6_2.pdf");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inFile);
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			
			// server back-end URL
			HttpPost httppost = new HttpPost("http://localhost:8080/DivulgaEditais/rest/file/upload");
			// set the file input stream and file name as arguments
			
			builder.addPart("uploadfile", new InputStreamBody(fis, inFile.getName()));

			HttpEntity entity = builder.build();
			httppost.setEntity(entity);
			// execute the request
			HttpResponse response = httpclient.execute(httppost);
			
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity responseEntity = response.getEntity();
			String responseString = EntityUtils.toString(responseEntity, "UTF-8");
			
			System.out.println("[" + statusCode + "] " + responseString);
			
		} catch (ClientProtocolException e) {
			System.err.println("Unable to make connection");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to read file");
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (IOException e) {}
		}
	}
}