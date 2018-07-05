import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashingWithVirtualNode
{

    private static String[] servers = {"192.168.0.2:8080", "192.168.0.8080", "192.168.0.4:8080",
            "192.168.0.5:8080", "192.168.0.6:8080"};


    private static List<String> realNodes = new LinkedList<String>();

    private static SortedMap<Integer, String> virtualNodes =
            new TreeMap<Integer, String>();


    private static final int VIRTUAL_NODES = 5;

    static
    {
        for (int i = 0; i < servers.length; i++)
            realNodes.add(servers[i]);
        for (String str : realNodes)
        {
            for (int i = 0; i < VIRTUAL_NODES; i++)
            {
                String virtualNodeName = str + "_vnode" + String.valueOf(i);
                int hash = virtualNodeName.hashCode();
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }


    private static String getServer(String node)
    {

        SortedMap<Integer, String> subMap =
                virtualNodes.tailMap(node.hashCode());
        Integer i = subMap.firstKey();
        String virtualNode = subMap.get(i);
        return virtualNode.substring(0, virtualNode.indexOf("_"));
    }

    public static void main(String[] args)
    {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (int i = 0; i < nodes.length; i++)
            System.out.println("[" + nodes[i] + "]的hash值为" +
                    nodes[i].hashCode() + ", 被路由到结点[" + getServer(nodes[i]) + "]");
    }
}
