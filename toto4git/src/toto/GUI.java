package toto;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {

	public GUI() {
		// Create and set up the window.
//		frame.setBounds(10, 10, 600, 400);

		// Create and set up the content pane.
//		newContentPane.setBounds(10, 10, 500, 200);
//		newContentPane.setOpaque(true); // content panes must be opaque
//		frame.setContentPane(newContentPane);
		// Display the window.
		Container c = this.getContentPane();
		c.setLayout(new CardLayout());

		DialogDemo newContentPane = new DialogDemo();

		JPanel toto = new JPanel();
		JButton jButton = new JButton("toto");
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout card = (CardLayout) c.getLayout();
				card.show(c, "dialog");				
			}
		});
		
		toto.add(jButton, BorderLayout.CENTER);

		c.add(newContentPane, "dialog");
		c.add(toto, "toto");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

		CardLayout card = (CardLayout) c.getLayout();
		card.show(c, "toto");
		
//		this.revalidate();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		card.show(c, "dialog");
//		c.add(newContentPane);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}
}
