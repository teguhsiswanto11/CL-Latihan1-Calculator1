package com.teguhsiswanto.id.calculator_1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val btnAbout = findViewById<Button>(R.id.btnAbout)
        val context = this

        btnAboutExplicit.setOnClickListener{
            val intent = Intent(context, About::class.java)
            startActivity(intent)
        }

        btnAboutImplicit.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Kalkulator Sederhana \nDeveloped by: Siswanto Teguh \n\n" +
                    "See on github by clicking link below \n https://github.com/teguhsiswanto11/CL-Latihan1-Calculator1")
            intent.type = "text/plain"
            startActivity(intent)
        }


    }

    //    Numbers
    fun btn1Clicked(v: View) {
        sisipEkspresi("1", true)
    }

    fun btn2Clicked(v: View) {
        sisipEkspresi("2", true)
    }

    fun btn3Clicked(v: View) {
        sisipEkspresi("3", true)
    }

    fun btn4Clicked(v: View) {
        sisipEkspresi("4", true)
    }

    fun btn5Clicked(v: View) {
        sisipEkspresi("5", true)
    }

    fun btn6Clicked(v: View) {
        sisipEkspresi("6", true)
    }

    fun btn7Clicked(v: View) {
        sisipEkspresi("7", true)
    }

    fun btn8Clicked(v: View) {
        sisipEkspresi("8", true)
    }

    fun btn9Clicked(v: View) {
        sisipEkspresi("9", true)
    }

    fun btn0Clicked(v: View) {
        sisipEkspresi("0", true)
    }

    fun btnDotClicked(v: View) {
        sisipEkspresi(".", true)
    }

    fun btnOpenPClicked(v: View) {
        sisipEkspresi("(", true)
    }

    fun btnClosePClicked(v: View) {
        sisipEkspresi(")", true)
    }


    //    Operators
    fun btnPlusClicked(v: View) {
        sisipEkspresi("+", false)
    }

    fun btnMinusClicked(v: View) {
        sisipEkspresi("-", false)
    }

    fun btnMultiplicationClicked(v: View) {
        sisipEkspresi("*", false)
    }

    fun btnDivideClicked(v: View) {
        sisipEkspresi("/", false)
    }


//  Delete and Clear
    fun btnClearClicked(v: View) {
        textLayer.text = ""
        textLayer2.text = ""
    }

    fun btnDelClicked(v: View) {
        var str = textLayer.text.toString()
        if (str.isNotEmpty()) {
            textLayer.text = str.substring(0, str.length - 1)
        }
        textLayer2.text = ""
    }

//    mencegah duplikat point (.) pada ekspresi
    fun isMultiPoint(str:String):Boolean {
//        var str = textLayer.text.toString()
        var temp:Boolean = str.contains(".", ignoreCase = true)
        if (temp == true) {
            return temp
        }
        return temp
    }

//    Cek digit ke terakhir apakah merupakan Operator
    fun isOperatorLastDigit(lastDigit:String):Boolean {
        var x:String = lastDigit
        var operator:Boolean = false
        if (x == "x" || x == "÷" || x == "+" || x == "-") {
            operator = true
        }
        return operator
    }

//    Memasukkan ekspresi kel textLayer
    fun sisipEkspresi(str: String, operand: Boolean) {
        var lengTextLayer = textLayer.text.length
        var maxDigit:Byte = 28
        var temp = textLayer.text.toString()
        var bantu = str.replace("*","x").replace("/","÷")

        if (textLayer2.text.isNotEmpty()) {
            textLayer.text = ""
        }

        if (operand) {
            if (lengTextLayer < maxDigit) {
                textLayer2.text = ""
                textLayer.append(str)
            } else {
                Toast.makeText(this@MainActivity, "Maximum number of digit (28)", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (isOperatorLastDigit(temp.takeLast(1))) {
                textLayer.text = textLayer.text.substring(0, lengTextLayer-1)
            } else {
                textLayer.append(textLayer2.text) // untuk menggabungkan nilai yg sudah ada di textLayer2 (hasil operasi sebelumnya)
                textLayer2.text = ""
            }
                textLayer.append(bantu)
        }

    }

//    jika sama dengan (=) di klik
    fun btnEqualClicked(v: View) {
        try {
            var bantu = textLayer.text.toString().replace("x","*").replace("÷","/")

            val ekspresi = ExpressionBuilder(bantu).build()
            val result = ekspresi.evaluate() // result hasilnya selalu ada koma (.0123) dibelakangnya
            val longResult = result.toLong() // long result tidak ada .0 nya / kecuali di beri .toDouble

//            jika 7.2 == 7.0
            if (result == longResult.toDouble()) {
                textLayer2.text = longResult.toString() //tampilkan yg TIDAK ada koma
            } else {
                textLayer2.text = result.toString() //tampilkan yg ADA koma
            }

        } catch (ex:Exception) {
            Log.d("Exception", "message : " + ex.message )
        }
    }


}