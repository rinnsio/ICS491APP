/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clockinsystem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinson
 */
public class logs extends javax.swing.JDialog {
    private ResultSet logs;
    /**
     * Creates new form logs
     */
    // an array that store logs records from database 
    private ArrayList<Object> dataObjectForSubTable;
    
    public logs(java.awt.Frame parent, boolean modal, ResultSet logs_) {
        super(parent, modal);
        initComponents();
        logs= logs_;
        dataObjectForSubTable = new ArrayList<Object>();
        getAllLogsIntoJTable();
    }
   
    // initial jtable
    public void getAllLogsIntoJTable(){
          //create a temperary variable to contain the object from the resultset
          ArrayList<Object> temp= new ArrayList<Object>();; 
       
          try{
            while(logs.next()){
//              logs.getTime("time_in");
//              System.out.println(logs.getTime("time_in"));
                Date date = logs.getDate("date");
                int wage = logs.getInt("wage");
                //logs.getTime("time_in") doesn't work for me, you guys can tryit, it maybe my mysql connector outdated
                Time time_in = logs.getTime("time_in");
                //logs.getTime("time_in") doesn't work for me, you guys can tryit, it maybe my mysql connector outdated
                Time time_out = logs.getTime("time_out");
                Double total_hours = logs.getDouble("total_hours");
                Double total_wages = logs.getDouble("total_wages");

                // create a row with each colum in the database resultset 
                Object[] oneRowLog = new Object[]{date,wage,time_in,time_out,total_hours,total_wages};
                // add to the tamperary variable
                temp.add(oneRowLog);
                // debug
                System.out.println(date + " "+wage+ " "+time_in+ " "+time_out+ " "+total_hours+ " "+total_wages);
            }
                  
          }catch(Exception e){
                System.out.println("Error occur on processing the logs result set at class - logs.java \n"+e);
          }
          // convert the data into string
          this.addSubTableRowsToDataObject(temp);  
          // build the jtable now
          Object[][] finalData = dataObjectForSubTable.toArray(new Object[dataObjectForSubTable.size()][]);
          this.buildJtableAfterMapData(finalData);
    }
    
    // reformat and validate the data from database
    public void addSubTableRowsToDataObject(ArrayList<Object> rows){
            SimpleDateFormat dateFormater = new SimpleDateFormat("HH : mm : ss");
            
            
            // converting the row is starting from row 1 to ..
            for(int i=0;i<rows.size(); i++){
                // proccesing each row of the record
                Object[] itemInRow = (Object[])rows.get(i);
                
                // covert all the data type to string to insert into jTable
                String date = ((Date)itemInRow[0]).toString();
                String wage = ((Integer)itemInRow[1]).toString();
                String time_in = dateFormater.format((Time)itemInRow[2]);
                String time_out = dateFormater.format((Time)itemInRow[3]);
                String total_hours = ((Double)itemInRow[4]).toString();
                String total_wages = '$'+((Double)itemInRow[5]).toString();
                  
                // pack the datas into the object array which is prepare for the jtable
                Object[] convertedRow = new Object[]{date, wage, time_in, time_out, total_hours, total_wages};
                // the global object of jtable instance
                dataObjectForSubTable.add(convertedRow);
            }
    }
    
    // build jtable object after reformat the data
    public void buildJtableAfterMapData(Object[][] data) {
        DefaultTableModel model;
        final ColorTable colorTable;
        
        model = new DefaultTableModel(data,
                new Object[]{"date","wage","time in","time out","total hours","total wages"});
        colorTable = new ColorTable(model);
        colorTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        colorTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        colorTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        colorTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        colorTable.getColumnModel().getColumn(4).setPreferredWidth(200);
        colorTable.getColumnModel().getColumn(5).setPreferredWidth(200);
        colorTable.setRowHeight(30);

        colorTable.getTableHeader().setReorderingAllowed(false);
        colorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        colorTable.setFillsViewportHeight(true);
        jScrollPane1.getViewport().add(colorTable);  
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
