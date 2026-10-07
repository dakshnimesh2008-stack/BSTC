package com.bstc.app;
public class Question { public int id,answer; public String subject,topic,q,source; public String[] options; public boolean verified; Question(int i,String s,String t,String q0,String[] o,int a,String src,boolean v){id=i;subject=s;topic=t;q=q0;options=o;answer=a;source=src;verified=v;} }
