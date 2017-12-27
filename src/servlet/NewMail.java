package servlet;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewMail implements Runnable {

    private String mPassword,mTo;
    public NewMail(String pd, String to) {
        this.mPassword=pd;
        this.mTo=to;
    }
    @Override
     public void run(){
        Map<String,String> map= new HashMap<String,String>();
        map.put("mail.smtp.host", "smtp.qq.com");
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        SendMail fromMail = new SendMail("1315260650@qq.com","eimifytdlzsafhbe");
        fromMail.setPros(map);
        fromMail.initMessage();
        System.out.println("�����ʼ�...");
        try {
            fromMail.setRecipient(mTo);
            fromMail.setSubject("��������");
            fromMail.setDate(new Date());
            fromMail.setFrom("MY");
            fromMail.setContent("�𾴵��û�������,��������Ϊ:"+mPassword, "text/html; charset=UTF-8");
            System.out.println("�����ʼ��ɹ�");
            System.out.println(fromMail.sendMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
     }
}