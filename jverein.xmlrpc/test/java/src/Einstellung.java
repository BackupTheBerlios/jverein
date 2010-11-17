import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;

public class Einstellung
{

  @SuppressWarnings("unchecked")
  public Einstellung(XmlRpcClient client) throws Exception
  {
    Object[] param = null;
    Map<String, Object> line = (Map<String, Object>) client.execute(
        "jverein.xmlrpc.einstellung.read", param);
    if (line == null)
    {
      return; // Kein Ergebnis erhalten
    }
    for (String key : line.keySet())
    {
      System.out.println(key + ": " + line.get(key));
    }
  }
}
