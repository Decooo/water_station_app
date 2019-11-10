package pl.edu.wsiz.waterstation.importsensors;

import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ImportSocket implements Runnable {
	private static ArrayList<Thread> servicingThreads;
	private ServerSocket serverSocket;

	private volatile boolean running = true;

	ImportSocket(String address, int port) {
		servicingThreads = new ArrayList<>();
		new Thread(this).start();

		try {
			InetAddress addr = InetAddress.getByName(address);
			serverSocket = new ServerSocket(port, 50, addr);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..");
			running = true;
		} catch (SocketException se) {
			System.out.println("Socket Exception when connecting to client");
			se.printStackTrace();
		} catch (SocketTimeoutException ste) {
			System.out.println("Timeout occured while connecting to client");
		} catch (IOException io) {
			System.out.println("IO exception when connecting to client");
		}
	}

	void listen() {
		while (running) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("socket.getPort() = " + socket.getPort());
				Thread thread = new Thread();
				servicingThreads.add(thread);

				String input = read(socket);
				if (!StringUtils.isEmpty(input)) {
					ImportDataService dataService = new ImportDataService();
					Long deepSleep = dataService.importDataAndReturnDeepSleep(input);
					sendResponse(socket, deepSleep);
				}

				thread.start();
			} catch (SocketException e) {
				System.out.println("Server closed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String read(Socket socket) throws IOException {
		InputStream is = socket.getInputStream();
		InputStreamReader isReader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isReader);

		String headerLine;
		while ((headerLine = br.readLine()).length() != 0) {
			headerLine += headerLine;
		}

		StringBuilder body = new StringBuilder();
		while (br.ready()) {
			body.append((char) br.read());
		}
		System.out.println("Body data is: " + body.toString());
		return body.toString();
	}

	private void sendResponse(Socket socket, Long deepSleep) {
		try {
			Map<String, Long> body = new HashMap<>();
			body.put("deepSleep", deepSleep);

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			out.write("HTTP/1.1 200 OK\r\n");
			out.write("Content-Type: text/html\r\n");
			out.write("\r\n");
			out.write(new JSONObject(body).toString());

			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void closeSocket() {
		System.out.println("Closing Server..");
		running = false;
		try {
			for (Thread thread : servicingThreads) {
				if (thread.isAlive()) {
					System.out.print("Waiting on " + thread.getId() + " to close..");
					thread.join();
					System.out.println(" closed");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("Terminating Connection");
			serverSocket.close();
		} catch (Exception e) {
			System.out.println("Exception closing import socket");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
	}
}
