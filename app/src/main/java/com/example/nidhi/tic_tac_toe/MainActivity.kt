package com.example.nidhi.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var board: Array<Array<Button>>
    var Player = true
    var turn_count = 0
    var BoardStatus = Array(3) { IntArray(3) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        reset.setOnClickListener {
            initializeBoardStatus()
            turn_count = 0
            Player = true
            turn.setText("Player X's Turn")
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                BoardStatus[i][j] = -1

            }
        }

        for (i in board){
            for (button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {
             updateValue (row =0 , column = 0 , player = Player)
            }
            R.id.button2 -> {
                updateValue (row =0 , column = 1 , player = Player)
            }
            R.id.button3 -> {
                updateValue (row =0 , column = 2, player = Player)
            }
            R.id.button4 -> {
                updateValue (row =1 , column = 0 , player = Player)
            }
            R.id.button5 -> {
                updateValue (row =1 , column = 1 , player = Player)
            }
            R.id.button6 -> {
                updateValue (row =1 , column = 2 , player = Player)
            }
            R.id.button7 -> {
                updateValue (row =2 , column = 0 , player = Player)
            }
            R.id.button8 -> {
                updateValue (row =2 , column = 1 , player = Player)
            }
            R.id.button9 -> {
                updateValue (row =2 , column = 2 , player = Player)
            }

        }

        turn_count++
        Player = !Player

        if(Player){
            updateDisplay("Player X's Turn")
        }
        else{
            updateDisplay("Player 0's Turn")
        }

        if(turn_count == 9){
            updateDisplay("Game Draw")
        }

        checkWinner()
    }
    public fun checkWinner(){
        //Horizontal
       for (i in 0..2){
           if (BoardStatus[i][0] == BoardStatus[i][1] && BoardStatus[i][0] == BoardStatus[i][2]){
               if (BoardStatus[i][0] == 1){
                   updateDisplay("Player X Wins!")
                   break
               }
               else if (BoardStatus[i][0] == 0){
                   updateDisplay("Player 0 wins!")
                   break
               }
           }
       }

        //Vertical Columns
        for (i in 0..2) {
            if (BoardStatus[0][i] == BoardStatus[1][i] && BoardStatus[0][i] == BoardStatus[2][i]) {
                if (BoardStatus[0][i] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (BoardStatus[0][i] == 0) {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }

        //First Diagonal
        if(BoardStatus[0][0] == BoardStatus[1][1] && BoardStatus[0][0] == BoardStatus[2][2]){
            if (BoardStatus[0][0] == 1) {
                updateDisplay("Player X Wins!")
            } else if (BoardStatus[0][0] == 0) {
                updateDisplay("Player O Wins!")
            }
        }

        //Second Diagonal
        if(BoardStatus[0][2] == BoardStatus[1][1] && BoardStatus[0][2] == BoardStatus[2][0]){
            if (BoardStatus[0][2] == 1) {
                updateDisplay("Player X Wins!")
            } else if (BoardStatus[0][2] == 0) {
                updateDisplay("Player O Wins!")
            }
        }


    }
    private fun updateDisplay(text: String) {
        turn.text = text
        if (text.contains("wins")){
            DisableButtons()
        }
    }

    private fun DisableButtons(){

        for (i in board) {
            for (button in i) {
                button.isEnabled = false
            }
        }
    }


    private fun updateValue(row: Int, column: Int, player: Boolean) {
        val text = if(player) "X" else "0"
        val value = if(player) 1 else 0
    board[row][column].apply {
        isEnabled = false
        setText(text)
    }
        BoardStatus[row][column] = value
    }
}