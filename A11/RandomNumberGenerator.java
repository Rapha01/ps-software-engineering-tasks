
package a11;



import java.util.HashMap;


/**
 * @team Pingert Michael, Sebastian Hattinger
 * 
 * @param <T>
 * 		a Generic RandomGererator for '@' BonusCells or 'Keys' with a
 * 		adjustable distribution
 *  
 * @return
 * 		The requested Elementzs 
 */
	public class RandomNumberGenerator<T> {

	    private HashMap<T , Double> distribution;
	    private double distSum;

	    public RandomNumberGenerator() {
	        distribution = new HashMap<>();
	    }

	    public void addNumber(T value, double distribution) {
	        if (this.distribution.get(value) != null) {
	            distSum -= this.distribution.get(value);
	        }
	        this.distribution.put(value, distribution);
	        distSum += distribution;
	    }

	    public T getDistributedRandomNumber() {
	        double rand = Math.random();
	        double ratio = 1.0f / distSum;
	        double tempDist = 0;
	        for (T i : distribution.keySet()) {
	            tempDist += distribution.get(i);
	            if (rand / ratio <= tempDist) {
	                return i;
	            }
	        }
	        return null;
	    }

	}