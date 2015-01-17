import java.util.ArrayList;
import java.util.Collections;


public class DecisionTree {
	
	ArrayList<Movie> Movies;
	ArrayList<Evaluation> Evaluations;
	ArrayList<Evaluation> MissingEvaluations;
	boolean limitedFeatures;

	DecisionTree(ArrayList<Movie> Movies, ArrayList<Evaluation> Evaluations, ArrayList<Evaluation> MissingEvaluations, boolean limitedFeatures)
	{
		this.Movies = Movies;
		this.Evaluations = Evaluations;
		this.MissingEvaluations = MissingEvaluations;
		this.limitedFeatures = limitedFeatures;
	}
	
	public ArrayList<Evaluation> PredictEvaluations()
	{
		for(int i=5; i<1817; i++) //iterating through each person (I know the range from the observation)
		{
			ArrayList<Evaluation> PersonEvaluations = GetPersonEvaluations(i, Evaluations);
			ArrayList<Evaluation> PersonMissingEvaluations = GetPersonEvaluations(i, MissingEvaluations);
			
			if(PersonMissingEvaluations.size() > 0 && PersonEvaluations.size() > 0) //TODO sprawdziæ, czy w ogóle kiedyœ tu wchodzi
			{
				System.out.println("*********** Person ID = " + i + " ***********");
				
				for(int j=0; j<PersonMissingEvaluations.size(); j++) //iterating through the not evaluated movies
				{
					int evaluationId = PersonMissingEvaluations.get(j).id;
					int movieId = PersonMissingEvaluations.get(j).movieId;
					
					ArrayList<BestMatch> BestMatch = GetMostSimilarMovies(movieId, PersonEvaluations, 5);
					
					if(limitedFeatures)
					{
						PersonMissingEvaluations.get(j).evaluation = GetPredictionWithSelectedFeatures(movieId, BestMatch);
					}
					else
					{
						PersonMissingEvaluations.get(j).evaluation = GetPredictionWithAllFeatures(movieId, BestMatch);
					}	
					FillMissingEvaluation(evaluationId, PersonMissingEvaluations.get(j).evaluation);
				}
			}
			else
			{
				if(PersonMissingEvaluations.size() == 0)
				{
					System.out.println("WARNING! Person ID = " + i + " has evaluated all of the movies.");
				}
				
				if(PersonEvaluations.size() == 0)
				{
					System.out.println("WARNING! Person ID = " + i + " has not evaluated any movie. No data for predictions.");
				}
			}
		}
		return MissingEvaluations;
	}
	
	private ArrayList<BestMatch> GetMostSimilarMovies(int movieId, ArrayList<Evaluation> PersonEvaluations, int numberOfMovies)
	{
		ArrayList<BestMatch> BestMatches = new ArrayList<BestMatch>();
		
		for(int i=0; i<PersonEvaluations.size(); i++)
		{
			int currentEvaluatedMovieId = PersonEvaluations.get(i).movieId;
			Comparator comparator = new Comparator(Movies.get(movieId-1), Movies.get(currentEvaluatedMovieId-1), Movies); 
			double similarityRate = comparator.GetMoviesSimilarityRate(limitedFeatures);
			
			if(i<numberOfMovies)
			{
				BestMatch bestMatch = new BestMatch(similarityRate, PersonEvaluations.get(i).evaluation, PersonEvaluations.get(i).movieId);
				BestMatches.add(bestMatch);
			}
			else if(similarityRate > BestMatches.get(0).similarityRate)
			{
				BestMatch bestMatch = new BestMatch(similarityRate, PersonEvaluations.get(i).evaluation, PersonEvaluations.get(i).movieId);
				BestMatches.remove(0);
				BestMatches.add(bestMatch);
			}
			
			Collections.sort(BestMatches, BestMatch.SimilarityRateComparator);
		}
		return BestMatches;
	}
	
	private String GetPredictionWithAllFeatures(int movieId, ArrayList<BestMatch> MostSimilarMovies)
	{
		int evaluation = 0;
		
		Movie movie = Movies.get(movieId-1);
		
		BestMatch bestMatch = MostSimilarMovies.get(4);
		Movie mostSimilarMovie = Movies.get(bestMatch.movieId - 1);
		Comparator comparator = new Comparator(movie, mostSimilarMovie, Movies);
		comparator.CalculateSimilarityRatesForSeparateFeatures(limitedFeatures);
		
		int mostSimilarMovieEvaluation = GetMovieEvaluation(mostSimilarMovie.id);
		int mostSimilarMoviesEvaluation_2 = GetAverageEvaluation(MostSimilarMovies, 2);
		int mostSimilarMoviesEvaluation_3 = GetAverageEvaluation(MostSimilarMovies, 3);
		int mostSimilarMoviesEvaluation_4 = GetAverageEvaluation(MostSimilarMovies, 4);
		int mostSimilarMoviesEvaluation_5 = GetAverageEvaluation(MostSimilarMovies, 5);
		
		if(comparator.collectionSimilarityRate == 1)
		{
			evaluation = mostSimilarMovieEvaluation;
		}
		else if(comparator.directingSimilarityRate > 0)
		{
			evaluation = mostSimilarMovieEvaluation;
		}
		else if(comparator.genresSimilarityRate > 0)
		{
			evaluation = mostSimilarMovieEvaluation;
		}
		else if(comparator.castSimilarityRate > 0)
		{
			evaluation = mostSimilarMovieEvaluation;
		}
		else if(comparator.popularitySimilarityRate >= 0.5)
		{
			if(Math.abs(movie.releaseDate - mostSimilarMovie.releaseDate) < 20)
			{
				evaluation = mostSimilarMoviesEvaluation_2;
			}
			else if(comparator.budgetSimilarityRate >= 0.5)
			{
				evaluation = mostSimilarMoviesEvaluation_2;
			}
			else
			{
				evaluation = mostSimilarMoviesEvaluation_3;
			}
		}
		else if(comparator.budgetSimilarityRate >= 0.5)
		{
			evaluation = mostSimilarMoviesEvaluation_2;
		}
		else if(Math.abs(movie.releaseDate - mostSimilarMovie.releaseDate) < 20)
		{
			if(comparator.runtimeSimilarityRate >= 0.5)
			{
				evaluation = mostSimilarMoviesEvaluation_3;
			}
			else
			{
				evaluation = mostSimilarMoviesEvaluation_4;
			}
		}
		else if(comparator.runtimeSimilarityRate >= 0.5)
		{
			evaluation = mostSimilarMoviesEvaluation_4;
		}
		else
		{
			evaluation = mostSimilarMoviesEvaluation_5;
		}
		return Integer.toString(evaluation);
	}
	
