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
	
	//�Զ��ػ�
	//Runtime.getRuntime().exec("shutdown -a");
	//URL�࣬ʹ�ò��裺 
	//1. �ȴ���һ����ַ����URL�Ķ���u1 
	String str="ͩ������";
	str=URLEncoder.encode(str,"UTF-8");
	URL u1 = new URL("https://api.jisuapi.com/iqa/query? appkey=62958a3a6ef3c56d&question="+str); 	
	//2. ͨ������url���������磬���õ����ӹ���conn
	URLConnection conn = u1.openConnection(); 
	//3. ��ȡ�����ڼ�����Դ�� ������ 
	InputStream is = conn.getInputStream(); 
	//4. ��������is��װ��Ϊ�ܶ�ȡ���� �� ����һ�ζ�ȡ һ�е� �����ַ���ȡ�� 
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8")); 
	//5. ��ȡһ����Դ 
	String text = br.readLine(); 
	//6. ��ʾ��Դ 
   System.out.println(text); 
	//7. �ͷ���Դ
   br.close();
   }
}
