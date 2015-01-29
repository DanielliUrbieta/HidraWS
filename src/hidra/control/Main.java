package hidra.control;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;



public class Main {

	public static void main(String[] args) throws InvalidRemoteException, TransportException, IOException, GitAPIException {

		HidraControl hc = new HidraControl();
		//hc.inicializar("/home/danielli/teste01");
		//hc.clone("https://github.com/DanielliUrbieta/TCC.git", "/home/danielli/novoClone");
		//hc.adicionar("arquivo5.txt");
		//hc.remove("arquivo5.txt");
		//hc.commit("Testando Commit");
		hc.cloneW("/home/danielli/teste01","/home/danielli/clone01");
		//String teste = hc.status();
		//System.out.println(teste);
		
		//System.out.println(hc.showBranch());
		//hc.getLogs();
	}

}
