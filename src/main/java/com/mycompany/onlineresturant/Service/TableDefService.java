package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.TableDef;
import java.util.List;

public class TableDefService {
    
    TableDef tDef = new TableDef();
    
    public String defineTable(TableDef td){
        return tDef.defineTable(td);
    }
    
    public String deleteTable(int idIn){
        return tDef.deleteTable(idIn);
    }

    public String updateTable(TableDef t){
        return tDef.updateTable(t);
    }
    
    public List<TableDef> getAllTables(){
        return tDef.getAllTables();
    }


}
