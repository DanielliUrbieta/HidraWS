package hidra.control;



public class Main {

	public static void main(String[] args) {

		HidraControl hc = new HidraControl();
		hc.inicializar("/home/danielli/teste01");
		
		//hc.adicionar("arquivo5.txt");
		//hc.remove("arquivo5.txt");
		//hc.commit("Testando Commit");
		//hc.clone("/home/danielli/teste01","/home/danielli/clone01");
		//hc.status();
		hc.getLogs();
	}

}
