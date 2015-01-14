package com.danimaniarqsoft.report.chart.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.junit.runners.Parameterized.Parameters;

public abstract class AbstractPoiTest extends AbstractParameterizedTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { createMapListForTest() } });
    }

    public static List<Map<String, Object>> createMapListForTest() {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        int maxColumn = 500;
        int maxRow = 10;
        for (int row = 0; row < maxRow; row++) {
            Map<String, Object> rowMap = new TreeMap<String, Object>();
            for (int column = 0; column < maxColumn; column++) {
                rowMap.put(column + "", genRandomDouble());
            }
            mapList.add(rowMap);
        }
        return mapList;
    }

    private static double genRandomDouble() {
        Random r = new Random();
        double rangeMin = 1;
        double rangeMax = 101;
        return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }
}
