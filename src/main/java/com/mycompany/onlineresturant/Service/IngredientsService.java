
package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.Ingredients;

public class IngredientsService {

    Ingredients ingred = new Ingredients();
    
    public String addIngredients(Ingredients i){
        try
        {
            return ingred.addIngredients(i);
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    public String deleteIngredient(int delId){
        try
        {
            return ingred.deleteIngredients(delId);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String updateIngredient(Ingredients i){
        try
        {
            return ingred.updateIngredients(i);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
}
