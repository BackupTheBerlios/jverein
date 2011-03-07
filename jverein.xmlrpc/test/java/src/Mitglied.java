import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;

public class Mitglied
{

  @SuppressWarnings("unchecked")
  public Mitglied(XmlRpcClient client) throws Exception
  {
    Object[] o = (Object[]) client.execute("jverein.xmlrpc.mitglied.getIDs",
        new Object[] { 1 });
    if (o == null)
    {
      return; // Kein Ergebnis erhalten
    }
    for (int i = 0; i < o.length; i++)
    {
      System.out.println(o[i]);
    }
    Map<String, Object> mitglied = (Map<String, Object>) client.execute(
        "jverein.xmlrpc.mitglied.read", new Object[] { "11" });
    if (mitglied == null)
    {
      return; // Kein Ergebnis erhalten
    }
    for (String key : mitglied.keySet())
    {
      System.out.println(key + ": " + mitglied.get(key));
    }
    mitglied.put(de.jost_net.JVerein.xmlrpc.server.MitgliedServiceImpl.HANDY,
        "bla");
    client.execute("jverein.xmlrpc.mitglied.update", new Object[] { mitglied });

  }

}
