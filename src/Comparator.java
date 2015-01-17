import java.util.ArrayList;


public class Comparator {
	
	int collectionWeight = 10;
	int budgetWeight = 4;
	int genresWeight = 9;
	int popularityWeight = 5;
	int productionCompaniesWeight = 5;
	int productionCountriesWeight = 6;
	int releaseDateWeight = 7;
	int revenueWeight = 5;
	int runtimeWeight = 2;
	int spokenLanguagesWeight = 8;
	int voteAverageWeight = 5;
	int voteCountWeight = 4;
	int castWeight = 9;
	int costumeMakeUpWeight = 3;
	int directingWeight = 9;
	int cameraWeight = 4;
	int editingWeight = 2;
	int productionWeight = 4;
	int soundWeight = 2;
	int writingWeight = 6;
	int artWeight = 2;
	int crewWeight = 1;
	int visualEffectsWeight = 3;
	
	double collectionSimilarityRate;
	double budgetSimilarityRate;
	double genresSimilarityRate;
	double popularitySimilarityRate;
	double productionCompaniesSimilarityRate;
	double productionCountriesSimilarityRate;
	double releaseDateSimilarityRate;
	double revenueSimilarityRate;
	double runtimeSimilarityRate;
	double spokenLanguagesSimilarityRate;
	double voteAverageSimilarityRate;
	double voteCountSimilarityRate;
	double castSimilarityRate;
	double costumeMakeUpSimilarityRate;
	double directingSimilarityRate;
	double cameraSimilarityRate;
	double editingSimilarityRate;
	double productionSimilarityRate;
	double soundSimilarityRate;
	double writingSimilarityRate;
	double artSimilarityRate;
	double crewSimilarityRate;
	double visualEffectsSimilarityRate;
    
	Movie movie1;
	Movie movie2;
	
	ArrayList<Movie> Movies;
	
    double maxBudgetInSet;
    double minBudgetInSet;
    
    double maxPopularityInSet;
    double minPopularityInSet;

    int maxReleaseDateInSet;
    int minReleaseDateInSet;
    
    double maxRevenue;
    double minRevenue;
    
    int maxRuntime;
    int minRuntime;

    double maxVoteAverage;
    double minVoteAverage;
    
    int maxVoteCount;
    int minVoteCount;
	
	Comparator(Movie movie1, Movie movie2, ArrayList<Movie> Movies)
	{
		this.movie1 = movie1;
		this.movie2 = movie2;
		this.Movies = Movies;
		
		CalculateMinMaxValues();
	}
	
	public void CalculateMinMaxValues()
	{
	    this.maxBudgetInSet = GetMaxBudgetInSet();
	    this.minBudgetInSet = GetMinBudgetInSet();
	    
	    this.maxPopularityInSet = GetMaxPopularityInSet();
	    this.minPopularityInSet = GetMinPopularityInSet();

	    this.maxReleaseDateInSet = GetMaxReleaseDateInSet();
	    this.minReleaseDateInSet = GetMinReleaseDateInSet();
	    
	    this.maxRevenue = GetMaxRevenue();
	    this.minRevenue = GetMinRevenue();
	    
	    this.maxRuntime = GetMaxRuntime();
	    this.minRuntime = GetMinRuntime();

	    this.maxVoteAverage = GetMaxVoteAverage();
	    this.minVoteAverage = GetMinVoteAverage();
	    
	    this.maxVoteCount = GetMaxVoteCount();
	    this.minVoteCount = GetMinVoteCount();
	}
	
