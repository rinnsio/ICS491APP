package gui;


import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

    




    public class ColorTable extends JTable {


    private static final long serialVersionUID = 1L;
    private Map rowColor = new HashMap();
    private Map columnColor = new HashMap();
    private Color cellColor;
    private Color defaultColor;

    public ColorTable( TableModel model ) {
        super( model );
    }
    public boolean isCellEditable(int rowIndex, int colIndex) {
        return false;   //Disallow the editing of any cell
    }
    
    public void setRowColor( int row, Color c) {
        rowColor.put( new Integer( row ), c );
    }

    public void setColumnColor( int column, Color c ) {
        columnColor.put( new Integer( column ), c );
    }

    public void setCellColor( Color c ) {
        cellColor = c;
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    public Component prepareRenderer( TableCellRenderer renderer, int row, int column ) {

        Component c = super.prepareRenderer( renderer, row, column );
        if ( defaultColor == null )
            defaultColor = c.getBackground();

        // Color order is as follows:
        // rowSelection, checkBox toggle for row color, column color, cell color
        if ( ! isRowSelected( row ) ) {
            Color color = (Color) rowColor.get( new Integer( row ) );
            if ( color == null || Boolean.FALSE.equals( getModel().getValueAt( row, 0 ) ) )
                color = (Color) columnColor.get( new Integer( column ) );
            if ( color == null ) {
                // cell color only if cell has special value, for example purposes,
                // if the cell value begins with a 2
                Object value = getValueAt( row, column );
                if ( value != null && value.toString().startsWith( "2" ) )
                    color = cellColor;
            }
            if ( color != null )
                c.setBackground( color );
            else
                c.setBackground( defaultColor );
        }
        return c;
    }

    public void resetColor(Color color) {
        for(int i=0;i<this.getRowCount();i++)
            this.setRowColor(i, color);
    }
}