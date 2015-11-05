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


// 加键乘除 取余 等于
public class MainActivity extends Activity implements OnClickListener {
	private static final String ADD="＋";
	private static final String SUB="－";
	private static final String MULTIPLY="×";
	private static final String DIV="÷";
	private static final String MODULE="%";
	private static final String DIAN=".";
/**
 * .＋%－×÷=
 */
	private Button btnEqual = null;// 等于
	private Button btnBackSpace = null;// 退格按钮
	private Button btnClear = null;// 清空
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
	private Button btnNadd = null;// 加
	private Button btnNsub = null;// 减
	private Button btnNmult = null;// 乘
	private Button btnNdiv = null;// 除
	private Button btnNy = null;// 取余数
	private Button btnNd = null;// .
	private EditText editCalcResult = null;// 结果框
	private BigDecimal decimal = new BigDecimal(0);
	private String current = "0";// 当前运算结果
	private Button btnNMinus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editCalcResult = (EditText) findViewById(R.id.editCalcReuslt);// 结果显示框
		btnEqual = (Button) findViewById(R.id.btnEqual);// 等于
		btnBackSpace = (Button) findViewById(R.id.btnbackSpace);// 退格键号码
		btnClear = (Button) findViewById(R.id.btnClear);// 清空
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
		btnNMinus= (Button) findViewById(R.id.btnNMinus);//负数-
		btnNadd = (Button) findViewById(R.id.btnAdd);// 加
		btnNsub = (Button) findViewById(R.id.btnSub);// 减
		btnNmult = (Button) findViewById(R.id.btnMult);// 乘
		btnNdiv = (Button) findViewById(R.id.btnDiv);// 除
		btnNy = (Button) findViewById(R.id.btnNy);// 取余
		btnNd = (Button) findViewById(R.id.btnNd);// 点

