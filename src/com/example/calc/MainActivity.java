package com.example.calc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.send.R;


// �Ӽ��˳� ȡ�� ����
public class MainActivity extends Activity implements OnClickListener {
	private static final String ADD="��";
	private static final String SUB="��";
	private static final String MULTIPLY="��";
	private static final String DIV="��";
	private static final String MODULE="%";
	private static final String DIAN=".";
/**
 * .��%������=
 */
	private Button btnEqual = null;// ����
	private Button btnBackSpace = null;// �˸�ť
	private Button btnClear = null;// ���
	private Button btnN0 = null;
	private Button btnN1 = null;
	private Button btnN2 = null;
	private Button btnN3 = null;
	private Button btnN4 = null;
	private Button btnN5 = null;
	private Button btnN6 = null;
	private Button btnN7 = null;
	private Button btnN8 = null;
	private Button btnN9 = null;
	private Button btnNadd = null;// ��
	private Button btnNsub = null;// ��
	private Button btnNmult = null;// ��
	private Button btnNdiv = null;// ��
	private Button btnNy = null;// ȡ����
	private Button btnNd = null;// .
	private EditText editCalcResult = null;// �����
	private BigDecimal decimal = new BigDecimal(0);
	private String current = "0";// ��ǰ������
	private Button btnNMinus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editCalcResult = (EditText) findViewById(R.id.editCalcReuslt);// �����ʾ��
		btnEqual = (Button) findViewById(R.id.btnEqual);// ����
		btnBackSpace = (Button) findViewById(R.id.btnbackSpace);// �˸������
		btnClear = (Button) findViewById(R.id.btnClear);// ���
		btnN0 = (Button) findViewById(R.id.btnN0);
		btnN1 = (Button) findViewById(R.id.btnN1);
		btnN2 = (Button) findViewById(R.id.btnN2);
		btnN3 = (Button) findViewById(R.id.btnN3);
		btnN4 = (Button) findViewById(R.id.btnN4);
		btnN5 = (Button) findViewById(R.id.btnN5);
		btnN6 = (Button) findViewById(R.id.btnN6);
		btnN7 = (Button) findViewById(R.id.btnN7);
		btnN8 = (Button) findViewById(R.id.btnN8);
		btnN9 = (Button) findViewById(R.id.btnN9);
		btnNMinus= (Button) findViewById(R.id.btnNMinus);//����-
		btnNadd = (Button) findViewById(R.id.btnAdd);// ��
		btnNsub = (Button) findViewById(R.id.btnSub);// ��
		btnNmult = (Button) findViewById(R.id.btnMult);// ��
		btnNdiv = (Button) findViewById(R.id.btnDiv);// ��
		btnNy = (Button) findViewById(R.id.btnNy);// ȡ��
		btnNd = (Button) findViewById(R.id.btnNd);// ��

