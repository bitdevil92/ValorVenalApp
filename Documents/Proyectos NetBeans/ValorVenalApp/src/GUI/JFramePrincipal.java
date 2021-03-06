/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import DatabaseManager.AutoDAO;
import DatabaseManager.AutoDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tobi
 */
public class JFramePrincipal extends javax.swing.JFrame {
    
    Thread hiloAuxiliar;
    
    DefaultTableModel modeloTabla;        
    
    HashMap <Integer, AutoDTO> carMap;
    
    /**
     * Creates new form JFramePrincipal
     */
    public JFramePrincipal() {                          
        initComponents();
        
        realizarBusqueda(jtxtMarca.getText(),jtxtModelo.getText());
    }
    
    
    public void reiniciarTabla(){
        int filas = modeloTabla.getRowCount();
        for(int i = filas-1; i >= 0; i--){
            modeloTabla.removeRow(i);            
        }
    }        
    
    public boolean isEmptyDB(){
        if(AutoDAO.consultarTodos().size() == 0){
            System.out.println("VACIA");
            return true;
        }else{
            System.out.println("LLENA");
            return false;
        }                
    }
    
    public void realizarBusqueda(String marca, String modelo){
        AutoDTO autoBuscado = new AutoDTO();
        String marcaBuscada = null;
        String modeloBuscado = null;
        
        if(!marca.equals(""))marcaBuscada = marca;
        if(!modelo.equals(""))modeloBuscado = modelo;        
        
        autoBuscado.setMarca(marcaBuscada);
        autoBuscado.setModelo(modeloBuscado); 
        
        carMap = AutoDAO.searchAuto(autoBuscado);
        
        actualizarTabla();
    }        
    
    public void actualizarTabla(){
        int count = 1;
        reiniciarTabla();
        
        for(AutoDTO auto: carMap.values()){
            Object[] fila = new Object[12];
            fila[0]=auto.getMarca();
            fila[1]=auto.getModelo();
            fila[2]=auto.getIniPerComercial();
            fila[3]=auto.getIniPerComercial();
            fila[4]=auto.getCilindrada();
            fila[5]=auto.getNumCilindros();
            fila[6]=auto.getCombustible();
            fila[7]=auto.getPotenciaKW();
            fila[8]=auto.getPotenciaFiscal();
            fila[9]=auto.getPotenciaCv();
            fila[10]=auto.getEmisiones();
            fila[11]=auto.getValor();
            
            System.out.println(count);
            count++;
            modeloTabla.addRow(fila);
        }       
    }
        
    public void newDataUpdate(){
        if(hiloAuxiliar != null && hiloAuxiliar.isAlive()){
            try {
                hiloAuxiliar.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            hiloAuxiliar = new Thread(){
                @Override
                public void run() {                    
                    realizarBusqueda(jtxtMarca.getText(),jtxtModelo.getText());
                } 
            };  
            hiloAuxiliar.start();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtxtMarca = new javax.swing.JTextField();
        jbtnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jtxtModelo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("MARCA");
        modeloTabla.addColumn("MODELO");
        modeloTabla.addColumn("INI PER COMERCIAL");
        modeloTabla.addColumn("FIN PER COMERCIAL");
        modeloTabla.addColumn("CILINDRADA");
        modeloTabla.addColumn("N CILIN");
        modeloTabla.addColumn("COMBUSTIBLE");
        modeloTabla.addColumn("POT KW");
        modeloTabla.addColumn("POT Fiscal");
        modeloTabla.addColumn("POT CV");
        modeloTabla.addColumn("EMISIONES");
        modeloTabla.addColumn("VALOR");
        jTable1.setModel(modeloTabla);
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("MARCA");

        jtxtMarca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jtxtMarcaCaretUpdate(evt);
            }
        });

        jbtnBuscar.setText("Buscar");
        jbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("MODELO");

        jtxtModelo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jtxtModeloCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnBuscar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtxtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnBuscar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarActionPerformed
        // TODO add your handling code here:       
        
        
    }//GEN-LAST:event_jbtnBuscarActionPerformed

    private void jtxtMarcaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jtxtMarcaCaretUpdate
        // TODO add your handling code here:
        newDataUpdate();
    }//GEN-LAST:event_jtxtMarcaCaretUpdate

    private void jtxtModeloCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jtxtModeloCaretUpdate
        // TODO add your handling code here:
        newDataUpdate();        
    }//GEN-LAST:event_jtxtModeloCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JTextField jtxtMarca;
    private javax.swing.JTextField jtxtModelo;
    // End of variables declaration//GEN-END:variables
}
