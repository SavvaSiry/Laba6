package com.company;

import java.io.*;

public class ConsoleWorker {

    private BufferedReader reader;

    public ConsoleWorker() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public byte[] read() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        System.out.print("Введите команду: ");
        String command = reader.readLine();
        String[] strings = command.split(" ");
        String name = strings[0];
        String argument = "";
        if (strings.length > 1)
            argument = strings[1];
        if (name.equals("add") || name.equals("update") || name.equals("remove_greater") || name.equals("remove_lower")){
            argument = Swither.start(name, reader);
        }
        Message message = new Message(name, argument);
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
        if (message.getValue().equals("exit\r\n"))
            System.exit(1);
        System.out.println(message.getValue());
        ois.close();
        bis.close();
    }
}
