package pl.edu.wsiz.waterstation.importsensors;

public class ImportDataFacade implements ImportData {
	private ImportSocket socket;

	@Override
	public synchronized void startImport(String address, int port) {
		if(socket == null){
			socket = new ImportSocket(address, port);
			socket.listen();
		}
	}

	@Override
	public synchronized void stopImport() {
		socket.closeSocket();
		socket = null;
	}
}
