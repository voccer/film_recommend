package test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ReaderCSV {
	
	// input : file CSV
	// output : List String ứng với các cột database
	public ArrayList<ArrayList<String>> ReadCsv (String PATH) throws IOException {
		ArrayList<ArrayList<String>> list = new ArrayList<>();
		ArrayList<String> listLinkFilm = new ArrayList<>();
		ArrayList<String> listNameFilm = new ArrayList<>();
		ArrayList<String> listStar = new ArrayList<>();
		ArrayList<String> listDate = new ArrayList<>();
		ArrayList<String> listDesc = new ArrayList<>();
		ArrayList<String> listLinkImage = new ArrayList<>();
		ArrayList<String> listTotal = new ArrayList<>();
		ArrayList<String> listPosotive = new ArrayList<>();
		try (
				Reader reader = Files.newBufferedReader(Paths.get(PATH));
				//System.out.println(reader.toString());
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
	                   //.withHeader("","LinkFilm", "NameFilm", "Start", "Date","Description", "Link_Image", "Total_Comment", "Positive_Comment")
						.withFirstRecordAsHeader()
						.withIgnoreHeaderCase()
	                    .withTrim());
		){
			for (CSVRecord csvRecord : csvParser) {
				// Accessing Values by Column Index
				listLinkFilm.add(csvRecord.get("LinkFilm"));
                listNameFilm.add(csvRecord.get("NameFilm"));
                listStar.add(csvRecord.get("Star"));
                listDate.add(csvRecord.get("Date"));
                listDesc.add(csvRecord.get("Description"));
                listLinkImage.add(csvRecord.get("LinkImage"));
                listTotal.add(csvRecord.get("TotalComment"));
                listPosotive.add(csvRecord.get("PositiveComment"));
			}
		}
		list.add(listLinkFilm);
		list.add(listNameFilm);
		list.add(listStar);
		list.add(listDate);
		list.add(listDesc);
		list.add(listLinkImage);
		list.add(listTotal);
		list.add(listPosotive);
		
		return list;
	}
}