	public double GetMoviesSimilarityRate(boolean limitedFeatures)
	{
		CalculateSimilarityRatesForSeparateFeatures(limitedFeatures);

		double similarityRate = 0;
		
		if(!limitedFeatures)
		{
			similarityRate = (
					collectionWeight * collectionSimilarityRate + 
					budgetWeight * budgetSimilarityRate + 
					genresWeight * genresSimilarityRate + 
					popularityWeight * popularitySimilarityRate +
					productionCompaniesWeight * productionCompaniesSimilarityRate + 
					productionCountriesWeight * productionCountriesSimilarityRate + 
					releaseDateWeight * releaseDateSimilarityRate +
					revenueWeight * revenueSimilarityRate + 
					runtimeWeight * runtimeSimilarityRate + 
					spokenLanguagesWeight * spokenLanguagesSimilarityRate + 
					voteAverageWeight * voteAverageSimilarityRate +
					voteCountWeight * voteCountSimilarityRate + 
					castWeight * castSimilarityRate + 
					costumeMakeUpWeight * costumeMakeUpSimilarityRate + 
					directingWeight * directingSimilarityRate +
					cameraWeight * cameraSimilarityRate + 
					editingWeight * editingSimilarityRate + 
					productionWeight * productionSimilarityRate + 
					soundWeight * soundSimilarityRate +
					writingWeight * writingSimilarityRate + 
					artWeight * artSimilarityRate + 
					crewWeight * crewSimilarityRate + 
					visualEffectsWeight * visualEffectsSimilarityRate
					)/(
					collectionWeight + 
					budgetWeight + 
					genresWeight + 
					popularityWeight + 
					productionCompaniesWeight + 
					productionCountriesWeight + 
					releaseDateWeight + 
					revenueWeight + 
					runtimeWeight +
					spokenLanguagesWeight + 
					voteAverageWeight +
					voteCountWeight + 
					castWeight +
					costumeMakeUpWeight +
					directingWeight +
					cameraWeight +
					editingWeight +
					productionWeight +
					soundWeight +
					writingWeight +
					artWeight +
					crewWeight +
					visualEffectsWeight
					); //weighted average
		}
		else
		{
			popularityWeight = 10;
			runtimeWeight = 9;
			voteCountWeight = 8;
			voteAverageWeight = 7;
			releaseDateWeight = 6;
			budgetWeight = 5;
			revenueWeight = 4;
			productionCountriesWeight = 3;
			collectionWeight = 2;
			spokenLanguagesWeight = 1;
			
			similarityRate = (
					collectionWeight * collectionSimilarityRate + 
					budgetWeight * budgetSimilarityRate + 
					popularityWeight * popularitySimilarityRate +
					productionCountriesWeight * productionCountriesSimilarityRate + 
					releaseDateWeight * releaseDateSimilarityRate +
					revenueWeight * revenueSimilarityRate + 
					runtimeWeight * runtimeSimilarityRate + 
					spokenLanguagesWeight * spokenLanguagesSimilarityRate + 
					voteAverageWeight * voteAverageSimilarityRate +
					voteCountWeight * voteCountSimilarityRate 
					)/(
					collectionWeight + 
					budgetWeight + 
					popularityWeight + 
					productionCountriesWeight + 
					releaseDateWeight + 
					revenueWeight + 
					runtimeWeight +
					spokenLanguagesWeight + 
					voteAverageWeight +
					voteCountWeight
					); //weighted average
		}
		
		//System.out.println("************** MOVIES SIMILARTY RATE **************");
		//System.out.println("Similarity rate = " + similarityRate);
		return similarityRate;
	}
	
	public void CalculateSimilarityRatesForSeparateFeatures(boolean limitedFeatures)
	{
		popularitySimilarityRate = GetSimilarityRateForPopularity();
		runtimeSimilarityRate = GetSimilarityRateForRuntime();
		voteCountSimilarityRate = GetSimilarityRateForVoteCount();
		voteAverageSimilarityRate = GetSimilarityRateForVoteAverage();
		releaseDateSimilarityRate = GetSimilarityRateForReleaseDate();
		budgetSimilarityRate = GetSimilarityRateForBudget();
		revenueSimilarityRate = GetSimilarityRateForRevenue();
		productionCountriesSimilarityRate = GetSimilarityRateForProductionCountries();
		collectionSimilarityRate = GetSimilarityRateForCollection();
		spokenLanguagesSimilarityRate = GetSimilarityRateForSpokenLanguages();

		if(!limitedFeatures)
		{
			genresSimilarityRate = GetSimilarityRateForGenre();
			productionCompaniesSimilarityRate = GetSimilarityRateForProductionCompanies();
			castSimilarityRate = GetSimilarityRateForCast();
			costumeMakeUpSimilarityRate = GetSimilarityRateForCostumeMakeUp();
			directingSimilarityRate = GetSimilarityRateForDirecting();
			productionSimilarityRate = GetSimilarityRateForProduction();
			soundSimilarityRate = GetSimilarityRateForSound();
			cameraSimilarityRate = GetSimilarityRateForCamera();
			writingSimilarityRate = GetSimilarityRateForWriting();
			editingSimilarityRate = GetSimilarityRateForEditing();
			artSimilarityRate = GetSimilarityRateForArt();
			crewSimilarityRate = GetSimilarityRateForCrew();
			visualEffectsSimilarityRate = GetSimilarityRateForVisualEffects();
		}
	}
	
