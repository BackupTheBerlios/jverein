import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;

public class Stammdaten
{

  @SuppressWarnings("unchecked")
  public Stammdaten(XmlRpcClient client) throws Exception
  {
    Object[] param = null;
    Map<String, Object> line = (Map<String, Object>) client.execute(
        "jverein.xmlrpc.stammdaten.read", param);
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
