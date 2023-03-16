/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineresturant.Controller;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Administrator
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mycompany.onlineresturant.Configuration.CorsFilter.class);
        resources.add(com.mycompany.onlineresturant.Controller.DeliverController.class);
        resources.add(com.mycompany.onlineresturant.Controller.FoodsController.class);
        resources.add(com.mycompany.onlineresturant.Controller.IngredientsCntroller.class);
        resources.add(com.mycompany.onlineresturant.Controller.OrdersController.class);
        resources.add(com.mycompany.onlineresturant.Controller.ReservationController.class);
        resources.add(com.mycompany.onlineresturant.Controller.TableDefController.class);
        resources.add(com.mycompany.onlineresturant.Controller.TakeawayController.class);
        resources.add(com.mycompany.onlineresturant.Controller.TakeawayStuffController.class);
        resources.add(com.mycompany.onlineresturant.Controller.UsersController.class);
    }
    
}
