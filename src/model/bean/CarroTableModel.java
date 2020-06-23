
package model.bean;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class CarroTableModel extends AbstractTableModel {
    private List<Carro> dados = new ArrayList<>(); 
    private String[]   colunas = {"Modelo","Ano","Placa","Valor"};
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void adcionarLinha(Carro c)    {
        this.dados.add(c);
        this.fireTableDataChanged();
}
    public void removerLinha(int linha)    {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
}

    @Override
    public String getColumnName(int column) {
        return colunas [column]; //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
    return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
            case 0:
                    return dados.get(linha).getModelo();
        }
        switch(coluna){
            case 1:
                    return dados.get(linha).getAno();
        }
        switch(coluna){
            case 2:
                    return dados.get(linha).getPlaca();
        }
        switch(coluna){
            case 3:
                    return dados.get(linha).getValor();
        }
      return null;
    }

    @Override
    public void setValueAt(Object v, int linha, int coluna) {
        
         switch(coluna){
            case 0:
                     dados.get(linha).setModelo((String)v);
                     break;
        }
        switch(coluna){
            case 1:
                     dados.get(linha).setAno( Integer.parseInt( (String)v) );
                     break;
        }
        switch(coluna){
            case 2:
                    dados.get(linha).setPlaca((String)v);
                     break;
        }
        switch(coluna){
            case 3:
                   dados.get(linha).setValor(Float.parseFloat((String)v));
                     break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }
    

}