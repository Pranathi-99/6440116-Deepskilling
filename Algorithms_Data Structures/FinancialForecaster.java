/*
Exercise 7: Financial Forecasting

FinancialForecaster: A concise tool for forecasting financial growth using compound interest.
Demonstrates basic data structures (List) and algorithms (iterative calculation) for clear, accurate results.
*/

import java.util.ArrayList;
import java.util.List;

public class FinancialForecaster {
    public List<Double> forecastCompoundInterest(double principal, double annualRate, int years) {
        List<Double> yearlyBalances = new ArrayList<>();
        double currentBalance = principal;

        System.out.printf("Forecasting %d years for Rs. %.2f at %.2f%% annual rate:\n",years, principal, annualRate * 100);

        for (int year = 1; year <= years; year++) {
            currentBalance *= (1 + annualRate);
            yearlyBalances.add(currentBalance);
            System.out.printf("Year %2d: Rs. %,.2f\n", year, currentBalance);
        }
        return yearlyBalances;
    }

    public static void main(String[] args) {
        FinancialForecaster forecaster = new FinancialForecaster();
        List<Double> results1 = forecaster.forecastCompoundInterest(1000.0, 0.05, 5);
        if (!results1.isEmpty()) {
            System.out.printf("Final Balance: Rs. %,.2f\n\n", results1.get(results1.size() - 1));
        }

    }
}

/*
OUTPUT:
Forecasting 5 years for Rs. 1000.00 at 5.00% annual rate:
Year  1: Rs. 1,050.00
Year  2: Rs. 1,102.50
Year  3: Rs. 1,157.63
Year  4: Rs. 1,215.51
Year  5: Rs. 1,276.28
Final Balance: Rs. 1,276.28
*/