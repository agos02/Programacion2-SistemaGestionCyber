package com.cyber.vistas;

import com.cyber.dao.ComputadoraDAO;
import com.cyber.controladores.ComputadoraControlador;

public class ComputadoraVista extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ComputadoraVista.class.getName());

    public ComputadoraVista() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaCompus = new javax.swing.JTable();
        EstadoLabel = new javax.swing.JLabel();
        AgregarButton = new javax.swing.JButton();
        EliminarButton = new javax.swing.JButton();
        ModificarButton = new javax.swing.JButton();
        NumeroPCLabel = new javax.swing.JLabel();
        NumeroPCInput = new javax.swing.JTextField();
        Title = new javax.swing.JLabel();
        EstadoSelector = new javax.swing.JComboBox<>();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaCompus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Numero PC", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCompus.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TablaCompus);
        if (TablaCompus.getColumnModel().getColumnCount() > 0) {
            TablaCompus.getColumnModel().getColumn(2).setResizable(false);
        }

        EstadoLabel.setText("Estado");

        AgregarButton.setText("Agregar");
        AgregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarButtonActionPerformed(evt);
            }
        });

        EliminarButton.setText("Eliminar");
        EliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarButtonActionPerformed(evt);
            }
        });

        ModificarButton.setText("Modificar");
        ModificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarButtonActionPerformed(evt);
            }
        });

        NumeroPCLabel.setText("Numero PC");

        NumeroPCInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroPCInputActionPerformed(evt);
            }
        });

        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Gestion de Computadoras");
        Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        EstadoSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Libre", "Ocupada", "Mantenimiento" }));
        EstadoSelector.setName(""); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(AgregarButton)
                            .addGap(84, 84, 84)
                            .addComponent(ModificarButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EliminarButton))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(NumeroPCLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(NumeroPCInput, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EstadoLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(EstadoSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumeroPCLabel)
                    .addComponent(NumeroPCInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EstadoLabel)
                    .addComponent(EstadoSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AgregarButton)
                    .addComponent(EliminarButton)
                    .addComponent(ModificarButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    public String getNumeroPC() {
        return NumeroPCInput.getText();
    }

    public String getEstado() {
        return EstadoSelector.getSelectedItem().toString();
    }

    public javax.swing.JTable getTablaCompus() {
        return TablaCompus;
    }


    public void agregarListener(java.awt.event.ActionListener e) {
        AgregarButton.addActionListener(e);
    }

    public void modificarListener(java.awt.event.ActionListener e) {
        ModificarButton.addActionListener(e);
    }

    public void eliminarListener(java.awt.event.ActionListener e) {
        EliminarButton.addActionListener(e);
    }
    
    private void AgregarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
    }                                             

    private void EliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
    }                                              

    private void ModificarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
    }                                               

    private void NumeroPCInputActionPerformed(java.awt.event.ActionEvent evt) {                                              
    }                                             

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new ComputadoraVista().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton AgregarButton;
    private javax.swing.JButton EliminarButton;
    private javax.swing.JLabel EstadoLabel;
    private javax.swing.JComboBox<String> EstadoSelector;
    private javax.swing.JButton ModificarButton;
    private javax.swing.JTextField NumeroPCInput;
    private javax.swing.JLabel NumeroPCLabel;
    private javax.swing.JTable TablaCompus;
    private javax.swing.JLabel Title;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration                   
}
