package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.Deliver;

public class DeliverService {

    Deliver dm = new Deliver();
    
    public String addDeliver(Deliver dv){
        System.out.println("salam");
        try
        {
            System.out.println("Begin of Service");
            System.err.println(dv.getUserId());
            System.err.println(dv.getByWho());
            
            return dm.addDeliver(dv);
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    public String deleteDeliver(int delId){
        try
        {
            return dm.deleteDeleiver(delId);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    public String updateDeliver(Deliver dv){
        try
        {
            return dm.updateDeliver(dv);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

}