	private double GetSimilarityRateForCollection()
	{
		double similarityRate = 0;
		
		if((movie1.collectionId != 0 && movie2.collectionId != 0) && movie1.collectionId == movie2.collectionId) //if the same collection or if no collection
		{
			similarityRate = 1;
		}
		else if(movie1.collectionId != 0 && movie2.collectionId != 0) //if different collections
		{
			similarityRate = 0.5;
		}
		else //if one belongs to the collection and second doesn't
		{
			similarityRate = 0;
		}

		//PrintSeparateFeatureResult(movie1.collectionId, movie2.collectionId, similarityRate, "COLLECTION");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForBudget()
	{
		double similarityRate = calculateNumbersSimilarity(movie1.budget, movie2.budget, minBudgetInSet, maxBudgetInSet);
		
		//PrintSeparateFeatureResult(movie1.budget, movie2.budget, similarityRate, "BUDGET");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForGenre() //TODO more sophisticated way, e.g. thriller is similar to action movie
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.genresIds, movie2.genresIds);
		
		//PrintSeparateFeatureResult(movie1.genresIds, movie2.genresIds, similarityRate, "GENRE");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForPopularity()
	{
		double similarityRate = calculateNumbersSimilarity(movie1.popularity, movie2.popularity, minPopularityInSet, maxPopularityInSet);
		
		//PrintSeparateFeatureResult(movie1.popularity, movie2.popularity, similarityRate, "POPULARITY");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForProductionCompanies()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.productionCompaniesIds, movie2.productionCompaniesIds);
		
		//PrintSeparateFeatureResult(movie1.productionCompaniesIds, movie2.productionCompaniesIds, similarityRate, "PRODUCTION COMPANIES");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForProductionCountries()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.productionCountriesIso, movie2.productionCountriesIso);
		
		//PrintSeparateFeatureResult(movie1.productionCountriesIso, movie2.productionCountriesIso, similarityRate, "PRODUCTION COUNTRIES");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForReleaseDate() //TODO more sophisticated way, e.g. division for decades 60s, 70s, 80s, 90s etc.
	{
		double similarityRate = calculateNumbersSimilarity(movie1.releaseDate, movie2.releaseDate, minReleaseDateInSet, maxReleaseDateInSet);
		
		//PrintSeparateFeatureResult(movie1.releaseDate, movie2.releaseDate, similarityRate, "RELEASE DATE");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForRevenue()
	{
		double similarityRate = calculateNumbersSimilarity(movie1.revenue, movie2.revenue, minRevenue, maxRevenue);
		
		//PrintSeparateFeatureResult(movie1.revenue, movie2.revenue, similarityRate, "REVENUE");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForRuntime()
	{
		double similarityRate = calculateNumbersSimilarity(movie1.runtime, movie2.runtime, minRuntime, maxRuntime);
		
		//PrintSeparateFeatureResult(movie1.runtime, movie2.runtime, similarityRate, "RUNTIME");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForSpokenLanguages()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.spokenLanguagesIso, movie2.spokenLanguagesIso);
		
		//PrintSeparateFeatureResult(movie1.spokenLanguagesIso, movie2.spokenLanguagesIso, similarityRate, "SPOKEN LANGUAGES");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForVoteAverage()
	{
		double similarityRate = calculateNumbersSimilarity(movie1.voteAverage, movie2.voteAverage, minVoteAverage, maxVoteAverage);
		
		//PrintSeparateFeatureResult(movie1.voteAverage, movie2.voteAverage, similarityRate, "VOTE AVERAGE");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForVoteCount()
	{
		double similarityRate = calculateNumbersSimilarity(movie1.voteCount, movie2.voteCount, minVoteCount, maxVoteCount);
		
		//PrintSeparateFeatureResult(movie1.voteCount, movie2.voteCount, similarityRate, "VOTE COUNT");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForCast()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.castIds, movie2.castIds);
		
		//PrintSeparateFeatureResult(movie1.castIds, movie2.castIds, similarityRate, "CAST");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForCostumeMakeUp()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.costumeMakeUpIds, movie2.costumeMakeUpIds);
		
		//PrintSeparateFeatureResult(movie1.costumeMakeUpIds, movie2.costumeMakeUpIds, similarityRate, "COSTUME & MAKE-UP");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForDirecting()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.directingIds, movie2.directingIds);
		
		//PrintSeparateFeatureResult(movie1.directingIds, movie2.directingIds, similarityRate, "DIRECTING");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForCamera()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.cameraIds, movie2.cameraIds);
		
		//PrintSeparateFeatureResult(movie1.cameraIds, movie2.cameraIds, similarityRate, "CAMERA");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForEditing()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.editingIds, movie2.editingIds);
		
		//PrintSeparateFeatureResult(movie1.editingIds, movie2.editingIds, similarityRate, "EDITING");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForProduction()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.productionIds, movie2.productionIds);
		
		//PrintSeparateFeatureResult(movie1.productionIds, movie2.productionIds, similarityRate, "PRODUCTION");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForSound()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.soundIds, movie2.soundIds);
		
		//PrintSeparateFeatureResult(movie1.soundIds, movie2.soundIds, similarityRate, "SOUND");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForWriting()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.writingIds, movie2.writingIds);
		
		//PrintSeparateFeatureResult(movie1.writingIds, movie2.writingIds, similarityRate, "WRITING");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForArt()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.artIds, movie2.artIds);
		
		//PrintSeparateFeatureResult(movie1.artIds, movie2.artIds, similarityRate, "ART");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForCrew()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.crewIds, movie2.crewIds);
		
		//PrintSeparateFeatureResult(movie1.crewIds, movie2.crewIds, similarityRate, "CREW");
		
		return similarityRate;
	}
	
	private double GetSimilarityRateForVisualEffects()
	{
		double similarityRate = calculateTwoSetsSimilarity(movie1.visualEffectsIds, movie2.visualEffectsIds);
		
		//PrintSeparateFeatureResult(movie1.visualEffectsIds, movie2.visualEffectsIds, similarityRate, "VISUAL EFFECTS");
		
		return similarityRate;
	}
	
	//*********** UTILITIES ***********
	
	private double GetMaxBudgetInSet() {

		double maxBudget = Double.MIN_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(maxBudget < Movies.get(i).budget)
			{
				maxBudget = Movies.get(i).budget;
			}
		}
		//System.out.println("Max budget in set = " + maxBudget);
		return maxBudget;
	}
	
	private double GetMinBudgetInSet() {
		
		double minBudget = Double.MAX_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(minBudget > Movies.get(i).budget && Movies.get(i).budget > 0) //to avoid wrong results caused by no information about the feature value. Observed that some movies have budget=0 which is not possible.
			{
				minBudget = Movies.get(i).budget;
			}
		}
		//System.out.println("Min budget in set = " + minBudget);
		return minBudget;
	}
	
	private double GetMaxPopularityInSet() {

		double maxPopularity = Double.MIN_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(maxPopularity < Movies.get(i).popularity)
			{
				maxPopularity = Movies.get(i).popularity;
			}
		}
		//System.out.println("Max popularity in set = " + maxPopularity);
		return maxPopularity;
	}
	
	private double GetMinPopularityInSet() {

		double minPopularity = Double.MAX_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(minPopularity > Movies.get(i).popularity)
			{
				minPopularity = Movies.get(i).popularity;
			}
		}
		//System.out.println("Min popularity in set = " + minPopularity);
		return minPopularity;
	}
	
	private int GetMaxReleaseDateInSet() {

		int maxReleaseDate = Integer.MIN_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(maxReleaseDate < Movies.get(i).releaseDate)
			{
				maxReleaseDate = Movies.get(i).releaseDate;
			}
		}
		//System.out.println("Max release date in set = " + maxReleaseDate);
		return maxReleaseDate;
	}
	
	private int GetMinReleaseDateInSet() {

		int minReleaseDate = Integer.MAX_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(minReleaseDate > Movies.get(i).releaseDate)
			{
				minReleaseDate = Movies.get(i).releaseDate;
			}
		}
		//System.out.println("Min release date in set = " + minReleaseDate);
		return minReleaseDate;
	}
	
