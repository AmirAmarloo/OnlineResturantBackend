package com.mycompany.onlineresturant.Configuration;

public class Email {

    public enum EmailConfig{
        EMAIL("a.amarloo@gmail.com"),
        PASSWORD("lefkhrhxsmgucmtr"),
        HOST("smtp.gmail.com"),
        PORT("465"),
        CONFIRMLINK("http://127.0.0.1:8080/OnlineResturant-1.0-SNAPSHOT/webresources/Users/ActivateUser?t="),
        PWLINK("http://localhost:4200/changepassword?t=");
        
        private String value;
        EmailConfig(String value){
            this.value = value;
        }
        
        public String get(){
            return this.value;
        }
    }
    
}
