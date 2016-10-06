package com.codekul.ormdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekul.database.dao.DaoFactory;
import com.codekul.database.domain.Car;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Car car = new Car();
        car.setCarId(1l);
        car.setCarName("Android");
        car.setCarNum("Mh-10 5125");
        car.setColor(456);
        car.setCountry("India");
        car.setDate(System.currentTimeMillis());
        car.setIsSecond(true);
        car.setManufacturer("Android");
        car.setMilage(75.123f);
        car.setModel(7);
        car.setPrice(25_000d);

        DaoFactory.getInstance(this).getCarDao().insert(car);
        List<Car> cars = DaoFactory.getInstance(this).getCarDao().loadAll();
    }
}
