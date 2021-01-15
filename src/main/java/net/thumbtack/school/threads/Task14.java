package net.thumbtack.school.threads;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task14 {
    //Написать класс Message, содержащий 4 текстовых поля : emailAddress, sender, subject, body, и класс Sender, с методом send(Message msg), отсылающий письмо на некий SMTP-сервер.
    //Реализовать массовую рассылку одного и того же текста, email адреса берутся из текстового файла.
    //Имейте в виду, что отправка одного письма требует довольно много времени (установление соединения с сервером, отсылка, получение ответа), поэтому последовательная отправка писем не является хорошим решением.
    //Порядок отправки писем произвольный и не обязан совпадать с порядком email адресов в файле.
    //Примечание 1. Реальную отправку писем производить не надо, вместо этого достаточно выводить их в некоторый файл.
    //Примечание 2. Если все же есть желание произвести реальную отсылку, подключите javax.mail-api с помощью maven и используйте классы MimeMessage и Sender.
    //Ответственность за возможные последствия целиком возлагается на Вас :-).


    public static void main(String[] args) {

        ExecutorService executServ = Executors.newCachedThreadPool();
        Object fileWriterSynchronize = new Object();

        try {
            Files.lines(Paths.get("mails.txt"), StandardCharsets.UTF_8).forEach(mail -> {
                executServ.submit(
                        new Sender(
                                new Message(mail, "Bakunov.oleg@gmail.com", "this is subject", "this is message"), fileWriterSynchronize)
                );
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        executServ.shutdown();
        System.out.println("Main thread Done");
    }

    static class Message {
        private String emailAddress;
        private String sender;
        private String subject;
        private String body;

        public Message(String emailAddress, String sender, String subject, String body) {
            this.emailAddress = emailAddress;
            this.sender = sender;
            this.subject = subject;
            this.body = body;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    static class Sender implements Runnable {

        private final Message msg;
        private final Object fileWriterSynchronize;

        public Sender(Message message, Object fileWriterSynchronize) {
            this.msg = message;
            this.fileWriterSynchronize = fileWriterSynchronize;
        }

        public void send() {
            try {
                System.out.println("Start e-meail sender thread.");
                Thread.sleep(1000);
                System.out.println("Message send to: " + msg.getEmailAddress());
                synchronized (fileWriterSynchronize) {
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("sends_message.csv", true), StandardCharsets.UTF_8))) {

                        try {
                            writer.write(msg.getEmailAddress() + ";");
                            writer.write(msg.getSender() + ";");
                            writer.write(msg.getSubject() + ";");
                            writer.write(msg.getBody() + ";");
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("E-mail sender thread is sleep.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            send();
        }
    }
}