		editCalcResult.setOnClickListener(this);// �����
		btnEqual.setOnClickListener(this);// ����
		btnBackSpace.setOnClickListener(this);// �˸�
		btnClear.setOnClickListener(this);// ���
		btnN0.setOnClickListener(this);
		btnN1.setOnClickListener(this);
		btnN2.setOnClickListener(this);
		btnN3.setOnClickListener(this);
		btnN4.setOnClickListener(this);
		btnN5.setOnClickListener(this);
		btnN6.setOnClickListener(this);
		btnN7.setOnClickListener(this);
		btnN8.setOnClickListener(this);
		btnN9.setOnClickListener(this);
		btnNMinus.setOnClickListener(this);//������
		btnNadd.setOnClickListener(this);
		btnNsub.setOnClickListener(this);
		btnNmult.setOnClickListener(this);
		btnNdiv.setOnClickListener(this);
		btnNy.setOnClickListener(this);
		btnNd.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * ������ֵ
	 * @param value
	 */
	public void addValue(String value) {
		System.out.println("��ֵ:" + value);
		String calcInput=editCalcResult.getText().toString();
		Log.i("��ʾ","���������ڵ�ֵ��:"+calcInput);
		if(value.matches("\\.|��|%|��|��|��|=")){
//		if ("��".equals(value) || "��".equals(value) || "��".equals(value)
//				|| "��".equals(value) || "��".equals(value) || "��".equals(value)
//				|| "=".equals(value)) {
			  System.out.println("�����ֵƥ��Ӽ��˳����������");
			if (editCalcResult.getText().length() == 0) {
				Toast.makeText(MainActivity.this, "���벻�Ϸ�", 0).show();
			}else
			{
				//[\d*[\+-]*\d+\.\d*]*$ ��С����ֻ����һ�ε����� ������������ֻ��Ҫһ��\�Ϳ���ת��
				if(".".equals(value))
				{
				//.*\\d*\\.[\\d|��|%|��|��|��]*$
					//".*\\d*\\.[\\d\\��\\��\\��\\��]*$"
					  if((calcInput).matches(".*\\d*\\.\\d*$|.*\\-$"))// ��� �� ������ �� ������ȫ���Ǽ�.* ���Ӽ��˳����滻
					//�����Ǿ������ʾ -  Ҳ���Ǹ���-���治�������С����
					  {
						  System.out.println("���벻�Ϸ�Ŷ");
						  Toast.makeText(MainActivity.this, "��,��Ҫ�ٴ�����С������Ŷ", 0).show();
						  return;
					  }
					  else
							System.out.println("��������.�Ϸ�"+value+"==="+calcInput);
				}
			  if(calcInput.matches(".*[\\.%��������=]$"))	//��������ԭ������Щ���ţ�
				  //��ô�����滻���� \\.��ת�壬���򱨴�, ���������-������\\-����ôҲ�������
			  {
				 if(calcInput.endsWith("."))
				 {
					  Toast.makeText(MainActivity.this, "��,С����������������Ŷ", 0).show();
					 return;
				 }
				 if("=".equals(value))
				 {	
					 Toast.makeText(MainActivity.this, "��������������ֵ��������Ŷ", 0).show();
					 return;
					 }
				  System.out.println("��������һλ���������,ִ���滻���ò���");
				  editCalcResult.setText(calcInput.substring(0, calcInput.length()-1)+value);

			  
			  }else
			  { 
				  System.out.println("��������һλ�����������,����ֱ��ֵ�ۼӵ�ǰ����������"+value);
				  editCalcResult.setText(calcInput+value); 
			
			  }
				 if("=".equals(value))
				  {
					  calcResult();
				  }
			  //���������ǵ��� ��ô...
			}
		}
		else
		{
			  if("-".equals(value))
			  {
				  
				  if(calcInput.matches(".*[\\d+\\.\\-]$"))//���ǰ����1����������  �� �� һ��С���㣬����   ����һ����������
					  //��Ӽ��˳�����Ų��ò���
				  {
					  Toast.makeText(MainActivity.this, "�������Ų�����������Ŷ", 0).show();
					  return; 
				  }
			  }
			editCalcResult.setText(calcInput+value);
		System.out.println("���������������ֱ���ۼ�!");
		}
		
		editCalcResult.setSelection(editCalcResult.getText().length());
		
	}
		// .��%������=
	/**
	 * ������ʽ�������ֵ�ӵ�list�����
	 */
	private void calcResult() {
		
				String temp=editCalcResult.getText().toString();
				temp=temp.substring(0, temp.length()-1);//���ں��Լ�����
/*		
  				//������������ȡ������Լ���������
 			String [] result=temp.split("[.%��������]");
				for (int i = 0; i < result.length; i++) {
					System.out.println("��ǰֵ:"+result[i]);
				}*/
				
				String regex="";//���������Ƿ�Ϊ������ĸ  + :1-��
				regex="\\-?\\d+(\\.\\d+)?|[��������()\\+\\-\\%*/]|\\-";
//				regex=".*[%��������].*";
//				regex="\\-?\\d+(\\.\\d+)?|[*/()]|\\-";
//				regex="/d[.%��������]/d/g";
				ArrayList<String> arrayList=new ArrayList<String>();
				Pattern p=Pattern.compile(regex);//ģʽ���
				Matcher m=p.matcher(temp);//ƥ��������
				System.out.println("�Ƿ�ƥ��:"+m.matches());
				String result="";
				while(m.find())
				{
				//dfdfdfd[��][fdfdfd][֤]����ȷ����������[��] ������ҵ���fd�����ȫ�� ������
				arrayList.add(m.group());//temp;//�ҵ�֮�������[]�ַ���֪����ôȥ�� 
				System.out.println("���ҵ�"+arrayList.get(arrayList.size()-1));
				}
				doListValue(arrayList);
				editCalcResult.setText(current);
				current="0";
				

				
		
	}
/**
 * ���� list���	
 * @param arrayList
 */
	public void doListValue(List<String> arrayList)
	{
		Log.i("��ʾ", "�����й���"+arrayList.size()+"��ֵ");
		try{
			//current  Ϊ��������
			BigDecimal preDecimal=null;
			BigDecimal nextDecimal=null;
			BigDecimal cResultDecimal=null;//��ǰ ǰһλ�ͺ�һλ ������  +ǰһ��������
			if(arrayList.size()==0)//�����ܵ���1����Ϊ ���򲻻�ͨ��
				{
				
				current=editCalcResult.getText().toString();
				current=current.substring(0, current.length()-1);//ȥ��=
				Log.i("��ʾ", "editlist:"+current);
				current=new BigDecimal(current).round(MathContext.DECIMAL32).toString();
				System.out.println("�༭���е�ֵ ת��ΪС�������:"+current);
				editCalcResult.setText(current);
				return;
				}
			for(int i=0;i<arrayList.size();i++)
			{
				
				String temp_for=arrayList.get(i);//�����ҵ������
				if(temp_for.equals(MainActivity.DIV))
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					System.out.println("ǰһλ"+pre+" ��һλ"+next);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.divide(nextDecimal);
				//	cResultDecimal=cResultDecimal.add(new BigDecimal(current));//��һ��������
					System.out.println(pre+"��"+next+"������Ȼ���"+current+"�����:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					//arrayList.remove(i-1);
					i--;
				}
				else if(temp_for.equals(MainActivity.MULTIPLY))//��
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.multiply(nextDecimal);
				//	cResultDecimal=cResultDecimal.add(new BigDecimal(current));//��һ��������
					System.out.println(pre+"��"+next+"������Ȼ���"+current+"�����:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					i--;
				}	else if(temp_for.equals(MainActivity.MODULE))//ȡģ
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.remainder(nextDecimal);
				//	cResultDecimal=cResultDecimal.add(new BigDecimal(current));//��һ��������
					System.out.println(pre+"��"+next+"������Ȼ���"+current+"�����:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					i--;
				}
			}
			
			//��Ӽ� --
			
			for(int i=0;i<arrayList.size();i++)
			{
				
				String temp_for=arrayList.get(i);//�����ҵ������
				if(temp_for.equals(MainActivity.ADD))//��
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.add(nextDecimal);
					System.out.println(pre+"��"+next+"������Ȼ���"+current+"�����:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					i--;
				}
				else if(temp_for.equals(MainActivity.SUB))//��
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.subtract(nextDecimal);
					System.out.println(pre+"��"+next+"������Ȼ���"+current+"�����:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					i--;
				}
			}
			
		}catch(Exception e)
		{
			Toast.makeText(MainActivity.this, "�����쳣"+e.toString(), 1).show();
		}
	}
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnClear:
			editCalcResult.setText("");
			break;// ���������
		case R.id.btnbackSpace:
			String num = editCalcResult.getText().toString();
			editCalcResult.setText(num.length() == 0 ? "" : num.substring(0,
					num.length() - 1));
			editCalcResult.setSelection(editCalcResult.getText().length());
			break;// �˸����
			default:Button button=new Button(MainActivity.this);
			button.setId(v.getId());
				addValue(button.getText().toString());//��������ȡ��������ֵ��
			break;
			
			
		case R.id.btnEqual:
			addValue(btnEqual.getText().toString());
			break;// ���ڽ���
		case R.id.btnN0:
			addValue(btnN0.getText().toString());
			System.out.println("0��ť����");
			break;
		case R.id.btnN1:
			addValue(btnN1.getText().toString());
			break;
		case R.id.btnN2:
			addValue(btnN2.getText().toString());
			break;
		case R.id.btnN3:
			addValue(btnN3.getText().toString());
			break;
		case R.id.btnN4:
			addValue(btnN4.getText().toString());
			break;
		case R.id.btnN5:
			addValue(btnN5.getText().toString());
			break;
		case R.id.btnN6:
			addValue(btnN6.getText().toString());
			break;
		case R.id.btnN7:
			addValue(btnN7.getText().toString());
			break;
		case R.id.btnN8:
			addValue(btnN8.getText().toString());
			break;
		case R.id.btnN9:
			addValue(btnN9.getText().toString());
			break;
		case R.id.btnNMinus:
			addValue(btnNMinus.getText().toString());
			break;
		case R.id.btnAdd:
			addValue(btnNadd.getText().toString());
			break;
		case R.id.btnSub:
			addValue(btnNsub.getText().toString());
			break;
		case R.id.btnMult:
			addValue(btnNmult.getText().toString());
			break;
		case R.id.btnDiv:
			addValue(btnNdiv.getText().toString());
			break;
		case R.id.btnNy:
			addValue(btnNy.getText().toString());
			break;
		case R.id.btnNd:
			addValue(btnNd.getText().toString());// ��
			break;
		}
	}

}
