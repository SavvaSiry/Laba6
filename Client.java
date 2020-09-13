package com.company;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Client {

    private ConsoleWorker worker;
    private ByteBuffer buffer;
    private SocketChannel channel;
    private Selector selector;
    private String hostName;
    private int port;
    private BufferedReader reader;

    public Client() throws IOException, ClassNotFoundException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        worker = new ConsoleWorker();
        buffer = ByteBuffer.allocate(1024 * 4);
        start();
    }

    private void start() throws IOException, ClassNotFoundException {

        while (true) {
            try {
                System.out.print("Введите адрес сервера: ");
                hostName = reader.readLine();
                System.out.print("Введите port: ");
                port = Integer.parseInt(reader.readLine());
                connect(hostName,port);
                break;
            } catch (Exception e) {
                System.out.println("Некоректный данные, попробуйте еще раз");
            }
        }

        while (true) {
            if (channel.isConnected()) System.out.println("Подключение прошло успешно: " + channel.getRemoteAddress());

            SelectionKey key = channel.keyFor(selector);
            key.interestOps(SelectionKey.OP_WRITE);
            selector.wakeup();

            while (true) {
                selector.select();
                for (SelectionKey selectionKey : selector.selectedKeys()) {
                    if (selectionKey.isConnectable()) {
                        channel.finishConnect();
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                    } else if (selectionKey.isReadable()) {
                        read(selectionKey);
                    } else if (selectionKey.isWritable()) {
                        write(selectionKey);
                    }
                }
            }
        }
    }

    private void connect(String hostname, int port) throws IOException {
        channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(hostname, port));
        channel.configureBlocking(false);
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    private void read(SelectionKey selectionKey) throws IOException, ClassNotFoundException {
        channel.read(buffer);
        worker.write(buffer.array());
        buffer.clear();
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    private void write(SelectionKey selectionKey) throws IOException {
        buffer.put(worker.read());
        buffer.flip();
        channel.write(buffer);
        buffer.clear();
        selectionKey.interestOps(SelectionKey.OP_READ);
    }
}