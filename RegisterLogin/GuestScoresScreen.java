/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;

import java.util.ArrayList;

import com.mygdx.game.MyGame;
import com.mygdx.game.desktop.DesktopLauncher;

/**
 *
 * @author Karalee
 */
public class GuestScoresScreen extends javax.swing.JFrame {

    /**
     * Creates new form ScoresScreen
     */
    public GuestScoresScreen() {
        initComponents();
        setSize(1296,1005);
        setLocation(300,20);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //set table column widths below
        scoresTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        scoresTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        scoresTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        scoresTable.setRowHeight(40);
        //make table not editable
        scoresTable.setEnabled(false);
        scoresTable.getTableHeader().setBackground(new java.awt.Color(179, 11, 11));
        scoresTable.getTableHeader().setForeground(new java.awt.Color(250, 250, 250));
        scoresTable.setBackground(new java.awt.Color(250, 250, 250));
        addScoresToTable();//update table to display the top 15 high scores
    }
    
    public void close(){
        this.setVisible(false);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scoresTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        backBtn.setBackground(new java.awt.Color(179, 11, 11));
        backBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(250, 250, 250));
        if(StartUp.user == null)//playing as guest
        {
        	backBtn.setText("Play Again");
        }
        else
        {
        	backBtn.setText("Back");
        }
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn);
        backBtn.setBounds(40, 870, 145, 57);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        jLabel1.setText("Top Scores");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(538, 27, 290, 58);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Your Score: 100 pts");
        //jLabel2.setText(StartUp.user.getUname()+"'s High Score: "+ Integer.toString(StartUp.user.getHighScore())+" pts");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(310, 170, 680, 29);

        scoresTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scoresTable.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        scoresTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rank", "Username", "Score"
            }
        ));
        scoresTable.setSelectionBackground(new java.awt.Color(179, 11, 11));
        jScrollPane1.setViewportView(scoresTable);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(316, 241, 655, 508);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/registerlogin/gradient-white-yellow-linear-1920x1080-c2-ffffff-ffd700-a-90-f-14.png")));
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 1300, 1010);

        pack();
    }// </editor-fold>                        

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // go back to home frame
    	if(StartUp.user == null)
    	{
    		StartUp.isGuest = true;
        	System.out.println("Playing as guest: "+StartUp.isGuest);
        	close();
    	}
    	else//not [laying as guest
    	{
    		StartUp.isGuest = false;
        	System.out.println("not Playing as guest: "+StartUp.isGuest);
        	Home homescreen  = new Home();
        	close();
    	}
    	
    }                                       
    private void addScoresToTable()
    {
        ArrayList<User> topScores = StartUp.userDriver.getTopFifteenScores();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel)scoresTable.getModel();
        for(int i = 0; i < topScores.size(); i++)
        {
            try
            {
                model.addRow(new Object[]{i+1, topScores.get(i).getUname(), topScores.get(i).getHighScore() });
            }
            catch(NullPointerException e)
            {
                System.out.println("MODEL IS NULL???");
            }
        }
    }
    public void setMyScore(int score)
    {
    	jLabel2.setText("Your Score: "+Integer.toString(score)+" pts");
    	//update data base score if logged in
    	if(StartUp.user != null)
    	{
    		if(StartUp.user.getHighScore() < score)
    		{
    			StartUp.user.setHighScore(score);
    		}
    		addScoresToTable();//update scores table
    	}
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
            java.util.logging.Logger.getLogger(GuestScoresScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuestScoresScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuestScoresScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuestScoresScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuestScoresScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable scoresTable;
    // End of variables declaration                   
}