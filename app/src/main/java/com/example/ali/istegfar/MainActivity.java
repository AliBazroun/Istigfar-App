package com.example.ali.istegfar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

public class MainActivity extends AppCompatActivity {
    int count=100;          // the displayed number
    int i=0;                // the iteration number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // To display exit confirmation pop up window
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("إغلاق البرنامج")
                .setMessage("هل أنت متأكد من إغلاق البرنامج ؟؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("الغاء", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        TextView tvM = (TextView) findViewById(R.id.msg);
        TextView tvC = (TextView) findViewById(R.id.count);

        //noinspection SimplifiableIfStatement
        if (id == R.id.op1) {
            tvM.setText("سبحان الله");
            //count=100;
            reset(tvC);
            return true;
        }
        if (id == R.id.op2) {
            tvM.setText("الحمد لله");
            reset(tvC);
            return true;
        }
        if (id == R.id.op3) {
            tvM.setText("لا إله إلا الله");
            reset(tvC);
            return true;
        }
        if (id == R.id.op4) {
            tvM.setText("أستغفر الله ربي و أتوب إليه");
            reset(tvC);
            return true;
        }
        if (id == R.id.op5) {
            dialog();           // Add Istigfar pop up window
            return true;
        }
        if (id == R.id.op6) {
            numberDialog();     // edit the COUNT number
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void count(View view) {
        TextView tvC = (TextView) findViewById(R.id.count);

        if(i < count) {
            i++;
            tvC.setText("" + i);
        }
        if (i >= count) {

            tvC.setText("أتممت: " +i);

            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 400 milliseconds
            v.vibrate(400);
        }
    }

    public void reset(View view) {
        i=0;
        TextView tvC = (TextView) findViewById(R.id.count);
        tvC.setText("0");
    }


    public void dialog() {
        final EditText txt = new EditText(this);
        final TextView tvM = (TextView) findViewById(R.id.msg);
        txt.setSingleLine();
        txt.setHint("مثال: الله أكبر");

        new AlertDialog.Builder(this)
                .setTitle("اضافة استغفار")
                .setMessage("ادخل استغفار هنا..")
                .setView(txt)
                .setIcon(android.R.drawable.ic_menu_edit)
                .setPositiveButton("تغيير", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String newMsg = txt.getText().toString();

                        if (newMsg.length() < 70 && newMsg.length() > 1) {
                            reset(txt);

                            tvM.setText(newMsg.toString());
                            Toast.makeText(MainActivity.this,
                                    "تم اضافة النص بنجاح.", Toast.LENGTH_LONG).show();
                        } else if (newMsg.length() <= 1) {
                            Toast.makeText(MainActivity.this,
                                    "لم يتم ادخال الحد الأدنى للنص (2) !!", Toast.LENGTH_LONG).show();
                            }
                        else {
                                reset(txt);
                                tvM.setText(newMsg.toString().substring(0, 70));

                                Toast.makeText(MainActivity.this,
                                        "تم اقتصاص النص لطوله !!", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    })
                    .show();
    }

    public int numberDialog() {
        final EditText editNum = new EditText(this);
        final TextView tvC = (TextView) findViewById(R.id.count);

        editNum.setInputType(InputType.TYPE_CLASS_NUMBER);
        editNum.setSingleLine();
        editNum.setHint("مثال: 34");

        new AlertDialog.Builder(this)
                .setTitle("ضبط عدد الاسغفارات")
                .setMessage("العدد:  ")
                .setView(editNum)
                .setIcon(android.R.drawable.ic_popup_reminder)
                .setPositiveButton("موافق", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        if(editNum.length() > 0) {
                            String newNum = editNum.getText().toString();

                            int x = Integer.parseInt(newNum);
                            if(x > 10000) {
                                Toast.makeText(MainActivity.this,
                                        "عذرا.. العدد المدخل أكبر من 10000 !!", Toast.LENGTH_LONG).show();
                            }
                            else if(x > 9 && x <= 10000){
                                count = Integer.parseInt(newNum);         // number to be displayed
                                Toast.makeText(MainActivity.this,
                                        "تم تغيير العدد.", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this,
                                        "عذرا.. العدد المدخل أصغر من 10 !!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this,
                                    "لم يتم ادخال العدد !!", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();


        return count;
    }

}
