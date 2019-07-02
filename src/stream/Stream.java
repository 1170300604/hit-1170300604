package stream;

import material.Material;

public class Stream {
  private final Material material;
  private final double amount;

  public Stream(Material material, double amount) {
    this.material = material;
    this.amount = amount;
  }

  public String getname() {
    return material.getname();
  }

  public double getamount() {
    return amount;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Stream) {
      Stream that = (Stream) obj;
      if (that.material.equals(this.material))
        return true;
      else
        return false;
    } else
      return false;
  }
  @Override
  public int hashCode() {
    return (int) (material.hashCode()+2*amount);
  }
  
  public boolean islarger(Stream that) {
    if(this.amount>=that.amount)
      return true;
    else 
      return false;
  }
  
  public Material materialcopy() {
    return this.material.copy();
  }
}

