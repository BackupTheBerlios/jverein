/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/MailSender.java,v $
 * $Revision: 1.10 $
 * $Date: 2011/10/01 21:47:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/

package de.jost_net.JVerein.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.TreeSet;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import de.jost_net.JVerein.rmi.MailAnhang;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Level;
import de.willuhn.logging.Logger;

public class MailSender
{

  private String smtp_host_name;

  private String smtp_port;

  private String smtp_auth_user;

  private String smtp_auth_pwd;

  private String smtp_from_address;

  private boolean smtp_ssl;

  private boolean smtp_starttls;

  public MailSender(String smtp_host_name, String smtp_port,
      String smtp_auth_user, String smtp_auth_pwd, String smtp_from_address,
      boolean smtp_ssl, boolean smtp_starttls)
  {
    this.smtp_host_name = smtp_host_name;
    this.smtp_port = smtp_port;
    this.smtp_auth_user = smtp_auth_user;
    this.smtp_auth_pwd = smtp_auth_pwd;
    this.smtp_from_address = smtp_from_address;
    this.smtp_ssl = smtp_ssl;
    this.smtp_starttls = smtp_starttls;
  }

  // Send to a single recipient
  public void sendMail(String email, String subject, String text,
      TreeSet<MailAnhang> anhang) throws Exception
  {
    String[] emailList = new String[1];
    emailList[0] = email;
    sendMail(emailList, subject, text, anhang);
  }

  // //Send to multiple recipients

  public void sendMail(String[] emailadresses, String subject, String text,
      TreeSet<MailAnhang> anhang) throws Exception
  {
    Properties props = new Properties();

    props.put("mail.smtp.host", smtp_host_name);
    props.put("mail.smtp.auth", "true");
    props.put("mail.debug", "true");
    props.put("mail.smtp.user", smtp_auth_user);
    props.put("mail.smtp.password", smtp_auth_pwd);
    props.put("mail.smtp.port", smtp_port);
    props.put("mail.mime.charset", "ISO-8859-15");
    System.setProperty("mail.mime.charset", "ISO-8859-15");

    if (smtp_ssl)
    {
      java.security.Security
          .addProvider(new com.sun.net.ssl.internal.ssl.Provider());
      props.setProperty("mail.smtp.socketFactory.class",
          "javax.net.ssl.SSLSocketFactory");
      props.setProperty("mail.smtp.socketFactory.fallback", "false");
    }
    props.setProperty("mail.smtp.port", smtp_port);
    props.setProperty("mail.smtp.socketFactory.port", smtp_port);
    if (smtp_starttls)
    {
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.tls", "true");
    }
    Session session = null;

    if (smtp_auth_user != null)
    {
      session = Session.getDefaultInstance(props, new SMTPAuthenticator());
    }
    else
    {
      session = Session.getDefaultInstance(props);
    }
    if (Application.getConfig().getLogLevel().equals(Level.DEBUG.getName()))
    {
      session.setDebug(true);
    }
    else
    {
      session.setDebug(false);
    }
    Message msg = new MimeMessage(session);
    InternetAddress addressFrom = new InternetAddress(smtp_from_address);
    msg.setFrom(addressFrom);

    InternetAddress[] addressTo = new InternetAddress[emailadresses.length];

    for (int i = 0; i < emailadresses.length; i++)
    {
      addressTo[i] = new InternetAddress(emailadresses[i]);
    }

    msg.setRecipients(Message.RecipientType.TO, addressTo);
    msg.setSubject(subject);

    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.addHeader("Content-Encoding", "ISO-8859-15");
    // Fill the message
    messageBodyPart.setContent(text, "text/plain");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    for (MailAnhang ma : anhang)
    {
      messageBodyPart = new MimeBodyPart();

      messageBodyPart.setDataHandler(new DataHandler(
          new ByteArrayDataSource(ma)));
      messageBodyPart.setFileName(ma.getDateiname());
      multipart.addBodyPart(messageBodyPart);
    }
    // Put parts in message
    msg.setContent(multipart);

    Transport.send(msg);

  }

  private class SMTPAuthenticator extends Authenticator
  {

    @Override
    public PasswordAuthentication getPasswordAuthentication()
    {
      String username = smtp_auth_user;
      String password = smtp_auth_pwd;
      return new PasswordAuthentication(username, password);
    }
  }

  private static class ByteArrayDataSource implements DataSource
  {

    private MailAnhang ma;

    public ByteArrayDataSource(MailAnhang ma)
    {
      this.ma = ma;
    }

    public String getContentType()
    {
      try
      {
        return MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(
            ma.getDateiname());
      }
      catch (RemoteException e)
      {
        Logger.error("", e);
      }
      return null;
    }

    public InputStream getInputStream() throws IOException
    {
      return new ByteArrayInputStream(ma.getAnhang());
    }

    public String getName()
    {
      String name = null;
      try
      {
        name = ma.getDateiname();
      }
      catch (RemoteException e)
      {
        Logger.error("", e);
      }
      return name;
    }

    public OutputStream getOutputStream()
    {
      return new ByteArrayOutputStream();
    }

  }
}