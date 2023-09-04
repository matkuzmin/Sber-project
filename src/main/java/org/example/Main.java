package org.example;

import org.example.model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("test.csv");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(System.getProperty("line.separator"));
        List<City> cities = new ArrayList<>();
        while (scanner.hasNext()) {
            City emp = Main.parseCitiData(scanner.next());
            cities.add(emp);
        }
        scanner.close();
        System.out.println(cities.get(0));
        System.out.println(cities.get(1));

        System.out.println(sortNameCity(cities));
        System.out.println(sortDistrictAndNameCity(cities));
        System.out.println(findMaxPopulationCity(cities));
        countCityInRegion(cities);

    }


    private static City parseCitiData(String str) {
        Scanner sc = new Scanner(str);
        sc.useDelimiter(";");
        City city = new City();
        sc.nextInt();
        city.setName(sc.next());
        city.setRegion(sc.next());
        city.setDistrict(sc.next());
        city.setPopulation(sc.nextLong());
        city.setFoundation(sc.next().replace("\r", ""));
        sc.close();
        return city;
    }


    private static List<City> sortNameCity(List<City> cities) {
        Comparator<City> cityComparator = new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
        Collections.sort(cities, cityComparator);
        return cities;
    }
    private static List<City> sortDistrictAndNameCity(List<City> cities) {
       return cities.stream().sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
               .collect(Collectors.toList());
    }
    private static String findMaxPopulationCity(List<City> cities) {
        var index = -1;
        long countPopulation = -1;
       City[] citiesArray = cities.toArray(new City[0]);
       cities.toArray(citiesArray);
        for (int i = 0; i < citiesArray.length ; i++) {
            if (citiesArray[i].getPopulation()>countPopulation)
                countPopulation=citiesArray[i].getPopulation();
            index = i;
        }

        return "["+index+"] = " + countPopulation ;
    }
    private static void countCityInRegion(List<City> cities) {
        Map<String,Integer> cityInRegion = new HashMap<>();
        for (City city:cities){
           String region = city.getRegion();
           cityInRegion.put(region, cityInRegion.getOrDefault(region,0)+1);
        }
            for (Map.Entry<String, Integer> entry : cityInRegion.entrySet()) {

                System.out.println(  entry.getKey() + " - " + entry.getValue());

            }
    }
}