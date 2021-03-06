/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Karalee
 */
public class Store extends javax.swing.JFrame {

    int button1State;//states to represent if button will display: select, selected, or buy
    //states: 0=select  1=selected  2=buy
    int button2State;
    int button3State;
    int button4State;
    /**
     * Creates new form Store
     */
    public Store() {
        initComponents();
        initButtonStates();//set buttons to display correct text/state
        setSize(1296,1005);
        setLocation(300,20);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void close(){//closes this window
        this.setVisible(false);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        jLabel1.setText("Store");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(583, 42, 116, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/registerlogin/trojanSelectBox.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(385, 288, 230, 230);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/registerlogin/sonicSelectBox.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(677, 288, 230, 230);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/registerlogin/marioSelectBox.png"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(970, 288, 230, 230);

        jLabel5.setBackground(new java.awt.Color(255, 255, 204));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/registerlogin/millerSelectBox.png"))); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(92, 288, 230, 230);

        jButton1.setBackground(new java.awt.Color(179, 11, 11));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(250, 250, 250));
        jButton1.setText("Selected");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(90, 540, 230, 49);

        jButton2.setBackground(new java.awt.Color(179, 11, 11));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(250, 250, 250));
        jButton2.setText("Buy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(390, 540, 220, 49);

        jButton3.setBackground(new java.awt.Color(179, 11, 11));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(250, 250, 250));
        jButton3.setText("Buy");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(680, 540, 230, 49);

        jButton4.setBackground(new java.awt.Color(179, 11, 11));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(250, 250, 250));
        jButton4.setText("Buy");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(970, 540, 230, 49);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel6.setText("Coins: ");
        jLabel6.setText("Coins: "+StartUp.user.getCoin());
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 50, 340, 27);

        backBtn.setBackground(new java.awt.Color(179, 11, 11));
        backBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(250, 250, 250));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn);
        backBtn.setBounds(30, 880, 145, 57);

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Karalee\\Desktop\\CSCI 201\\201FinalProject\\RegisterLogin\\images\\gradient-white-yellow-linear-1920x1080-c2-ffffff-ffd700-a-90-f-14.png")); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 1300, 1010);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // this character is unlocked by default, so dont worry about state 2 or buying him
        if(button1State == 0){
            button1State = 1;//change it to selected
            StartUp.user.setCharSelectedID(1);//set selected character to miller
            //if any of the other button staes where on "selected" change them to unselect
            if(button2State == 1)
                button2State = 0;
            if(button3State == 1)
                button3State = 0;
            if(button4State == 1)
                button4State = 0;
        }
        //update text now
        updateButtonTexts();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // change state and text of button, if character is bought, update database
        if(button2State == 0){//select state, change to selected
            button2State = 1;//change it to selected
            StartUp.user.setCharSelectedID(2);//change character to be trojan
            //if any of the other button staes where on "selected" change them to unselect
            if(button1State == 1)
                button1State = 0;
            if(button3State == 1)
                button3State = 0;
            if(button4State == 1)
                button4State = 0;
        }
        else if(button2State == 2)//if user has enough coins, buy this player, update database for bought characters and coin amount for this user
        {
            int userCoins = StartUp.user.getCoin();
            int cost = StartUp.charactersList.get(1).getPrice();
            if(userCoins >= cost)//user has enough coins to buy this yay!
            {
                button2State = 0;//change it to now being able to be selected
                StartUp.user.setCoin(userCoins - cost);//update current object coin amount
                //update database now, and coin amount
                StartUp.user.buyCharacter(StartUp.charactersList.get(1));//buy character and update database to buy trojan character
            }    
        }
        updateButtonTexts();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // change state and text of button, if character is bought, update database
        if(button3State == 0){//select state, change to selected
            button3State = 1;//change it to selected
            StartUp.user.setCharSelectedID(3);//change character to be trojan
            //if any of the other button staes where on "selected" change them to unselect
            if(button1State == 1)
                button1State = 0;
            if(button2State == 1)
                button2State = 0;
            if(button4State == 1)
                button4State = 0;
        }
        else if(button3State == 2)//if user has enough coins, buy this player, update database for bought characters and coin amount for this user
        {
            int userCoins = StartUp.user.getCoin();
            int cost = StartUp.charactersList.get(2).getPrice();
            if(userCoins >= cost)//user has enough coins to buy this yay!
            {
                button3State = 0;//change it to now being able to be selected
                StartUp.user.setCoin(userCoins - cost);//update current object coin amount
                //update database now, and coin amount
                StartUp.user.buyCharacter(StartUp.charactersList.get(2));//buy character and update database to buy trojan character
            }    
        }
        updateButtonTexts();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // change state and text of button, if character is bought, update database
        if(button4State == 0){//select state, change to selected
            button4State = 1;//change it to selected
            StartUp.user.setCharSelectedID(4);//change character to be trojan
            //if any of the other button staes where on "selected" change them to unselect
            if(button1State == 1)
                button1State = 0;
            if(button2State == 1)
                button2State = 0;
            if(button3State == 1)
                button3State = 0;
        }
        else if(button4State == 2)//if user has enough coins, buy this player, update database for bought characters and coin amount for this user
        {
            int userCoins = StartUp.user.getCoin();
            int cost = StartUp.charactersList.get(3).getPrice();
            if(userCoins >= cost)//user has enough coins to buy this yay!
            {
                button4State = 0;//change it to now being able to be selected
                StartUp.user.setCoin(userCoins - cost);//update current object coin amount
                //update database now, and coin amount
                StartUp.user.buyCharacter(StartUp.charactersList.get(3));//buy character and update database to buy trojan character
            }    
        }
        updateButtonTexts();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // direct back to home screen
        Home homeObj = new Home();
        close();
    }//GEN-LAST:event_backBtnActionPerformed
    public void initButtonStates(){//initialize button states, using database info and user info
        int charSelected = StartUp.user.getCharSelectedID();
        ArrayList<Integer> charactersBought = StartUp.user.getCharactersBought();
        if(charSelected == 1)//miller is selected
        {
            button1State = 1;
            StartUp.user.setCharSelectedID(1);
            //set other button states now 2,3,4
            if(charactersBought.contains(2)){
                button2State = 0;//text on button will be select
            }else{
                button2State = 2;//must buy this character to select
            }
            if(charactersBought.contains(3)){
                button3State = 0;//text on button will be select
            }else{
                button3State = 2;//must buy this character to select
            }
            if(charactersBought.contains(4)){
                button4State = 0;//text on button will be select
            }else{
                button4State = 2;//must buy this character to select
            }
        }
        else if(charSelected == 2)//trojan selected
        {
            button2State = 1;
            StartUp.user.setCharSelectedID(2);
            System.out.println("trojan sleected");
            //set other button states now 1,3,4
            if(charactersBought.contains(1)){
                button1State = 0;//text on button will be select
            }
            if(charactersBought.contains(3)){
                button3State = 0;//text on button will be select
            }else{
                button3State = 2;//must buy this character to select
            }
            if(charactersBought.contains(4)){
                button4State = 0;//text on button will be select
            }else{
                button4State = 2;//must buy this character to select
            }
        }
        else if(charSelected == 3)//sonic selected
        {
            button3State = 1;
            StartUp.user.setCharSelectedID(3);
            System.out.println("SONIC SELECTED");
            //set other button states now 1,2,4
            if(charactersBought.contains(1)){
                button1State = 0;//text on button will be select
            }
            if(charactersBought.contains(2)){
                button2State = 0;//text on button will be select
            }else{
                button2State = 2;//must buy this character to select
            }
            if(charactersBought.contains(4)){
                button4State = 0;//text on button will be select
            }else{
                button4State = 2;//must buy this character to select
            }
        }
        else//mario selected
        {
            button4State = 1;
            StartUp.user.setCharSelectedID(4);
            System.out.println("MARIO SELECTED");
            //set other button states now 1,2,3
            if(charactersBought.contains(1)){
                button1State = 0;//text on button will be select
            }
            if(charactersBought.contains(2)){
                button2State = 0;//text on button will be select
            }else{
                button2State = 2;//must buy this character to select
            }
            if(charactersBought.contains(3)){
                button3State = 0;//text on button will be select
            }else{
                button3State = 2;//must buy this character to select
            }
        }
        updateButtonTexts();
    }
    public void updateButtonTexts(){//updates text on buttons accorsing to the states
        ArrayList<Character> allCharacters = StartUp.charactersList;//get all characters in database,THERE SHOULD BE 4!!
        //button 1
        if(button1State == 0)
            jButton1.setText("Select");
        else if(button1State == 1)
            jButton1.setText("Selected");
        else
            jButton1.setText("Buy: "+allCharacters.get(0).getPrice()+" coins");
        //button 2
        if(button2State == 0)
            jButton2.setText("Select");
        else if(button2State == 1)
            jButton2.setText("Selected");
        else
            jButton2.setText("Buy: "+allCharacters.get(1).getPrice()+" coins");
        //button 3 
        if(button3State == 0)
            jButton3.setText("Select");
        else if(button3State == 1)
            jButton3.setText("Selected");
        else
            jButton3.setText("Buy: "+allCharacters.get(2).getPrice()+" coins");
        //button 4
        if(button4State == 0)
            jButton4.setText("Select");
        else if(button4State == 1)
            jButton4.setText("Selected");
        else
            jButton4.setText("Buy: "+allCharacters.get(3).getPrice()+" coins");
        //also update the coins value in the top left corner!
        jLabel6.setText("Coins: "+StartUp.user.getCoin());
    }
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
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Store().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
