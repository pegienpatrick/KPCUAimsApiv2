package org.aims.kpcu;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsManager {
    public static String balance()
    {
        String balance="0.0";
        try
        {
            String param="?ApiKey=o1EmlvRYVqAUY80avGC/LxhsrXUNj4+AHAc9ai1M6Yw=&ClientId=6cd0be5e-6753-4db4-9603-eceac7fdaaba";
            URL url=new URL("https://api.onfonmedia.co.ke/v1/sms/Balance"+param);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();

//String basicAuth="Basic " + new String(Base64.getEncoder().encode(user.getBytes()));
            con.setRequestProperty("AccessKey","0nua0g4gffvbs0k4ZRHlnXLK6JWcVJYl");
            con.setRequestProperty("Content-Type","application/json");

//con.setRequestProperty ("Authorization", basicAuth);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setConnectTimeout(10000);

            InputStream in=con.getInputStream();

            byte[] me=in.readAllBytes();
            balance=new String(me);
            balance=balance.split("KSh")[1].split("\"")[0];

            in.close();
//url.close();
        }catch(Exception hhgghd)
        {
            balance=hhgghd+" ";
        }

        return balance;

    }


    public static boolean sendSms(String phone,String msg)
    {
        boolean sn=false;
        String balance="0.0";
        try
        {
            //String param="?ApiKey=5suvbD+/UG0miMZHs06H54m4g/+pdEZweZOkBTZG+mE=&ClientId=64ae495f-3501-403a-9385-d73a0b82e635";
            URL url=new URL("https://api.onfonmedia.co.ke/v1/sms/SendBulkSMS");
            HttpURLConnection con=(HttpURLConnection)url.openConnection();

//String basicAuth="Basic " + new String(Base64.getEncoder().encode(user.getBytes()));
            con.setRequestProperty("AccessKey","0nua0g4gffvbs0k4ZRHlnXLK6JWcVJYl");
            con.setRequestProperty("Content-Type","application/json");

//con.setRequestProperty ("Authorization", basicAuth);
            con.setRequestMethod("POST");

            con.setRequestProperty("Accept", "application/json");

            String headers="{ \"SenderId\": \""+Sender()+"\",\"MessageParameters\": [ { \"Number\": \""+cela(phone)+"\", \"Text\": \""+msg+"\" } ], \"ApiKey\": \"o1EmlvRYVqAUY80avGC/LxhsrXUNj4+AHAc9ai1M6Yw=\", \"ClientId\": \"6cd0be5e-6753-4db4-9603-eceac7fdaaba\" }";
//,\"IsUnicode\": true, \"IsFlash\": false,
//System.out.println(headers);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setConnectTimeout(10000);
            con.setUseCaches(false);

            OutputStream out=con.getOutputStream();
            out.write(headers.getBytes());

            out.close();


            InputStream in=con.getInputStream();

            byte[] me=in.readAllBytes();
            balance=new String(me);
            System.out.println(balance);



            in.close();


//url.close();

        }catch(Exception hhgghd)
        {
            balance=hhgghd+" ";
        }

        if(balance.contains("Success"))
            sn=true;
//System.out.println(balance);


        return sn;

    }


    public static String Sender()
    {
        //return "NJIMBI_KOFI";
        return "Wyzer";
    }


    public static String cela(String phone)
    {
        String fin=phone;
        if((fin.charAt(0)+"").equals("0"))
        {
            fin="254y"+fin;
            fin=fin.replaceAll("y0","");
            fin=fin.replaceAll("y","");
        }
        else
        {
            fin="254"+fin;
        }

        return fin;
    }

    public String details()
    {
        String ms="To Recharge, Lipa na Mpesa : Paybill 156211 account no:ngupatgi";
        return ms;
    }

}
