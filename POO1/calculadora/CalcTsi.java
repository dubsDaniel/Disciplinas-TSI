package calculadora;

public class CalcTsi extends javax.swing.JFrame {

  Double valor1,valor2;
  String sinal, convert;
    
    public CalcTsi() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton12 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btn_Sete = new javax.swing.JButton();
        btn_Oito = new javax.swing.JButton();
        btn_Nove = new javax.swing.JButton();
        btn_Quatro = new javax.swing.JButton();
        btn_Cinco = new javax.swing.JButton();
        btn_Seis = new javax.swing.JButton();
        btn_Um = new javax.swing.JButton();
        btn_Dois = new javax.swing.JButton();
        btn_tres = new javax.swing.JButton();
        btn_zero = new javax.swing.JButton();
        btn_Ponto = new javax.swing.JButton();
        btn_CE = new javax.swing.JButton();
        btn_C = new javax.swing.JButton();
        btn_igual = new javax.swing.JButton();
        btn_Adicionar = new javax.swing.JButton();
        btn_menos = new javax.swing.JButton();
        btn_Multiplicar = new javax.swing.JButton();
        btn_Dividir = new javax.swing.JButton();
        txf_Resultado = new javax.swing.JTextField();
        btn_oct = new javax.swing.JButton();
        btn_bin = new javax.swing.JButton();
        btn_hex = new javax.swing.JButton();
        btn_dec = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jButton12.setText("jButton12");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_Sete.setText("7");
        btn_Sete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeteActionPerformed(evt);
            }
        });

        btn_Oito.setText("8");
        btn_Oito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_OitoActionPerformed(evt);
            }
        });

        btn_Nove.setText("9");
        btn_Nove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NoveActionPerformed(evt);
            }
        });

        btn_Quatro.setText("4");
        btn_Quatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuatroActionPerformed(evt);
            }
        });

        btn_Cinco.setText("5");
        btn_Cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CincoActionPerformed(evt);
            }
        });

        btn_Seis.setText("6");
        btn_Seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeisActionPerformed(evt);
            }
        });

        btn_Um.setText("1");
        btn_Um.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UmActionPerformed(evt);
            }
        });

        btn_Dois.setText("2");
        btn_Dois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DoisActionPerformed(evt);
            }
        });

        btn_tres.setText("3");
        btn_tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tresActionPerformed(evt);
            }
        });

        btn_zero.setText("0");
        btn_zero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_zeroActionPerformed(evt);
            }
        });

        btn_Ponto.setText(".");
        btn_Ponto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PontoActionPerformed(evt);
            }
        });

        btn_CE.setText("CE");
        btn_CE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CEActionPerformed(evt);
            }
        });

        btn_C.setText("C");
        btn_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CActionPerformed(evt);
            }
        });

        btn_igual.setText("=");
        btn_igual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_igualActionPerformed(evt);
            }
        });

        btn_Adicionar.setText("+");
        btn_Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AdicionarActionPerformed(evt);
            }
        });

        btn_menos.setText("-");
        btn_menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menosActionPerformed(evt);
            }
        });

        btn_Multiplicar.setText("*");
        btn_Multiplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MultiplicarActionPerformed(evt);
            }
        });

        btn_Dividir.setText("/");
        btn_Dividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DividirActionPerformed(evt);
            }
        });

        btn_oct.setText("OCT");
        btn_oct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_octActionPerformed(evt);
            }
        });

        btn_bin.setText("BINA");
        btn_bin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_binActionPerformed(evt);
            }
        });

        btn_hex.setText("HEX");
        btn_hex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hexActionPerformed(evt);
            }
        });

        btn_dec.setText("DEC");
        btn_dec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_decActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txf_Resultado)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_Sete)
                                    .addComponent(btn_Quatro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_Cinco)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_Seis)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_C, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_Oito)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_Nove)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_CE, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_Um, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_Dois))
                                    .addComponent(btn_zero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_tres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Ponto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_igual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Multiplicar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_hex))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Dividir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_dec))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Adicionar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_oct))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_menos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_bin)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txf_Resultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Sete)
                    .addComponent(btn_Oito)
                    .addComponent(btn_Nove)
                    .addComponent(btn_CE)
                    .addComponent(btn_Adicionar)
                    .addComponent(btn_oct))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Quatro)
                    .addComponent(btn_Cinco)
                    .addComponent(btn_Seis)
                    .addComponent(btn_C)
                    .addComponent(btn_menos)
                    .addComponent(btn_bin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Um)
                            .addComponent(btn_Dois)
                            .addComponent(btn_tres))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_zero)
                            .addComponent(btn_Ponto)))
                    .addComponent(btn_igual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Multiplicar)
                            .addComponent(btn_hex))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Dividir)
                            .addComponent(btn_dec))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Calculadora");

        jLabel2.setText("Por favor, para usar, siga as regras da calculadora...");

        jLabel3.setText("- Para converter, digite somente o primeiro valor;");

        jLabel4.setText("- Sempre que for converter novamente, voltar para o modo Decimal;");

        jLabel5.setText("- Somente converter números inteiros;");

        jLabel6.setText("- Se divirta.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CincoActionPerformed
       txf_Resultado.setText(txf_Resultado.getText()+"5");
    }//GEN-LAST:event_btn_CincoActionPerformed

    private void btn_SeisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeisActionPerformed
              txf_Resultado.setText(txf_Resultado.getText()+"6");

    }//GEN-LAST:event_btn_SeisActionPerformed

    private void btn_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menosActionPerformed
       valor1=Double.parseDouble(txf_Resultado.getText());
        txf_Resultado.setText("");
        sinal="subtrair";
    }//GEN-LAST:event_btn_menosActionPerformed

    private void btn_MultiplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MultiplicarActionPerformed
      valor1=Double.parseDouble(txf_Resultado.getText());
        txf_Resultado.setText("");
        sinal="multiplicar";
    }//GEN-LAST:event_btn_MultiplicarActionPerformed

    private void btn_zeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_zeroActionPerformed
       txf_Resultado.setText(txf_Resultado.getText()+"0");
    }//GEN-LAST:event_btn_zeroActionPerformed

    private void btn_UmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UmActionPerformed
              txf_Resultado.setText(txf_Resultado.getText()+"1");

    }//GEN-LAST:event_btn_UmActionPerformed

    private void btn_QuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuatroActionPerformed
               txf_Resultado.setText(txf_Resultado.getText()+"4");

    }//GEN-LAST:event_btn_QuatroActionPerformed

    private void btn_SeteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeteActionPerformed
             txf_Resultado.setText(txf_Resultado.getText()+"7");

    }//GEN-LAST:event_btn_SeteActionPerformed

    private void btn_OitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_OitoActionPerformed
               txf_Resultado.setText(txf_Resultado.getText()+"8");

    }//GEN-LAST:event_btn_OitoActionPerformed

    private void btn_NoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NoveActionPerformed
               txf_Resultado.setText(txf_Resultado.getText()+"9");

    }//GEN-LAST:event_btn_NoveActionPerformed

    private void btn_tresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tresActionPerformed
            txf_Resultado.setText(txf_Resultado.getText()+"3");

    }//GEN-LAST:event_btn_tresActionPerformed

    private void btn_PontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PontoActionPerformed
              txf_Resultado.setText(txf_Resultado.getText()+".");

    }//GEN-LAST:event_btn_PontoActionPerformed

    private void btn_DoisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DoisActionPerformed
               txf_Resultado.setText(txf_Resultado.getText()+"2");

    }//GEN-LAST:event_btn_DoisActionPerformed

    private void btn_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AdicionarActionPerformed
        valor1=Double.parseDouble(txf_Resultado.getText());
        txf_Resultado.setText("");
        sinal="somar";
        
    }//GEN-LAST:event_btn_AdicionarActionPerformed

    private void btn_DividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DividirActionPerformed
        valor1=Double.parseDouble(txf_Resultado.getText());
        txf_Resultado.setText("");
        sinal="dividir";
    }//GEN-LAST:event_btn_DividirActionPerformed

    private void btn_igualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_igualActionPerformed
      valor2=Double.parseDouble(txf_Resultado.getText());
      if(sinal=="somar"){
        txf_Resultado.setText(String.valueOf(valor1+valor2));
      }
      if(sinal=="subtrair"){
        txf_Resultado.setText(String.valueOf(valor1-valor2));

      }
      if(sinal=="multiplicar"){
         txf_Resultado.setText(String.valueOf(valor1*valor2));

      }
      if(sinal=="dividir"){
         txf_Resultado.setText(String.valueOf(valor1/valor2));

      }
      
      
    }//GEN-LAST:event_btn_igualActionPerformed

    private void btn_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CActionPerformed
      valor1=null;
      valor2=null;
      txf_Resultado.setText("");

    }//GEN-LAST:event_btn_CActionPerformed

    private void btn_CEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CEActionPerformed
         txf_Resultado.setText("");
    }//GEN-LAST:event_btn_CEActionPerformed

    private void btn_octActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_octActionPerformed
        convert = "oct";
        String introstrg = String.valueOf(txf_Resultado.getText());
        int oct = Integer.parseInt(txf_Resultado.getText());
        String nvResultado = Integer.toOctalString(oct);
        txf_Resultado.setText(nvResultado);
    }//GEN-LAST:event_btn_octActionPerformed

    private void btn_hexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hexActionPerformed
        convert = "hex";
        String introstrg = String.valueOf(txf_Resultado.getText());
        int hex = Integer.parseInt(txf_Resultado.getText());
        String nvResultado = Integer.toHexString(hex);
        txf_Resultado.setText(nvResultado);
    }//GEN-LAST:event_btn_hexActionPerformed

    private void btn_binActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_binActionPerformed
        convert = "bin";
        String introstrg = String.valueOf(txf_Resultado.getText());
        int bin = Integer.valueOf(introstrg);
        String nvResultado = Integer.toBinaryString(bin);
        txf_Resultado.setText(String.valueOf(nvResultado));
    }//GEN-LAST:event_btn_binActionPerformed

    private void btn_decActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_decActionPerformed
        String introstrg = String.valueOf(txf_Resultado.getText());
        int dec = 0;
        if ("bin".equals(convert)) {
            
            dec = Integer.parseInt(introstrg, 2);
        }
        if ("oct".equals(convert)) {
            dec = Integer.parseInt(introstrg, 8);
        }
        if ("hex".equals(convert)) {
            dec = Integer.parseInt(introstrg, 16);
        } else {
            txf_Resultado.setText("ERRO");
        }
        
        txf_Resultado.setText(String.valueOf(dec));
    }//GEN-LAST:event_btn_decActionPerformed

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
            java.util.logging.Logger.getLogger(CalcTsi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalcTsi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalcTsi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalcTsi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalcTsi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Adicionar;
    private javax.swing.JButton btn_C;
    private javax.swing.JButton btn_CE;
    private javax.swing.JButton btn_Cinco;
    private javax.swing.JButton btn_Dividir;
    private javax.swing.JButton btn_Dois;
    private javax.swing.JButton btn_Multiplicar;
    private javax.swing.JButton btn_Nove;
    private javax.swing.JButton btn_Oito;
    private javax.swing.JButton btn_Ponto;
    private javax.swing.JButton btn_Quatro;
    private javax.swing.JButton btn_Seis;
    private javax.swing.JButton btn_Sete;
    private javax.swing.JButton btn_Um;
    private javax.swing.JButton btn_bin;
    private javax.swing.JButton btn_dec;
    private javax.swing.JButton btn_hex;
    private javax.swing.JButton btn_igual;
    private javax.swing.JButton btn_menos;
    private javax.swing.JButton btn_oct;
    private javax.swing.JButton btn_tres;
    private javax.swing.JButton btn_zero;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txf_Resultado;
    // End of variables declaration//GEN-END:variables
}
