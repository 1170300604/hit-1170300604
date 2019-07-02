package material;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Material {
  final String name;

  private Material(String name) {
    this.name = name;
  }

  public String getname() {
    return new String(name);
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof String) {
      String that=(String) obj;
      if(that.equals(this.name))
        return true;
      else
        return false;
    }
    else if (obj instanceof Material) {
      Material that = (Material) obj;
      if (that.name.equals(this.name))
        return true;
      else
        return false;
    } 
    else
      return false;
  }
  
  @Override
  public int hashCode() {
    return this.name.hashCode();
  }
  
  public Material copy() {
    return new Material(this.name);
  }
  
  public static class MaterialPool {
    List<String> materialList=new ArrayList<String>();
    Set<String> startSet=new HashSet<String>();
    public Material get(String name) {
      int i=materialList.indexOf(name);
      if(i>=0)
        return new Material(name);
      else {
        materialList.add(name);
        return new Material(name);
      }
    }
    public void addstartpoint(String name) {
      if(materialList.contains(name)) 
        startSet.add(name);
    }
    public boolean isstart(String name) {
      return startSet.contains(name);
    }
  }

}
