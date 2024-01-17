package com.example.packyourbag.Data;

import android.app.Application;
import android.content.Context;

import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Database.RoomDB;
import com.example.packyourbag.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {
    RoomDB database;
    String category;
    Context context;

    public static final String LAST_VERSION="LAST_VERSION";
    public static final int NEW_VERSION = 1;

    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }

    public List<Items> getBasicData(){
        category = "Basic Needs";
        List<Items> basicItem = new ArrayList<>();
        basicItem.add(new Items("Visa", category, false));
        basicItem.add(new Items("Passport", category, false));
        basicItem.add(new Items("Tickets", category, false));
        basicItem.add(new Items("Wallet", category, false));
        basicItem.add(new Items("Driving Licence", category, false));
        basicItem.add(new Items("Currency", category, false));
        basicItem.add(new Items("House Key", category, false));
        basicItem.add(new Items("Book", category, false));
        basicItem.add(new Items("Travel Pillow", category, false));
        basicItem.add(new Items("Eye Patch", category, false));
        basicItem.add(new Items("Umbrella", category, false));
        basicItem.add(new Items("Note Book", category, false));

        return basicItem;
    }

    public List<Items> getPersonalCareData(){
        String []data = {"Tooth-brush", "Tooth-paste", "Floss", "Mouthwash", "Shaving Cream", "Razor Blade", "Soap", "Fiber", "Shampoo",
        "Hair Conditioner", "Brush", "Comb", "Hair Dryer"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE,data);
    }

    public List<Items> getClothingData(){
        String []data = {"T-Shirts", "Casual Dress", "Pants", "Trousers", "Jeans", "Shorts", "Shirt", "Jacket", "Shoes",
                "Scarf", "Sports Wear", "Coat", "Rain Coat"};
        return prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE,data);
    }

    public List<Items> getBabyNeedsData(){
        String []data = {"Outfit", "Baby Socks", "Baby Hat", "Baby Pajamas", "Baby Blanket", "Baby Bottles", "Wet Wipes", "Baby Food", "Baby Cotton",
                "Snapsuit", "Baby Lotion", "Baby Towel", "Diaper"};
        return prepareItemsList(MyConstants.BABY_NEEDS_CAMEL_CASE,data);
    }

    public List<Items> getHeathData(){
        String []data = {"Aspirine", "vitamins", "Napa", "Lens Solution", "Eye Drop", "Spray",
                "Bandage", "Burn Lotion", "First Aid Kit", "Pain Killer"};
        return prepareItemsList(MyConstants.HEALTH_CAMEL_CASE,data);
    }

    public List<Items> getTechnologyData(){
        String []data = {"Phone", "Tablet", "Laptop", "Earphones", "Ipod", "Phone Charger", "Laptop Charger", "Mouse", "Keyboard",
                "Audio Gear", "Cable", "Cover", "Power Bank"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE,data);
    }



    public List<Items> prepareItemsList(String category, String []data){
        List<String> list = Arrays.asList(data);
        List<Items> dataList = new ArrayList<>();
        dataList.clear();

        for(int i=0 ; i< list.size() ; i++){
            dataList.add(new Items(list.get(i), category, false));
        }
        return dataList;
    }
    public List<List<Items>> getAllData(){
        List<List<Items>> listOfAllItems = new ArrayList<>();
        listOfAllItems.clear();;
        listOfAllItems.add(getBasicData());
        listOfAllItems.add(getPersonalCareData());
        listOfAllItems.add(getClothingData());
        listOfAllItems.add(getBabyNeedsData());
        listOfAllItems.add(getHeathData());
        listOfAllItems.add(getTechnologyData());
        return listOfAllItems;
    }

    public void persistAllData(){
        List<List<Items>> listOfAllItems = getAllData();
        for(List<Items>list: listOfAllItems){
            for(Items items:list){
                database.mainDao().saveItem(items);
            }
        }
        System.out.println("Data added");
    }
}
