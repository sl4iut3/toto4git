package toto;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fen extends JFrame implements ActionListener {
 
	private JButton b = new JButton("toto");
	private JPanel p = new JPanel();
	private JTextField jtf=new JTextField("8");
	 
	public Fen() {
		Container cc;
		c = this.getContentPane();
		c.add(b,BorderLayout.NORTH);
		c.add(jtf,BorderLayout.CENTER);
		c.add(p, BorderLayout.SOUTH);
		p.add(new JButton());
		//p.setPreferredSize(getMaximumSize());
		b.addActionListener(this);
		setBounds(10,10,200,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated meth
		Fen f=new Fen();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Fen f=new Fen();
		int nb=Integer.parseInt(jtf.getText());
		System.out.println("nb="+nb);
		JOptionPane.showMessageDialog(this, "create "+nb+" JButtons ?");
		c.remove(p);
		p=new JPanel(new GridLayout(3, 3)) ;
		for (int i=0;i<nb;i++) {
			p.add(new JButton("B"+i));
		}
		c.add(p,BorderLayout.SOUTH);
		revalidate();
		//repaint();
		//update(getGraphics());

	}

}
