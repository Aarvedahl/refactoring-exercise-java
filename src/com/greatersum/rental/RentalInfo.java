package com.greatersum.rental;

import java.math.BigDecimal;
import java.util.HashMap;

public class RentalInfo {


    public String statement(Customer customer) {
        HashMap<String, Movie> movies = getMovieInfo();
        RentalAmount amount = new RentalAmount(0, "Rental Record for " + customer.getName() + "\n", BigDecimal.valueOf(0));

        RentalAmount rentalAmount = customerRentals(customer, movies, amount);
        return rentalAmount.getResult();
    }

    private RentalAmount customerRentals(Customer customer, HashMap<String, Movie> movies, RentalAmount rentalAmount) {
        for (MovieRental rental : customer.getRentals()) {
            Movie movie = movies.get(rental.getMovieId());

            // determine amount for each movie
            BigDecimal thisAmount = calculateAmount(movie.getCode(), rental.getDays());

            //add frequent renter points
            rentalAmount.incRenterPoints();
            // add bonus for a two day new release rental
            if (movie.getCode() == "new" && rental.getDays() > 2) rentalAmount.incRenterPoints();

            //print figures for this rental
            rentalAmount.addToResult("\t" + movie.getTitle() + "\t" + thisAmount + "\n");
            rentalAmount.addTotalAmount(thisAmount);
        }
        rentalAmount.addToResult("Amount owed is " + rentalAmount.getTotalAmount() + "\n");
        rentalAmount.addToResult("You earned " + rentalAmount.getFrequentRenterPoints() + " frequent renter points\n");
        return rentalAmount;
    }

    private BigDecimal calculateAmount(String movieCode, int days) {
        HashMap<String, BigDecimal> map = new HashMap<>();
        map.put("regular", BigDecimal.valueOf(2));
        map.put("new", BigDecimal.valueOf(days * 3));
        map.put("childrens", BigDecimal.valueOf(1.5));

        BigDecimal thisAmount = map.get(movieCode);
        if (movieCode.equals("regular") && days > 2) {
            thisAmount = BigDecimal.valueOf((days - 2) * 1.5).add(thisAmount);
        } else if (movieCode.equals("childrens") && days > 3) {
            thisAmount = BigDecimal.valueOf((days - 3) * 1.5).add(thisAmount);
        }
        return thisAmount;
    }

    private HashMap<String, Movie> getMovieInfo() {
        HashMap<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("Ran", "regular"));
        movies.put("F002", new Movie("Trois Couleurs: Bleu", "regular"));
        movies.put("F003", new Movie("Cars 2", "childrens"));
        movies.put("F004", new Movie("Latest Hit Release", "new"));
        return movies;
    }
}
