package hidra.control;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

@WebService
public interface IHidra {
	@WebMethod
	public void inicializar(@WebParam(name = "localPath")String localPath);
	
	@WebMethod
	public boolean adicionar(@WebParam(name = "fileName") String fileName);
	
	@WebMethod
	public boolean remove (@WebParam(name="filename") String filename);
	
	@WebMethod
	public boolean commit(@WebParam(name = "message")String message);
	
	@WebMethod
	public boolean cloneW(@WebParam(name="remotePath")String remotePath, @WebParam(name="localPath")String localPath) throws IOException, InvalidRemoteException, TransportException, GitAPIException;
	
	@WebMethod
	public String status();
	
	@WebMethod
	public String getLogs();
	
	@WebMethod
	public String showBranch();
	
	@WebMethod
	public String createBranch(@WebParam(name = "nameBranch")String nameBranch);
	
/*	@WebMethod
	public void deleteBranch(@WebParam(name = "nameBranchD")String nameBranch);
*/}
