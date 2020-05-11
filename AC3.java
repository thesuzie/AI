import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javafx.util.Pair;

public class AC3 {
  private Map<Integer,Integer[]> graph;
  private LinkedList<LinkedList<String>> domains;

  public AC3(Map<Integer, Integer[]> g, LinkedList<String> colours, int num_nodes){


    graph = new HashMap<>(g);
    domains = new LinkedList<>();
    for(int i = 0; i<num_nodes;i++){
      domains.add(colours);
    }
    LinkedList<String> first_assignment = new LinkedList<>();
    first_assignment.add("R");
    domains.set(0,first_assignment);
    System.out.println(domains);
    algorithm();

  }

  public void algorithm(){
    Queue toCheck = new LinkedList();

    for(Integer k : graph.keySet()){
      for(Integer v: graph.get(k)){
        toCheck.add(new Pair(k,v));
      }
    }

    while (toCheck.size() > 0){
      Pair<Integer,Integer> edge = (Pair<Integer, Integer>) toCheck.remove();
      if(removeInconsistencies(edge)){
        for(int k : graph.get(edge.getKey())){
          toCheck.add(new Pair(k,edge.getKey()));
        }

      }
    }
    System.out.println(domains);
  }

  public boolean removeInconsistencies(Pair<Integer,Integer> edge){
    boolean result = false;
    for(String d1 : domains.get(edge.getKey()-1)){
      int valid = 0;
      for(String d2 : domains.get(edge.getValue()-1)){
        System.out.println("d1 ="+d1+" d2= "+d2);
        if(d1 != (d2)){
          valid++;
        }
      }
      System.out.println(valid);
      if(valid ==0){
        System.out.println("need to remove");
        domains.get(edge.getKey()-1).remove(d1);

        if(domains.get(edge.getKey()-1).size() ==0){
          System.out.println("empty domain: "+edge.getKey() );
        }
        result = true;
      }
    }
    return result;
  }

  public static void main(String[] args) {

    int num_nodes = 8;
    LinkedList<String> colours = new LinkedList<String>();
    colours.add("R");
    colours.add("C");
    colours.add("B");
    Map<Integer, Integer[]> g = new HashMap<>();
    g.put(1,new Integer[]{2,3,4});
    g.put(2,new Integer[]{1,6,4});
    g.put(3,new Integer[]{1,7,4});
    g.put(4,new Integer[]{2,3,1,5});
    g.put(5,new Integer[]{4,6,7});
    g.put(6,new Integer[]{2,8,5,7});
    g.put(7,new Integer[]{5,3,6,8});
    g.put(8,new Integer[]{6,7});
    new AC3(g, colours, num_nodes);



  }


}
