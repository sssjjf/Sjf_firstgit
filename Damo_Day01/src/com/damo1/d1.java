package com.damo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class d1  {
	public static void main(String[] args) throws IOException {
	
	//自动关机
	//Runtime.getRuntime().exec("shutdown -a");
	//URL类，使用步骤： 
	//1. 先创建一个网址工具URL的对象，u1 
	String str="桐梓天气";
	str=URLEncoder.encode(str,"UTF-8");
	URL u1 = new URL("https://api.jisuapi.com/iqa/query? appkey=62958a3a6ef3c56d&question="+str); 	
	//2. 通过工具url，连接网络，并得到连接工具conn
	URLConnection conn = u1.openConnection(); 
	//3. 获取到用于加载资源的 输入流 
	InputStream is = conn.getInputStream(); 
	//4. 将输入流is，装饰为能读取文字 且 可以一次读取 一行的 缓存字符读取流 
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8")); 
	//5. 读取一行资源 
	String text = br.readLine(); 
	//6. 显示资源 
   System.out.println(text); 
	//7. 释放资源
   br.close();
   }
}
