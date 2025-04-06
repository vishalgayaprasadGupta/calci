package com.example.calci

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var view:TextView
    lateinit var clear:Button
    lateinit var equal:Button
    var first=""
    var second=""
    var operator=""
    var isSecond=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        view=findViewById(R.id.view)
        val number= listOf(
            R.id.one,R.id.two,R.id.three,R.id.four,R.id.five,R.id.six,R.id.seven,R.id.eight,R.id.nine,R.id.zero
        )

        for(id in number){
            findViewById<Button>(id).setOnClickListener({
                val value=(it as Button).text.toString()
                if(!isSecond){
                    first+=value
                    view.text=first
                }else{
                    second+=value
                    view.text="$first $operator $second"
                }
            })
        }

        val operators=listOf(
            R.id.add,R.id.subtract,R.id.multiply,R.id.divide
        )
        for(id in operators){
            findViewById<Button>(id).setOnClickListener({
                operator=(it as Button).text.toString()
                if(!first.isEmpty()){
                    isSecond=true
                    view.text="$first $operator"
                }
            })
        }

        clear=findViewById(R.id.clear)
        clear.setOnClickListener({
            first=""
            second=""
            operator=""
            isSecond=false
            view.text="0"
        })

        equal=findViewById(R.id.equal)
        equal.setOnClickListener({
            if(!first.isEmpty()&& !second.isEmpty()&& !operator.isEmpty()) {
                var output = calculate(first, second, operator)
                view.text = output.toString()
                first = output.toString()
                second = ""
                operator = ""
                isSecond = false
            }else{
                view.text="Invalid Input"
            }
        })
    }

    private fun calculate(first:String,second:String,operator:String):Double{
        var num1=first.toDouble()
        var num2=second.toDouble()
        var result:Double=when(operator){
            "+"->num1+num2
            "-"->num1-num2
            "*"->num1*num2
            "/"->num1/num2
            else->0.0
        }
        return result
    }
}