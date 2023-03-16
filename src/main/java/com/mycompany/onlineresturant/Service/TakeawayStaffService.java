 
package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.TakeawayStuff;
import java.util.List;

public class TakeawayStaffService {

    TakeawayStuff ts = new TakeawayStuff();
    
    public String addTakeawayStuff(TakeawayStuff tsf){
        try{
            return ts.addTakeawayStuff(tsf);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String updateTakeawayStuff(TakeawayStuff tsf){
        try{
            return ts.updateTakeawayStuff(tsf);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String deleteTakeawayStuff(int delId){
        try{
            return ts.deleteTakeawayStuff(delId);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public List<TakeawayStuff> getAllTakeawayStuff(){
        return ts.getAllTakeawayStuff();
    }
}
