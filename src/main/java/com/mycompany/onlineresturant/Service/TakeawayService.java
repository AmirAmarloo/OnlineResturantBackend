
package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.Takeaway;

public class TakeawayService {
    
    Takeaway tw = new Takeaway();
    
    public String addTakeaway(Takeaway tk){
        try
        {
            return tw.addTakeaway(tk);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String updateTakeaway(Takeaway tk){
        try
        {
            return tw.updateTakeaway(tk);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    public String deleteTakeaway(int delId){
        try
        {
            return tw.deleteTakeaway(delId);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
}
