import java.util.Comparator;

public class BestMatch{

	double similarityRate;
	String evaluation;
	int movieId;
	
	BestMatch(double similarityRate, String evaluation, int movieId)
	{
		this.similarityRate = similarityRate;
		this.evaluation = evaluation;
		this.movieId = movieId;
	}
	
	public static Comparator<BestMatch> SimilarityRateComparator = new Comparator<BestMatch>()
	{
		public int compare(BestMatch bestMatch1, BestMatch bestMatch2) 
		{
			double similarityRate1 = bestMatch1.similarityRate;
			double similarityRate2 = bestMatch2.similarityRate;
			
			return Double.compare(similarityRate1, similarityRate2);
		}
	};
}