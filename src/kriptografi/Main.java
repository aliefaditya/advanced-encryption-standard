/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kriptografi;

/**
 *
 * @author alief
 */
public class Main {
    public static void main(String[] args) throws Exception{       
        //data yang mau dienkripsi
        String data = "aliefadi";
    
        /***
          konstanta untuk kunci 
          panjang kunci dibawah ada 64 x 4 = 256
        ***/
        final String secretKey = "6D669C6EC30FB2E5BB9D41A966837F4328ED49ECDE4ECAA42D5D97DF614CC3C0";    
        
        //proses
        String enkrip = AES.encrypt(data, secretKey);
        String dekrip = AES.decrypt(enkrip, secretKey);
        
        //output
        System.out.println("data asli : " + data);
        System.out.println("data yang sudah dienkripsi : " + enkrip);
        System.out.println("data yang sudah didekripsi : " + dekrip);
    }
}
