import java.util.Scanner;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

enum SeriesCategory{
    ARITHMETIC("Arithmetic series"),
    GEOMETRIC("Geometric series"),
    TAYLOR("Taylor series"),
    FIBONACCI("Ficonacci sequence");
    
    private final String description;
    
    SeriesCategory(String d){
        description = d;
    }
    
    public String getDescription(){
        return description;
    }
    
}

class Start{
    public static final Scanner input = new Scanner(System.in);
    public static HashMap<Integer,SeriesCategory> serieses = new HashMap<>();
    public static SeriesCategory cat;
    public static double sum;
    public static int a=0;
    public static int b=0;
    public static double c=0;
    public static int temp;

    public static void main(String[] args){
        System.out.println("Welcome to the sum calculator!");
        System.out.println("How many terms would you like to consider");
        temp=intInput(0);
        for (int i = 1; i <= SeriesCategory.values().length; i++) {
            serieses.put(i, SeriesCategory.values()[i - 1]);
        }
        cat =seriesSelector();
        saveSeriesSum(cat, temp);
        
        
    }

    public static void saveSeriesSum(SeriesCategory series, int limit){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("series_sums.txt"))){
            switch(series){
                case ARITHMETIC:
                    System.out.println("Please enter the first term for your arithmetic series");
                    a = intInput(0);
                    System.out.println("Please enter the common difference between each term");
                    b = intInput(0);
                    writer.write("The sum of "+limit+" terms of the "+series.getDescription()+" add up to " +SeriesSumCalculator.sumArithmetic(limit, a ,b) +" with a starting value of "+a+" and a common difference of "+b);
                    break;
                case GEOMETRIC:
                    System.out.println("Please enter the first term for your geometric series");
                    a= intInput(0);
                    System.out.println("Please enter the common ratio between each term in an integer value");
                    b=intInput(0);
                    writer.write("The sum of "+limit+" terms of the "+series.getDescription()+" add up to " +SeriesSumCalculator.sumGeometric(limit, a ,b) +" with a starting value of "+a+" and a ratio of "+b+" between terms");
                    break;
                case FIBONACCI:
                    System.out.println("Enter the first term for your fibonacci sequence");
                    a = intInput(0);
                    System.out.println("Enter the second term for your fibonacci squence");
                    b = intInput(0);
                    writer.write("The sum of "+limit+" terms of the "+series.getDescription()+" add up to " +SeriesSumCalculator.sumFibonacci(limit, a ,b) +" the first value is "+a+" and the second value is "+b);
                    break;
                case TAYLOR:
                    System.out.println("Enter the value of x for the taylor series (e^x)");
                    c= dubInput();
                    writer.write("The sum of "+limit+" terms of the "+series.getDescription()+" add up to " +SeriesSumCalculator.sumTaylor(limit, c) +" with an x value of "+c);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SeriesCategory seriesSelector(){
        System.out.println("What series would you like to calculate the sum of?");
        System.out.println(serieses.toString());
        switch(intInput(4)){
            case 1:
                return SeriesCategory.ARITHMETIC;
            case 2:
                return SeriesCategory.GEOMETRIC;
            case 3:
                return SeriesCategory.TAYLOR;
            case 4:
                return SeriesCategory.FIBONACCI;
            default:
                System.out.println("???");
                return seriesSelector();
        }

        
    }

    public static int intInput(int r){
        if(!input.hasNextInt()){ 
            input.next();
            System.out.println("Invalid input");
            return intInput(r);
        }
        int num=input.nextInt();
        if(r!=0 && num>r||num<=0){
            System.out.println("Invalid option");
            return intInput(r);
        } 
        return num; 
    }
    
    public static double dubInput(){
        if(!input.hasNextDouble()){ 
            input.next();
            System.out.println("Invalid input");
            return dubInput();
        }
        return input.nextDouble(); 
    }
}

class SeriesSumCalculator{
    
    public static int sumArithmetic(int n, int a, int d) {
        if (n == 1) {
            return a;
        }
        return a + (n - 1) * d + sumArithmetic(n - 1, a, d);
    }
    
     public static int sumGeometric(int n, int a, int r) {
        if (n == 1) {
            return a;
        }
        return a * (int) Math.pow(r, n - 1) + sumGeometric(n - 1, a, r);
    }
    
     public static int sumFibonacci(int n, int a, int b) {
        if (n == 1) {
            return a;
        }
        if (n == 2) {
            return a + b;
        }
        return sumFibonacci(n - 1, a, b) + sumFibonacci(n - 2, a, b);
    }
    
    public static double sumTaylor(int n, double x) {
        if (n == 0) {
            return 1;
        }
        return Math.pow(x, n-1) / factorial(n-1) + sumTaylor(n-1, x);
    }

    private static double factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }
}
