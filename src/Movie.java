
public class Movie {
	
	int id;
	int collectionId;
    double budget;
    int[] genresIds;
    double popularity;
    int[] productionCompaniesIds;
    String[] productionCountriesIso;
    int releaseDate;
    double revenue;
    int runtime;
    String[] spokenLanguagesIso;
    double voteAverage;
    int voteCount;
    int[] castIds;
    int[] costumeMakeUpIds;
    int[] directingIds;
    int[] cameraIds;
    int[] editingIds;
    int[] productionIds;
    int[] soundIds;
    int[] writingIds;
    int[] artIds;
    int[] crewIds;
    int[] visualEffectsIds;
    
    Movie()
    {
    	
    }
    
    Movie(int id, int collectionId, double budget, int[] genresIds, double popularity, int[] productionCompaniesIds, String[] productionCountriesIso, int releaseDate, double revenue, int runtime, String[] spokenLanguagesIso, double voteAverage, int voteCount, int[] castIds, int[] costumeMakeUpIds, int[] directingIds, int[] cameraIds, int[] editingIds, int[] productionIds, int[] soundIds, int[] writingIds, int[] artIds, int[] crewIds, int[] visualEffectsIds)
    {
    	this.id = id;
    	this.collectionId = collectionId;
    	this.budget = budget;
    	this.genresIds = genresIds;
    	this.popularity = popularity;
    	this.productionCompaniesIds = productionCompaniesIds;
    	this.productionCountriesIso = productionCountriesIso;
    	this.releaseDate = releaseDate;
    	this.revenue = revenue;
    	this.runtime = runtime;
    	this.spokenLanguagesIso = spokenLanguagesIso;
    	this.voteAverage = voteAverage;
    	this.voteCount = voteCount;
    	this.castIds = castIds;
    	this.costumeMakeUpIds = costumeMakeUpIds;
    	this.directingIds = directingIds;
    	this.cameraIds = cameraIds;
    	this.editingIds = editingIds;
    	this.productionIds = productionIds;
    	this.soundIds = soundIds;
    	this.writingIds = writingIds;
    	this.artIds = artIds;
    	this.crewIds = crewIds;
    	this.visualEffectsIds = visualEffectsIds;
    }
}
