public class Polynomial {
    private double[] coefficients;
    private int[] exponents;
    public Polynomial() {
        this.coefficients = new double[]{0};
	this.exponents = new int []{0};
    }
    public Polynomial(double[] coefficients, int []exponents) {
        this.coefficients = new double[coefficients.length];
	this.exponents = new int[exponents.length];
	if(exponents.length != coefficients.length) { 
	    return;	
	}
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
	    this.exponents[i] = exponents[i];
        }
    }
    public Polynomial add(Polynomial other) {
        Map<Integer, Double> terms = new HashMap<>();
        for (int i = 0; i < coefficients.length; i++) {
            terms.put(exponents[i], terms.getOrDefault(exponents[i], 0.0) + coefficients[i]);
        }
        for (int i = 0; i < other.coefficients.length; i++) {
            terms.put(other.exponents[i], terms.getOrDefault(other.exponents[i], 0.0) + other.coefficients[i]);
        }
        return fromMap(terms);
    }
    public Polynomial multiply(Polynomial other) {
        Map<Integer, Double> terms = new HashMap<>();
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                int e = exponents[i] + other.exponents[j];
                double c = coefficients[i] * other.coefficients[j];
                terms.put(e, terms.getOrDefault(exp, 0.0) + c);
            }
        }
        return fromMap(terms);
    }
    private Polynomial fromMap(Map<Integer, Double> terms) {
    	double[] c = new double[terms.size()];
    	int[] e = new int[terms.size()];
    	int k = 0;
    	for (int h : terms.keySet()) {
    	    c[k] = terms.get(h);
   	    e[k] = h;
            k++;
    	}
    	return new Polynomial(c, e);
    }	
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}