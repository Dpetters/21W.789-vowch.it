package it.vowch.android;

import java.lang.Math;

import android.util.Log;
public class Scoring {
	final static double beta = 102.89844;
	final static double alpha = 10.782116;
	final static double gamma = -48.9615489590976881;
	
	public static double getLevel(double reputationPoints){
		Double log = Math.log(reputationPoints + beta);
		Log.d("Dmitrij", log.toString());
		Double mult = alpha*log;
		Log.d("Dmitrij", mult.toString());
		return mult + gamma;
	}

	public static double getPoints(double level){
		double power = (level-gamma)/alpha;
		return Math.pow(Math.E, power) - beta;
		
	}
}
