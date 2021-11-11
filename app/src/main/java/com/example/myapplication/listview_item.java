package com.example.myapplication;

public class listview_item {
    //리스트뷰를 만들기 위해서 사용되는 공간 / 왜 이렇게 구현 해야하는 이유는 잘모름...
     String DESC_KOR;
     String SERVING_WT;
     String NUTR_CONT1;
     String NUTR_CONT2;
     String NUTR_CONT3;
     String NUTR_CONT4;
     String NUTR_CONT5;
     String NUTR_CONT6;

 public void setDESC_KOR(String KOR) { DESC_KOR=KOR; }
    public void setSERVING_WT(String WT) {
        SERVING_WT=WT;
    }
    public void setNUTR_CONT1(String CONT1){
        NUTR_CONT1=CONT1;
    }
    public void setNUTR_CONT2(String CONT2){
        NUTR_CONT2=CONT2;
    }
    public void setNUTR_CONT3(String CONT3){
        NUTR_CONT3=CONT3;
    }
    public void setNUTR_CONT4(String CONT4){
        NUTR_CONT4=CONT4;
    }
    public void setNUTR_CONT5(String CONT5){
        NUTR_CONT5=CONT5;
    }
    public void setNUTR_CONT6(String CONT6){
        NUTR_CONT6=CONT6;
    }


    public String getDESC_KOR() {
        return this.DESC_KOR;
    }
    public String getSERVING_WT() {
        return this.SERVING_WT;
    }
    public String getNUTR_CONT1() {
        return this.NUTR_CONT1;
    }
    public String getNUTR_CONT2() {
        return this.NUTR_CONT2;
    }
    public String getNUTR_CONT3() {
        return this.NUTR_CONT3;
    }
    public String getNUTR_CONT4() {
        return this.NUTR_CONT4;
    }
    public String getNUTR_CONT5() {
        return this.NUTR_CONT5;
    }
    public String getNUTR_CONT6() {
        return this.NUTR_CONT6;
    }
}
