package com.company.Server;

import com.company.Commands.CollectionWorker;
import com.company.Message;

import java.io.*;

public class ConsoleWorker {

    private CollectionWorker worker;
    private String value;

    public ConsoleWorker(CollectionWorker worker) {
        this.worker = worker;
    }

    public byte[] read() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        String name = "Command";
        Message message = new Message(name, value);
        oos.writeObject(message);
        oos.flush();
        oos.close();
        bos.close();
        return bos.toByteArray();
    }

    public void write(byte[] buffer) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bis));
        Message message = (Message) ois.readObject();
        value = worker.start(message.getName(), message.getValue());
        ois.close();
        bis.close();
    }
}
