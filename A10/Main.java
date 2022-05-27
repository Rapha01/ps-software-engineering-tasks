/**
 * @team Pingert Michael, Sebastian Hattinger
 * @credits pvm30, https://github.com/pvm30
 */
package stateExploration;
import stateExploration.waterJarExploration.*;
import stateExploration.hourGlassExploration.*;

public class Main {
	public static void main(String[] args) {
		
		int capacities[] = {10,5};
		
		//WaterJarExploration ex = new WaterJarExploration(capacities);
		WaterJarExploration ex = new WaterJarExploration(capacities, "noFill");
		//HourGlassExploration ex = new HourGlassExploration(capacities);
		
		System.out.println(ex.getShortestSolution(2).toString());
	}
}
