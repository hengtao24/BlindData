package servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMail {
    private String username;
    private String password;
    private Authenticator auth = null;
    private MimeMessage mimeMessage =null;
    private Properties pros = null;
    private Multipart multipart = null;
    private BodyPart bodypart= null;

    public SendMail(String username,String password){
        this.username = username;
        this.password = password;
    } 

    public void initMessage(){
        this.auth = new Email_Autherticator();
        Session session = Session.getDefaultInstance(pros,auth);
        session.setDebug(true);
        mimeMessage = new MimeMessage(session);
    }

    public void setPros(Map<String,String> map){
        pros = new Properties();
        for(Map.Entry<String,String> entry:map.entrySet()){
            pros.setProperty(entry.getKey(), entry.getValue());
        }
    }

    public class Email_Autherticator extends Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(username, password);
        }
    }

    public void setDefaultMessagePros(String sub,String text,String rec) throws MessagingException, UnsupportedEncodingException{
        mimeMessage.setSubject(sub);
        mimeMessage.setText(text);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
        mimeMessage.setSentDate(new Date());
        mimeMessage.setFrom(new InternetAddress(username,username));
    }

    public void  setSubject(String subject) throws MessagingException{
        mimeMessage.setSubject(subject);
    }

    public void  setDate(Date date) throws MessagingException{
        mimeMessage.setSentDate(new Date());
    }
    /**
     * �����ʼ��ı�����
     * @param text
     * @throws MessagingException
     */
    public void setText(String text) throws MessagingException{
        mimeMessage.setText(text);
    }
    /**
     * �����ʼ�ͷ��
     * @param arg0
     * @param arg1
     * @throws MessagingException
     */
    public void setHeader(String arg0,String arg1) throws MessagingException{
        mimeMessage.setHeader(arg0, arg1);
    }
    /**
     * �����ʼ������˵�ַ <���˷���>
     * @param recipient
     * @throws MessagingException
     */
    public void setRecipient(String recipient) throws MessagingException{
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    }
    /**
     * �����ʼ������˵�ַ <���˷���>
     * @param list
     * @throws MessagingException 
     * @throws AddressException 
     */
    public String setRecipients(List<String> recs) throws AddressException, MessagingException{
        if(recs.isEmpty()){
            return "�����˵�ַΪ��!";
        }
        for(String str:recs){
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(str));
        }
        return "��������˵�ַ�ɹ�!";
    }
    /**
     * �����ʼ������˵�ַ <���˷���>
     * @param StringBuffer<parms,parms2,parms.....>
     * @throws MessagingException 
     * @throws AddressException 
     */
    @SuppressWarnings("static-access")
    public String setRecipients(StringBuffer sb) throws AddressException, MessagingException{
        if(sb==null||"".equals(sb)){
            return "�ַ�������Ϊ��!";
        }
        Address []address = new InternetAddress().parse(sb.toString());
        mimeMessage.addRecipients(Message.RecipientType.TO, address);
        return "�ռ��˼���ɹ�";
    }
    /**
     * �����ʼ������˵�����
     * @param from
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public void setFrom(String from) throws UnsupportedEncodingException, MessagingException{
        mimeMessage.setFrom(new InternetAddress(username,from));
    }
    /**
     * �����ʼ�<���˷���>
     * return �Ƿ��ͳɹ�
     * @throws MessagingException 
     */
    public String sendMessage() throws MessagingException{
        Transport.send(mimeMessage);
        return "success";
    }
    /**
     * ���ø���
     * @param file �����ļ���·��
     */
    public void setMultipart(String file) throws MessagingException, IOException{
        if(multipart==null){
            multipart = new MimeMultipart();
        }
        multipart.addBodyPart(writeFiles(file));
        mimeMessage.setContent(multipart);
    }
    /**
     * ���ø���<��Ӷ฽��>
     * @param fileList<����List����>
     * @throws MessagingException
     * @throws IOException
     */
    public void setMultiparts(List<String> fileList) throws MessagingException, IOException{
        if(multipart==null){
            multipart = new MimeMultipart();
        }
        for(String s:fileList){
            multipart.addBodyPart(writeFiles(s));
                }
                      mimeMessage.setContent(multipart);
    }
    /**
     * �����ı����ݣ����ñ��뷽ʽ
     * <�����뷢�͸�������ʹ��>
     * <������ͨ���ı�������ʹ��setText()����>
     * @param s
     * @param type
     * @throws MessagingException
     */
    public void setContent(String s,String type) throws MessagingException{
        if(multipart==null){
            multipart = new MimeMultipart();
        }
        bodypart = new MimeBodyPart();
        bodypart.setContent(s, type);
        multipart.addBodyPart(bodypart);
        mimeMessage.setContent(multipart);
        mimeMessage.saveChanges();
    }
    /**
     * ��ȡ����
     * @param filePath
     * @return
     * @throws IOException
     * @throws MessagingException
     */
    public BodyPart writeFiles(String filePath)throws IOException, MessagingException{
        File file = new File(filePath);
        if(!file.exists()){
            throw new IOException("�ļ�������!��ȷ���ļ�·���Ƿ���ȷ");
        }
        bodypart = new MimeBodyPart();
        DataSource dataSource = new FileDataSource(file);
        bodypart.setDataHandler(new DataHandler(dataSource));
        //�ļ���Ҫ������룬��Ȼ��������
        bodypart.setFileName(MimeUtility.encodeText(file.getName()));
        return bodypart;
    }
       
}