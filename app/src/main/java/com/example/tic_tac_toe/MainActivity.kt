package com.example.tic_tac_toe

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3){IntArray(3)}
    private lateinit var  textView : TextView
    lateinit var board : Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val reset = findViewById<Button>(R.id.reset_button)
        val button11 = findViewById<Button>(R.id.button11)
        val button12 = findViewById<Button>(R.id.button12)
        val button13 = findViewById<Button>(R.id.button13)
        val button21 = findViewById<Button>(R.id.button21)
        val button22 = findViewById<Button>(R.id.button22)
        val button23 = findViewById<Button>(R.id.button23)
        val button31 = findViewById<Button>(R.id.button31)
        val button32 = findViewById<Button>(R.id.button32)
        val button33 = findViewById<Button>(R.id.button33)
        textView = findViewById<TextView>(R.id.winner_name)
        reset.setBackgroundColor(Color.TRANSPARENT)
        window.setNavigationBarColor(Color.WHITE)
        button11.setBackgroundColor(Color.TRANSPARENT)
        button21.setBackgroundColor(Color.TRANSPARENT)
        button31.setBackgroundColor(Color.TRANSPARENT)
        button12.setBackgroundColor(Color.TRANSPARENT)
        button22.setBackgroundColor(Color.TRANSPARENT)
        button23.setBackgroundColor(Color.TRANSPARENT)
        button13.setBackgroundColor(Color.TRANSPARENT)
        button32.setBackgroundColor(Color.TRANSPARENT)
        button33.setBackgroundColor(Color.TRANSPARENT)

        board = arrayOf(arrayOf(button11, button12, button13), arrayOf(button21, button22, button23), arrayOf(button31, button32, button33)        )

        for (i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        reset.setOnClickListener{
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
            }
        }

        for(i in board){
            for(button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View?){
        when (view!!.id){
            R.id.button11 -> {updateValue(row =0, col = 0, player = PLAYER)}
            R.id.button12 -> {updateValue(row =0, col = 1, player = PLAYER)}
            R.id.button13 -> {updateValue(row =0, col = 2, player = PLAYER)}
            R.id.button21 -> {updateValue(row =1, col = 0, player = PLAYER)}
            R.id.button22 -> {updateValue(row =1, col = 1, player = PLAYER)}
            R.id.button23 -> {updateValue(row =1, col = 2, player = PLAYER)}
            R.id.button31 -> {updateValue(row =2, col = 0, player = PLAYER)}
            R.id.button32 -> {updateValue(row =2, col = 1, player = PLAYER)}
            R.id.button33 -> {updateValue(row =2, col = 2, player = PLAYER)}
        }
        TURN_COUNT++
        PLAYER = !PLAYER

        if(PLAYER){updateDisplay("Player X Turn")}
        else{updateDisplay("Player 0 Turn")}
        if(TURN_COUNT == 9){updateDisplay("Game Draw")}

        checkWinner()

    }

    private fun checkWinner() {
        //Horizontal Rows
        for (i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if (boardStatus[i][0] == 1){
                    updateDisplay("Winner : X")
                    break
                }
                else if(boardStatus[i][0] == 0){
                    updateDisplay("Winner : 0")
                    break
                }
            }
        }

        //Vertical Columns
        for (i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if (boardStatus[0][i] == 1){
                    updateDisplay("Winner : X")
                    break
                }
                else if(boardStatus[0][i] == 0){
                    updateDisplay("Winner : 0")
                    break
                }
            }
        }

        // First Diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if (boardStatus[0][0] == 1){
                updateDisplay("Winner : X")
            }
            else if(boardStatus[0][0] == 0){
                updateDisplay("Winner : 0")
            }
        }

        // Second Diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if (boardStatus[0][2] == 1){
                updateDisplay("Winner : X")
            }
            else if(boardStatus[0][2] == 0){
                updateDisplay("Winner : 0")
            }
        }
    }

    private fun updateDisplay(s: String) {
        textView.text = s

        if(s.contains("Winner")){
            disableButton()
        }

    }

    private fun disableButton(){
        for(i in board){
            for(button in  i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text : String = if(player) "X" else "0"
        val value : Int = if(player) 1 else 0


        board[row][col].apply {
            isEnabled=false
            setText(text)
        }

        boardStatus[row][col] = value
    }
}