package utils.divulga.editais.ifsuldeminas.edu.br;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class FileResource extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String _filesPath = "context.path";

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadFile(@FormDataParam("noticeId") int noticeId,
			@FormDataParam("uploadfile") InputStream uploadedInputStream,
			@FormDataParam("uploadfile") FormDataContentDisposition fileDetail) {
		try {
			String fileName = fileDetail.getFileName();
			String path = createDirectoryStructure(noticeId);
			String fullPath = path + fileName;
			OutputStream outStream = new FileOutputStream(fullPath);
			int fileSize = IOUtils.copy(uploadedInputStream, outStream);
			fileSize = fileSize / 1024;
			outStream.close();
			return Response.ok(Integer.toString(fileSize), "text/plain").build();
		} catch (final Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	private String createDirectoryStructure(int noticeId) {
		String filesPath = PropsUtil.getInstance().getProperty(_filesPath);
		String path = filesPath + File.separatorChar + String.valueOf(noticeId) + File.separatorChar;
		File targetDir = new File(path);
		
		if (!targetDir.exists()) {
		    System.out.println("Criando Estrutura de Pastas: " + targetDir.getPath());
		    boolean result = false;

		    try{
		    	targetDir.mkdirs();
		        result = true;
		    } 
		    catch(SecurityException se){
		        throw se;
		    }        
		    if(result) {    
		        System.out.println("Estrutura de Pastas Criada!");  
		    }
		}
		return path;
	}
}
