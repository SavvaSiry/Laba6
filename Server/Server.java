package com.company.Server;

import com.company.Commands.CollectionWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private ConsoleWorker worker;
    private ServerSocketChannel serverSocket;
    private Selector selector;
    private ByteBuffer buffer;
    private BufferedReader reader;
    private InetSocketAddress address;


    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public Server(CollectionWorker worker) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.worker = new ConsoleWorker(worker);
        start();
    }

    public void start() throws IOException {
        System.out.print("Введите адрес сервера: ");
        String hostName = reader.readLine();
        while (true) {
            try {
                System.out.print("Введите port: ");
                int port = Integer.parseInt(reader.readLine());
                connection(hostName, port);
                logger.info("Server starts and waiting connections");
                buffer = ByteBuffer.allocate(1024 * 4);

                while (true) {
                    selector.select();
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectedKeys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        if (key.isAcceptable()) {
                            logger.info("Server register connection");
                            register(key);
                        }
                        if (key.isReadable()) {
                            logger.info("Server read message");
                            read(key);
                        }
                        if (key.isWritable()) {
                            logger.info("Server write answer");
                            write(key);
                        }
                        iter.remove();
                    }
                }
            } catch (IOException | ClassNotFoundException | IllegalArgumentException e) {
                logger.log(Level.SEVERE, "Exception: ", e);
            }
        }
    }

    private void read(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel client = (SocketChannel) key.channel();
        client.read(buffer);
        worker.write(buffer.array());
        buffer.clear();
        client.register(key.selector(), SelectionKey.OP_WRITE);
        key.selector().wakeup();
    }

    private void register(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        if (socketChannel.isConnected()) logger.info("Client is connected " + socketChannel.getRemoteAddress());
        socketChannel.register(key.selector(), SelectionKey.OP_READ);
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.put(worker.read());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        socketChannel.register(key.selector(), SelectionKey.OP_READ);
        key.selector().wakeup();
    }

    private void connection(String hostName, Integer port) throws IOException, IllegalArgumentException {
        if (address == null || !(port.equals(address.getPort()))) {
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();
            address = new InetSocketAddress(hostName, port);
            serverSocket.bind(address);
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        }
    }
}