package com.example;
import java.util.*;
class shopItem{
    private String name ;
    private double BuyingPricePerUnit;
    private double SellingPricePerUnit;
    shopItem(String S , double a , double b){
        this.name = S ; this.BuyingPricePerUnit = a ; this.SellingPricePerUnit = b;
    }
    public String getName(){ return name ; }
    public double getBuyingPricePerUnit(){ return BuyingPricePerUnit ; }
    public  double getSellingPricePerUnit(){ return SellingPricePerUnit ; }
}

class Apple extends shopItem{
    private int color;
    Apple(){
        super("Green Apple",3,5); this.color = 0;
    }
    Apple(int col){
        super("Red Apple",3,5); this.color = 1;
    }
}

class Orange extends shopItem{
    Orange(){
        super("Orange",3,6);
    }
}

class Strawberry extends shopItem{
    private double packedBuying ;
    private double packedSelling;
    private int condition;
    Strawberry() {
            super("Canned Strawberry", 8, 10); this.condition = 0 ; /// 0->Canned Strawberry;
    }
    Strawberry(boolean f){
        super("Packed Strawberry",5,8);this.condition = 1 ; ///1->Packed Condition ;
    }
}
class LogEntry{
    Date timeStamp;
    String name ;
    int amount ;
    String soldOrBought;
}

interface Shop{
    void buy(int type, int amount);
    void sell(int type, int amount);
    ArrayList<LogEntry> getLog();
    ArrayList<shopItem> getInventory();
    double getBalance();
}

class FruitShop implements Shop{
    private int number , capacity  ;
    private double balance ;
    ArrayList<LogEntry> le;
    ArrayList<shopItem> si;
    private int Inventory[];
    FruitShop(int cap , double bal){
        this.number = 0 ; this.capacity = cap ; this.balance = bal;
        Inventory = new int[10] ; le = new ArrayList<>(); si = new ArrayList<>();
    }
    int getTotal(){
        int sum = 0;
        for(int i =1 ; i <= 5; i++){
            sum+=Inventory[ i ];
        }
        return  sum;
    }
    String getName(int type){
        if(type==1){ return  "Green Apple"; }
        else if(type==2){ return  "Red Apple"; }
        else if(type==3){ return  "Orange"; }
        else if(type==4){ return  "Canned Strawberry"; }
        else if(type==5){ return "Packed Strawberry" ; }
        return  "Default";
    }
    shopItem getItem(int type){
        if(type==1){ return  new Apple() ; }
        else if(type==2){ return  new Apple(0); }
        else if(type==3){ return  new Orange(); }
        else if(type==4){ return  new Strawberry() ; }
        else if(type==5){ return new Strawberry(true) ; }
        return null;
    }
     public void buy(int type, int amount){
        if( this.number + amount > this.capacity ){
            System.out.println("Invalid Storage");
            return;
        }
        shopItem S = getItem(type);
        if( (amount * S.getBuyingPricePerUnit() ) > this.balance ){
            System.out.println("Not Enough Balance to Buy");
            return;
        }
        this.number+=amount;
        LogEntry temp = new LogEntry() ;
        temp.name = getName(type);
        temp.timeStamp = new Date();  temp.amount = amount ;
        temp.soldOrBought = "Bought";
        le.add(temp); Inventory[ type ]+=amount;
        this.balance-=(amount * S.getBuyingPricePerUnit());
        return;
    }
    public void sell(int type, int amount){
        if( Inventory[ type ] < amount ){
            System.out.println("Not Enough Amount to Sell");
            return;
        }
        LogEntry temp = new LogEntry() ;
        this.number-=amount;
        temp.name = getName(type);
        temp.timeStamp = new Date();  temp.amount = amount ;
        temp.soldOrBought = "Sold"; le.add(temp);
        shopItem S = getItem(type); Inventory[ type ]-=amount ;
        this.balance+=(amount * S.getSellingPricePerUnit());
    }
    public ArrayList<LogEntry> getLog(){ return le; }
    public ArrayList<shopItem> getInventory(){
        for(int i = 1; i <= 5; i++){
            int temp = Inventory[ i ];
            for(int j =1 ; j <= temp ; j++){
                si.add(getItem(i));
            }
        }
        return si;
    }
    public double getBalance(){ return  this.balance; }
}

public class Testing {
    public static void main(String [] args){
//        System.out.println(S.getName()+" , "+S.getBuyingPricePerUnit()+" , "+S.getSellingPricePerUnit());
        FruitShop fruitShop= new FruitShop(20, 60);
        System.out.println(fruitShop.getBalance());
        fruitShop.buy(1, 20);
        System.out.println(fruitShop.getBalance());
        fruitShop.sell(1, 5);
        System.out.println(fruitShop.getBalance());
        fruitShop.buy(4, 5);
        fruitShop.buy(4, 10);
        fruitShop.sell(4, 5);
        fruitShop.sell(1, 15);
        fruitShop.buy(3, 10);
        fruitShop.buy(4, 2);
        fruitShop.buy(5, 3);
        fruitShop.sell(4,1);
        System.out.println(fruitShop.getBalance());
//        System.out.println("-------------------------------------------------------------------------------------");
        ArrayList<LogEntry> L = fruitShop.getLog();
        System.out.println("Generated Logs.........");
        System.out.println("\t\tTime Stamp"+"\t\t\t\t"+"Name"+"\t"+"Amount"+"\t"+"BoughtOrSold");
//        System.out.println(L.size()+"kkkkkkkkkkkkkk");
        for(LogEntry l : L){
            System.out.println(l.timeStamp+"  "+l.name+"  "+l.amount+"  "+l.soldOrBought);
        }
        System.out.println("Items in inventory.........");
        ArrayList<shopItem> S  = fruitShop.getInventory();
        for(shopItem s : S ){
            System.out.println(s.getName()+"\t"+s.getBuyingPricePerUnit()+"\t"+s.getSellingPricePerUnit());
        }
    }
}
