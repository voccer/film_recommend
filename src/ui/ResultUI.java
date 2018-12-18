package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.FilmDetail;
import model.TOPSIS;

public class ResultUI extends JFrame {

	JTable tblFilms;
	DefaultTableModel dm;
	private static ArrayList<FilmDetail> listFilm;
	JTextField txtName, txtLink, txtStar, txtNumberCmt, txtNumberNag, txtNumberPos, txtEvaluation;

	JButton Enter;
	JTextArea txtAreaDescription;
	String SAMPLE_IMAGE_FILE_PATH = System.getProperty("user.dir") + "/Data/image/";
	private static String nameImage = "Aquaman: De Vuong Atlantis (2018).jpg";
	BufferedImage image;
	ImageIcon icon;
	JPanel pnImage;
	JLabel lblImage;
	JPanel pnRight;
	private static int row;

	public ResultUI(String title) {
		super(title);
		addControls();
		addEvents();
		addFakeData();
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnLeft = new JPanel();
		pnRight = new JPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
		con.add(sp);

		// Left
		pnLeft.setLayout(new BorderLayout());
		pnLeft.setPreferredSize(new Dimension(500, 700));

		dm = new DefaultTableModel();
		dm.addColumn("Name");
		dm.addColumn("Date");
		dm.addColumn("Score");
		tblFilms = new JTable(dm);
		tblFilms.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

		JScrollPane sc = new JScrollPane(tblFilms, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		pnLeft.add(sc, BorderLayout.CENTER);

		// Right
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
		// Image
		pnImage = new JPanel();
		pnImage.setLayout(new GridBagLayout());
		pnRight.add(pnImage);

		lblImage = new JLabel();
		lblImage.setSize(new Dimension(122, 190));
		try {
			BufferedImage image = ImageIO.read(new File(SAMPLE_IMAGE_FILE_PATH + nameImage));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(122, 170, image.SCALE_SMOOTH));
			lblImage.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pnImage.add(lblImage);

		JPanel pnRow1 = new JPanel();
		pnRow1.setLayout(new GridBagLayout());
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.BOLD, 15));
		txtName = new JTextField(40);
		txtName.setEditable(false);
		pnRow1.add(lblName);
		pnRow1.add(txtName);
		pnRight.add(pnRow1);

		JPanel pnRow2 = new JPanel();
		pnRow2.setLayout(new GridBagLayout());
		JLabel lblLink = new JLabel("Link:");
		lblLink.setFont(new Font("Arial", Font.BOLD, 15));
		txtLink = new JTextField(40);
		txtLink.setEditable(false);
		pnRow2.add(lblLink);
		pnRow2.add(txtLink);
		pnRight.add(pnRow2);

		JPanel pnRow3 = new JPanel();
		pnRow3.setLayout(new GridBagLayout());
		JLabel lblDate = new JLabel("Star:");
		lblDate.setFont(new Font("Arial", Font.BOLD, 15));
		txtStar = new JTextField(40);
		txtStar.setEditable(false);
		pnRow3.add(lblDate);
		pnRow3.add(txtStar);
		pnRight.add(pnRow3);

		JPanel pnRow4 = new JPanel();
//		pnRow4.setLayout(new BorderLayout());
		pnRow4.setLayout(new GridBagLayout());
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Arial", Font.BOLD, 15));
		txtAreaDescription = new JTextArea(5, 40);
		txtAreaDescription.setLineWrap(true);
		txtAreaDescription.setWrapStyleWord(true);
		txtAreaDescription.setEditable(false);
		JScrollPane scDes = new JScrollPane(txtAreaDescription, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnRow4.add(lblDescription);
		pnRow4.add(scDes);
		pnRight.add(pnRow4);

		JPanel pnRow5 = new JPanel();
		pnRow5.setLayout(new FlowLayout());
		JLabel lblNumberCmt = new JLabel("Total Comment:");
		lblNumberCmt.setFont(new Font("Arial", Font.BOLD, 15));
		txtNumberCmt = new JTextField(7);
		txtNumberCmt.setEditable(false);

		JLabel lblNumberNag = new JLabel("Negative:");
		lblNumberNag.setFont(new Font("Arial", Font.BOLD, 15));
		txtNumberNag = new JTextField(7);
		txtNumberNag.setEditable(false);

		JLabel lblNumberPos = new JLabel("Positive:");
		lblNumberPos.setFont(new Font("Arial", Font.BOLD, 15));
		txtNumberPos = new JTextField(7);
		txtNumberPos.setEditable(false);
		pnRow5.add(lblNumberCmt);
		pnRow5.add(txtNumberCmt);
		pnRow5.add(lblNumberPos);
		pnRow5.add(txtNumberPos);
		pnRow5.add(lblNumberNag);
		pnRow5.add(txtNumberNag);
		pnRight.add(pnRow5);

		JPanel pnRow6 = new JPanel();
		pnRow6.setLayout(new GridBagLayout());
		JLabel lblScoreEvaluation = new JLabel("Evaluation of user: ");
		lblScoreEvaluation.setFont(new Font("Arial", Font.BOLD, 15));
		txtEvaluation = new JTextField(5);
		txtEvaluation.setEditable(false);
		pnRow6.add(lblScoreEvaluation);
		pnRow6.add(txtEvaluation);
		Enter = new JButton("Save");
		pnRow6.add(Enter);
		pnRight.add(pnRow6);

		// Align
		lblName.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblLink.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblDate.setPreferredSize(lblNumberCmt.getPreferredSize());
//		lblNumberCmt.setPreferredSize(lblNumberCmt.getPreferredSize());
//		lblNumberNag.setPreferredSize(lblNumberCmt.getPreferredSize());
//		lblNumberPos.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblDescription.setPreferredSize(lblNumberCmt.getPreferredSize());
		// lblScoreEvaluation.setPreferredSize(lblNumberCmt.getPreferredSize());
	}

