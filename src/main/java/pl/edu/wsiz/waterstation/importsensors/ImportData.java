package pl.edu.wsiz.waterstation.importsensors;

public interface ImportData {

	void startImport(String address, int port);

	void stopImport();
}
