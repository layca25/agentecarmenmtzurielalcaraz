package gui;
//Para tener los datos del BookBuyerAgent
import agents.BookBuyerAgent;
//importaciones necesarias para la interfaz gráfica
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class BookBuyerGui extends JFrame 
{
    private BookBuyerAgent myAgent;	
    private JTextField titleField, priceField;
    private JLabel book;	
    public BookBuyerGui(BookBuyerAgent a) 
    {       
            super(a.getLocalName());
            myAgent = a;
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(2, 1)); //2,2
            book = new JLabel("Titulo del Libro: "); 
            book.setFont(new Font("Serif", Font.ROMAN_BASELINE, 17));
            book.getColorModel();
            book.setHorizontalAlignment(SwingConstants.CENTER);
            p.add(book);
            titleField = new JTextField(27);
            p.add(titleField);
            getContentPane().add(p, BorderLayout.CENTER);
            JButton addButton = new JButton("Comprar");
            addButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                            try {
                                    String title = titleField.getText().trim();//Manda el nombre del título 
                                    myAgent.setBookTitle(title);
                                    titleField.setText("");
                                    myAgent.proceso();
                            }catch(Exception e) {
                                    JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values","Error",JOptionPane.ERROR_MESSAGE);
                            }
                    }
            });

            p = new JPanel();
            p.add(addButton);
            getContentPane().add(p, BorderLayout.SOUTH);
            addWindowListener(new WindowAdapter() {
              public void windowClosing(WindowEvent e) {
                myAgent.doDelete();                
              }
            });

            setResizable(false);
    }

    public void informacion(String confirmation)
    {
        JOptionPane.showMessageDialog(null, confirmation);
    }
    
    public void noDis(String no)
    {
        JOptionPane.showMessageDialog(null, no);
    }
    
    public void showGui() 
    {
      pack();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int centerX = (int)screenSize.getWidth() / 2;
      int centerY = (int)screenSize.getHeight() / 2;
      //Locación de la ventanita
      setSize(280,170);
      setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
      super.setVisible(true);
    }
}
