
package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.Foods;
import java.util.List;

public class FoodService {

    Foods fd = new Foods();
    
    public String addFood(Foods f){
        try{
            return fd.addFood(f);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String updateFood(Foods f){
        try{
            return fd.updateFood(f);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String deleteFood(int delId){
        try{
            return fd.deleteFood(delId);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public List<Foods> getAllFoods(){
        return fd.getAllFoods();
    }
}
