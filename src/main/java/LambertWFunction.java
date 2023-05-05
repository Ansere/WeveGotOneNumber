public class LambertWFunction {
    //W(x) is the inverse of f(x) = x*e^x
    //W(x*e^x) = x
    //W(x)*e^(W(x)) = x
    //code for finding the W of a number using Newton's method
    public static void main(String[]args) {
        System.out.println(Math.pow(Math.E, W(Math.log(71 * 71/Math.sqrt(2 * Math.PI))/Math.E) + 1) - 1/2.);
    }

    public static int getBoundedFactorial(int upperBound, int lowerBound){
        double invFact = (Math.pow(Math.E, W(Math.log(upperBound/Math.sqrt(2 * Math.PI))/Math.E) + 1) - 1/2.);
        double invFactl = (Math.pow(Math.E, W(Math.log(lowerBound/Math.sqrt(2 * Math.PI))/Math.E) + 1) - 1/2.);
        if (Math.floor(invFact) >= Math.ceil(invFactl)){
            return (int) Math.floor(invFactl);
        } else {
            return 0;
        }
    }

    public static double normalFunction(double x) {
        //This is the regular function which Lambert W is the inverse of.
        //It is just f(x) = x*e^x
        return x * (Math.exp(x));
    }

    public static double derivativeOfFunction(double x) {
        //This is the derivative of the function in the previous method
        //f(x) = (x+1)*e^x
        return (x + 1) * Math.exp(x);
    }

    public static double W(double x) {
        //This is the method which finds the W
        int iter = 0;
        double b;
        if (x < 0)
            b = x * Math.exp(1 - Math.sqrt(2 * (Math.E * x + 1)));
        else if (x < Math.E)
            b = x / Math.E;
        else
            b = Math.log(x);
        //These are the first values of our Newton Iteration
        while (Math.abs(normalFunction(b) - x) > 0.0001) {
            //Checks whether we are getting an incorrect value and continues the loop if yes
            b -= (normalFunction(b) - x) / derivativeOfFunction(b);//System.out.println((b));
            //Newton iteration trying to get the closer value
            iter++;
            if (iter > 1000) {
                return b;
            }
        }
        return b;
    }
}