	private double GetMaxRevenue() {

		double maxRevenue = Double.MIN_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(maxRevenue < Movies.get(i).revenue)
			{
				maxRevenue = Movies.get(i).revenue;
			}
		}
		//System.out.println("Max revenue in set = " + maxRevenue);
		return maxRevenue;
	}
	
	private double GetMinRevenue() {

		double minRevenue = Double.MAX_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(minRevenue > Movies.get(i).revenue && Movies.get(i).revenue > 0) //to avoid wrong results caused by no information about the feature value. Observed that some movies have revenue=0 which is not possible.
			{
				minRevenue = Movies.get(i).revenue;
			}
		}
		//System.out.println("Min revenue in set = " + minRevenue);
		return minRevenue;
	}
	
	private int GetMaxRuntime() {

		int maxRuntime = Integer.MIN_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(maxRuntime < Movies.get(i).runtime)
			{
				maxRuntime = Movies.get(i).runtime;
			}
		}
		//System.out.println("Max runtime in set = " + maxRuntime);
		return maxRuntime;
	}
	
	private int GetMinRuntime() {

		int minRuntime = Integer.MAX_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(minRuntime > Movies.get(i).runtime)
			{
				minRuntime = Movies.get(i).runtime;
			}
		}
		//System.out.println("Min runtime in set = " + minRuntime);
		return minRuntime;
	}
	
	private double GetMaxVoteAverage() {

		double maxVoteAverage = Double.MIN_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(maxVoteAverage < Movies.get(i).voteAverage)
			{
				maxVoteAverage = Movies.get(i).voteAverage;
			}
		}
		//System.out.println("Max vote average in set = " + maxVoteAverage);
		return maxVoteAverage;
	}
	
	private double GetMinVoteAverage() {

		double minVoteAverage = Double.MAX_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(minVoteAverage > Movies.get(i).voteAverage)
			{
				minVoteAverage = Movies.get(i).voteAverage;
			}
		}
		//System.out.println("Min vote average in set = " + minVoteAverage);
		return minVoteAverage;
	}
	
	private int GetMaxVoteCount() {

		int maxVoteCount = Integer.MIN_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(maxVoteCount < Movies.get(i).voteCount)
			{
				maxVoteCount = Movies.get(i).voteCount;
			}
		}
		//System.out.println("Max vote count in set = " + maxVoteCount);
		return maxVoteCount;
	}
	
	private int GetMinVoteCount() {

		int minVoteCount = Integer.MAX_VALUE;
		
		for(int i=0; i<Movies.size(); i++)
		{
			if(minVoteCount > Movies.get(i).voteCount)
			{
				minVoteCount = Movies.get(i).voteCount;
			}
		}
		//System.out.println("Min vote count in set = " + minVoteCount);
		return minVoteCount;
	}
	
	private double calculateNumbersSimilarity(double x, double y, double min, double max)
	{
		double similarity = 0;
		
		if(x == 0 || y == 0) //after observing the results I have noticed that for some movies budget or revenue were nor provided and the value is equal 0
		{
			similarity = 0.5; //then, having no info about those values, we have to assume that they are half similar
		}
		else
		{
			similarity = (1 - (Math.abs(x - y)/(max - min)));
		}
		return similarity;
	}
	
	private double calculateTwoSetsSimilarity(int[] set1, int[] set2)
	{
		double similarityRate = 0;
		
		int numberOfMatchingElements = 0;
		
		if(set1 != null && set2 != null)
		{
			numberOfMatchingElements = getNumberOfMatchingElements(set1, set2);
			similarityRate = (double)(numberOfMatchingElements * 2) / (set1.length + set2.length);
		}
		else
		{
			similarityRate = 0.5; //having no info about those values, we have to assume that they are half similar
		}
		
		return similarityRate;
	}
	
	private double calculateTwoSetsSimilarity(String[] set1, String[] set2)
	{
		double similarityRate = 0;
		
		int numberOfMatchingElements = 0;
		
		if(set1 != null && set2 != null)
		{
			numberOfMatchingElements = getNumberOfMatchingElements(set1, set2);
			similarityRate = (double)(numberOfMatchingElements * 2) / (set1.length + set2.length);
		}
		else
		{
			similarityRate = 0.5; //having no info about those values, we have to assume that they are half similar
		}
		
		return similarityRate;
	}
	
	private int getNumberOfMatchingElements(int[] set1, int[] set2)
	{
		int numberOfMathingElements = 0;
		
		if(set1 != null && set2 != null)
		{
			for(int i=0; i<set1.length; i++)
			{
				for(int j=0; j<set2.length; j++)
				{
					if(set1[i] == set2[j])
					{
						numberOfMathingElements++;
					}
				}
			}
		}
		return numberOfMathingElements;
	}
	
	private int getNumberOfMatchingElements(String[] set1, String[] set2)
	{
		int numberOfMathingElements = 0;
		
		if(set1 != null && set2 != null)
		{
			for(int i=0; i<set1.length; i++)
			{
				for(int j=0; j<set2.length; j++)
				{
					if(set1[i].equals(set2[j]))
					{
						numberOfMathingElements++;
					}
				}
			}
		}
		return numberOfMathingElements;
	}
}
