package ru.merkulyevsasha.industrytest.repositories.http;

import java.util.ArrayList;
import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class HttpDataSourceImpl implements HttpDataSource {

    @Override
    public List<Building> getBuildings() {
        List<Building> builds = new ArrayList<>();

        builds.add(new Building(1, "Старый город", "Алекс", 10, getFlats1()));
        builds.add(new Building(2, "Кремлевские звезды", "Александр", 5, getFlats2()));
        builds.add(new Building(3, "Новый Оккервиль", "Авангард", 11, getFlats3()));
        builds.add(new Building(4, "Приневский", "Центр Плюс", 17, getFlats4()));
        builds.add(new Building(5, "Петр Великий и Екатерина Великая", "Hamlet", 7, getFlats5()));
        builds.add(new Building(6, "Кантемировский", "Скандинавия", 9, getFlats6()));
        builds.add(new Building(7, "Времена года", "Северное сияние", 11, getFlats7()));
        builds.add(new Building(8, "Тридевяткино царство", "Итака", 15, getFlats8()));

        return builds;
    }

    private List<Flat> getFlats1() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(1, 1, 1, 50));
        flats.add(new Flat(2, 1, 1, 100));
        flats.add(new Flat(3, 1, 2, 55));
        flats.add(new Flat(4, 1, 3, 80));

        return flats;
    }

    private List<Flat> getFlats2() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat( 5, 2, 1, 50));
        flats.add(new Flat( 6, 2, 1, 100));
        flats.add(new Flat( 7, 2, 2, 55));
        flats.add(new Flat( 8, 2, 3, 80));
        return flats;
    }

    private List<Flat> getFlats3() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat( 9, 3, 1, 50));
        flats.add(new Flat(10, 3, 1, 100));
        flats.add(new Flat(11, 3, 2, 55));
        flats.add(new Flat(12, 3, 3, 80));
        return flats;
    }

    private List<Flat> getFlats4() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(13, 4, 1, 50));
        flats.add(new Flat(14, 4, 1, 100));
        flats.add(new Flat(16, 4, 2, 55));
        flats.add(new Flat(17, 4, 3, 80));
        return flats;
    }

    private List<Flat> getFlats5() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(18, 5, 1, 50));
        flats.add(new Flat(19, 5, 1, 100));
        flats.add(new Flat(20, 5, 2, 55));
        flats.add(new Flat(21, 5, 3, 80));
        return flats;
    }

    private List<Flat> getFlats6() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(22, 6, 1, 50));
        flats.add(new Flat(23, 6, 1, 100));
        flats.add(new Flat(24, 6, 2, 55));
        flats.add(new Flat(25, 6, 3, 80));
        return flats;
    }

    private List<Flat> getFlats7() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(26, 7, 1, 50));
        flats.add(new Flat(27, 7, 1, 100));
        flats.add(new Flat(28, 7, 2, 55));
        flats.add(new Flat(29, 7, 3, 80));
        return flats;
    }

    private List<Flat> getFlats8() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(30, 8, 1, 50));
        flats.add(new Flat(31, 8, 1, 100));
        flats.add(new Flat(32, 8, 2, 55));
        flats.add(new Flat(33, 8, 3, 80));

        return flats;
    }
}
