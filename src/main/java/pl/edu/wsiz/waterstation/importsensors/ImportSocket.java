package pl.edu.wsiz.waterstation.importsensors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

class ImportSocket implements Runnable {
	private static ArrayList<Thread> servicingThreads;
	private ServerSocket serverSocket;

	private volatile boolean running = true;

	public ImportSocket(String address, int port) {
		servicingThreads = new ArrayList<>();
		new Thread(this).start();

		try {
			serverSocket = new ServerSocket(port);
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

				read(socket);

				thread.start();
			} catch (SocketException e) {
				System.out.println("Server closed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void read(Socket socket) {
		String readLine;
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()))) {
			String inputString = "";
			while ((readLine = reader.readLine()) != null) {
				inputString += readLine;
			}

			System.out.println("inputString = " + inputString);
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