	private String GetPredictionWithSelectedFeatures(int movieId, ArrayList<BestMatch> MostSimilarMovies)
	{
		int evaluation = 0;
		
		Movie movie = Movies.get(movieId-1);
		
		BestMatch bestMatch = MostSimilarMovies.get(4);
		Movie mostSimilarMovie = Movies.get(bestMatch.movieId - 1);
		Comparator comparator = new Comparator(movie, mostSimilarMovie, Movies);
		comparator.CalculateSimilarityRatesForSeparateFeatures(limitedFeatures);
		
		int mostSimilarMovieEvaluation = GetMovieEvaluation(mostSimilarMovie.id);
		int mostSimilarMoviesEvaluation_2 = GetAverageEvaluation(MostSimilarMovies, 2);
		int mostSimilarMoviesEvaluation_3 = GetAverageEvaluation(MostSimilarMovies, 3);
		int mostSimilarMoviesEvaluation_4 = GetAverageEvaluation(MostSimilarMovies, 4);
		int mostSimilarMoviesEvaluation_5 = GetAverageEvaluation(MostSimilarMovies, 5);
		
		if(comparator.collectionSimilarityRate == 1)
		{
			evaluation = mostSimilarMovieEvaluation;
		}
		else if(comparator.budgetSimilarityRate >= 0.5)
		{
			evaluation = mostSimilarMovieEvaluation;
		}
		else if(comparator.popularitySimilarityRate >= 0.5)
		{
			if(comparator.productionCountriesSimilarityRate > 0)
			{
				evaluation = mostSimilarMovieEvaluation;
			}
			else
			{
				evaluation = mostSimilarMoviesEvaluation_2;
			}
		}
		else if(Math.abs(movie.releaseDate - mostSimilarMovie.releaseDate) < 20)
		{
			evaluation = mostSimilarMoviesEvaluation_2;
		}
		else if(comparator.productionCountriesSimilarityRate > 0)
		{
			evaluation = mostSimilarMoviesEvaluation_3;
		}
		else if(comparator.spokenLanguagesSimilarityRate > 0)
		{
			evaluation = mostSimilarMoviesEvaluation_4;
		}
		else
		{
			evaluation = mostSimilarMoviesEvaluation_5;
		}
		return Integer.toString(evaluation);
	}
	
	private int GetAverageEvaluation(ArrayList<BestMatch> BestMatches, int numberToConsider)
	{
		int evaluationsSum = 0;
		
		int bm_4 = Integer.parseInt(BestMatches.get(4).evaluation);
		int bm_3 = Integer.parseInt(BestMatches.get(3).evaluation);
		int bm_2 = Integer.parseInt(BestMatches.get(2).evaluation);
		int bm_1 = Integer.parseInt(BestMatches.get(1).evaluation);
		int bm_0 = Integer.parseInt(BestMatches.get(0).evaluation);
		
		switch(numberToConsider)
		{
		case 2:
			evaluationsSum = bm_4 + bm_3;
			break;
		case 3:
			evaluationsSum = bm_4 + bm_3 + bm_2;
			break;
		case 4:
			evaluationsSum = bm_4 + bm_3 + bm_2 + bm_1;
			break;
		case 5:
			evaluationsSum = bm_4 + bm_3 + bm_2 + bm_1 + bm_0;
			break;
		}
		
		int averageEvaluation = (int) Math.round(evaluationsSum / numberToConsider);
		
		return averageEvaluation;
	}
	
	private int GetMovieEvaluation(int movieId)
	{
		int evaluation = 0;
		
		for(int i=0; i<Evaluations.size();i++)
		{
			if(movieId == Evaluations.get(i).movieId)
			{
				evaluation = Integer.parseInt(Evaluations.get(i).evaluation);
				break;
			}
		}
		return evaluation;
	}
	
	private ArrayList<Evaluation> GetPersonEvaluations(int personId, ArrayList<Evaluation> Evaluations)
	{
		ArrayList<Evaluation> PersonEvaluations = new ArrayList<Evaluation>();
		
		for(int i=0;i<Evaluations.size();i++)
		{
			if(personId == Evaluations.get(i).personId)
			{
				PersonEvaluations.add(Evaluations.get(i));
			}
		}
		return PersonEvaluations;
	}
	
	private void FillMissingEvaluation(int evaluationId, String evaluation)
	{
		for(int i=0; i<MissingEvaluations.size();i++)
		{
			if(MissingEvaluations.get(i).id == evaluationId)
			{
				MissingEvaluations.get(i).evaluation = evaluation;
				return;
			}
		}
	}
}
