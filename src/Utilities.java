import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Utilities {
	
	static final int numberOfMovies = 200;
	static final int numberOfFeatures = 23;
	
	ArrayList<MovieRecord> MovieRecords;
	ArrayList<Evaluation> Evaluations;
	ArrayList<Evaluation> MissingEvaluations;
	
	public ArrayList<Movie> GetMoviesFromFile(String fileName) throws NumberFormatException, IOException
	{
		ArrayList<Movie> Movies = new ArrayList<Movie>();
		
		readMoviesFile(fileName);
		
		Movies = ConvertRecordsToMovies();
		
		return Movies;
	}
	
	public ArrayList<Evaluation> GetEvaluations(String fileName) throws NumberFormatException, IOException
	{		
		readEvaluationsFile(fileName);
		
		return Evaluations;
	}
	
	public ArrayList<Evaluation> GetMissingEvaluations(String fileName) throws NumberFormatException, IOException
	{		
		readMissingEvaluationsFile(fileName);
		
		return MissingEvaluations;
	}
	
	public void SavePredictedEvaluationsToFile(String fileName, ArrayList<Evaluation> PredictedEvaluations) throws IOException
	{
		FileWriter fw = new FileWriter(fileName);
		
		for(int i=0; i<PredictedEvaluations.size();i++)
		{
			fw.append(Integer.toString(PredictedEvaluations.get(i).id));
			fw.append(";");
			fw.append(Integer.toString(PredictedEvaluations.get(i).personId));
			fw.append(";");
			fw.append(Integer.toString(PredictedEvaluations.get(i).movieId));
			fw.append(";");
			fw.append(PredictedEvaluations.get(i).evaluation);
			fw.append("\n");
		}
		fw.flush();
	    fw.close();
	}
	
	private void readMoviesFile(String fileName) throws NumberFormatException, IOException
	{
		FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		MovieRecords = new ArrayList<MovieRecord>();
		
		String line = null;
		
		while((line = br.readLine()) != null) {
			
			String lineParts[] = line.split(";");
			
			if(lineParts.length != 4) continue;
			
			int id = Integer.parseInt(lineParts[0]);
			int movieId = Integer.parseInt(lineParts[1]);
			int featureId = Integer.parseInt(lineParts[2]);
			String value = lineParts[3];

			MovieRecord record = new MovieRecord(id, movieId, featureId, value);			
			MovieRecords.add(record);
		}
		br.close();
	}
	
	private void readEvaluationsFile(String fileName) throws NumberFormatException, IOException
	{
		FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		Evaluations = new ArrayList<Evaluation>();
		
		String line = null;
		
		while((line = br.readLine()) != null) {
			
			String lineParts[] = line.split(";");
			
			if(lineParts.length != 4) continue;
			
			int id = Integer.parseInt(lineParts[0]);
			int personId = Integer.parseInt(lineParts[1]);
			int movieId = Integer.parseInt(lineParts[2]);
			String evaluation = lineParts[3];

			Evaluation Evaluation = new Evaluation(id, personId, movieId, evaluation);			
			Evaluations.add(Evaluation);
		}
		br.close();
	}
	
	private void readMissingEvaluationsFile(String fileName) throws NumberFormatException, IOException
	{
		FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		MissingEvaluations = new ArrayList<Evaluation>();
		
		String line = null;
		
		while((line = br.readLine()) != null) {
			
			String lineParts[] = line.split(";");
			
			if(lineParts.length != 4) continue;
			
			int id = Integer.parseInt(lineParts[0]);
			int personId = Integer.parseInt(lineParts[1]);
			int movieId = Integer.parseInt(lineParts[2]);
			String evaluation = lineParts[3];

			Evaluation MissingEvaluation = new Evaluation(id, personId, movieId, evaluation);			
			MissingEvaluations.add(MissingEvaluation);
		}
		br.close();
	}
	
	private ArrayList<Movie> ConvertRecordsToMovies()
	{
		ArrayList<Movie> Movies = new ArrayList<Movie>();
		
		for(int i=1; i<=numberOfMovies; i++)
		{
			int id = i;
			int collectionId = 0;
		    double budget = 0;
		    int[] genresIds = null;
		    double popularity = 0;
		    int[] productionCompaniesIds = null;
		    String[] productionCountriesIso = null;
		    int releaseDate= 0;
		    double revenue= 0;
		    int runtime= 0;
		    String[] spokenLanguagesIso = null;
		    double voteAverage= 0;
		    int voteCount= 0;
		    int[] castIds = null;
		    int[] costumeMakeUpIds = null;
		    int[] directingIds = null;
		    int[] cameraIds = null;
		    int[] editingIds = null;
		    int[] productionIds = null;
		    int[] soundIds = null;
		    int[] writingIds = null;
		    int[] artIds = null;
		    int[] crewIds = null;
		    int[] visualEffectsIds = null;
		    
			for(int j=1; j<=numberOfFeatures; j++)
			{
			    int movieRecordId = GetRecordId(i, j);
			    
				switch(j)
				{
				case 1:
					if(MovieRecords.get(movieRecordId-1).Value.equals("null"))
					{
						collectionId = 0;
					}
					else
					{
						collectionId = Integer.parseInt(MovieRecords.get(movieRecordId-1).Value);
					}
					break;
				case 2:
					budget = Double.parseDouble(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 3:
					genresIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 4:
					popularity = Double.parseDouble(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 5:
					productionCompaniesIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 6:
					productionCountriesIso = ConvertNumerousStringsToStringTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 7:
					releaseDate = GetReleaseYear(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 8:
					revenue = Double.parseDouble(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 9:
					runtime = Integer.parseInt(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 10:
					spokenLanguagesIso = ConvertNumerousStringsToStringTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 11:
					voteAverage = Double.parseDouble(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 12:
					voteCount = Integer.parseInt(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 13:
					castIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 14:
					costumeMakeUpIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 15:
					directingIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 16:
					cameraIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 17:
					editingIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 18:
					productionIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 19:
					soundIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 20:
					writingIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 21:
					artIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 22:
					crewIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				case 23:
					visualEffectsIds = ConvertNumerousStringsToIntTable(MovieRecords.get(movieRecordId-1).Value);
					break;
				}
			}
			Movie movie = new Movie(id, collectionId, budget, genresIds, popularity, productionCompaniesIds, productionCountriesIso, releaseDate, revenue, runtime, spokenLanguagesIso, voteAverage, voteCount, castIds, costumeMakeUpIds, directingIds, cameraIds, editingIds, productionIds, soundIds, writingIds, artIds, crewIds, visualEffectsIds);
			Movies.add(movie);
		}	
		return Movies;
	}

	private int GetRecordId(int movieId, int featureId)
	{
		int recordId = 0;
		
		for(int i=0; i<MovieRecords.size(); i++)
		{
			if(MovieRecords.get(i).movieId == movieId && MovieRecords.get(i).featureId == featureId)
			{
				recordId = MovieRecords.get(i).id;
			}
		}
		return recordId;
	}
	
	private int[] ConvertNumerousStringsToIntTable(String text)
	{
		if(!text.equals("null"))
		{
			String[] tempTable = text.split("/");
			int[] table = new int[tempTable.length];
			
			for(int i=0; i<tempTable.length; i++)
			{
				table[i] = Integer.parseInt(tempTable[i]);
			}
			return table;
		}
		else
		{
			return null;
		}
	}
	
	private String[] ConvertNumerousStringsToStringTable(String text)
	{
		if(!text.equals("null"))
		{
			String[] table = text.split("/");
			return table;
		}
		else
		{
			return null;
		}
	}
	
	private int GetReleaseYear(String text) {
		
		String[] table = text.split("-");
		return Integer.parseInt(table[0]);
	}
}
