package com.company;

import java.io.IOException;

public class Main {

    public static void main(String args[]) {
        while (true) {
            try {
                Client client = new Client();
            } catch (IOException e) {
                System.out.println("Сервер не доступен, попробуйте подключиться еще раз");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
