package form;

import conn.Conexion;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author farca
 */
public class BuscarCiudad extends javax.swing.JDialog {

    Conexion connBD = new Conexion();
    private int posiRegistro = 0; //posición de registro
    static String valorID = ""; //valor de código
    javax.swing.table.DefaultTableModel m;//crear un modelo de tabla

    public BuscarCiudad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Buscar ciudad");
        m = (javax.swing.table.DefaultTableModel) tbCiudades.getModel();
        this.setLocationRelativeTo(null);
        carga_grilla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCiudades = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCiudad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbCiudades.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbCiudades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Ciudad", "Departamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCiudades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCiudadesKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbCiudadesKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tbCiudades);
        if (tbCiudades.getColumnModel().getColumnCount() > 0) {
            tbCiudades.getColumnModel().getColumn(0).setMinWidth(100);
            tbCiudades.getColumnModel().getColumn(0).setMaxWidth(100);
            tbCiudades.getColumnModel().getColumn(1).setMinWidth(170);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ciudad:");

        txtNombreCiudad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombreCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreCiudadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreCiudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCiudadKeyTyped
        filtrar_grilla();
    }//GEN-LAST:event_txtNombreCiudadKeyTyped

    private void tbCiudadesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiudadesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCiudadesKeyTyped

    private void tbCiudadesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiudadesKeyPressed
        eligio();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            m.setRowCount(0);
            txtNombreCiudad.setText("");
            carga_grilla();
            dispose();
        }
    }//GEN-LAST:event_tbCiudadesKeyPressed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarCiudad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarCiudad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarCiudad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarCiudad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BuscarCiudad dialog = new BuscarCiudad(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCiudades;
    private javax.swing.JTextField txtNombreCiudad;
    // End of variables declaration//GEN-END:variables

    void carga_grilla() {
        try {
            // Inicializa el modelo de tabla si aún no está inicializado
            if (m == null) {
                m = new DefaultTableModel();
                m.setColumnIdentifiers(new Object[]{"ID Ciudad", "Nombre", "Depto"});
            }

            // Limpia el modelo de la tabla antes de cargar nuevos datos
            m.setRowCount(0);

            connBD.st = connBD.connMySQL().createStatement();
            connBD.re = connBD.st.executeQuery("SELECT ciudad.id_ciudad, ciudad.ciu_nombre, departamentos.dep_nombre FROM ciudad "
                    + "JOIN departamentos ON ciudad.id_depto = departamentos.id_depto");

            while (connBD.re.next()) {
                m.addRow(new Object[]{connBD.re.getInt("id_ciudad"), connBD.re.getString("ciu_nombre"), connBD.re.getString("dep_nombre")});
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Verifíquelo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    void filtrar_grilla() {
        m.setRowCount(0);
        try {
            connBD.st = (Statement) connBD.connMySQL().createStatement();
            //connBD.re = (ResultSet) connBD.st.executeQuery("SELECT * FROM ciudad WHERE ciu_nombre LIKE '%" + txtNombreCiudad.getText() + "%'");
            connBD.re = connBD.st.executeQuery("SELECT ciudad.id_ciudad, ciudad.ciu_nombre, departamentos.dep_nombre FROM ciudad "
                    + "JOIN departamentos ON ciudad.id_depto = departamentos.id_depto "
                    + "WHERE ciu_nombre LIKE '%" + txtNombreCiudad.getText().toUpperCase().trim() + "%'");

            if (connBD.re.next()) {
                do {
                    m.addRow(new Object[]{connBD.re.getInt("id_ciudad"), connBD.re.getString("ciu_nombre"), connBD.re.getString("dep_nombre")});
                } while (connBD.re.next());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Verifíquelo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    void eligio() {
        posiRegistro = tbCiudades.getSelectedRow();
        valorID = (String.valueOf(this.tbCiudades.getValueAt(posiRegistro, 0)));
        System.out.println(valorID);
    }
}
