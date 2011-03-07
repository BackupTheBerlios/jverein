import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XmlrpcTest
{

  public static void main(String[] args) throws Exception
  {
    XmlrpcTest test = new XmlrpcTest();
    XmlRpcClient client = test.get(args[0]);
    new Einstellung(client);
    new Beitragsgruppe(client);
    new Mitglied(client);
  }

  private XmlRpcClient get(String passwd) throws Exception
  {
    String url = "https://localhost:8080/xmlrpc/";

    // Client-Config erzeugen
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    config.setBasicPassword(passwd);
    config.setBasicUserName("admin");
    config.setServerURL(new URL(url));
    config.setEnabledForExceptions(true);

    if (url.startsWith("https"))
      disableCertCheck();

    XmlRpcClient client = new XmlRpcClient();
    client.setConfig(config);
    return client;
  }

  /**
   * Falls HTTPS aktiviert ist, muessen Sie entweder Java das Jameica-Zertifikat
   * (Datei->Einstellungen->Zertifikate) bekannt machen. Sie können es mit dem
   * bei SUN Java mitgelieferten Programm
   * [[http://java.sun.com/j2se/1.4.2/docs/tooldocs
   * /windows/keytool.html|keytool]] importieren oder (NUR ZU TESTZWECKEN!) die
   * Zertifikats-Prüfung abschalten. Die folgende Funktion tut dies.
   * 
   * @throws Exception
   */
  private static void disableCertCheck() throws Exception
  {
    TrustManager dummy = new X509TrustManager()
    {
      public java.security.cert.X509Certificate[] getAcceptedIssuers()
      {
        return null;
      }

      public void checkClientTrusted(X509Certificate[] certs, String authType)
      {
      }

      public void checkServerTrusted(X509Certificate[] certs, String authType)
      {
      }
    };

    SSLContext sc = SSLContext.getInstance("SSL");
    sc.init(null, new TrustManager[] { dummy }, new SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    HostnameVerifier dummy2 = new HostnameVerifier()
    {
      public boolean verify(String host, SSLSession session)
      {
        return true;
      }
    };
    HttpsURLConnection.setDefaultHostnameVerifier(dummy2);
  }
}
