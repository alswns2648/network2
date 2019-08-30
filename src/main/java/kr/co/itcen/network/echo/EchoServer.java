package kr.co.itcen.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	private static final int PORT = 6000;

	public static void main(String[] args) {
//		ServerSocket serverSocket = null;
		
		//서버소켓생성
		ServerSocket serverSocket = null;
		
		try {
			//소켓 생성
			serverSocket = new ServerSocket();
			
			//bind
			InetAddress inetAddress = InetAddress.getLocalHost(); // host server IP주소 변수 할당
			InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, PORT); // 소켓주소에 (IP,POST)넣음
			serverSocket.bind(inetSocketAddress); // 서버 소켓이 (ip,port) 갖고있음
			EchoServer.log("바인딩");
			
			//accpet(connect 요청 대기)
			while(true) {
				Socket socket = serverSocket.accept(); // Blocking
				new EchoServerReceiveThread(socket).start();
			}
			
	

		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			//8. Server Socket 자원정리
			try {
				if(serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}			
	}
	public static void log(String log) {
		System.out.println("[EchoServer#" + Thread.currentThread().getId() + "]" + log);
	}
}
