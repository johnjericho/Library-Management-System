package view.UserModule.StudentTab;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.UserModule.StudentTab.SectionController;
import utility.AppContext;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class SectionEditForm extends JDialog {

	private SectionController sectionController = AppContext.getInstance().getSectionController();
	private int gradeId = -1;
	private int sectionId = -1;           // BAGO: kailangan para malaman kung alin row sa DB ang iu-update
	private boolean isUpdateMode = false; // BAGO: flag para malaman ng saveSection() kung Add o Update mode
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JTextField txtSection;
	private JButton btnSave;


	/**
	 * @wbp.parser.constructor
	 */
	public SectionEditForm(int gradeId, int sectionId, String sectionName) {
		this.gradeId = gradeId;       // FIX: dati hindi naka-assign, laging -1
		this.sectionId = sectionId;   // BAGO: i-store para magamit sa updateSection() query
		this.isUpdateMode = true;     // BAGO: markahan "update mode" tayo

		initialize();

		txtSection.setText(sectionName); // BAGO: i-preload current name sa textfield, di simula sa blangko
		btnSave.setText("UPDATE");       // OPTIONAL: klaro sa user na update mode siya, hindi add
	}

	// ADD MODE constructor — dito dumadaan pag pinindot ADD button
	public SectionEditForm(int gradeId) {
		this.gradeId = gradeId;
		this.isUpdateMode = false; // explicit lang, para klaro na add mode
		System.out.println(gradeId);

		initialize();
	}

	public void initialize() {
		setPanel();
		initAction();
	}

	public void setPanel() {
		this.setBounds(100, 100, 292, 164);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 276, 133);
		getContentPane().add(contentPanel);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		btnSave = new JButton("SAVE");
		btnSave.setBounds(20, 94, 81, 23);
		contentPanel.add(btnSave);
		btnSave.setForeground(new Color(51, 102, 51));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 13));
 

		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(0, 128, 0), 2));
		componentsBorder.setBackground(Color.LIGHT_GRAY);
		componentsBorder.setBounds(10, 31, 256, 76);
		contentPanel.add(componentsBorder);

		txtSection = new JTextField();
		txtSection.setColumns(10);
		txtSection.setBounds(10, 27, 234, 23);
		componentsBorder.add(txtSection);

		JLabel lblSection = new JLabel("Section");
		lblSection.setBounds(10, 0, 71, 28);
		componentsBorder.add(lblSection);
		lblSection.setForeground(new Color(0, 128, 0));
		lblSection.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 121, 256, 10);
		contentPanel.add(separator_1);
		separator_1.setBackground(new Color(51, 204, 51));


	}

	public void initAction() {
		btnSave.addActionListener(e -> {
			saveSection(); // RENAME: dating addSection(), pinalitan para di misleading — hindi na laging "add"
		});
	}

	// RENAME + BRANCH LOGIC: dating addSection(), ngayon may if-else para pumili Add o Update path
	public void saveSection() {
		String sectionName = txtSection.getText();

		try {
			if (isUpdateMode) {
				// 1. UPDATE path — tatawagin lang kung galing sa Update button (may sectionId na)
				sectionController.updateSection(sectionId, sectionName, gradeId);
				JOptionPane.showMessageDialog(this, "Successfully updated");
			} else {
				// 2. ADD path — dating logic, walang binago dito
				sectionController.addSection(sectionName, gradeId);
				JOptionPane.showMessageDialog(this, "Successfully added");
			}

			clearFields(); // 3. i-reset fields
			dispose();     // 4. BAGO: isara agad yung dialog pagkatapos mag-save,
			               //    para bumalik sa SectionPickerDialog na naka-refresh na

		} catch (Exception e) {
			// 5. kung may validation error (duplicate name, empty, etc.) — di isasara yung dialog,
			//    para makapag-ayos pa yung user ng input niya
			JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}


	private void clearFields() {
		txtSection.setText("");
		gradeId = -1;
		sectionId = -1; // BAGO: i-reset din, para di ma-carry-over sa susunod na paggamit
		System.out.println(gradeId);
	}
}