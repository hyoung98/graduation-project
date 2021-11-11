package com.example.myapplication;

public class listview_item2 {
    //알레르기 리스트뷰 구현 공간
    String prdlstNm;//식품이름
    String allergy;//알레르기
    String rawmtrl;//재료



    public void setprdlstNm(String KOR) { prdlstNm=KOR; }
    public void setallergy(String WT) {
        allergy=WT;
    }
    public void setrawmtrl(String CONT1){
        rawmtrl=CONT1;
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

}