	public void addEvents() {

		Enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handingEntered();
			}
		});

		tblFilms.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				handingMouseClicked();
			}
		});
	}

	protected void handingEntered() {
		String evaluation = txtEvaluation.getText();
		if (evaluation.equals("")) {
			JOptionPane.showMessageDialog(null, "Nhập số điểm bạn đánh giá (thang điểm 100), hãy nhập lại!",
					"Thông báo", JOptionPane.WARNING_MESSAGE);
		} else if (!isNumber(evaluation)) {
			JOptionPane.showMessageDialog(null, "Nhập số điểm bạn đánh giá (thang điểm 100), hãy nhập lại!",
					"Thông báo", JOptionPane.WARNING_MESSAGE);
		} else {
			int scoreUser = Integer.parseInt(evaluation);
			if (scoreUser < 0 || scoreUser > 100) {
				JOptionPane.showMessageDialog(null, "Nhập số điểm bạn đánh giá (thang điểm 100), hãy nhập lại!",
						"Thông báo", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Cảm ơn bạn đã đánh giá!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
				writeFile(listFilm.get(row).getScore_rank(), scoreUser,
						System.getProperty("user.dir") + "/Data/evaluation.txt");
			}
		}
	}

	// Ghi file
	private void writeFile(Float Score, int Evaluation, String path) {
		try {
			String score = Score.toString();
			String evaluation = String.valueOf(Evaluation);
			File f = new File(path);
			FileWriter fw = new FileWriter(f, true);
			fw.write(score + " " + evaluation + "\n");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// kiểm tra xem String có chuyển được sang Int không?
	private boolean isNumber(String string) {
		try {
			int number = Integer.parseInt(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	protected void handingMouseClicked() {
		row = tblFilms.getSelectedRow();
		if (row == -1) {
			return;
		}
		txtLink.setText(listFilm.get(row).getLink());
		txtName.setText(listFilm.get(row).getName());
		txtStar.setText(String.valueOf(listFilm.get(row).getScore_star()));
		txtAreaDescription.setText(listFilm.get(row).getDescription());
		txtNumberCmt.setText(String.valueOf(listFilm.get(row).getTotalcomment()));
		txtNumberNag
				.setText(String.valueOf(listFilm.get(row).getTotalcomment() - listFilm.get(row).getPositivecomment()));
		txtNumberPos.setText(String.valueOf(listFilm.get(row).getPositivecomment()));

		// add image
		try {
			BufferedImage image = ImageIO.read(new File(SAMPLE_IMAGE_FILE_PATH + listFilm.get(row).getName() + ".jpg"));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(122, 170, image.SCALE_SMOOTH));
			lblImage.setIcon(icon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pnImage.add(lblImage);

		JPanel pnRow6 = new JPanel();
		pnRow6.setLayout(new GridBagLayout());
		JLabel lblScoreEvaluation = new JLabel("Evaluation of user: ");
		lblScoreEvaluation.setFont(new Font("Arial", Font.BOLD, 15));
		// Bật edit
		txtEvaluation.setEditable(true);
		pnRight.add(pnRow6);
	}

	private void addFakeData() {
		listFilm = new ArrayList<>();
		String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir") + "/Data/file.csv";
		TOPSIS topsis = new TOPSIS();
		listFilm = topsis.ListFilmRanked(SAMPLE_CSV_FILE_PATH);
		
		for (FilmDetail film : listFilm) {
			Vector vec = new Vector<>();
			vec.add(film.getName());
			vec.add(film.getYear());
			vec.add(film.getScore_rank());
			dm.addRow(vec);
		}
		
		
	}

	public void showWindow() {
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//deleteCSV();
	}
	
	public void deleteCSV() {
		String dirPath = System.getProperty("user.dir");
		File csv = new File(dirPath + "/Data/file.csv");
		csv.delete();
	}
}
