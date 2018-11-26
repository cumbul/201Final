package registerlogin;

import java.io.IOException;

/**
 *
 * @author Karalee
 */
public class CoinRequestRejected extends javax.swing.JFrame {

	int coinAmount;//# coins being requested
	String username;//username of person requesting
	String userToRequestCoins;//our username
    /**
     * Creates new form CoinRequest
     */
    public CoinRequestRejected() {
        initComponents();
        setSize(630, 400);
        setLocation(300,20);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void setParameters(int coinAmount_, String username_, String userToRequestCoins_)
    {
    	coinAmount = coinAmount_;
        username = username_;
        userToRequestCoins = userToRequestCoins_;
        setMessage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">    
    private void setMessage()
    {
    	jLabel1.setText(userToRequestCoins+" has rejected your coin request of "+coinAmount+" coins");
    }
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Username has requested n coins");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 72, 410, 58);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/registerlogin/gradient-white-yellow-linear-1920x1080-c2-ffffff-ffd700-a-90-f-14.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 680, 400);

        pack();
    }// </editor-fold>                        

    public void close(){
        this.setVisible(false);
        this.dispose();
    }
    private void RejectBtnActionPerformed(java.awt.event.ActionEvent evt) {                                   
        // reject coin request
    	Message mToSend = new Message(3,userToRequestCoins);//make reject message
    	mToSend.userToRequestCoins = username;//this is user that requested's username
    	try
		{
        	StartUp.startUp.oos.writeObject(mToSend);
        	StartUp.startUp.oos.flush(); 
        	close();
		}
		catch(IOException ioe)
		{
			System.out.println("ioe: "+ioe.getMessage());
			close();
		}
    }                                         

    private void acceptBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // accept coin request
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
            java.util.logging.Logger.getLogger(CoinRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CoinRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CoinRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CoinRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoinRequestRejected().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify   
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration                   
}