		editCalcResult.setOnClickListener(this);// 结果框
		btnEqual.setOnClickListener(this);// 等于
		btnBackSpace.setOnClickListener(this);// 退格
		btnClear.setOnClickListener(this);// 清除
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
		btnNMinus.setOnClickListener(this);//负数减
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
	 * 结果框加值
	 * @param value
	 */
	public void addValue(String value) {
		System.out.println("加值:" + value);
		String calcInput=editCalcResult.getText().toString();
		Log.i("提示","计算器现在的值是:"+calcInput);
		if(value.matches("\\.|＋|%|－|×|÷|=")){
//		if ("＋".equals(value) || "＋".equals(value) || "＋".equals(value)
//				|| "－".equals(value) || "×".equals(value) || "÷".equals(value)
//				|| "=".equals(value)) {
			  System.out.println("输入的值匹配加减乘除等运算符号");
			if (editCalcResult.getText().length() == 0) {
				Toast.makeText(MainActivity.this, "输入不合法", 0).show();
			}else
			{
				//[\d*[\+-]*\d+\.\d*]*$ 让小数点只出现一次的正则 在易语言里面只需要一个\就可以转义
				if(".".equals(value))
				{
				//.*\\d*\\.[\\d|＋|%|－|×|÷]*$
					//".*\\d*\\.[\\d\\＋\\－\\×\\÷]*$"
					  if((calcInput).matches(".*\\d*\\.\\d*$|.*\\-$"))// 如果 是 有数字 且 由于是全局是加.* 而加减乘除是替换
					//后面那句正则表示 -  也就是负数-后面不允许插入小数点
					  {
						  System.out.println("输入不合法哦");
						  Toast.makeText(MainActivity.this, "亲,不要再次输入小数点了哦", 0).show();
						  return;
					  }
					  else
							System.out.println("您输入了.合法"+value+"==="+calcInput);
				}
			  if(calcInput.matches(".*[\\.%＋－×÷=]$"))	//如果结果框原来是这些符号，
				  //那么进行替换操作 \\.是转义，否则报错, 如果后面是-【减】\\-号那么也不许插入
			  {
				 if(calcInput.endsWith("."))
				 {
					  Toast.makeText(MainActivity.this, "亲,小数点后不能输入运算符哦", 0).show();
					 return;
				 }
				 if("=".equals(value))
				 {	
					 Toast.makeText(MainActivity.this, "运算符后面必须有值才能运算哦", 0).show();
					 return;
					 }
				  System.out.println("结果框最后一位数是运算符,执行替换设置操作");
				  editCalcResult.setText(calcInput.substring(0, calcInput.length()-1)+value);

			  
			  }else
			  { 
				  System.out.println("结果框最后一位数不是运算符,而是直接值累加当前输入的运算符"+value);
				  editCalcResult.setText(calcInput+value); 
			
			  }
				 if("=".equals(value))
				  {
					  calcResult();
				  }
			  //如果输入的是等于 那么...
			}
		}
		else
		{
			  if("-".equals(value))
			  {
				  
				  if(calcInput.matches(".*[\\d+\\.\\-]$"))//如果前面是1个或多个数字  或 是 一个小数点，或者   又是一个负数符号
					  //或加减乘除点符号不让插入
				  {
					  Toast.makeText(MainActivity.this, "负数符号不能这样插入哦", 0).show();
					  return; 
				  }
			  }
			editCalcResult.setText(calcInput+value);
		System.out.println("输入的是正常数字直接累加!");
		}
		
		editCalcResult.setSelection(editCalcResult.getText().length());
		
	}
		// .＋%－×÷=
	/**
	 * 运算表达式框里面的值加到list结合中
	 */
	private void calcResult() {
		
				String temp=editCalcResult.getText().toString();
				temp=temp.substring(0, temp.length()-1);//等于号自己不算
/*		
  				//经过测试这样取运算符自己被忽略了
 			String [] result=temp.split("[.%＋－×÷]");
				for (int i = 0; i < result.length; i++) {
					System.out.println("当前值:"+result[i]);
				}*/
				
				String regex="";//所有输入是否为数字字母  + :1-多
				regex="\\-?\\d+(\\.\\d+)?|[＋－×÷()\\+\\-\\%*/]|\\-";
//				regex=".*[%＋－×÷].*";
//				regex="\\-?\\d+(\\.\\d+)?|[*/()]|\\-";
//				regex="/d[.%＋－×÷]/d/g";
				ArrayList<String> arrayList=new ArrayList<String>();
				Pattern p=Pattern.compile(regex);//模式逛街
				Matcher m=p.matcher(temp);//匹配器构建
				System.out.println("是否匹配:"+m.matches());
				String result="";
				while(m.find())
				{
				//dfdfdfd[罗][fdfdfd][证]大幅度发到发大幅度[正] 会出现找到了fd后面的全部 ，蛋疼
				arrayList.add(m.group());//temp;//找到之后包含了[]字符不知道怎么去掉 
				System.out.println("已找到"+arrayList.get(arrayList.size()-1));
				}
				doListValue(arrayList);
				editCalcResult.setText(current);
				current="0";
				

				
		
	}
/**
 * 处理 list结果	
 * @param arrayList
 */
	public void doListValue(List<String> arrayList)
	{
		Log.i("提示", "集合中共有"+arrayList.size()+"个值");
		try{
			//current  为已运算结果
			BigDecimal preDecimal=null;
			BigDecimal nextDecimal=null;
			BigDecimal cResultDecimal=null;//当前 前一位和后一位 运算结果  +前一次运算结果
			if(arrayList.size()==0)//不可能等于1，因为 正则不会通过
				{
				
				current=editCalcResult.getText().toString();
				current=current.substring(0, current.length()-1);//去掉=
				Log.i("提示", "editlist:"+current);
				current=new BigDecimal(current).round(MathContext.DECIMAL32).toString();
				System.out.println("编辑框中的值 转换为小数点后是:"+current);
				editCalcResult.setText(current);
				return;
				}
			for(int i=0;i<arrayList.size();i++)
			{
				
				String temp_for=arrayList.get(i);//必须找到运算符
				if(temp_for.equals(MainActivity.DIV))
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					System.out.println("前一位"+pre+" 后一位"+next);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.divide(nextDecimal);
				//	cResultDecimal=cResultDecimal.add(new BigDecimal(current));//加一次运算结果
					System.out.println(pre+"和"+next+"除运算然后加"+current+"结果是:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					//arrayList.remove(i-1);
					i--;
				}
				else if(temp_for.equals(MainActivity.MULTIPLY))//乘
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.multiply(nextDecimal);
				//	cResultDecimal=cResultDecimal.add(new BigDecimal(current));//加一次运算结果
					System.out.println(pre+"和"+next+"乘运算然后加"+current+"结果是:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					i--;
				}	else if(temp_for.equals(MainActivity.MODULE))//取模
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.remainder(nextDecimal);
				//	cResultDecimal=cResultDecimal.add(new BigDecimal(current));//加一次运算结果
					System.out.println(pre+"和"+next+"乘运算然后加"+current+"结果是:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					i--;
				}
			}
			
			//后加减 --
			
			for(int i=0;i<arrayList.size();i++)
			{
				
				String temp_for=arrayList.get(i);//必须找到运算符
				if(temp_for.equals(MainActivity.ADD))//加
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.add(nextDecimal);
					System.out.println(pre+"和"+next+"加运算然后加"+current+"结果是:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					i--;
				}
				else if(temp_for.equals(MainActivity.SUB))//减
				{
					String pre=arrayList.get(i-1);
					String next=arrayList.get(i+1);
					preDecimal=new BigDecimal(pre);
					nextDecimal=new BigDecimal(next);
					cResultDecimal=preDecimal.subtract(nextDecimal);
					System.out.println(pre+"和"+next+"减运算然后加"+current+"结果是:"+cResultDecimal.toString());
					current=cResultDecimal.toString();
					arrayList.remove(i+1);
					arrayList.remove(i);
					arrayList.set(i-1, current);
					i--;
				}
			}
			
		}catch(Exception e)
		{
			Toast.makeText(MainActivity.this, "算术异常"+e.toString(), 1).show();
		}
	}
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnClear:
			editCalcResult.setText("");
			break;// 清除键结束
		case R.id.btnbackSpace:
			String num = editCalcResult.getText().toString();
			editCalcResult.setText(num.length() == 0 ? "" : num.substring(0,
					num.length() - 1));
			editCalcResult.setSelection(editCalcResult.getText().length());
			break;// 退格结束
			default:Button button=new Button(MainActivity.this);
			button.setId(v.getId());
				addValue(button.getText().toString());//这样还是取不到他的值啊
			break;
			
			
		case R.id.btnEqual:
			addValue(btnEqual.getText().toString());
			break;// 等于结束
		case R.id.btnN0:
			addValue(btnN0.getText().toString());
			System.out.println("0按钮触发");
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
			addValue(btnNd.getText().toString());// 点
			break;
		}
	}

}
