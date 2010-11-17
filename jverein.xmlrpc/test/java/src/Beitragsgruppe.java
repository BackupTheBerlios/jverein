import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;

public class Beitragsgruppe
{

  @SuppressWarnings("unchecked")
  public Beitragsgruppe(XmlRpcClient client) throws Exception
  {

    List<String> param = null;

    Object[] o = (Object[]) client.execute(
        "jverein.xmlrpc.beitragsgruppe.getIDs", param);
    if (o == null)
    {
      return; // Kein Ergebnis erhalten
    }
    for (int i = 0; i < o.length; i++)
    {
      System.out.println(o[i]);
    }
    param = new ArrayList<String>();
    param.add("1");
    Map<String, Object> line = (Map<String, Object>) client.execute(
        "jverein.xmlrpc.beitragsgruppe.read", param);
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
