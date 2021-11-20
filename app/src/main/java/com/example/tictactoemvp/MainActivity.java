package com.example.tictactoemvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BoardView {

    BoardPresenter presenter;
    TableLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new BoardPresenter(this);
        board = findViewById(R.id.board);

        for (byte row = 0; row < 3; row++) {
            TableRow tableRow = (TableRow) board.getChildAt(row);
            for (byte col = 0; col < 3; col++) {
                Button button = (Button) tableRow.getChildAt(col);
                BoardPresenter.CellClickListener buttonListener = new
                        BoardPresenter.CellClickListener(presenter, row, col);
                button.setOnClickListener(buttonListener);
                presenter.addCellClickListener(buttonListener);
            }

        }
    }
        @Override
        public void newGame() {
            TableLayout boardView = findViewById(R.id.board);
            for (int row = 0; row < 3; row++) {
                TableRow tableRow = (TableRow) boardView.getChildAt(row);
                for (int col = 0; col < 3; col++) {
                    Button button = (Button) tableRow.getChildAt(col);
                    button.setText("");
                    button.setEnabled(true);
                }
            }
        }

        @Override
        public void putSymbol ( char symbol, byte row, byte col){
            TableRow tableRow = (TableRow) board.getChildAt(row);
            Button btn = (Button) tableRow.getChildAt(col);
            btn.setText(Character.toString(symbol));
        }

        @Override
        public void gameEnded ( byte winner){
            for (byte row = 0; row < 3; row++) {
                TableRow tableRow = (TableRow) board.getChildAt(row);
                for (byte col = 0; col < 3; col++) {
                    Button btn = (Button) tableRow.getChildAt(col);
                    btn.setText("");
                    btn.setEnabled(false);
                }
            }
            String msg = null;
            if (winner != 0)
                msg = "Player" + winner + "won the game";
            else
                msg = "It is Draw";
            Toast.makeText(this, "Player" + winner + "won the game",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void invalidPlay ( byte row, byte col){
            Toast.makeText(this, "Inavlid Move", Toast.LENGTH_SHORT).show();
        }
    }
