package pl.edu.wsiz.waterstation.importsensors;

public class ImportDataFacade implements ImportData {
	private ImportSocket socket;

	@Override
	public synchronized void startImport(String address, int port) {
		socket = new ImportSocket(address, port);
		socket.listen();
	}

	@Override
	public void stopImport() {
		socket.closeSocket();
	}
}
