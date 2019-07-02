package formula;

import java.util.ArrayList;
import java.util.List;
import stream.Stream;

public class Formula {
  private final Stream inputStream1;
  private final Stream inputStream2;
  private final Stream outputStream;
  private final String factory;
  private Stragegy strategy=Stragegy.performance;
  private int machines=0;
  private double effectiveness=0;
  public Formula(Stream inputStream1,Stream inputStream2,Stream outputStream,String factory) {
    this.inputStream1=inputStream1;
    this.inputStream2=inputStream2;
    this.outputStream=outputStream;
    this.factory=factory;
  }
  public boolean isstart() {
    if(inputStream1==null&&inputStream2==null) 
      return true;
    else 
      return false;
  }
  enum Stragegy{
    performance,capacity
  }
  public void setstrategy() {
    
  }
  public String factory() {
    return new String(factory);
  }
  public Stream output(Stream inputStream1,Stream inputStream2) {
    if(this.inputStream1.equals(inputStream1)&&this.inputStream2.equals(inputStream2)) {
      
    }
    else if(this.inputStream1.equals(inputStream2)&&this.inputStream2.equals(inputStream1)) {
      Stream tmp=inputStream1;
      inputStream1=inputStream2;
      inputStream2=tmp;
    }
    else
      return null;
    if(inputStream1.islarger(this.inputStream1)&&inputStream2.islarger(this.inputStream2)) {
      int m1=(int) (inputStream1.getamount()/this.inputStream1.getamount());
      int m2=(int) (inputStream2.getamount()/this.inputStream2.getamount());
      double outputamount=(m1<m2?m1:m2)*this.outputStream.getamount();
      return new Stream(outputStream.materialcopy(), outputamount);
    }
    else {
      double m1=inputStream1.getamount()/this.inputStream1.getamount();
      double m2=inputStream2.getamount()/this.inputStream2.getamount();
      double outputamount2=(m1<m2?m1:m2)*this.outputStream.getamount();
      return new Stream(outputStream.materialcopy(), outputamount2);
    }
  }
  public List<Stream> input(Stream outputStream) {
    assert(machines==0);
    assert(effectiveness==0);
    if(this.outputStream.equals(outputStream)) {
      List<Stream> tmp=new ArrayList<Stream>();
        if(this.outputStream.islarger(outputStream)) {
          tmp.add(new Stream(inputStream1.materialcopy(), 
              inputStream1.getamount()*outputStream.getamount()/this.outputStream.getamount()));
          tmp.add(new Stream(inputStream2.materialcopy(), 
              inputStream2.getamount()*outputStream.getamount()/this.outputStream.getamount()));
          return tmp;
        }
        else {
          if(this.strategy==Stragegy.performance) {
            int m1=(int) (outputStream.getamount()/this.outputStream.getamount());
            this.machines=m1;
            this.effectiveness=1;
            tmp.add(new Stream(inputStream1.materialcopy(), 
                inputStream1.getamount()*m1));
            tmp.add(new Stream(inputStream2.materialcopy(), 
                inputStream2.getamount()*m1));
            return tmp;
          }
          else {
            double m2=outputStream.getamount()/this.outputStream.getamount();
            if(m2==(int)m2) {
              this.machines=(int) m2;
              this.effectiveness=1;
            }
            else {
              this.machines=((int)m2)+1;
              this.effectiveness=m2/machines;
            }
            tmp.add(new Stream(inputStream1.materialcopy(), 
                inputStream1.getamount()*m2));
            tmp.add(new Stream(inputStream2.materialcopy(), 
                inputStream2.getamount()*m2));
            return tmp;
          }
        }
    }
    else {
      this.machines=0;
      this.effectiveness=0;
      return null;
      }
  }
  public int getmachineamount() {
    int tmp=this.machines;
    this.machines=0;
    return tmp;
  }
  public double geteffectiveness() {
    double tmp=this.effectiveness;
    this.effectiveness=0;
    return tmp;
  }

}
