package com.example.omur.contacs;

/**
 * Created by omur on 18.04.2016.
 */
public class PersonalInfo {

    private String name ;
    private String no ;
    private int resim ;

    public PersonalInfo(String name ,String no ,int resim) {
        super();
        this.resim = resim;
        this.name = name;
        this.no = no;
    }

    public String getName()
    {
        return name ;
    }
    public void setName(String name)
    {
        this.name=name ;
    }

    public String getNo()
    {
        return no ;
    }
    public void setNo(String no)
    {
        this.no=no ;
    }

    public int getResim()
    {
        return resim ;
    }
    public void setResim(int resim)
    {
        this.resim=resim ;
    }


}
