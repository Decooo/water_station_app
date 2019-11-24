package pl.edu.wsiz.waterstation.importsensors;

public class ImportDataFacade implements ImportData {

	private final ImportDataService importDataService;
	private ImportSocket socket;
	private Thread threadSocket;

	public ImportDataFacade(ImportDataService importDataService) {
		this.importDataService = importDataService;
	}

	@Override
	public synchronized void startImport(String address, int port) {
		if (socket == null) {
			threadSocket = new Thread();

			Runnable serverTask = () -> {
				socket = new ImportSocket(address, port, importDataService);
				socket.listen();
			};

			threadSocket = new Thread(serverTask);
			threadSocket.start();
		}
	}

	@Override
	public synchronized void stopImport() {
		try {
			socket.closeSocket();
			socket = null;
			threadSocket.join();
		} catch (InterruptedException e) {
			System.out.println("Nie udało się zamknąć socketa");
		}
	}

	@Override
	public boolean isRunning() {
		return socket != null;
	}
}
