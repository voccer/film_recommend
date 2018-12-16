package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Film;
import test.ReaderCSV;

public class ResultUI extends JFrame {
	
	JTable tblFilms;
	DefaultTableModel dm;
	private static ArrayList<ArrayList<String>> ListInfo ;
	JTextField txtName, txtLink, txtDate,
			   txtNumberCmt, txtNumberNag, txtNumberPos;
	
	JTextArea txtAreaDescription;
	
	public ResultUI(String title)
	{
		super(title);
		addControls();
		addEvents();
		addFakeData();
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnLeft = new JPanel();
		JPanel pnRight = new JPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
		con.add(sp);
		
		// Left
		pnLeft.setLayout(new BorderLayout());
		pnLeft.setPreferredSize(new Dimension(600, 700));
		
		dm = new DefaultTableModel();
		dm.addColumn("Name");
		dm.addColumn("Date");
		dm.addColumn("Score");
		tblFilms = new JTable(dm);
		tblFilms.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		
		JScrollPane sc = new JScrollPane(tblFilms,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		pnLeft.add(sc, BorderLayout.CENTER);
		
		// Right
		pnRight.setLayout(new GridLayout(7, 1));
		
		JPanel pnRow1  = new JPanel();
		pnRow1.setLayout(new GridBagLayout());
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.BOLD, 15));
		txtName 	   = new JTextField(40);
//		txtName.setEditable(false);
		pnRow1.add(lblName);
		pnRow1.add(txtName);
		pnRight.add(pnRow1);
		
		JPanel pnRow2  = new JPanel();
		pnRow2.setLayout(new GridBagLayout());
		JLabel lblLink = new JLabel("Link:");
		lblLink.setFont(new Font("Arial", Font.BOLD, 15));
		txtLink 	   = new JTextField(40);
//		txtLink.setEditable(false);
		pnRow2.add(lblLink);
		pnRow2.add(txtLink);
		pnRight.add(pnRow2);
		
		JPanel pnRow3  = new JPanel();
		pnRow3.setLayout(new GridBagLayout());
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.BOLD, 15));
		txtDate 	   = new JTextField(40);
//		txtDate.setEditable(false);
		pnRow3.add(lblDate);
		pnRow3.add(txtDate);
		pnRight.add(pnRow3);
		
		JPanel pnRow4         = new JPanel();
//		pnRow4.setLayout(new BorderLayout());
		pnRow4.setLayout(new GridBagLayout());
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Arial", Font.BOLD, 15));
		txtAreaDescription		  = new JTextArea(5, 40);
//		txtAreaDescription.setEditable(false);
		JScrollPane scDes = new JScrollPane(txtAreaDescription,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnRow4.add(lblDescription);
		pnRow4.add(scDes);
		pnRight.add(pnRow4);

		JPanel pnRow5       = new JPanel();
		pnRow5.setLayout(new GridBagLayout());
		JLabel lblNumberCmt = new JLabel("NumberCmt: ");
		lblNumberCmt.setFont(new Font("Arial", Font.BOLD, 15));
		txtNumberCmt        = new JTextField(40);
//		txtNumberCmt.setEditable(false);
		pnRow5.add(lblNumberCmt);
		pnRow5.add(txtNumberCmt);
		pnRight.add(pnRow5);
		
		JPanel pnRow6 		  = new JPanel();
		pnRow6.setLayout(new GridBagLayout());
		JLabel lblNumberNag	  = new JLabel("NumberNag: ");
		lblNumberNag.setFont(new Font("Arial", Font.BOLD, 15));
//		txtNumberNag.setEditable(false);
		txtNumberNag		  = new JTextField(40);
		pnRow6.add(lblNumberNag);
		pnRow6.add(txtNumberNag);
		pnRight.add(pnRow6);
		
		JPanel pnRow7         = new JPanel();
		pnRow7.setLayout(new GridBagLayout());
		JLabel lblNumberPos	  = new JLabel("NumberPos: ");
		lblNumberPos.setFont(new Font("Arial", Font.BOLD, 15));
		txtNumberPos		  = new JTextField(40);
//		txtNumberPos.setEditable(false);
		pnRow7.add(lblNumberPos);
		pnRow7.add(txtNumberPos);
		pnRight.add(pnRow7);
		
		// Align
		lblName.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblLink.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblDate.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblNumberCmt.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblNumberNag.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblNumberPos.setPreferredSize(lblNumberCmt.getPreferredSize());
		lblDescription.setPreferredSize(lblNumberCmt.getPreferredSize());
	}
	
	public void addEvents() {
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
				int row = tblFilms.getSelectedRow();
				if(row == -1)
				{
					return;
				}
				txtLink.setText(ListInfo.get(0).get(row));
				txtName.setText(ListInfo.get(1).get(row));
				txtDate.setText(ListInfo.get(3).get(row));
				txtAreaDescription.setText(ListInfo.get(4).get(row));
				txtNumberCmt.setText(ListInfo.get(5).get(row));
				txtNumberNag.setText(String.valueOf((Integer.parseInt(ListInfo.get(5).get(row)) - Integer.parseInt(ListInfo.get(6).get(row)))));
				txtNumberPos.setText(ListInfo.get(6).get(row));
			}
		});
	}
	private void addFakeData(){
		ArrayList<Film> listFilm = new ArrayList<>();
		String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir") + "/Data/file.csv";
		ReaderCSV read = new ReaderCSV();
		ListInfo = new ArrayList<>();
		try {
			ListInfo = read.ReadCsv(SAMPLE_CSV_FILE_PATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			for(int j = 0; j < ListInfo.get(0).size(); j++) {// Name Date 	...
				listFilm.add(new Film(ListInfo.get(1).get(j), ListInfo.get(3).get(j), ListInfo.get(7).get(j)));
			}				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		for (Film film : listFilm)
		{
			Vector<String> vec = new Vector<>();
			vec.add(film.getName());
			vec.add(film.getDate()+"");
			vec.add(film.getScore()+"");
			dm.addRow(vec);
		}
	}
	
	public void showWindow() {
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
