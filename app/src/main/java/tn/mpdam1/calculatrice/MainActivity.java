package tn.mpdam1.calculatrice;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView upperRes,result;
    Button n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,b_div,b_plus,b_mult,b_moin,b_pt,b_del,b_res,b_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignId(n0,R.id.btn_0);
        assignId(n1,R.id.btn_1);
        assignId(n2,R.id.btn_2);
        assignId(n3,R.id.btn_3);
        assignId(n4,R.id.btn_4);
        assignId(n5,R.id.btn_5);
        assignId(n6,R.id.btn_6);
        assignId(n7,R.id.btn_7);
        assignId(n8,R.id.btn_8);
        assignId(n9,R.id.btn_9);
        assignId(b_div,R.id.btn_div);
        assignId(b_moin,R.id.btn_moin);
        assignId(b_mult,R.id.btn_multiple);
        assignId(b_plus,R.id.btn_plus);
        assignId(b_pt,R.id.btn_point);
        assignId(b_res,R.id.btn_result);
        assignId(b_clear,R.id.clear);
        assignId(b_del,R.id.delete);

        upperRes = findViewById(R.id.upper_result);
        result = findViewById(R.id.result);

    }

    void assignId(Button btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Button button =(Button) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = result.getText().toString();
        if (buttonText.equals("C")){
            upperRes.setText("");
            result.setText("0");
        }
        else if (buttonText.equals("⌫")){
            if (dataToCalculate.length()==1){
                result.setText("0");
            } else
            result.setText(dataToCalculate.substring(0, dataToCalculate.length() - 1));
        }
        else if (buttonText.equals("=")){
            upperRes.setText(dataToCalculate);
            result.setText(getResult(dataToCalculate));
        }
        else if (dataToCalculate.equals("0")){
            result.setText(buttonText);
        }else{
            result.setText(dataToCalculate+buttonText);
        }


    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}