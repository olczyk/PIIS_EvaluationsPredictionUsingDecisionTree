import java.io.IOException;
import java.util.ArrayList;


public class Main {

	static final String moviesFileName = "records.csv";
	static final String evaluationsFileName = "train.csv";
	static final String missingEvaluationsFileName = "task.csv";
	static final boolean limitedFeatures = true;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Utilities util = new Utilities();
		ArrayList<Movie> Movies = util.GetMoviesFromFile(moviesFileName);
		ArrayList<Evaluation> Evaluations = util.GetEvaluations(evaluationsFileName);

		ArrayList<Evaluation> MissingEvaluations = util.GetMissingEvaluations(missingEvaluationsFileName);
		
		DecisionTree dt = new DecisionTree(Movies, Evaluations, MissingEvaluations, limitedFeatures);
		ArrayList<Evaluation> PredictedEvaluations = dt.PredictEvaluations();
		
		util.SavePredictedEvaluationsToFile("result8.csv", PredictedEvaluations);
	}
}