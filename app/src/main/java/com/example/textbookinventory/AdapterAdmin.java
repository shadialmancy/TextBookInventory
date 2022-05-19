package com.example.textbookinventory;

public class AdapterAdmin {
    public int ID ;
    public String Name ;
    public String Username ;
    public String Password;
    public int Phone;
    public int Salary;
    public String Type;

    AdapterAdmin(String ID,String Name,String Username,String Password,String Phone,String Salary,String Type){
        this.ID=Integer.parseInt(ID);
        this.Name=Name;
        this.Username=Username;
        this.Password=Password;
        this.Phone=Integer.parseInt(Phone);
        this.Salary=Integer.parseInt(Salary);
        this.Type=Type;
    }
}
