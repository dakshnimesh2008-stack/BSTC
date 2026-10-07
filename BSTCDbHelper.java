package com.bstc.app;

import android.content.*;
import android.database.sqlite.*;
import java.util.*;

public class BSTCDbHelper extends SQLiteOpenHelper {
    static final String DB="bstc.db";
    BSTCDbHelper(Context c){super(c,DB,null,1);}
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE results(id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, score INTEGER, total INTEGER, correct INTEGER, wrong INTEGER, unattempted INTEGER, ts INTEGER)");
        db.execSQL("CREATE TABLE questions(id INTEGER PRIMARY KEY, subject TEXT, topic TEXT, q TEXT, a TEXT, b TEXT, c TEXT, d TEXT, answer INTEGER, source TEXT, verified INTEGER)");
        seed(db);
    }
    public void onUpgrade(SQLiteDatabase db,int oldV,int newV){db.execSQL("DROP TABLE IF EXISTS results");db.execSQL("DROP TABLE IF EXISTS questions");onCreate(db);}
    private void seed(SQLiteDatabase db){
        insert(db,1,"Mental Ability","Number Series","325, 271, 226, ?, 163, 145, 136 में ? के स्थान पर कौनसी संख्या आएगी?","196","190","180","186",1,"2025 2nd shift.pdf Q6",0);
        insert(db,2,"Mental Ability","Analogy","कप : तश्तरी :: चाकू : ?","रसोईघर","काँटा","सब्जी","थाली",1,"2025 2nd shift.pdf Q7",0);
        insert(db,3,"Mental Ability","Time-Speed-Distance","150 कि.मी./घंटे की गति से 900 कि.मी. दूरी तय करने में कितना समय लगेगा?","7 घंटे","6 घंटे","5 घंटे","4 घंटे",1,"2025 2nd shift.pdf Q5",0);
        insert(db,4,"Mental Ability","Analogy","लड़की : सुन्दर :: सोना : ?","बहुमूल्य","चमकीला","आभूषण","अंगूठी",0,"2025 2nd shift.pdf Q9",0);
        insert(db,5,"Mental Ability","Number Series","369 पृष्ठों वाली पुस्तक में पृष्ठ संख्या लिखने के लिए कितने अंक चाहिए?","1005","999","1002","990",2,"2023.pdf Q20",0);
        insert(db,6,"English","Vocabulary","‘SAME’ meaning (synonym) to HISTORIC is:","Hyper sensitive","Overdramatic","Historically important","Interactive",2,"2022.pdf Q69",0);
        insert(db,7,"English","Articles","Choose the correct article: He is ___ honest man.","a","an","the","no article",1,"2022.pdf English section",0);
        insert(db,8,"Rajasthan GK","History","राजस्थान की सामान्य जानकारी के पुराने प्रश्नों को अभ्यास के लिए किस स्रोत से लिया गया है?","2020 paper","2021 paper","2024 paper","सभी उपलब्ध papers",3,"Source inventory",1);
        insert(db,9,"Teaching Aptitude","Teaching Learning","शिक्षण-अधिगम विषय किस आधिकारिक syllabus section में दिया गया है?","मानसिक योग्यता","शिक्षण अभिक्षमता","अंग्रेजी","राजस्थान GK",1,"Syllabus.pdf",1);
        insert(db,10,"Mental Ability","Exam Pattern","आधिकारिक syllabus के अनुसार कुल प्रश्न कितने हैं?","150","180","200","250",2,"Syllabus.pdf",1);
    }
    private void insert(SQLiteDatabase db,int id,String s,String t,String q,String a,String b,String c,String d,int ans,String src,int ver){
        ContentValues v=new ContentValues();v.put("id",id);v.put("subject",s);v.put("topic",t);v.put("q",q);v.put("a",a);v.put("b",b);v.put("c",c);v.put("d",d);v.put("answer",ans);v.put("source",src);v.put("verified",ver);db.insert("questions",null,v);
    }
    public ArrayList<Question> questions(String subject){
        ArrayList<Question> out=new ArrayList<>();SQLiteDatabase db=getReadableDatabase();Cursor c;
        if(subject==null||subject.equals("All Subjects")) c=db.rawQuery("SELECT * FROM questions",null); else c=db.rawQuery("SELECT * FROM questions WHERE subject=?",new String[]{subject});
        while(c.moveToNext()) out.add(new Question(c.getInt(c.getColumnIndexOrThrow("id")),c.getString(c.getColumnIndexOrThrow("subject")),c.getString(c.getColumnIndexOrThrow("topic")),c.getString(c.getColumnIndexOrThrow("q")),new String[]{c.getString(c.getColumnIndexOrThrow("a")),c.getString(c.getColumnIndexOrThrow("b")),c.getString(c.getColumnIndexOrThrow("c")),c.getString(c.getColumnIndexOrThrow("d"))},c.getInt(c.getColumnIndexOrThrow("answer")),c.getString(c.getColumnIndexOrThrow("source")),c.getInt(c.getColumnIndexOrThrow("verified"))==1));
        c.close();return out;
    }
    public void addResult(String type,int score,int total,int correct,int wrong,int unattempted){SQLiteDatabase db=getWritableDatabase();ContentValues v=new ContentValues();v.put("type",type);v.put("score",score);v.put("total",total);v.put("correct",correct);v.put("wrong",wrong);v.put("unattempted",unattempted);v.put("ts",System.currentTimeMillis());db.insert("results",null,v);}
    public Cursor results(){return getReadableDatabase().rawQuery("SELECT * FROM results ORDER BY ts DESC",null);}
    public void clearResults(){getWritableDatabase().delete("results",null,null);}
    public void clearAll(){getWritableDatabase().delete("results",null,null);}
}
