package gameConnection;

import java.net.InetAddress;

public class Client {
	InetAddress IPAddress;
	int Port;

	public InetAddress getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(InetAddress iPAddress) {
		IPAddress = iPAddress;
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

}
