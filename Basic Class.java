package com.example.test;


import java.util.*;
class Transaction{
//    private:
   private  int fromBankAccountNo;
   private  int toBankAccountNo;
   private  double transactionAmount;
   Transaction(int from , int to , double amount){
       this.fromBankAccountNo = from ; this.toBankAccountNo = to;
       this.transactionAmount = amount;
   }
   public void display(){
       System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       System.out.println("from:- "+this.fromBankAccountNo);
       System.out.println("to:- "+this.toBankAccountNo);
       System.out.println("amount:- "+this.transactionAmount);
       System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
   }
};
class Client{
   private  int bankAccountNo;
   private  String name;
   private  Transaction transactions[];
   private int transactionNo;
   private  double balance;
   private  int top;
   Client(int accountNo , String S , double taka){
       this.balance  = taka ; this.name = S ; this.bankAccountNo = accountNo ;
       transactions = new Transaction[35]; top = 0 ;
   }
   public int getAccountNo(){
       return this.bankAccountNo;
   }
   public double getBalance(){ return this.balance ; }
   public void setBalance(double amount){
       this.balance = amount;
   }
   public int getAccNo(){ return this.getAccountNo() ; }
   public String getName(){ return this.name ; }
   void printClientInfo(){
       System.out.println("-------------------------");
       System.out.println("AccountNo:- "+bankAccountNo);
       System.out.println("name:- "+name);
       System.out.println("balance:- "+balance);
       System.out.println("transactionNo:- "+transactionNo);
       System.out.println("--------------------------");
   }

   public void adding(Transaction T){
       transactions[top] = T; top++;
   }
};


class Bank{
   private  int maxCapacity;
   private  int numberOfClients;
   private  Client clients[];
   private  int top ;
   Bank(int cap){
       maxCapacity = cap ; numberOfClients = cap;
       clients = new Client[cap];
       top = 0;
   }
   void AddClient(Client C){
       if(top==maxCapacity){ return ; }
       boolean ok = true ;
       C.printClientInfo();
       System.out.println(C.getAccountNo());
       for(int i = 0 ; i < top ; i++){
           if(clients[i].getAccountNo()==C.getAccountNo()){ return  ; }
       }
       clients[top++] = C ;
       return ;
   }
   void printClients(){
       for(Client X : clients){
           X.printClientInfo();
       }
   }
   void makeTransaction(int id1 , int id2 , double amount){
       if(id1== id2 ){
           return;
       }
       int f1 = -1 , f2 = -1 ;
       for(int i = 0 ; i < top ; i++){
           if(clients[i].getAccNo()==id1){
               if(clients[i].getBalance() < amount ){
                   return;
               }
           }
           if(clients[i].getAccNo() == id1 ){ f1 = i ; }
           if(clients[i].getAccNo()==id2){ f2 = i ; }
       }
       if( f1==-1 || f2==-1){
           return;
       }
       Transaction T = new Transaction(id1,id2,amount);
       clients[f1].adding(T);
       clients[f2].adding(T);
       clients[f1].setBalance(clients[f1].getBalance() - amount);
       clients[f2].setBalance(clients[f2].getBalance() + amount );
   }
};


public  class HelloWorld {
   public static void main(String [] args) {
       Bank SonaliBank = new Bank(3);
       Client anik = new Client(1,"Anik Ahmed",5000); ///Create appropriate constructor for creating object
       Client azad = new Client(2,"Abul kalam Azad",5000);
       Client azad2 = new Client(2,"Abul kalam Azad2",5000);
       Client tanvir = new Client(3,"Tanvir Hossain",5000);
       Client anonto = new Client (4,"Anonto Ghosh",5000);
       Client minhaz = new Client (5,"Minhazul Rahman",5000);
       Client sakib = new Client(6,"Sakib Khan",5000);
       SonaliBank.AddClient(anik);
       SonaliBank.AddClient(azad);
       SonaliBank.AddClient(azad2);
       SonaliBank.AddClient(tanvir);
       SonaliBank.AddClient(anonto);
       SonaliBank.AddClient(minhaz);
       SonaliBank.AddClient(sakib);
//        SonaliBank.printClients();
       SonaliBank.makeTransaction(1,2,1500.00);
       SonaliBank.printClients();
   }
}

