package connectpc;

import android.util.Log;

import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class mouseManager {
    DataOutputStream dos;
    Boolean connected;
    String ipAddress;
    int port;
    DatagramSocket socket ;
    public mouseManager(){
        try {
            socket = new DatagramSocket();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        connected = false;
        return;
    }
    public boolean isConnected(){
        return connected; //已连接  返回true
    }
    public void connect(final String ipAddress, final int port){
        this.connected  =  true;
        this.ipAddress = ipAddress;
        this.port = port;
    }
    public void sendMovement(final String move){
        new Thread(){
            @Override
            public void run(){
                try {
                    String str = move;
                    DatagramPacket packet = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName(ipAddress), port);
                    socket.send(packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void sendKey(final String k){
        new Thread(){
            @Override
            public void run(){
                try {
                    String str = k;
                    DatagramPacket packet = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName(ipAddress), port);
                    socket.send(packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
