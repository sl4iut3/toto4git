package toto;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class DialogDemo extends JPanel implements ActionListener {

	public final int NB_JOUEURS_MAX = 4;

	private final String joueurPhysiqueCommand = "Physique";
	private final String joueurVirtuelCommand = "Virtuel";

	private static JFrame frame = new JFrame("Jest");

	private String[] modes = { "Mode 3 Joueurs", "Mode 4 Joueurs" };
	private JComboBox<String> combo = new JComboBox<String>(modes);

	private JRadioButton[][] typeJoueurButtons = new JRadioButton[NB_JOUEURS_MAX][2];
	private ButtonGroup[] typeJoueurGroup = new ButtonGroup[NB_JOUEURS_MAX];
	private JRadioButton[][] strategieButtons = new JRadioButton[NB_JOUEURS_MAX][2];
	private ButtonGroup[] strategieGroup = new ButtonGroup[NB_JOUEURS_MAX];
	private JTextField[] nomTextFields = new JTextField[NB_JOUEURS_MAX];

	private JButton button = new JButton("Démarrer la partie");

	private JPanel[] panels = new JPanel[NB_JOUEURS_MAX];
	private JTabbedPane tabbedPane = new JTabbedPane();
	private Font fontDefault = new Font("Arial", Font.BOLD, 24);

	private int nbJoueurs = 3;

	/** Creates the GUI shown inside the frame's content pane. */
	public DialogDemo(/* JFrame frame */) {
		super(new BorderLayout());

		tabbedPane.setFont(fontDefault);
		tabbedPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//		tabbedPane.setAlignmentX(CENTER_ALIGNMENT);

		// Create the components.
		for (int i = 0; i < nbJoueurs; i++) {
			this.panels[i] = createSimpleDialogBox(i);
			String nom;
			if (i == 0)
				nom = "Joueur Physique";
			else
				nom = "Bot " + i;
			tabbedPane.addTab(nom, null, this.panels[i], null);
		}

		combo.setFont(fontDefault);
		combo.addActionListener(this);
//		combo.setHorizontalAlignment(JTextField.CENTER);

		button.setFont(fontDefault);
		button.addActionListener(this);

		add(combo, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}

	private JPanel createSimpleDialogBox(int numJoueur) {
		this.typeJoueurGroup[numJoueur] = new ButtonGroup();
		typeJoueurButtons[numJoueur][0] = new JRadioButton("Physique");
		typeJoueurButtons[numJoueur][0].setActionCommand(this.joueurPhysiqueCommand);
		typeJoueurButtons[numJoueur][0].setFont(fontDefault);

		typeJoueurButtons[numJoueur][1] = new JRadioButton("Virtuel");
		typeJoueurButtons[numJoueur][1].setActionCommand(this.joueurVirtuelCommand);
		typeJoueurButtons[numJoueur][1].setFont(fontDefault);

		for (int i = 0; i < 2; i++) {
			typeJoueurButtons[numJoueur][i].addActionListener(this);
			this.typeJoueurGroup[numJoueur].add(typeJoueurButtons[numJoueur][i]);
		}
		if (numJoueur == 0)
			typeJoueurButtons[numJoueur][0].setSelected(true);
		else
			typeJoueurButtons[numJoueur][1].setSelected(true);

		JPanel haut = createPane("Joueur :", typeJoueurButtons[numJoueur]);
		haut.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		this.strategieGroup[numJoueur] = new ButtonGroup();
		strategieButtons[numJoueur][0] = new JRadioButton("Strategie A");
		strategieButtons[numJoueur][0].setActionCommand(this.joueurPhysiqueCommand);
		strategieButtons[numJoueur][0].setFont(fontDefault);
		if (numJoueur == 0)
			strategieButtons[numJoueur][0].setEnabled(false);

		strategieButtons[numJoueur][1] = new JRadioButton("Strategie B");
		strategieButtons[numJoueur][1].setActionCommand(this.joueurVirtuelCommand);
		strategieButtons[numJoueur][1].setFont(fontDefault);
		if (numJoueur == 0)
			strategieButtons[numJoueur][1].setEnabled(false);

		for (int i = 0; i < 2; i++) {
			this.strategieGroup[numJoueur].add(strategieButtons[numJoueur][i]);
		}
		strategieButtons[numJoueur][0].setSelected(true);

		JPanel bas = createPane("Strategie :", strategieButtons[numJoueur]);
		bas.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		// panel texte: nom du JOueur
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
		textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		this.nomTextFields[numJoueur] = new JTextField("J" + (numJoueur + 1));
		this.nomTextFields[numJoueur].setFont(fontDefault);
		this.nomTextFields[numJoueur].setHorizontalAlignment(JTextField.CENTER);
//		this.nomTextFields[numJoueur].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.nomTextFields[numJoueur].addActionListener(this);
		JLabel label = new JLabel("Nom du Joueur : ");
		label.setFont(fontDefault);
		textPanel.add(label);
		textPanel.add(this.nomTextFields[numJoueur]);

		// panel complet
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(textPanel, BorderLayout.NORTH);
		panel.add(haut, BorderLayout.CENTER);
		panel.add(bas, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Used by createSimpleDialogBox and createFeatureDialogBox to create a pane
	 * containing a description, a single column of radio buttons, and the Show it!
	 * button.
	 */
	private JPanel createPane(String description, JRadioButton[] radioButtons) {

		int numChoices = radioButtons.length;
		JPanel box = new JPanel();
		JLabel label = new JLabel(description);
		label.setFont(fontDefault);

		box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
		box.add(label);

		for (int i = 0; i < numChoices; i++) {
			box.add(radioButtons[i]);
		}

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.PAGE_START);
		return pane;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 600, 400);

		// Create and set up the content pane.
		DialogDemo newContentPane = new DialogDemo();
//		newContentPane.setBounds(10, 10, 500, 200);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);
		// Display the window.
//		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		for (int i = 0; i < nomTextFields.length; i++) {
			if (e.getSource() == this.nomTextFields[i])
				this.tabbedPane.setTitleAt(i, this.nomTextFields[i].getText());
		}
		for (int i = 0; i < typeJoueurButtons.length; i++) {
			for (int j = 0; j < typeJoueurButtons[i].length; j++) {
				if (e.getSource() == typeJoueurButtons[i][j])
					if (j == 0)
						for (int j2 = 0; j2 < strategieButtons[i].length; j2++) {
							this.strategieButtons[i][j2].setEnabled(false);
						}
					else
						for (int j2 = 0; j2 < strategieButtons[i].length; j2++) {
							this.strategieButtons[i][j2].setEnabled(true);
						}
				System.out.println("typeJoueur " + i + ", " + j);
			}
		}
		if (e.getSource() == combo) {
			if (combo.getSelectedIndex() == 0) {
				if (nbJoueurs == 4) {
					this.nbJoueurs = 3;
					tabbedPane.removeTabAt(3);
				}
			} else {
				if (nbJoueurs == 3) {
					this.nbJoueurs = 4;
					this.panels[3] = createSimpleDialogBox(3);
					tabbedPane.addTab("Bot 3", null, this.panels[3], null);
				}
			}
		}
		if (e.getSource() == button) {
			if (!verifierValiditeNomJoueurs())
				return;
			for (int i = 0; i < nbJoueurs; i++) {

			}
		}
	}

	private boolean verifierValiditeNomJoueurs() {
		HashSet<String> noms = new HashSet<String>();
		for (int i = 0; i < nbJoueurs; i++) {
			if (nomTextFields[i].getText().compareTo("") == 0) {
				JOptionPane.showMessageDialog(frame, "Le joueur " + i + " n'a pas de nom !");
				return false;
			}
			noms.add(nomTextFields[i].getText());
		}
		if (noms.size() < nbJoueurs) {
			JOptionPane.showMessageDialog(frame, "Deux joueurs ont le même nom !");
			return false;
		}
		return true;
	}

}
