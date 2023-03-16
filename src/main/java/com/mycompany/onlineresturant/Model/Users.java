/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineresturant.Model;

import com.mycompany.onlineresturant.Configuration.Database;
import com.mycompany.onlineresturant.Configuration.Email;
import com.mycompany.onlineresturant.Configuration.Email.EmailConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByFamily", query = "SELECT u FROM Users u WHERE u.family = :family"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByAddress", query = "SELECT u FROM Users u WHERE u.address = :address"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByToken", query = "SELECT u FROM Users u WHERE u.token = :token"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByCategory", query = "SELECT u FROM Users u WHERE u.category = :category")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "family")
    private String family;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 250)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "phone")
    private String phone;
    @Size(max = 150)
    @Column(name = "password")
    private String password;
    @Size(max = 100)
    @Column(name = "token")
    private String token;
    @Column(name = "status")
    private Integer status;
    @Column(name = "category")
    private Integer category;

    public Users() {
    }

    public Users(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        try{
            Users u = em.find(Users.class, id);
            this.id = u.id;
            this.email = u.email;
            this.name = u.name;
            this.family = u.family;
            this.category = u.category;
        }
        catch(Exception ex){
            System.out.println (ex.getMessage());
        }
        finally{
            //clean up metods, and close connections
            em.clear();
            em.close();
            emf.close();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.Users[ id=" + id + " ]";
    }
    
    public List<Users> getAllUsers(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<Users> users = new ArrayList(); 
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllUsers");
            List<Object[]> result = spq.getResultList();
            for(Object[] res : result){
                Object idObj = res[0]; 
                String idString = idObj.toString(); 
                Integer id = Integer.parseInt(idString); 
                Users u = em.find(Users.class, id);
                users.add(u);
            }
            return users;
        }
        catch(Exception ex){
            return users;
        }
        finally{
            //clean up metods, and close connections
            em.clear();
            em.close();
            emf.close();
        }
                
    }

    public String addUser(Users u){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        int length = 40;
        boolean useLetters = true;
        boolean useNumbers = true;
        String token = RandomStringUtils.random(length, useLetters, useNumbers);
        System.out.println("Generated Token is:  " + token);
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addUser");
            
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("familyIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("addressIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("phoneIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tokenIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("idOUT", String.class, ParameterMode.OUT);
            
            spq.setParameter("nameIN", u.getName());
            spq.setParameter("familyIN", u.getFamily());
            spq.setParameter("emailIN", u.getEmail());
            spq.setParameter("addressIN", u.getAddress());
            spq.setParameter("passwordIN", u.getPassword());
            spq.setParameter("phoneIN", u.getPhone());
            spq.setParameter("tokenIN", token);
            
            spq.execute();
            Integer result = Integer.parseInt(spq.getOutputParameterValue("idOUT").toString());

            return "id is: " + Integer.toString(result);
            
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }

    public Boolean registrationEmail(String email){
        try{
            //who will get the email
            String to = email;
            //Who is the sender of the email
            String from = Email.EmailConfig.EMAIL.get();
            
            //Confirmation link
            String token = this.getActivationToken(email);
            System.err.println("Token: " + token);
            String link = EmailConfig.CONFIRMLINK.get() + token;
            
            Properties properties = System.getProperties();
            //setup mail server to properties
            properties.put("mail.smtp.host", Email.EmailConfig.HOST.get());
            properties.put("mail.smtp.port", Email.EmailConfig.PORT.get());
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            
            //Get session object, and pass username and password in
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Email.EmailConfig.EMAIL.get(), Email.EmailConfig.PASSWORD.get());
                }
            });
            session.setDebug(true);
            
            //Configure the email (message object)
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            message.setSubject("Welcome");
            message.setText("Thanks for the registration to our service, and enjoy your journey with our application! \n Confirm link : " + link);
            
            //Send the email
            Transport.send(message);
            return true;
            
            
            
        }
        catch(Exception ex){
            return false;
        }
    }

    public String getActivationToken(String email){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try{
            //Create SPQ and run it
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getTokenByEmail");
            
            spq.registerStoredProcedureParameter("tokenOUT", String.class, ParameterMode.OUT);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            
            spq.setParameter("emailIN", email);
            spq.execute();
            String result = spq.getOutputParameterValue("tokenOUT").toString();
//            String[] splitted = result.split("\"");
            
            return result;
            
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        finally{
            //clean up metods, and close connections
            em.clear();
            em.close();
            emf.close();
        }
    }


    public Boolean activateAcount(String token){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("activateAcount");
            
            spq.registerStoredProcedureParameter("tokenIN", String.class, ParameterMode.IN);
            
            spq.setParameter("tokenIN", token);
            spq.execute();
            
            return true;
            
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }

    
    public Users loginUser(String email, String password){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        Users users = new Users(); 
       // List<Users> users = new ArrayList(); 

        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("loginUser");
            
            spq.registerStoredProcedureParameter("usernameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            
            spq.setParameter("usernameIN", email);
            spq.setParameter("passwordIN", password);
            List<Object[]>result = spq.getResultList();
            for(Object[] res : result){
                Object idObj = res[0]; 
                String idString = idObj.toString(); 
                Integer id = Integer.parseInt(idString); 
                Users u = em.find(Users.class, id);
                users = u;
            }
            return users;
            
        }
        catch(Exception ex){
            return users;
        }
        finally{            
            em.clear();
            em.close();
            emf.close();
        }
    }
    


    public boolean sendResetPassword(String email){
        
        try{
            String to = email;
            String from = Email.EmailConfig.EMAIL.get();
            
            String token = PasswordReplacement.addPasswordReplacement(email);
            String link = EmailConfig.PWLINK.get() + token + "&email="+email;
            
            Properties properties = System.getProperties();
            //setup mail server to properties
            properties.put("mail.smtp.host", Email.EmailConfig.HOST.get());
            properties.put("mail.smtp.port", Email.EmailConfig.PORT.get());
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            
            //Get session object, and pass username and password in
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Email.EmailConfig.EMAIL.get(), Email.EmailConfig.PASSWORD.get());
                }
            });
            session.setDebug(true);
            
            //Configure the email (message object)
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            message.setSubject("Password replacement");
            message.setText("This is your password replacement! \n click this link : " + link);
            
            //Send the email
            Transport.send(message);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public Boolean resetPasswordLink(String email, String password, String pwtoken){
        if (PasswordReplacement.validatePwReplacement(pwtoken)){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
            EntityManager em = emf.createEntityManager();
        
            try{
                StoredProcedureQuery spq = em.createStoredProcedureQuery("changePassword");

                spq.registerStoredProcedureParameter("pwIN", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("tokenIN", String.class, ParameterMode.IN);

                spq.setParameter("pwIN", password);
                spq.setParameter("emailIN", email);
                spq.setParameter("tokenIN", pwtoken);
                spq.execute();
                System.err.println("Change Password Executed.");
                return true;


            }
            catch(Exception ex){
                System.err.println(ex.getMessage());
                return false;
            }
            finally{
                em.clear();
                em.close();
                emf.close();
            }            
        }
        else
        {
            return false;
        }
        
    }
   
    public static Boolean validUserId(int vId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("validUserId");
            
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("cntResult", Integer.class, ParameterMode.OUT);
            
            spq.setParameter("userIdIN", vId);
            
            spq.execute();
            
            Integer result = Integer.parseInt(spq.getOutputParameterValue("cntResult").toString());
            
            return result == 1? true : false;
            
        }
        catch(Exception ex){
            return false;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }

    public String deleteUser(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUser");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            return "User deleted successfully.";
            
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }

    public String updateuser(Users u){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateUser");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("familyIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("addressIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("phoneIN", String.class, ParameterMode.IN);
             
            spq.setParameter("idIN", u.getId());
            spq.setParameter("nameIN", u.getName());
            spq.setParameter("familyIN", u.getFamily());
            spq.setParameter("emailIN", u.getEmail());
            spq.setParameter("addressIN", u.getAddress());
            spq.setParameter("phoneIN", u.getPhone());
            
            spq.execute();
            
            return "User updated successfully.";
            
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    
}
