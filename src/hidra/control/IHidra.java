package hidra.control;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

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
	public void clone(@WebParam(name="remotePath")String remotePath, @WebParam(name="localPath")String localPath);
	
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
