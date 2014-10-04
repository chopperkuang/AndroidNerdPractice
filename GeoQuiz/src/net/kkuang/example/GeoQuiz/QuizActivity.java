package net.kkuang.example.GeoQuiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends Activity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mBackButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private List<TrueFalse> mQuestionBank = new ArrayList<TrueFalse>(){{
        add(new TrueFalse(R.string.question_oceans, true));
        add(new TrueFalse(R.string.question_mideast, false));
        add(new TrueFalse(R.string.question_africa, false));
        add(new TrueFalse(R.string.question_americas, true));
        add(new TrueFalse(R.string.question_china, true));
    }};

    private int mCurrentIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNextQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNextQuestion();
            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toBackQuestion();
            }
        });

        updateQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

    private void updateQuestion() {
        mQuestionTextView.setText(mQuestionBank.get(mCurrentIndex).getQuestion());
    }

    private void toNextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size();
        updateQuestion();
    }

    private void toBackQuestion() {
        if(mCurrentIndex == 0) {
            Toast.makeText(this, R.string.back_over, Toast.LENGTH_SHORT).show();
        }
        mCurrentIndex = mCurrentIndex <= 0 ? 0 :  (mCurrentIndex - 1) % mQuestionBank.size();
        updateQuestion();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank.get(mCurrentIndex).isTrueQuestion();

        int messageResId = 0;
        if(userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
