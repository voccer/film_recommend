package ui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class SearchUI extends JFrame {
	JTextField txtNumberOfFilms;
	JButton btnSearch;

	// Check box Title Type
	Checkbox ckbFeatureFilm, ckbTVMovie, ckbTVSeries, ckbTVEpisode, ckbTVSpecial, ckbMiniSeries, ckbDocumentary,
			ckbVideoGame, ckbShortFilm, ckbVideo, ckbTVShort;

	// Check box Genres
	Checkbox ckbAction, ckbAdventure, ckbAnimation, ckbBiography, ckbComedy, ckbCrime, ckbDrama, ckbFamily, ckbFantasy,
			ckbGameShow, ckbHistory, ckbHorror, ckbMusic, ckbMusical, ckbMystery, ckbNews, ckbRealityTV, ckbRomance,
			ckbSport, ckbTalkShow, ckbThriller, ckbWar, ckbWestern;

	// Check box Companies
	Checkbox ckb20thCenturyFox, ckbSony, ckbDreamWork, ckbMGM, ckbParamount, ckbUniversal, ckbWaltDisney, ckbWarnerBros;

	// Date
	JTextField txtDateFrom, txtDateTo;

	public SearchUI(String title) {
		super(title);
		addControls();
		addEvents();
	}

	public void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		/* Top */
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new GridLayout(2, 2));
		con.add(pnTop, BorderLayout.CENTER);

		// Create Border Top
		Border boderTop = BorderFactory.createEtchedBorder(Color.BLUE, Color.RED);
		TitledBorder titleBorderBottom = new TitledBorder(boderTop, "Lựa chọn các tiêu chí của phim");
		titleBorderBottom.setTitleFont(new Font("Arial", Font.BOLD, 20));
		titleBorderBottom.setTitleJustification(TitledBorder.CENTER);
		titleBorderBottom.setTitleColor(Color.BLUE);
		pnTop.setBorder(titleBorderBottom);

		// Create panel TopLeft
		JPanel pnTopLeft = new JPanel();
		pnTopLeft.setLayout(new GridLayout(3, 4));
		pnTop.add(pnTopLeft);

		Border borderTopLeft = BorderFactory.createLineBorder(Color.GRAY);
		TitledBorder titleBorderTopLeft = new TitledBorder(borderTopLeft, "Title Type");
		titleBorderTopLeft.setTitleFont(new Font("Arial", Font.BOLD, 15));
		titleBorderTopLeft.setTitleJustification(TitledBorder.CENTER);
		titleBorderTopLeft.setTitleColor(Color.BLUE);
		pnTopLeft.setBorder(titleBorderTopLeft);

		ckbFeatureFilm = new Checkbox("Feature Film");
		ckbTVMovie = new Checkbox("TV Movie");
		ckbTVSeries = new Checkbox("TV Series");
		ckbTVEpisode = new Checkbox("TV Episode");
		ckbTVSpecial = new Checkbox("TV Special");
		ckbMiniSeries = new Checkbox("Mini-Series");
		ckbDocumentary = new Checkbox("Documentary");
		ckbVideoGame = new Checkbox("Video Game");
		ckbShortFilm = new Checkbox("Short Film");
		ckbVideo = new Checkbox("Video");
		ckbTVShort = new Checkbox("TV Short");

		pnTopLeft.add(ckbFeatureFilm);
		pnTopLeft.add(ckbTVMovie);
		pnTopLeft.add(ckbTVSeries);
		pnTopLeft.add(ckbTVEpisode);
		pnTopLeft.add(ckbTVSpecial);
		pnTopLeft.add(ckbMiniSeries);
		pnTopLeft.add(ckbDocumentary);
		pnTopLeft.add(ckbVideoGame);
		pnTopLeft.add(ckbShortFilm);
		pnTopLeft.add(ckbVideo);
		pnTopLeft.add(ckbTVShort);

		// Create panel BottomTopLeft
		JPanel pnBottomTopLeft = new JPanel();
		pnBottomTopLeft.setLayout(new BoxLayout(pnBottomTopLeft, BoxLayout.Y_AXIS));
		pnTop.add(pnBottomTopLeft);

		Border borderBottomTopLeft = BorderFactory.createLineBorder(Color.GRAY);
		TitledBorder titleBorderBottomTopLeft = new TitledBorder(borderBottomTopLeft, "Release Date");
		titleBorderBottomTopLeft.setTitleFont(new Font("Arial", Font.BOLD, 15));
		titleBorderBottomTopLeft.setTitleJustification(TitledBorder.CENTER);
		titleBorderBottomTopLeft.setTitleColor(Color.BLUE);
		pnBottomTopLeft.setBorder(titleBorderBottomTopLeft);

		JPanel pnTopReleaseDate = new JPanel();
		pnTopReleaseDate.setLayout(new GridBagLayout());
		JLabel lblDateFrom = new JLabel("Date From (yyyy-mm-dd): ");
		txtDateFrom = new JTextField(20);
		pnTopReleaseDate.add(lblDateFrom);
		pnTopReleaseDate.add(txtDateFrom);
		pnBottomTopLeft.add(pnTopReleaseDate);

		JPanel pnBottomReleaseDate = new JPanel();
		pnBottomReleaseDate.setLayout(new GridBagLayout());
		JLabel lblDateTo = new JLabel("To (yyyy-mm-dd): ");
		txtDateTo = new JTextField(20);
		pnBottomReleaseDate.add(lblDateTo);
		pnBottomReleaseDate.add(txtDateTo);
		pnBottomTopLeft.add(pnBottomReleaseDate);

		lblDateTo.setPreferredSize(lblDateFrom.getPreferredSize());

		// Create panel TopRight
		JPanel pnTopRight = new JPanel();
		pnTopRight.setLayout(new GridLayout(5, 5));
		pnTop.add(pnTopRight);

		Border borderTopRight = BorderFactory.createLineBorder(Color.GRAY);
		TitledBorder titleBorderTopRight = new TitledBorder(borderTopRight, "Genres");
		titleBorderTopRight.setTitleFont(new Font("Arial", Font.BOLD, 15));
		titleBorderTopRight.setTitleJustification(TitledBorder.CENTER);
		titleBorderTopRight.setTitleColor(Color.BLUE);
		pnTopRight.setBorder(titleBorderTopRight);

		ckbAction = new Checkbox("Action");
		ckbAdventure = new Checkbox("Adventure");
		ckbAnimation = new Checkbox("Animation");
		ckbBiography = new Checkbox("Biography");
		ckbComedy = new Checkbox("Comedy");
		ckbCrime = new Checkbox("Crime");
		ckbDrama = new Checkbox("Drama");
		ckbFamily = new Checkbox("Family");
		ckbFantasy = new Checkbox("Fantasy");
		ckbGameShow = new Checkbox("Game-Show");
		ckbHistory = new Checkbox("History");
		ckbHorror = new Checkbox("Horror");
		ckbMusic = new Checkbox("Music");
		ckbMusical = new Checkbox("Musical");
		ckbMystery = new Checkbox("Mystery");
		ckbNews = new Checkbox("News");
		ckbRealityTV = new Checkbox("Reality-TV");
		ckbRomance = new Checkbox("Romance");
		ckbSport = new Checkbox("Sport");
		ckbTalkShow = new Checkbox("Talk-Show");
		ckbThriller = new Checkbox("Thriller");
		ckbWar = new Checkbox("War");
		ckbWestern = new Checkbox("Western");

		pnTopRight.add(ckbAction);
		pnTopRight.add(ckbAdventure);
		pnTopRight.add(ckbAnimation);
		pnTopRight.add(ckbBiography);
		pnTopRight.add(ckbComedy);
		pnTopRight.add(ckbCrime);
		pnTopRight.add(ckbDrama);
		pnTopRight.add(ckbFamily);
		pnTopRight.add(ckbFantasy);
		pnTopRight.add(ckbGameShow);
		pnTopRight.add(ckbHistory);
		pnTopRight.add(ckbHorror);
		pnTopRight.add(ckbMusic);
		pnTopRight.add(ckbMusical);
		pnTopRight.add(ckbMystery);
		pnTopRight.add(ckbNews);
		pnTopRight.add(ckbRealityTV);
		pnTopRight.add(ckbRomance);
		pnTopRight.add(ckbSport);
		pnTopRight.add(ckbTalkShow);
		pnTopRight.add(ckbThriller);
		pnTopRight.add(ckbWar);
		pnTopRight.add(ckbWestern);

		// Create panel BottomTopRight
		JPanel pnBottomTopRight = new JPanel();
		pnBottomTopRight.setLayout(new GridLayout(3, 3));
		pnTop.add(pnBottomTopRight);

		Border borderBottomTopRight = BorderFactory.createLineBorder(Color.GRAY);
		TitledBorder titleBorderBottomTopRight = new TitledBorder(borderBottomTopRight, "Companies");
		titleBorderBottomTopRight.setTitleFont(new Font("Arial", Font.BOLD, 15));
		titleBorderBottomTopRight.setTitleJustification(TitledBorder.CENTER);
		titleBorderBottomTopRight.setTitleColor(Color.BLUE);
		pnBottomTopRight.setBorder(titleBorderBottomTopRight);

		ckb20thCenturyFox = new Checkbox("20th Century Fox");
		ckbSony = new Checkbox("Sony");
		ckbDreamWork = new Checkbox("DreamWorks");
		ckbMGM = new Checkbox("MGM");
		ckbParamount = new Checkbox("Paramount");
		ckbUniversal = new Checkbox("Universal");
		ckbWaltDisney = new Checkbox("Walt Disney");
		ckbWarnerBros = new Checkbox("Warner Bros");

		pnBottomTopRight.add(ckb20thCenturyFox);
		pnBottomTopRight.add(ckbSony);
		pnBottomTopRight.add(ckbDreamWork);
		pnBottomTopRight.add(ckbMGM);
		pnBottomTopRight.add(ckbParamount);
		pnBottomTopRight.add(ckbUniversal);
		pnBottomTopRight.add(ckbWaltDisney);
		pnBottomTopRight.add(ckbWarnerBros);

		/* Bottom */
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		pnBottom.setPreferredSize(new Dimension(0, 100));
		pnBottom.setBackground(Color.blue);
		con.add(pnBottom, BorderLayout.SOUTH);

		JPanel pnText = new JPanel();
		pnText.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblNumberOfFilms = new JLabel("Số lượng phim cần tìm (<= 50): ");
		lblNumberOfFilms.setFont(new Font("Arial", Font.BOLD, 20));
		txtNumberOfFilms = new JTextField(10);
		txtNumberOfFilms.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		pnText.add(lblNumberOfFilms);
		pnText.add(txtNumberOfFilms);
		pnBottom.add(pnText);

		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Arial", Font.BOLD, 20));
		pnButton.add(btnSearch);
		pnBottom.add(pnButton);
	}

	public void addEvents() {
		// System.out.println("comeherer");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (handingInfoSearch()) {
					writeFile();
					executePython();
				}
			}
		});
	}
	
	protected boolean handingInfoSearch() {
		if (txtNumberOfFilms.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Rỗng", "Empty", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			int num = 0;
			try {
				num = Integer.parseInt(txtNumberOfFilms.getText());
			} finally {

			}
			if (num > 50 || num <= 0) {
				JOptionPane.showMessageDialog(null, "Bạn đã nhập sai !", "Error", JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				return true;
			}
		}

	}

	protected void writeFile() {

		String path = System.getProperty("user.dir");
		File file = new File(path + "/Communication/request.txt");

		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tạo một OutputStream (luồng đầu ra) để ghi dữ liệu vào file.
		OutputStream out = null;
		try {
			out = new FileOutputStream(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tạo một Character Stream (luồng ghi ký tự) bao lấy OutputStream ở trên.
		// Mã hóa (encoding) là UTF-8.
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

		try {
			bw.write(ckbFeatureFilm.getLabel() + " " + Integer.toString(ckbFeatureFilm.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbTVMovie.getLabel() + " " + Integer.toString(ckbTVMovie.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbTVSeries.getLabel() + " " + Integer.toString(ckbTVMovie.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbTVEpisode.getLabel() + " " + Integer.toString(ckbTVEpisode.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbTVSpecial.getLabel() + " " + Integer.toString(ckbTVSpecial.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbMiniSeries.getLabel() + " " + Integer.toString(ckbMiniSeries.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbDocumentary.getLabel() + " " + Integer.toString(ckbDocumentary.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbVideoGame.getLabel() + " " + Integer.toString(ckbVideoGame.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbShortFilm.getLabel() + " " + Integer.toString(ckbShortFilm.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbVideo.getLabel() + " " + Integer.toString(ckbVideo.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbTVShort.getLabel() + " " + Integer.toString(ckbTVShort.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbAction.getLabel() + " " + Integer.toString(ckbAction.getState() ? 1 : 0));
			bw.newLine();

			bw.write(ckbAdventure.getLabel() + " " + Integer.toString(ckbAdventure.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbAnimation.getLabel() + " " + Integer.toString(ckbAnimation.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbBiography.getLabel() + " " + Integer.toString(ckbBiography.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbComedy.getLabel() + " " + Integer.toString(ckbComedy.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbCrime.getLabel() + " " + Integer.toString(ckbCrime.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbDrama.getLabel() + " " + Integer.toString(ckbDrama.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbFamily.getLabel() + " " + Integer.toString(ckbFamily.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbFantasy.getLabel() + " " + Integer.toString(ckbFantasy.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbGameShow.getLabel() + " " + Integer.toString(ckbGameShow.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbHistory.getLabel() + " " + Integer.toString(ckbHistory.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbHorror.getLabel() + " " + Integer.toString(ckbHorror.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbMusic.getLabel() + " " + Integer.toString(ckbMusic.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbMusical.getLabel() + " " + Integer.toString(ckbMusical.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbMystery.getLabel() + " " + Integer.toString(ckbMystery.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbNews.getLabel() + " " + Integer.toString(ckbNews.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbRealityTV.getLabel() + " " + Integer.toString(ckbRealityTV.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbRomance.getLabel() + " " + Integer.toString(ckbRomance.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbSport.getLabel() + " " + Integer.toString(ckbSport.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbTalkShow.getLabel() + " " + Integer.toString(ckbTalkShow.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbThriller.getLabel() + " " + Integer.toString(ckbThriller.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbWar.getLabel() + " " + Integer.toString(ckbWar.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbWestern.getLabel() + " " + Integer.toString(ckbWestern.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckb20thCenturyFox.getLabel() + " " + Integer.toString(ckb20thCenturyFox.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbSony.getLabel() + " " + Integer.toString(ckbSony.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbDreamWork.getLabel() + " " + Integer.toString(ckbDreamWork.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbMGM.getLabel() + " " + Integer.toString(ckbMGM.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbParamount.getLabel() + " " + Integer.toString(ckbParamount.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbUniversal.getLabel() + " " + Integer.toString(ckbUniversal.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbWaltDisney.getLabel() + " " + Integer.toString(ckbWaltDisney.getState() ? 1 : 0));
			bw.newLine();
			bw.write(ckbWarnerBros.getLabel() + " " + Integer.toString(ckbWarnerBros.getState() ? 1 : 0));
			bw.newLine();
			bw.write(txtDateFrom.getText());
			bw.newLine();
			bw.write(txtDateTo.getText());
			bw.newLine();
			bw.write(txtNumberOfFilms.getText());
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	

	public void executePython() {
		
		// Xóa csv nếu đã tồn tại do chương tình trước chạy
		String dirPath = System.getProperty("user.dir");
		File csv = new File(dirPath + "/Data/file.csv");
		if (csv.exists()) {
			csv.delete();
		}
		//System.out.println("comehere");
		
		File filePythonExecute = new File(dirPath + "/Python_Module/main.py");
		if (!filePythonExecute.exists()) {
			System.out.println("not found file");
		}

		Process process = null;
		ProcessBuilder pb = new ProcessBuilder("/home/voccer/anaconda3/bin/python", filePythonExecute.getAbsolutePath());
		try {
			process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		watch(process);
		
		while (!csv.exists()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Searching csv file");
		}
		System.out.println("Python Thread stop, begining next moulde");
		// Module xuat ket qua
	}

	// Hàm xem tiến trình chạy của process

	private static void watch(final Process process) {
		new Thread() {
			public void run() {
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = null;
				try {
					while ((line = input.readLine()) != null) {
						System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	
	public void showWindow() {
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	public void checkComplete() {
		String dirPath = System.getProperty("user.dir");
		File csv = new File(dirPath + "/Data/file.csv");
		while (true) {
			System.out.println("vao ham checkComplete");
			if (!csv.exists()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else break;
		}
	}
}
