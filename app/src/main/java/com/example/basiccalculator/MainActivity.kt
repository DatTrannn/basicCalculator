package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var display: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.value)
        display.showSoftInputOnFocus = false
    }

    private fun updateText(strToAdd: String) {
        val oldStr = display.text.toString()
        val cursorPos = display.selectionStart
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)
        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
        display.setSelection(cursorPos + 1)
    }

    fun zeroBTN(view: View?) {
        updateText("0")
    }

    fun oneBTN(view: View?) {
        updateText("1")
    }

    fun twoBTN(view: View?) {
        updateText("2")
    }

    fun threeBTN(view: View?) {
        updateText("3")
    }

    fun fourBTN(view: View?) {
        updateText("4")
    }

    fun fiveBTN(view: View?) {
        updateText("5")
    }

    fun sixBTN(view: View?) {
        updateText("6")
    }

    fun sevenBTN(view: View?) {
        updateText("7")
    }

    fun eightBTN(view: View?) {
        updateText("8")
    }

    fun nineBTN(view: View?) {
        updateText("9")
    }

    fun addBTN(view: View?) {
        updateText("+")
    }

    fun subtractBTN(view: View?) {
        updateText("-")
    }

    fun multiplyBTN(view: View?) {
        updateText("×")
    }

    fun clearBTN(view: View?) {
        display.setText("")
    }

    fun parenthesisBTN(view: View?) {
        val cursorPos = display.selectionStart
        var openPar = 0
        var closedPar = 0
        val textLen = display.text.length
        for (i in 0 until cursorPos) {
            if (display.text.toString()[i] == '(') {
                openPar += 1
            }
            if (display.text.toString()[i] == ')') {
                closedPar += 1
            }
        }
        if (openPar == closedPar || "+-(÷×^".indexOf(display.text.toString()[textLen - 1]) != -1) {
            updateText("(")
        } else if (closedPar < openPar && display.text.toString()[textLen - 1] != '(') {
            updateText(")")
        }
        display.setSelection(cursorPos + 1)
    }

    fun exponentBTN(view: View?) {
        updateText("^")
    }

    fun divideBTN(view: View?) {
        updateText("÷")
    }

    fun pointBTN(view: View?) {
        updateText(".")
    }

    fun backspaceBTN(view: View?) {
        val cursorPos = display.selectionStart
        val textLength = display.text.length
        if (cursorPos != 0 && textLength != 0) {
            val selection = display.text as SpannableStringBuilder
            selection.delete(cursorPos - 1, cursorPos)
        }
    }

    fun equalsBTN(view: View?) {
        var userExp = display.text.toString()
        userExp = userExp.replace("÷".toRegex(), "/")
        userExp = userExp.replace("×".toRegex(), "*")
        val exp = Expression(userExp)
        val result = exp.calculate().toString()
        display.setText(result)
        display.setSelection(result.length)
    }

    fun plusMinusBTN(view: View?) {
        updateText("-")
    }
}