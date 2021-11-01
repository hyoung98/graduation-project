package com.example.myapplication;

public class listview_item2 {
    String prdlstNm;
    String allergy;
    String rawmtrl;
    String nutrient;


    public void setprdlstNm(String KOR) { prdlstNm=KOR; }
    public void setallergy(String WT) {
        allergy=WT;
    }
    public void setrawmtrl(String CONT1){
        rawmtrl=CONT1;
    }
    public void setnutrient(String CONT2){
        nutrient=CONT2;
    }



    public String getprdlstNm() {
        return this.prdlstNm;
    }
    public String getallergy() {
        return this.allergy;
    }
    public String getrawmtrl() {
        return this.rawmtrl;
    }
    public String getnutrient() {
        return this.nutrient;
    }

}
