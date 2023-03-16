package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.Users;
import java.util.List;

public class UsersService {

    Users user = new Users();
    
    public List<Users> getAllUsers(){
        return user.getAllUsers();
    }
    
    public String addUser(Users u){
        String result = "";
        try{
            result = user.addUser(u);
            if(u.registrationEmail(u.getEmail())){
                result += " Email is also sent!";
            }
            else{
                result += " Email is not sent!";
            }
            return result;
            
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    public String activateAcount(String token){
        //Business logic validation


        if(token.length() != 40){
            return "The token length is invalid";
        }
        //Is the token exist
        else{
            if(user.activateAcount(token)){
                return "Succesfully activated";
            }
            else{
                return "The activation wan not succesfull";
            }
        }
    }
    
    public Users loginUser(String email, String password){
        return user.loginUser(email, password);
    }
    
    public String resetPassword(String email){
        if (user.sendResetPassword(email)){
            return "Email successfully sent";
        }
        else{
            return "There is some problem";
        }
    }
    

    
    public String resetPasswordLink(String email, String password, String pwtoken){
        if (user.resetPasswordLink(email, password, pwtoken)){
            return "Password changed successfully";
        }
        else{
            return "There is some problem";
        }
    }
    
    public String updateUser(Users u){
        try{
            return user.updateuser(u);
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }
    
    public String deleteUser(int id){
        try{
            return user.deleteUser(id);
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }
    
}
